/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.planets;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;
import spacetrader.Mercenary;
import spacetrader.Player;
import spacetrader.PoliceRecord;
import spacetrader.commerce.Market;
import spacetrader.commerce.PriceIncreaseEvent;
import spacetrader.system.InformationPresenter;
import spacetrader.system.MainController;

/**
 * Represents a Solar System the player can visit.
 *
 * @author maharshipatel999
 */
public class Planet implements Serializable {

    private final Random rand = new Random();
    private final String[] activity
            = {
                "Absent",
                "Minimal",
                "Few",
                "Some",
                "Moderate",
                "Many",
                "Abundant",
                "Swarms"
            };

    private final String name;
    private final Point location;
    private final TechLevel level;
    private final Resource resource;
    private final PoliticalSystem politSys;
    private final int size;
    private PriceIncreaseEvent priceIncEvent;
    private int priceIncDuration;
    private Market market;
    private Wormhole wormhole;
    private Mercenary merc;

    private boolean visited;

    /**
     * Constructor for Planet.
     *
     * @param name name of planet
     * @param location location of planet
     */
    public Planet(String name, Point location) {
        this(name, location, TechLevel.getRandomTechLevel(),
                Resource.getRandomResource(),
                PoliticalSystem.getRandomPoliticalSystem());
    }

    /**
     * Creates a new planet.
     * 
     * @param name name of planet
     * @param location locations of planet
     * @param level tech level of planet
     * @param resource main resource of planet
     * @param politSys political system of planet
     */
    public Planet(String name, Point location, TechLevel level,
            Resource resource, PoliticalSystem politSys) {
        this.name = name;
        this.location = location;
        this.level = level;
        this.resource = resource;
        this.politSys = politSys;
        this.priceIncEvent = PriceIncreaseEvent.NONE;
        this.size = rand.nextInt(5); //this should probably be moved to the Universe class

        this.market = new Market(this);
        this.wormhole = null;
        this.visited = false;
    }
    
    /**
     * Takes care of the actual act of going to a planet. Things that happens
     * every time you go to a planet from a different planet should be put here.
     *
     * @param player the player arriving on this planet.
     * @param mainControl the game's main controller
     */
    public void movePlayerLocation(Player player, MainController mainControl) {
        player.setLocation(this);
        this.visited = true;
        this.market.setAllPrices(player);
        mainControl.goToHomeScreen(this);
        displayPriceIncreaseEvent();
        displayMercenaryForHire();
    }
    
    /**
     * Displays the mercenaries that are for hire.
     */
    public void displayMercenaryForHire() {
        if(this.getMercenary() != null) {
            InformationPresenter.getInstance().displayInfoMessage(null,
                    "Mercenary For Hire",
                    "There is a mercenary available for hire on this planet. "
                    + "Check it out in the ship yard");
        }
    }
    /**
     * Display price increase event of this planet, if there is one.
     */
    public void displayPriceIncreaseEvent() {
        if (this.priceIncEvent != PriceIncreaseEvent.NONE) {
            InformationPresenter.getInstance()
                    .displayInfoMessage(null, "Notice!", "%s is currently %s",
                    this.name,
                    this.priceIncEvent.desc().toLowerCase());
        }
    }

    /**
     * Gets the name of the planet.
     *
     * @return the name of the planet
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get this planet's location.
     *
     * @return location of the planet
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Get tech level of planet.
     *
     * @return the tech level of planet
     */
    public TechLevel getLevel() {
        return this.level;
    }

    /**
     * Get resource.
     *
     * @return resource of the planet
     */
    public Resource getResource() {
        return this.resource;
    }

    /**
     * Get political system.
     *
     * @return political system
     */
    public PoliticalSystem getPoliticalSystem() {
        return this.politSys;
    }

    /**
     * Returns the size of this planet, a value between 0 and 4.
     *
     * @return this planet's size
     */
    public int getSize() {
        return size;
    }

