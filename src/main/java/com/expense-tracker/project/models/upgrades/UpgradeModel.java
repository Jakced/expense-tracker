package com.dt181g.project.models.upgrades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Abstract class for all upgrades in the application
 *
 * @author Jakob Cederblad
 */
public abstract class UpgradeModel {
    private int currentLevel;

    private final UpgradeType upgradeType;

    /** Holds all upgrade costs in a Map */
    protected Map<Integer, Integer> upgradeCostsMap = new HashMap<>();

    /**
     * Constructor to create a new upgrade
     * @param upgradeType type of upgrade to create
     */
    public UpgradeModel(UpgradeType upgradeType) {
        this.upgradeType = upgradeType;
        this.currentLevel = 0;
        setUpgradeCosts();
    }

    /**
     * Method called when an upgrade is bought
     */
    abstract void triggerUpgradeEffect();

    /**
     * Will get the upgrade type
     * @return the upgrade type
     */
    public UpgradeType getUpgradeType() {
        return upgradeType;
    }

    /**
     * Will get the current upgrade level
     * @return the current  level
     */
    private int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Will map each upgrade cost
     */
    private void setUpgradeCosts() {
        List<Integer> upgradeCosts = upgradeType.getUpgradeCostList();
        upgradeCostsMap.clear();
        IntStream.range(0, upgradeCosts.size())
                .forEach(i -> upgradeCostsMap.put(i + 1, upgradeCosts.get(i)));
    }

    /**
     * Upgrade current level if level is not max
     */
    public void upgradeLevel() {
        if(isMaxLevel())
            return;
        currentLevel++;
        triggerUpgradeEffect();
    }

    /**
     * Will check if max level is reached
     * @return if max level is reached
     */
    public boolean isMaxLevel() {
        return upgradeCostsMap.get(getCurrentLevel() + 1) == null;
    }

    /**
     * Will return the next upgrade cost
     * @return the cost of the next upgrade
     */
    public int getNextCost() {
        if (upgradeCostsMap.get(getCurrentLevel() + 1) == null) {
            return 0;
        }
        return upgradeCostsMap.get(getCurrentLevel() + 1);
    }

    /**
     * Will get the current upgrade value
     * @return the current upgrade value
     */
    public int getUpgradeValue() {
        return currentLevel;
    }
}
