/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import spacetrader.SkillList.Skill;
import static spacetrader.Tools.rand;
import spacetrader.planets.Planet;

/**
 * Represents a Mercenary the player can hire and add to his ship's crew.
 *
 * @author Caleb
 */
public class Mercenary extends Trader {
    private final Planet homePlanet;
    static final String[] mercNames = {
        "Misty",
        "Brock",
        "Erika",
        "Blaine",
        "LT.Serge",
        "Koga",
        "Sabrina",
        "Geovanni",
        "Michael", 
        "Andrew",
        "Jessica",
        "Stephanie",
        "Elizabeth",
        "Seth",
        "Caleb",
        "Maharshi",
        "Naveena",
        "Tejas",
        "Heather",
        "Ryan",
        "Brandon",
        "Joel",
        "Diana",
        "Natalie",
        "Brandi",
        "Karen",
        "Philip",
        "Brett",
        "Craig",
        "Alyssa",
        "Dennis",
    };
    /**
     * Constructor for Mercenary
     *
     * @param name - name of the mercenary
     * @param pilot - pilot skill of mercenary
     * @param fighter - fighter skill of mercenary
     * @param trader - trader skill of mercenary
     * @param engineer - engineer skill of mercenary
     * @param investor - investor skill of mercenary
     */
    public Mercenary(String name, int pilot, int fighter, int trader, int engineer, int investor, Planet homePlanet) {
        super(name, pilot, fighter, trader, engineer, investor);
        this.homePlanet = homePlanet;
    }
    public static Mercenary createRandomMercenary(String name, Planet homePlanet) {
        int pilot = rand.nextInt(SkillList.MAX_SKILL + 1);
        int fighter = rand.nextInt(SkillList.MAX_SKILL + 1);
        int trader = rand.nextInt(SkillList.MAX_SKILL + 1);
        int engineer = rand.nextInt(SkillList.MAX_SKILL + 1);
        int investor = rand.nextInt(SkillList.MAX_SKILL + 1);
        return new Mercenary(name, pilot, fighter, trader, engineer, investor, homePlanet);
    }
    public int calculateDailyWage(Mercenary merc) {
        int sum = 0;
        for (Skill skill : Skill.values()) {
            sum += merc.getSkill(skill);
        }
        return sum * 3;
    }
    /**
     * Get the planet that a Mercenary will be sent to if fired
     * @return home planet - the mercenaries planet of origin and return 
     */
    public Planet getHomePlanet() {
        return homePlanet;
    }
    /*
    */
    @Override
    public String toString() {
        return homePlanet.getName() + getName() + " " + + getSkill(Skill.PILOT) + " " + getSkill(Skill.FIGHTER) +  " " + 
        getSkill(Skill.TRADER) + " " + getSkill(Skill.ENGINEER)  + " " +  getSkill(Skill.INVESTOR);
    }
}
