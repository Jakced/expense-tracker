package com.dt181g.project.models.upgrades;

import com.dt181g.project.support.AppConfig;

import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representation of all upgrade types in the game
 * Includes name of the upgrade, the image path for the icon, the tooltip for information about what the upgrade does
 * and an arraylist of price for each upgrade.
 *
 * @author Jakob Cederblad
 */
public enum UpgradeType {
    /**
     * Represents an attack upgrade
     */
    ATTACK(
            "Attack Upgrade",
            UpgradeCategory.DAMAGE,
            "src/main/resources/upgrade-weapon.png",
            "Will add +" + AppConfig.UPGRADE_WEAPON_BONUS_DAMAGE + " melee attack damage per upgrade.",
            new ArrayList<>(AppConfig.UPGRADE_ATTACK_COST_LIST)
    ),

    /**
     * Represents a peon upgrade
     */
    PEON(
            "Peon Shop",
            UpgradeCategory.NPC,
            "src/main/resources/upgrade-peon.png",
            "Will generate " + AppConfig.UPGRADE_PEON_GOLD_GAIN + " gold every " + AppConfig.UPGRADE_PEON_GOLD_INTERVAL + " seconds.",
            new ArrayList<>(AppConfig.UPGRADE_PEON_COST_LIST)
    ),

    /**
     * Represents a knight upgrade
     */
    KNIGHT(
            "Knight Shop",
            UpgradeCategory.NPC,
            "src/main/resources/upgrade-knight.png",
            "Will cause " + AppConfig.UPGRADE_KNIGHT_DAMAGE_POWER + " damage to the current monster every " + AppConfig.UPGRADE_KNIGHT_DAMAGE_INTERVAL + " seconds.",
            new ArrayList<>(AppConfig.UPGRADE_KNIGHT_COST_LIST)
    ),

    /**
     * Represents a sorcerer upgrade
     */
    SORCERER(
            "Sorcerer Shop",
            UpgradeCategory.NPC,
            "src/main/resources/upgrade-sorcerer.png",
            "Will generate " + AppConfig.UPGRADE_SORCERER_GOLD_GAIN + " gold and cause " + AppConfig.UPGRADE_SORCERER_DAMAGE_POWER +
                    " damage to the current monster every " + AppConfig.UPGRADE_KNIGHT_DAMAGE_INTERVAL + " seconds.",
            new ArrayList<>(AppConfig.UPGRADE_SORCERER_COST_LIST)
    );

    private final String name;
    private final UpgradeCategory category;
    private final ImageIcon imageIcon;
    private final String toolTip;
    private final List<Integer> upgradeCostList;

    /**
     * Construct  to create a new upgrade type
     * @param name the name of the upgrade
     * @param category the upgrade category
     * @param imagePath the ImageIcon which visually represent the upgrade
     * @param toolTip the tooltip to show for the upgrade
     * @param upgradeCostList the cost list of the upgrade
     */
    UpgradeType(String name, UpgradeCategory category, String imagePath, String toolTip, List<Integer> upgradeCostList) {
        this.name = name;
        this.category = category;
        this.imageIcon = new ImageIcon(imagePath);
        this.toolTip = toolTip;
        this.upgradeCostList = upgradeCostList;
    }

    /**
     * Will get the upgrade name
     * @return the name of the upgrade
     */
    public String getName() {
        return name;
    }

    /**
     * Will get the upgrade category name
     * @return the name of the upgrade category
     */
    public String getCategoryName() {
        return category.getName();
    }

    /**
     * Will get the upgrade image icon
     * @return the upgrade image icon
     */
    public ImageIcon getImageIcon() {
        return new ImageIcon(imageIcon.getImage());
    }

    /**
     * Will get the upgrade tooltip
     * @return the upgrade tooltip
     */
    public String getToolTip() {
        return toolTip;
    }

    /**
     * Will get the upgrade cost list showing all cost each upgrade iteration
     * @return the upgrade cost list
     */
    public List<Integer> getUpgradeCostList() {
        return Collections.unmodifiableList(upgradeCostList);
    }

    /**
     * Will get the max upgrade level
     * @param type the upgrade type
     * @return the maximum upgrade level
     */
    public static int getMaxUpgrade(UpgradeType type) {
        return type.getUpgradeCostList().size();
    }
}