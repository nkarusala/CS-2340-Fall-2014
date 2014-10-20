/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.travel;

import spacetrader.Player;

/**
 *
 * @author Seth
 */
public class ChangeHullEvent extends RandomEvent {
    public ChangeHullEvent(Player player, String message, int quantityChange) {
        super(player, message, quantityChange);
    }
    @Override
    public void doEvent() {
        player.getShip().setHullStrength(player.getShip().getHullStrength() + quantityChange);
    }
}