    /**
     * Set the price increase event.
     *
     * @param priceIncEvent - price increase event for a planet
     */
    public void setPriceIncEvent(PriceIncreaseEvent priceIncEvent) {
        this.priceIncEvent = priceIncEvent;
        this.setPriceIncDuration(PriceIncreaseEvent.setRandomPriceIncDuration());
    }

    /**
     * Set random price increase event.
     */
    public void setRandomPriceIncEvent() {
        this.priceIncEvent = PriceIncreaseEvent.getRandomPriceEvent();
        this.setPriceIncDuration(PriceIncreaseEvent.setRandomPriceIncDuration());
    }

    /**
     * Get price increase event duration.
     *
     * @return price increase event duration
     */
    public int getPriceIncDuration() {
        return this.priceIncDuration;
    }

    /**
     * Set price increase event duration.
     *
     * @param priceIncDuration - the duration of a price increase event
     */
    public void setPriceIncDuration(int priceIncDuration) {
        this.priceIncDuration = priceIncDuration;
    }

    /**
     * Get price increase event.
     *
     * @return price increase event
     */
    public PriceIncreaseEvent getPriceIncEvent() {
        return this.priceIncEvent;
    }

    /**
     * Get this planet's market.
     *
     * @return this planet's market
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Check if planet has been visited.
     *
     * @return true if a planet has been visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the visited flag to indicate that the player has visited this planet.
     */
    public void setVisited() {
        visited = true;
    }

    /**
     * Get this planet's wormhole, or null if it has none.
     *
     * @return this planet's wormhole, or null
     */
    public Wormhole getWormhole() {
        return this.wormhole;
    }

    /**
     * Set this planet's wormhole, or null if it does not have one.
     *
     * @param wormhole the wormhole for this planet, or null
     */
    public void setWormhole(Wormhole wormhole) {
        this.wormhole = wormhole;
    }
    /**
     * Get the mercenary available for hire on this planet, or null if there
     * is none.
     *
     * @return the mercenary at this planet, or null
     */
    public Mercenary getMercenary() {
        return merc;
    }
    /**
     * Set the mercenary for hire on this planet.
     * 
     * @param merc the mercenary of the planet
     */
    public void setMercenary(Mercenary merc) {
        this.merc = merc;
    }

    /**
     * Calculate strength of police.
     *
     * @param record player's police record
     * @return the strength of the police
     */
    public int calculateStrengthOfPolice(PoliceRecord record) {
        int strength = politSys.strengthPolice();
        if (record.ordinal() < PoliceRecord.VILLAIN.ordinal()) {
            strength *= 3;
        } else if (record.ordinal() < PoliceRecord.CRIMINAL.ordinal()) {
            strength *= 2;
        }

        return strength;
    }

    /**
     * Determine the amount of police that will be on the planet
     *
     * @param record the player's police record
     * @return description of how many police can be expected to be on the
     * planet the player's police record
     */
    public String expectedAmountOfPolice(PoliceRecord record) {
        int policeVariable = calculateStrengthOfPolice(record);

        int activityIndex = Math.min(policeVariable, activity.length - 1);
        return activity[activityIndex];
    }

    /**
     * Determine the amount of pirates that will be on the planet
     *
     * @return description of how many pirates can be expected to be on the
     * planet
     */
    public String expectedAmountOfPirates() {
        int pirateVariable = this.politSys.strengthPirates();

        int activityIndex = Math.min(pirateVariable, activity.length - 1);
        return activity[activityIndex];
    }

    @Override
    public String toString() {
        StringBuilder finStr = new StringBuilder();
        finStr.append(name).append("\n");
        finStr.append("Location: (").append(location.getX()).append(", ").append(location.getY()).append(")\n");
        finStr.append("Tech Level: ").append(level).append("\n");
        finStr.append("Resource: ").append(resource).append("\n");
        finStr.append("Political System: ").append(politSys).append("\n");
        finStr.append("Price Increase Event: ").append(priceIncEvent).append("\n");
        return finStr.toString();
    }

}
