/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.system;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import spacetrader.Player;
import spacetrader.SkillList.Skill;
import spacetrader.commerce.TradeGood;

/**
 * FXML Controller class
 *
 * @author Caleb
 */
public class StartScreenController extends SceneController implements Initializable {
 
    @FXML private Label playerName;
    @FXML private Label playerMoney;
    @FXML private Label playerPoliceRecord;
    @FXML private Label playerRep;
    @FXML private Label playerTotalKills;
    @FXML private Label playerPilot;
    @FXML private Label playerTrader;
    @FXML private Label playerFighter;
    @FXML private Label playerEngineer;
    @FXML private Label playerInvestor;
    
    @FXML private Label cargoSlots;
    @FXML private GridPane inventory;
    
    @FXML private Label shipType;
    @FXML private Label shipFuel;
    @FXML private Label shipHull;
    @FXML private Label shipWeaponSlots;
    @FXML private Label shipShieldSlots;
    @FXML private Label shipGadgetSlots;
    
    
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
     * @param player the player who's stats should be displayed
     * @param myStage the window which should display the start screen
     */
    public void setUpPlayerStats(Player player, Stage myStage) {
        this.startStage = myStage;
        
        playerName.setText(player.getName());
        playerMoney.setText("₪" + player.getWallet().getCredits());
        playerRep.setText(player.getReputation().toString());
        playerPoliceRecord.setText(player.getPoliceRecord().toString());
        playerTotalKills.setText("" + (player.getTraderKills() + player.getPoliceKills() + player.getPirateKills()));
        playerPilot.setText("" + player.getSkill(Skill.PILOT));
        playerFighter.setText("" + player.getSkill(Skill.FIGHTER));
        playerTrader.setText("" + player.getSkill(Skill.TRADER));
        playerEngineer.setText("" + player.getSkill(Skill.ENGINEER));
        playerInvestor.setText("" + player.getSkill(Skill.INVESTOR));
        
        shipType.setText("" + player.getShip().getType().toString());
        shipFuel.setText(player.getShip().getTank().getFuelAmount() + "/" + player.getShip().getTank().getMaxFuel());
        shipHull.setText(player.getShip().getHullStrength() + "/" + player.getShip().getMaxHullStrength());
        shipWeaponSlots.setText(player.getShip().getWeapons().getNumFilledSlots() + "/" + player.getShip().getWeapons().getNumSlots());
        shipShieldSlots.setText(player.getShip().getShields().getNumFilledSlots() + "/" + player.getShip().getShields().getNumSlots());
        shipGadgetSlots.setText(player.getShip().getGadgets().getNumFilledSlots() + "/" + player.getShip().getGadgets().getNumSlots());
        
        
        cargoSlots.setText(player.getShip().getCargo().getCount() + "/" + player.getShip().getCargo().getMaxCapacity());
        
        List<TradeGood> tradeGoodList = player.getShip().getCargo().getTradeGoods();
        for (int i = 0; i < tradeGoodList.size(); i++) {
            TradeGood good = tradeGoodList.get(i);
            int quantity = player.getShip().getCargo().getQuantity(good);
            if (quantity > 0) {
                inventory.addRow(3 + i, new Label(tradeGoodList.get(i).type()), new Label("" + quantity));
            }
        }
    }
    
    @FXML protected void closeWindow(ActionEvent event) {
        startStage.close();
    }
    
}
