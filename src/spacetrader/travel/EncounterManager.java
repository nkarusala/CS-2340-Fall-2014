/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.travel;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import spacetrader.Planet;
import spacetrader.Player;
import spacetrader.PlayerShip;
import spacetrader.PoliceRecord;
import spacetrader.ShipType;
import spacetrader.SpaceShip;

/**
 * This class will handle all the probability of creating and handling encounters.
 * @author Caleb
 */
public class EncounterManager {
    
    private static Random rand = new Random();

    
    private Queue<Encounter> encounters;
    private final int numTotalEncounters;
    private Player player;
    private Planet destination;
    
    /**
     * Creates queue with all Encounters for one transition period
     * @param source source planet
     * @param destination destination planet
     * @param ship ship Player is using
     * @param player Player in the game
     */
    public EncounterManager(Planet source, Planet destination, PlayerShip ship, Player player) {
        encounters = new LinkedList<>();
        this.player = player;
        this.destination = destination;
        int pirateLevels = destination.getPoliticalSystem().strengthPirates();
        int traderLevels = destination.getPoliticalSystem().strengthTraders();
        int policeLevels = destination.getPoliticalSystem().strengthPolice();
        int counter = pirateLevels + traderLevels + policeLevels;
        int pirateProportion = pirateLevels / counter;
        int traderProportion = traderLevels / counter;
        Random rand = new Random();
        while(counter > 0) {
            double d = rand.nextDouble();
            if (d <= pirateProportion) {
                encounters.add(createPirateEncounter());
            } else if (pirateProportion < d && d <= (pirateProportion + traderProportion)) {
                encounters.add(createTraderEncounter());
            } else {
                encounters.add(createPoliceEncounter());
            }      
            counter -= 5;
        }
        numTotalEncounters = encounters.size();
         //figure this out with an algorithm
    }
    
    private Encounter createPoliceEncounter() {
        PoliceEncounter encounter = new PoliceEncounter(player);
        
        int tries = 1;
        if (player.getPoliceRecord().ordinal() < PoliceRecord.CRIMINAL.ordinal()) {
            tries = 3;
        } else if (player.getPoliceRecord().ordinal() < PoliceRecord.VILLAIN.ordinal()) {
            tries = 5;
        }
        SpaceShip ship = createShip(encounter, tries, ShipType.GNAT);
        encounter.setOpponent(ship);
        return encounter;
    }
    
    private Encounter createPirateEncounter() {
        PirateEncounter encounter = new PirateEncounter(player);
        
        //pirates are strong if the player is worth more
        int tries = 1 + player.getCurrentWorth() / 100000;
        //tries should be no less than 1
        tries = (tries < 1) ? 1 : tries;
        SpaceShip ship = createShip(encounter, tries, ShipType.GNAT);
        encounter.setOpponent(ship);
        return encounter;
    }
    
    private Encounter createTraderEncounter() {
        TraderEncounter encounter = new TraderEncounter(player);   
        int tries = 1;
        SpaceShip ship = createShip(encounter, tries, ShipType.FLEA);
        encounter.setOpponent(ship);
        return encounter;
    }

    /**
     * Creates the space ship for the opponent
     * @param encounter
     * @param tries
     * @param lowestShipType
     * @return 
     */
    private SpaceShip createShip(Encounter encounter, int tries, ShipType lowestShipType) {
        ShipType[] shipTypes = ShipType.values();
        
        ShipType opponentShipType = lowestShipType;
        //Pick a ship for the opponent "tries" number of times. The strongest one will be used.
        for (int i = 0; i < tries; i++) {
            int typeIndex = -1;
            
            //Keep picking a ship until you get one that is legal for the current situation
            do {
                int randomPercent = rand.nextInt(100);
                
                int sum = 0;
                for (typeIndex = 0; typeIndex < shipTypes.length && sum < randomPercent; typeIndex++) {
                    sum += shipTypes[typeIndex].occurrence();
                }
                if (typeIndex == -1) { //in case that randomNumber == 0
                    typeIndex = 0;
                }
            } while (!encounter.isLegalShipType(shipTypes[typeIndex], destination.getPoliticalSystem()));
            
            //if this chosen ship is stronger than the opponent's current ship, replace it
            if (shipTypes[typeIndex].ordinal() > opponentShipType.ordinal()) {
                opponentShipType = shipTypes[typeIndex];
            }
        }
        return new SpaceShip(opponentShipType);
    }

    
    /**
     * returns newest encounter and pops it off the ArrayList holding all encounters
     * @return latest encounter
     */
    public Encounter getNextEncounter() {
        return encounters.poll();
    }
    
    /**
     * returns the total number of encounters returned by LinkedList
     * @return total encounters for warp
     */
    public int getNumTotalEncounters() {
        return numTotalEncounters;
    }
    
    /**
     * returns the number of encounters remaining in current Warp
     * @return number encounters remaining
     */
    public int getEncountersRemaining() {
        return encounters.size();
    }
}
