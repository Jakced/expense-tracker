package com.dt181g.project.controllers;

import com.dt181g.project.models.PlayerModel;
import com.dt181g.project.models.listeners.PlayerGoldChangeListener;
import com.dt181g.project.models.upgrades.UpgradeModel;
import com.dt181g.project.views.panels.UpgradePanel;
import com.dt181g.project.views.panels.UpgradePrefabPanel;

import java.awt.event.ActionEvent;

/**
 * Handles interaction between the upgrade model and the upgrade view components.
 * Responsible for handling upgrade purchases to buy upgrades, check maximum level, refresh the UI with new upgrade
 * information and if the player can afford the upgrades with the current gold count.
 *
 * @author Jakob Cederblad
 */
public class UpgradeController implements PlayerGoldChangeListener {
    private final PlayerModel playerModel;
    private final UpgradeModel upgradeModel;
    private final UpgradePrefabPanel upgradePrefabPanel;

    /**
     * Constructs an upgrade controller, adds listener to the buy button and upgrade the visual panel
     * @param playerModel the player model to remove gold from when upgrading
     * @param upgradeModel the upgrade model to modify updates
     * @param upgradePanel the upgrade panel which view the upgrades
     */
    public UpgradeController(PlayerModel playerModel, UpgradeModel upgradeModel, UpgradePanel upgradePanel) {
        this.playerModel = playerModel;
        this.upgradeModel = upgradeModel;
        upgradePrefabPanel = upgradePanel.createUpgrade(
                UpgradePrefabPanel.class,
                upgradeModel.getUpgradeType().getName(),
                upgradeModel.getUpgradeType().getToolTip(),
                upgradeModel.getUpgradeType().getImageIcon()
        );
        upgradePrefabPanel.addBuyButtonListener(this::buyUpgrade);
        playerModel.addGoldChangeListener(this);
        updateVisuals();
    }

    /**
     * Handles the purchase of an upgrade when triggered by a UI action.
     * Will try to buy an upgrade if player has enough money, remove the cost, update the
     * view panel
     * @param e the ActionEvent triggered by clicking the button
     */
    private void buyUpgrade(ActionEvent e) {
        if (!upgradeModel.isMaxLevel()) {
            if (playerModel.buyUpgrade(upgradeModel.getNextCost())) {
                upgradeModel.upgradeLevel();
                updateVisuals();
            }
            checkMaxLevel();
        }
    }

    /**
     * Will check if upgrade is at max level and set it to max level if it is
     */
    private void checkMaxLevel() {
        if (upgradeModel.isMaxLevel()) {
            upgradePrefabPanel.setMaxLevel();
        }
    }

    /**
     * Will refresh the upgrade view panel with new upgrade information
     */
    private void updateVisuals() {
        upgradePrefabPanel.setUpgradeInformation(
                upgradeModel.getUpgradeType().getCategoryName(),
                upgradeModel.getUpgradeValue(),
                upgradeModel.getNextCost(),
                playerModel.getGold()
        );
    }

    /**
     * Triggered when player gold has changed, will refresh the UI to check if player afford upgrades
     * @param currentGold the new gold amount of player
     * @param addedGold the gold amount added
     */
    @Override
    public void onPlayerGoldChange(int currentGold, int addedGold) {
        upgradePrefabPanel.checkCanAfford(currentGold);
    }
}
