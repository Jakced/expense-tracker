package com.dt181g.project.views.panels;

import com.dt181g.project.support.AppConfig;
import com.dt181g.project.views.themes.ThemeFactory;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.Dimension;

/**
 * Panel showing player information
 * Will show the current player gold, attack damage and dungeon level along with a progress bar of how far it is to the next
 * dungeon level or if player has reached the endless dungeon level.
 *
 * @author Jakob Cederblad
 */
public class PlayerPanel extends JPanel {
    /** Label which shows the current gold amount player carries */
    private final JLabel playerGoldLabel;
    /** Label which shows the current dungeon level */
    private final JLabel dungeonLevelLabel;
    /** Progress bar which shows the current process for current dungeon level */
    private final JProgressBar dungeonLevelBar;
    /** Label which shows the current dungeon level */
    private final JLabel playerAttackDamageLabel;
    /** Will determine if visual show that dungeon level is maxed out */
    private boolean dungeonLevelMaxed;

    /**
     * Construct a new player panel showing gold and dungeon level progress
     * @param themeFactory the visual theme of the game
     */
    public PlayerPanel(ThemeFactory themeFactory) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(themeFactory.getBackgroundColor());

        add(Box.createRigidArea(new Dimension(0, 10)));

        playerGoldLabel = themeFactory.createLabel(
                "",
                AppConfig.BIG_FONT_BOLD,
                AppConfig.COLOR_YELLOW);
        add(playerGoldLabel);
        setPlayerGoldTextLabel(AppConfig.PLAYER_START_GOLD);

        add(Box.createRigidArea(new Dimension(0, 10)));

        playerAttackDamageLabel = themeFactory.createLabel(
                "",
                AppConfig.BIG_FONT_BOLD,
                AppConfig.COLOR_RED
        );
        setAttackDamageLabel(AppConfig.PLAYER_BASE_DAMAGE);
        add(playerAttackDamageLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        dungeonLevelLabel = themeFactory.createLabel(
                "",
                AppConfig.BIG_FONT_BOLD,
                AppConfig.COLOR_MAGENTA);
        setDungeonLevelLabel(Integer.toString(AppConfig.PLAYER_DUNGEON_START_LEVEL));
        add(dungeonLevelLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        dungeonLevelBar = themeFactory.createProgressbar();
        add(dungeonLevelBar);

        add(Box.createRigidArea(new Dimension(0, 20)));

        add(themeFactory.createSeparator());

        add(Box.createRigidArea(new Dimension(0, 20)));

        dungeonLevelMaxed = false;
    }

    /**
     * Set gold label to passed gold amount
     * @param gold the gold to show
     */
    public void setPlayerGoldTextLabel(int gold) {
        playerGoldLabel.setText("Gold: " + gold);
    }

    /**
     * Set attack damage label to damage
     * @param damage the attack damage to show
     */
    public void setAttackDamageLabel(int damage) {
        String newText = "Attack Damage: " + damage;
        if (!newText.equals(playerAttackDamageLabel.getText())) {
            playerAttackDamageLabel.setText(newText);
        }
    }

    /**
     * Set dungeon level label to passed level
     * @param level the level to show
     */
    private void setDungeonLevelLabel(String level) {
        String newText = "Dungeon Level: " + level;
        if (!newText.equals(dungeonLevelLabel.getText())) {
            dungeonLevelLabel.setText(newText);
        }
    }

    /**
     * Set visuals stating that the max dungeon level has been reached
     */
    private void setDungeonLevelMaxed() {
        dungeonLevelBar.setValue(dungeonLevelBar.getMinimum());
        dungeonLevelBar.setStringPainted(true);
        dungeonLevelBar.setString("Maximum floor reached");
        setDungeonLevelLabel("Endless Floor");
        dungeonLevelMaxed = true;
    }

    /**
     * Will return if the visuals show that max dungeon level has been reached
     * @return if visual shows that the maximum dungeon level has been reached
     */
    private boolean isDungeonMaxed() {
        return dungeonLevelMaxed;
    }

    /**
     * Will set the progress bar to show dungeon level progress (0-100%)
     * @param dungeonLevel the current dungeon level
     * @param killCount the current kill count
     * @param nextDungeonLevel the kills for next dungeon level
     */
    public void setLevelProgressBar(int dungeonLevel, int killCount, int nextDungeonLevel) {
        if (isDungeonMaxed())
            return;
        if (nextDungeonLevel == 0) {
            setDungeonLevelMaxed();
        } else {
            setDungeonLevelLabel(Integer.toString(dungeonLevel));
            int levelProgress = (int) (((double) killCount / nextDungeonLevel) * 100);
            levelProgress = Math.max(0, Math.min(levelProgress, 100));
            dungeonLevelBar.setValue(levelProgress);
        }
    }
}
