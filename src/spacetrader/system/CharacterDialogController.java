/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.system;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spacetrader.Player;

/**
 * FXML Controller class
 *
 * @author Caleb Stokols
 */
public class CharacterDialogController implements Initializable {

   //These instance variables will eventually go in a Player Class.
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private int investorSkill;
    private MainController mainControl;
    
    @FXML private TextField nameText;
    @FXML private Text pilotSkillText;
    @FXML private Text fighterSkillText;
    @FXML private Text traderSkillText;
    @FXML private Text engineerSkillText;
    @FXML private Text investorSkillText;
    @FXML private Text skillPointsRemaining;
    @FXML private Text confirmMessage;
    @FXML private ChoiceBox difficultyChoiceBox;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        difficultyChoiceBox.setItems(
            FXCollections.observableArrayList("Easy", "Medium", "Hard", "Ultra"));
        difficultyChoiceBox.setValue("Medium");
        confirmMessage.setFill(Color.TRANSPARENT);
    }

    /**
     * Gives this controller a reference to the MainController.
     * @param mainControl the Main Controller of SpaceTrader
     */
    public void setMainControl(MainController mainControl) {
        this.mainControl = mainControl;
    }
    
    /**
     * Determines if there are still points left to allocate.
     * 
     * @return true if there are still skill points left to allocate.
     */
    private boolean skillPointsRemaining() {
        return (pilotSkill + fighterSkill + traderSkill
                    + engineerSkill + investorSkill) < 15;
    }
    
    /**
     * Updates the "points remaining" text in the View.
     */
    private void adjustPointsRemainingText() {
        int points = 15 - (pilotSkill + fighterSkill + traderSkill
                    + engineerSkill + investorSkill);
        skillPointsRemaining.setText("" + points);
    }
    
    /**
     * Increase the pilot skill count when + is clicked.
     * 
     * @param event 
     */
    @FXML protected void increasePilot(ActionEvent event) {
        if(skillPointsRemaining()) {
            pilotSkill++;
            pilotSkillText.setText("" + pilotSkill);
        }
        adjustPointsRemainingText();
    }
    
    /**
     * Decrease the pilot skill count when - is clicked.
     * 
     * @param event 
     */
    @FXML protected void decreasePilot(ActionEvent event) {
        if (pilotSkill > 0) {
            pilotSkill--;
            pilotSkillText.setText("" + pilotSkill);
        }
        adjustPointsRemainingText();
    }
    
    /**
     * Increase the fighter skill count when + is clicked.
     * 
     * @param event 
     */
    @FXML protected void increaseFighter(ActionEvent event) {
        if(skillPointsRemaining()) {
            fighterSkill++;
            fighterSkillText.setText("" + fighterSkill);
        }
        adjustPointsRemainingText();
    }
    
    /**
     * Decrease the fighter skill count when - is clicked.
     * 
     * @param event 
     */
    @FXML protected void decreaseFighter(ActionEvent event) {
        if (fighterSkill > 0) {
            fighterSkill--;
            fighterSkillText.setText("" + fighterSkill);
        }
        adjustPointsRemainingText();
    }
    
    /**
     * Increase the trader skill count when + is clicked.
     * 
     * @param event 
     */
    @FXML protected void increaseTrader(ActionEvent event) {
        if(skillPointsRemaining()) {
            traderSkill++;
            traderSkillText.setText("" + traderSkill);
        }
        adjustPointsRemainingText();
    }
    
    /**
     * Decrease the trader skill count when - is clicked.
     * 
     * @param event 
     */
    @FXML protected void decreaseTrader(ActionEvent event) {
        if (traderSkill > 0) {
            traderSkill--;
            traderSkillText.setText("" + traderSkill);
        }
        adjustPointsRemainingText();
    }
    
    /**
     * Increase the engineer skill count when + is clicked.
     * 
     * @param event 
     */
    @FXML protected void increaseEngineer(ActionEvent event) {
        if(skillPointsRemaining()) {
            engineerSkill++;
            engineerSkillText.setText("" + engineerSkill);
        }
        adjustPointsRemainingText();
    }
    
    /**
     * Decrease the engineer skill count when - is clicked.
     * 
     * @param event 
     */
    @FXML protected void decreaseEngineer(ActionEvent event) {
        if (engineerSkill > 0) {
            engineerSkill--;
            engineerSkillText.setText("" + engineerSkill);
        }
        adjustPointsRemainingText();
    }
    
    /**
     * Increase the investor skill count when + is clicked.
     * 
     * @param event 
     */
    @FXML protected void increaseInvestor(ActionEvent event) {
        if(skillPointsRemaining()) {
            investorSkill++;
            investorSkillText.setText("" + investorSkill);
        }
        adjustPointsRemainingText();
    }
    
    /**
     * Decrease the investor skill count when - is clicked.
     * 
     * @param event 
     */
    @FXML protected void decreaseInvestor(ActionEvent event) {
        if (investorSkill > 0) {
            investorSkill--;
            investorSkillText.setText("" + investorSkill);
        }
        adjustPointsRemainingText();
    }

    @FXML protected void cancelDialogScreen(ActionEvent event) {
        mainControl.goToWelcomeScreen();
    }
    
    @FXML protected void okDialogScreen(ActionEvent event) {
        String name = nameText.getCharacters().toString();
        if (name.equals("")) {
            confirmMessage.setText("Please enter your name!");
            confirmMessage.setFill(Color.RED);
        } else if(skillPointsRemaining()) {
            confirmMessage.setText("Please allocate all skill points!");
            confirmMessage.setFill(Color.RED);
        } else {
            Player player = new Player(name, pilotSkill, fighterSkill,
                                    traderSkill, engineerSkill, investorSkill);
            mainControl.setUpGame(player);
            
            confirmMessage.setText("Greetings Space Trader!");
            confirmMessage.setFill(Color.GREEN);
        } 
    }
    
}