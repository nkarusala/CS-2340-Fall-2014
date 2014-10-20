/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.travel;

import java.util.ArrayList;
import java.util.Random;
import spacetrader.Planet;
import spacetrader.Player;
import spacetrader.Universe;

/**
 *
 * @author Seth
 */ 
public class RandomEventGenerator {
    private Player player;
    private Universe universe;
    private final Random random = new Random();
    private final ArrayList<RandomEvent> events = new ArrayList();
    private final int MAX_HULL_QUANTITY = 51;
    private final int MIN_HULL_QUANTITY = 10;
    private final int MAX_WALLET_QUANTITY = 301;
    private final int MIN_WALLET_QUANTITY = 10;
    
    public RandomEventGenerator() {
        events.add(new ChangeLocationEvent(player, "A giant space worm ate you and spat you out at a distant planet.", -1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY, getRandomPlanet()));
        events.add(new ChangeLocationEvent(player, "Your ship had a warp drive malfuntion and you found yourself at a distant planet.", 0, getRandomPlanet()));
        events.add(new ChangeLocationEvent(player, "You stayed up too late watching your favorite soap opera Lightyears Of Our Life, and fell asleep at the helm. You now find youself at a distant planet.", 0, getRandomPlanet()));
        events.add(new ChangeLocationEvent(player, "Your stupid crewmate slipped on a bannana and acciedentally sat on the hyperdrive taking you to a distant planet.",0, getRandomPlanet()));
        events.add(new ChangeHullEvent(player, "You ship was invaded by metal eating space termites causing damage to your hull.", -1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "Your ship passed through an asteroid field and was pelted by a barrage of space rocks causing damage to your hull.",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "The scretchy hitchhiker you picked up last week went on a rampage causing damage to your hull.",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "One of your crewmates burned popcorn causing the sprinklers go off creating a short circuit creating a fire in the hull.  ",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "Radiation from a nearby star caused damage to your hull",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "Your ship was ambushed by space pirates but you were able to get away with minimal damage.",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "Your ship crashed into a space cow causing damage to the hull",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "Poor craftsmanship of your hull plating resulted in brittle hull.",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "Your ship was rear ended by a space cop causing damage to your hull.",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "A garbage hauler prematurely ejected its load all over the hull of your ship",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "Some rowdy hooligans hit a space ball right into a window on the ship.",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "Your poor parallel space parking skills proved to be inadequate once again, you crashed into every surrounding ship.",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "You forgot to ask for permission to take off and crashed right into the hanger doors",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "Your space duct tape lost its adheesive quality causing everyhing on your ship to break.",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "While trying to show off to your space girlfriend by doing barrel rolls and summer saults, you crash into a satellite.",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "While trying to show off to your space girlfriend by doing barrel rolls and summer saults, you crash into a satellite.",-1 * random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "The hull repair man finally showed up... for once.",random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "That hitch hiker you picked up turned out to be a hull repair man, who paid you with his service.",random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "The space exterminator extermindated all the space termites in you hull.",random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "You won a beauty pagent and spent that money toward fixing your hull.",random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "You found 100 ₪ lying on the space ground and used it to fix your hull.",random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "There was a scoutmob for joe's hull repair shop that you took advantage of.",random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "You found a roll of duct tape lying on the shelf and used it to repair your hull.",random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "Your insurance company finally came through and paid to get you hull repaired.",random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "",random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeHullEvent(player, "",random.nextInt(MAX_HULL_QUANTITY - MIN_HULL_QUANTITY) + MIN_HULL_QUANTITY));
        events.add(new ChangeWalletEvent(player, "It's your lucky day! You found ₪ on the ground",random.nextInt(MAX_WALLET_QUANTITY - MIN_WALLET_QUANTITY) + MIN_WALLET_QUANTITY));
    }
    private Planet getRandomPlanet() {
        ArrayList<Planet> planets = universe.getPlanets();
        int randPlanet = random.nextInt(planets.size());
        return planets.get(randPlanet);
    }
}
