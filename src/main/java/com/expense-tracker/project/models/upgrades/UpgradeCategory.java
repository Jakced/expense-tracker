package com.dt181g.project.models.upgrades;

/**
 * Representation of all upgrade categories in the game
 *
 * @author Jakob Cederblad
 */
public enum UpgradeCategory {
    /**
     * Represent a bonus damage upgrade
     */
    DAMAGE("Bonus Damage"),

    /**
     * Represent a quantity purchase
     */
    NPC("Owned");

    private final String name;

    /**
     * Constructor for a new upgrade category
     * @param name the category name
     */
    UpgradeCategory(String name) {
        this.name = name;
    }

    /**
     * Will get the upgrade category name
     * @return the upgrade category name
     */
    public String getName() {
        return name;
    }
}