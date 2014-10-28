/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import spacetrader.ships.ShipType;

/**
 * FXML Controller class
 *
 * @author Seth
 */
public class ShipInfoPaneController implements Initializable {
@FXML private Text nameText;
    @FXML private Text sizeText;
    @FXML private Text cargoSlotsText;
    @FXML private Text fuelCapacityText;
    @FXML private Text hullStrengthText;
    @FXML private Text weaponSlotsText;
    @FXML private Text shieldSlotsText;
    @FXML private Text gadgetSlotsText;
    @FXML private Text crewQuartersText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setShipType(ShipType ship) {
        nameText.setText(ship.name());
        sizeText.setText(""+ ship.size());
        cargoSlotsText.setText(""+ ship.cargoBay());
        fuelCapacityText.setText(""+ ship.fuel());
        hullStrengthText.setText(""+ ship.hullStrength());
        weaponSlotsText.setText("" + ship.weaponSlots());
        shieldSlotsText.setText("" + ship.shieldSlots());
        gadgetSlotsText.setText("" + ship.gadgetSlots());
        crewQuartersText.setText("" + ship.crew());
    }
}