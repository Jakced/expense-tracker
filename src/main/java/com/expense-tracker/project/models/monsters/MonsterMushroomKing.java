package com.dt181g.project.models.monsters;


/**
 * Represents a Mushroom King monster in the game.
 *
 * @author Jakob Cederblad
 */
public class MonsterMushroomKing extends Monster {
    /** Base name for monster */
    public final static String BASE_NAME = "Mushroom King";
    /** Base health for monster */
    public final static int BASE_HEALTH = 100000;
    /** Image icon path for monster */
    public final static String ICON_PATH = "src/main/resources/monster-mushroom-king.png";
    /** base gold yield when killing monster */
    public final static int BASE_GOLD_YIELD = 100000;

    /**
     * Create a new instance of a Mushroom King monster
     */
    public MonsterMushroomKing() {
        super(BASE_NAME, ICON_PATH);
    }

    /**
     * Set showcase name for monster
     */
    @Override
    void setShowcaseName() {
        monsterShowcaseName = BASE_NAME + " (BOSS)";
    }

    /**
     * Set the current health for the monster
     */
    @Override
    void setHealth() {
        monsterHealth = BASE_HEALTH;
    }

    /**
     * Set the gold yield for monster, how much gold the monster will drop when killed
     */
    @Override
    void setGoldYield() {
        monsterGoldYield = BASE_GOLD_YIELD;
    }
}
