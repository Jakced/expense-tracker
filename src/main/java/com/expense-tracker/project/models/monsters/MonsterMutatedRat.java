package com.dt181g.project.models.monsters;

/**
 * Represents a Rat Boss monster in the game.
 *
 * @author Jakob Cederblad
 */
public class MonsterMutatedRat extends Monster {
    /** Base name for monster */
    public final static String BASE_NAME = "Mutated Rat";
    /** Base health for monster */
    public final static int BASE_HEALTH = 300;
    /** Image icon path for monster */
    public final static String ICON_PATH = "src/main/resources/monster-mutated-rat.png";
    /** base gold yield when killing monster */
    public final static int BASE_GOLD_YIELD = 250;

    /**
     * Create a new instance of a Rat Boss monster
     */
    public MonsterMutatedRat() {
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
