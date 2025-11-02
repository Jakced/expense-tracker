package com.dt181g.project.models.monsters;

import java.util.Random;

/**
 * Represents a Mushroom Cap monster in the game.
 *
 * @author Jakob Cederblad
 */
public class MonsterMushroomCap extends Monster {
    /** Base name for monster */
    public final static String BASE_NAME = "Mushroom Cap";
    /** Base health for monster */
    public final static int BASE_HEALTH = 200;
    /** Image icon path for monster */
    public final static String ICON_PATH = "src/main/resources/monster-mushroom-cap.png";
    /** base gold yield when killing monster */
    public final static int BASE_GOLD_YIELD = 210;

    /**
     * Current showcase name for monster
     */
    private String showcaseName;

    /**
     * Current enchant factor for monster
     */
    private float enchantFactor;

    /** Name of enchantment, will be in the showcased name */
    String[] enchantNames = {"Cursed", "Forsaken", "Furious", "Ancient"};
    /** Factor for the enchantment, will alter health and gold yield */
    float[] enchantFactors = {1f, 0.8f, 1.5f, 2f};

    /**
     * Create a new instance of a Mushroom Cap monster
     */
    public MonsterMushroomCap() {
        super(BASE_NAME, ICON_PATH);
    }

    /**
     * Set the enchantment for monster
     */
    @Override
    void setEnchant() {
        int randomEnchantIndex = new Random().nextInt(enchantNames.length);
        showcaseName = enchantNames[randomEnchantIndex];
        enchantFactor = enchantFactors[randomEnchantIndex];
    }

    /**
     * Set showcase name for monster
     */
    @Override
    void setShowcaseName() {
        monsterShowcaseName = showcaseName + " " + BASE_NAME;
    }

    /**
     * Set the current health for the monster
     */
    @Override
    void setHealth() {
        monsterHealth = (int) (BASE_HEALTH * enchantFactor);
    }

    /**
     * Set the gold yield for monster, how much gold the monster will drop when killed
     */
    @Override
    void setGoldYield() {
        monsterGoldYield = (int) (BASE_GOLD_YIELD * enchantFactor);
    }
}
