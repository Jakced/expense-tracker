package com.dt181g.project.views.panels;

import com.dt181g.project.support.AppConfig;
import com.dt181g.project.views.themes.ThemeFactory;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;

/**
 * Prefab for an available upgrade panel
 * Contains information about upgrade level and cost for next level and a button to buy the upgrade. It also contains
 * methods to set the upgrade cost and information on the screen, along with a method to check if the player can
 * afford the upgrade based on passed gold value.
 *
 * @author Jakob Cederblad
 */
public class UpgradePrefabPanel extends JPanel {
    /** Label which shows the current upgrade level of this upgrade */
    private final JLabel upgradeLevelLabel;
    /** Button which shows and allow for interaction with upgrading this upgrade */
    private final JButton upgradeBuyButton;
    /** Label which shows the current upgrade cost */
    private final JLabel upgradeCostLabel;
    /** The current cost of the upgrade */
    private int currentUpgradeCost;

    /** Text to show on buy button */
    private static final String BUY_TEXT = "Buy";
    /** Text to show if upgrade has been bought out */
    private static final String BUY_MAX_TEXT = "Max";
    /** Spacing dimension between upgrade components */
    private static final Dimension SPACING_DIMENSION = new Dimension(0,10);

    /**
     * Constructs an upgrade prefab
     * @param themeFactory the theme used in the game
     * @param title the title of the upgrade
     * @param toolTip the tool tip of the upgrade
     * @param image the image representation of the upgrade
     */
    public UpgradePrefabPanel(ThemeFactory themeFactory, String title, String toolTip, ImageIcon image) {
        setBackground(themeFactory.getBackgroundColor());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel upgradeNameLabel = themeFactory.createLabel(title, AppConfig.DEFAULT_FONT_BOLD);
        add(upgradeNameLabel);

        addSpacing();

        upgradeLevelLabel = themeFactory.createLabel(title, AppConfig.DEFAULT_FONT_PLAIN);
        add(upgradeLevelLabel);

        addSpacing();

        JLabel upgradeIconLabel = themeFactory.createLabel("", AppConfig.DEFAULT_FONT_PLAIN);
        upgradeIconLabel.setIcon(new ImageIcon(scaleImage(image)));
        upgradeIconLabel.setToolTipText(toolTip);
        add(upgradeIconLabel);

        addSpacing();

        upgradeBuyButton = themeFactory.createButton(BUY_TEXT);
        add(upgradeBuyButton);

        addSpacing();

        upgradeCostLabel = themeFactory.createLabel("", AppConfig.DEFAULT_FONT_PLAIN);
        add(upgradeCostLabel);

        addSpacing();
    }

    /**
     * Add listener to buy button
     * @param listener the listener to add
     */
    public void addBuyButtonListener(ActionListener listener) {
        upgradeBuyButton.addActionListener(listener);
    }

    /**
     * Add a vertical spacing between components
     */
    private void addSpacing() {
        add(Box.createRigidArea(SPACING_DIMENSION));
    }

    /**
     * Will scale image according to configuration
     * @param imageIcon image to scale
     * @return a scaled Image
     */
    private Image scaleImage(ImageIcon imageIcon) {
        return imageIcon.getImage().getScaledInstance(
                AppConfig.UPGRADE_ICON_SIZE.width,
                AppConfig.UPGRADE_ICON_SIZE.height,
                Image.SCALE_SMOOTH);
    }

    /**
     * Will set the upgrade information
     * @param upgradeType the upgrade type
     * @param upgradeLevel the upgrade level
     * @param nextCost the cost for upgrading
     * @param playerGold the amount of gold the player currently holds
     */
    public void setUpgradeInformation(String upgradeType, int upgradeLevel, int nextCost, int playerGold) {
        upgradeLevelLabel.setText(upgradeType + ": " + upgradeLevel);
        setUpgradeCost(nextCost, playerGold);
    }

    /**
     * Sets the current cost of an upgrade and checks if player can afford it
     * @param cost the current cost
     * @param playerGold the current player gold
     */
    protected void setUpgradeCost(int cost, int playerGold) {
        currentUpgradeCost = cost;
        upgradeCostLabel.setText("Cost: " + cost + " gold");
        checkCanAfford(playerGold);
    }

    /**
     * Set upgrade information for a maxed out upgrade
     * Disables buy button and change the text.
     */
    public void setMaxLevel() {
        upgradeBuyButton.setText(BUY_MAX_TEXT);
        upgradeBuyButton.setEnabled(false);
        upgradeCostLabel.setText(" ");
    }

    /**
     * Will check if the player has enough gold to buy upgrade
     * enables button and change font color to green if player can afford
     * else will disable button and change font color to red if not
     * @param playerGold the current amount of gold the player has
     */
    public void checkCanAfford(int playerGold) {
        if (!upgradeBuyButton.getText().equals(BUY_MAX_TEXT)) {
            if (playerGold >= currentUpgradeCost) {
                upgradeCostLabel.setForeground(AppConfig.COLOR_GREEN);
                upgradeBuyButton.setEnabled(true);
            } else {
                upgradeCostLabel.setForeground(AppConfig.COLOR_RED);
                upgradeBuyButton.setEnabled(false);
            }
        }

    }
}
