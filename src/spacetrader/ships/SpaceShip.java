/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.ships;

import java.io.Serializable;
import spacetrader.Player;
import spacetrader.SkillList.Skill;
import spacetrader.commerce.Cargo;
import spacetrader.commerce.TradeGood;

/**
 *
 * @author Caleb Stokols
 */
public class SpaceShip implements Serializable {
    
    private final ShipType type;
    private final Cargo cargo;
    private final FuelTank tank;
    private final EquipmentSlots<Weapon> weapons;
    private final EquipmentSlots<Shield> shields;
    private final EquipmentSlots<Gadget> gadgets;
    
    private int maxHullStrength;
    private int hullStrength;
    
    public SpaceShip(ShipType type) {
        this.type = type;
        this.cargo = new Cargo(type.cargoBay());
        this.tank = new FuelTank(type.fuel() + 10); //added fuel for testing
        this.weapons = new EquipmentSlots<>(type.weaponSlots());
        this.shields = new EquipmentSlots<>(type.shieldSlots());
        this.gadgets = new EquipmentSlots<>(type.gadgetSlots());
        
        this.maxHullStrength = type.hullStrength();
        this.hullStrength = maxHullStrength;
    }
    
    public Cargo getCargo() {
        return cargo;
    }

    public FuelTank getTank() {
        return tank;
    }

    public ShipType getType() {
        return type;
    }
    
    public EquipmentSlots<Weapon> getWeapons() {
        return weapons;
    }

    public EquipmentSlots<Shield> getShields() {
        return shields;
    }

    public EquipmentSlots<Gadget> getGadgets() {
        return gadgets;
    }

    public int getMaxHullStrength() {
        return maxHullStrength;
    }

    public int getHullStrength() {
        return hullStrength;
    }
    
    public void setHullStrength(int hullStrength) {
        this.hullStrength = hullStrength;
    }
    
    /**
     * Determines if this ship is carrying firearms or narcotics.
     * @return true if this ship is carrying firearms or narcotics, false otherwise
     */
    public boolean isCarryingIllegalGoods() {
        int firearms = cargo.getQuantity(TradeGood.FIREARMS);
        int narcotics = cargo.getQuantity(TradeGood.NARCOTICS);
        return firearms + narcotics > 0;
    }
    
    /**
     * This method should be put in the shipYard
     * Determines the price of this ship
     * @param player the player of the game
     * @return the price of this ship
     */
    public int shipPrice(Player player) {
        int basePrice = type.price() * (100 - player.getEffectiveSkill(Skill.TRADER)) / 100;
        
        //change basePrice more based on its contentes
        return basePrice;
    }
    
    /**
     * Used in calculating the worth of this ship
     * @return the selling price of this ship
     */
    public int currentShipPriceWithoutCargo() {
        //trade-in value is 3/4 original price
        int currentPrice = (type.price() * 3) / 4;
        
        //subtract repair costs
        currentPrice -= (maxHullStrength - hullStrength) * type.repairCost();
        
        //subtract cost to fill tank with fuel
        currentPrice -= (getTank().getMaxFuel() - getTank().getFuelAmount());
        
        //add reduced cost of equipment
        for (int i = 0; i < weapons.getNumFilledSlots(); i++) {
            currentPrice += weapons.getItem(i).getType().price() * .75;
        }
        for (int i = 0; i < shields.getNumFilledSlots(); i++) {
            currentPrice += weapons.getItem(i).getType().price() * .75;
        }
        //for (int i = 0; i < gadgets.getNumFilledSlots(); i++) {
        //    currentPrice += gadgets.getItem(i).getType().price() * .75;
        //}
        
        return currentPrice;
    }
    
    /**
     * Calculated the worth of this ship
     * @return this ship's current worth
     */
    public int currentShipPrice() {
        return currentShipPriceWithoutCargo() + cargo.getCostOfAllGoods();
    }
    
}