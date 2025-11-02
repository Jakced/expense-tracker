package com.dt181g.project.views.panels;

import com.dt181g.project.support.AppConfig;
import com.dt181g.project.views.themes.ThemeFactory;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

/**
 * Upgrade panel containing all the upgrade prefabs used in the game
 * Responsible for adding new upgrades to the game view.
 *
 * @author Jakob Cederblad
 */
public class UpgradePanel extends JPanel {
    /** Selected theme of the game */
    private final ThemeFactory themeFactory;
    /** Panel to show all the different upgrades in a grid layout */
    private final JPanel upgradeGridPanel = new JPanel();
    /** Title of upgrade panel */
    public static String UPGRADE_SHOP_TITLE = "Upgrade Shop";

    /**
     * Construct a new upgrade panel
     * @param themeFactory the theme used in the game
     */
    public UpgradePanel(ThemeFactory themeFactory) {
        this.themeFactory = themeFactory;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(themeFactory.getBackgroundColor());
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(themeFactory.createSeparator());

        JPanel upgradeInformationPanel = new JPanel();
        upgradeInformationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5 ,10));
        upgradeInformationPanel.setBackground(themeFactory.getBackgroundColor());
        upgradeInformationPanel.add(themeFactory.createLabel(UPGRADE_SHOP_TITLE, AppConfig.DEFAULT_FONT_BOLD));
        add(upgradeInformationPanel);

        upgradeGridPanel.setLayout(new GridLayout(1, 0, 0, 5));
        upgradeGridPanel.setBackground(themeFactory.getBackgroundColor());
        add(upgradeGridPanel);
    }

    /**
     * Will create and return a new instance of a passed extension of the UpgradePrefabPanel class
     * @param panelClass the UpgradePrefabPanel class object to create
     * @param title the title of the upgrade
     * @param toolTip the tooltip of the upgrade
     * @param image the image of the upgrade
     * @return the created instance of the class
     * @param <T> the type of UpgradePrefabPanel to create
     */
    public <T extends UpgradePrefabPanel> T createUpgrade(Class<T> panelClass, String title, String toolTip, ImageIcon image) {
        try {
            T panel = panelClass.getConstructor(ThemeFactory.class, String.class, String.class, ImageIcon.class)
                    .newInstance(themeFactory, title, toolTip, image);
            upgradeGridPanel.add(panel);
            return panel;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create panel: " + title, e);
        }
    }
}
