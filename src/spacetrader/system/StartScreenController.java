/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.system;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import spacetrader.Player;
import spacetrader.SkillList.Skill;
import spacetrader.commerce.TradeGood;
import spacetrader.ships.PlayerShip;
import spacetrader.ships.ShipType;

/**
 * FXML Controller class
 *
 * @author Caleb
 */
public class StartScreenController extends SceneController implements Initializable {

    @FXML
    private Label playerName, playerMoney, playerDebt, playerPoliceRecord,
            playerRep, playerTotalKills, playerPilot, playerTrader,
            playerFighter, playerEngineer, playerInvestor;

    @FXML
    private Label insuranceMessage, insuranceDailyCost, insuranceNoClaim;
    @FXML
    private HBox insuranceInfoBox;

    @FXML
    private Label cargoSlots;
    @FXML
    private GridPane inventory;

    @FXML
    private Label shipType, shipFuel, shipHull,
            shipWeaponSlots, shipShieldSlots, shipGadgetSlots;
    @FXML
    private ProgressBar fuelBar, hullBar;
    
    @FXML
    private Button retireButton;

    private Player player;
    private Stage startStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Display all the player's current information onto the Start Screen.
     *
     * @param player the player who's stats should be displayed
     * @param myStage the window which should display the start screen
     */
    public void setUpPlayerStats(Player player, Stage myStage) {
        this.startStage = myStage;
        this.player = player;
        PlayerShip ship = player.getShip();

        playerName.setText(player.getName());
        playerMoney.setText("₪" + player.getCredits());
        playerDebt.setText("₪" + player.getDebt());
        playerRep.setText(player.getReputation().toString());
        playerPoliceRecord.setText(player.getPoliceRecord().toString());
        playerTotalKills.setText(String.valueOf(player.getTraderKills() + player.getPoliceKills() + player.getPirateKills()));
        playerPilot.setText(String.format("%d [%d]", player.getSkill(Skill.PILOT), player.getEffectiveSkill(Skill.PILOT)));
        playerFighter.setText(String.format("%d [%d]", player.getSkill(Skill.FIGHTER), player.getEffectiveSkill(Skill.FIGHTER)));
        playerTrader.setText(String.format("%d [%d]", player.getSkill(Skill.TRADER), player.getEffectiveSkill(Skill.TRADER)));
        playerEngineer.setText(String.format("%d [%d]", player.getSkill(Skill.ENGINEER), player.getEffectiveSkill(Skill.ENGINEER)));
        playerInvestor.setText(String.format("%d [%d]", player.getSkill(Skill.INVESTOR), player.getEffectiveSkill(Skill.INVESTOR)));

        if (player.getInsuranceCost() > 0) {
            insuranceMessage.setText("You have insurance.");
            insuranceDailyCost.setText("âª" + player.getInsuranceCost());
            insuranceNoClaim.setText(String.valueOf(player.getNoClaimDays()));
        } else {
            insuranceInfoBox.setVisible(false);
        }

        shipType.setText(ship.getType().toString());
        shipFuel.setText(ship.getFuelAmount() + "/" + ship.getMaxFuel());
        fuelBar.setProgress((100 * ship.getFuelAmount() / ship.getMaxFuel()));
        shipHull.setText((100 * ship.getHullStrength() / ship.getMaxHullStrength()) + "%");
        hullBar.setProgress((double) ship.getHullStrength() / ship.getMaxHullStrength());
        shipWeaponSlots.setText(ship.getWeapons().getNumFilledSlots() + "/" + ship.getType().weaponSlots());
        shipShieldSlots.setText(ship.getShields().getNumFilledSlots() + "/" + ship.getType().shieldSlots());
        shipGadgetSlots.setText(ship.getGadgets().getNumFilledSlots() + "/" + ship.getType().gadgetSlots());

        cargoSlots.setText(ship.getCargo().getCount() + "/" + ship.getCargo().getMaxCapacity());

        List<TradeGood> tradeGoodList = ship.getCargo().getTradeGoods();
        for (int i = 0; i < tradeGoodList.size(); i++) {
            TradeGood good = tradeGoodList.get(i);
            int quantity = ship.getCargo().getQuantity(good);
            if (quantity > 0) {
                inventory.addRow(3 + i, new Label(tradeGoodList.get(i).toString()), new Label(String.valueOf(quantity)));
            }
        }
        
        InformationPresenter.getInstance().showTextOnHover(retireButton,
                "If you retire, the game ends. You sell your ship and "
                + "everything on it, and remain in the current system for the "
                + "rest of your days. If applicable, your total worth will be "
                + "entered in the high score table.");
    }

    @FXML
    protected void retirePressed() {
        boolean confirm = mainControl.displayYesNoConfirmation("Retirement", 
                "Are you sure you want to retire?", 
                String.format("If you retire, you will sell"
                + " your ship and everything on it and then live out the rest "
                + "of your days on the current system %d and possible earn "
                + "yourself a place on the highscore chart. This action CANNOT"
                + "be undone.", player.getLocation()));
        if (confirm) {
            
        }
            
    }
    
    @FXML
    protected void closeWindow(ActionEvent event) {
        startStage.close();
    }

    @FXML
    protected void enableCheats(ActionEvent event) {
        List<String> choices = new ArrayList<>();
        choices.add("Increase Fuel");
        choices.add("Get Money!");
        //choices.addCredits("Show Police Encounter");
        //choices.addCredits("Show Pirate Encounter");
        //choices.addCredits("Show Trader Encounter");
        choices.add("Show Ship Info");

        Optional<String> response = Dialogs.create()
                .owner(startStage)
                .title("Cheats!")
                .masthead("If you're not a member of the Wombo Combo, get out of here jerk!")
                .message("Choose your cheat:")
                .showChoices(choices);

        // One way to get the response value.
        if (response.isPresent()) {
            switch (response.get()) {
                case "Increase Fuel":
                    //player.getShip().increaseMaxFuel(50);
                    player.getShip().addFuel(50);
                    break;
                case "Get Money!":
                    player.addCredits(10000);
                    break;
                /*case "Show Police Encounter":
                 mainControl.goToEncounterScreen(new PoliceEncounter(player, universe.getPlanet("Pallet").getPoliticalSystem().getStrengthOfTraders()));
                 break;
                 case "Show Pirate Encounter":
                 mainControl.goToEncounterScreen(new PirateEncounter(player));
                 break;
                 case "Show Trader Encounter":
                 mainControl.goToEncounterScreen(new TraderEncounter(player));
                 break;*/
                case "Show Ship Info":
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/spacetrader/ships/ShipInfoPane.fxml"));
                    try {
                        Pane pane = loader.load();
                        stage.setScene(new Scene(pane));
                        ((ShipInfoPaneController) loader.getController()).setShipType(ShipType.FLEA);
                    } catch (IOException e) {
                        Logger.getLogger(SpaceTrader.class.getName()).log(Level.SEVERE, null, e);
                    }
                    stage.show();
                    break;
            }
        }
    }
}
