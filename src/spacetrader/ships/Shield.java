/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.ships;

/**
 *
 * @author Caleb
 */
public class Shield {

    private ShieldType type;
    private int health;

    public Shield(ShieldType type) {
        this.type = type;
        this.health = type.power();
    }

    /**
     * Gets the name of this shield.
     *
     * @return this shield's name
     */
    public String getName() {
        return type.toString();
    }

    /**
     * Gets the defense of this shield.
     *
     * @return the shield's defense
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Sets the power remaining of this shield.
     * @param health the power remaining
     */
    public void setHealth(int health) {
        this.health = health;
    }

    public ShieldType getType() {
        return type;
    }
}
