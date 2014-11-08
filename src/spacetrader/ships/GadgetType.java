/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.ships;

import spacetrader.planets.TechLevel;

/**
 *
 * @author nkaru_000
 */
public enum GadgetType {
    
    EXTRA_CARGO        (TechLevel.EARLY_INDUSTRIAL, 2500, 35, "5 Extra Cargo Bays"),
    AUTO_REPAIR        (TechLevel.INDUSTRIAL, 7500, 20, "Auto-Repair System"), // Increases engineer's effectivity
    NAVIGATION         (TechLevel.POST_INDUSTRIAL, 15000, 20, "Navigating System"), // Increases pilot's effectivity
    TARGETING          (TechLevel.POST_INDUSTRIAL, 25000, 20, "Targeting System"), // Increases fighter's effectivity
    CLOAK              (TechLevel.HI_TECH, 100000, 5, "Cloaking Device"); // If you have a good engineer, less pirates and police will notice you
    
    private final TechLevel minTechLevel;
    private final int price;
    private final int chance;
    private final String name;
    
    private GadgetType(TechLevel minTechLevel, int price, int chance, String name) {
        this.minTechLevel = minTechLevel;
        this.price = price;
        this.chance = chance;
        this.name = name;
    }
    
    public int price() {
        return price;
    }
    
    public int chance() {
        return chance;
    }
    
    public TechLevel minTechLevel() {
        return minTechLevel;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
