package com.dt181g.project.views.panels;

import com.dt181g.project.support.AppConfig;
import com.dt181g.project.views.themes.ThemeFactory;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

/**
 * Player statistics panel which will show player statistics gathered during the game.
 * It will show total player damage, total gold gained and total monsters killed.
 *
 * @author Jakob Cederblad
 */
public class PlayerStatisticsPanel extends JPanel {
    /** Label to show total amount of gold gained */
    private final JLabel goldGainValueLabel;
    /** Label to show total amount of damage done */
    private final JLabel damageDoneValueLabel;
    /** Label to show total amount of monsters killed */
    private final JLabel monsterKillValueLabel;

    /**
     * Construct a new kill statistics panel
     * @param themeFactory the theme of the game
     */
    public PlayerStatisticsPanel(ThemeFactory themeFactory) {
        setLayout(new BorderLayout());
        setBackground(themeFactory.getBackgroundColor());

        Dimension panelDimension = new Dimension(AppConfig.STATISTICS_PANEL_DIMENSION);
        setPreferredSize(panelDimension);

        JLabel titleLabel = themeFactory.createLabel(AppConfig.PLAYER_STATISTICS_TITLE, AppConfig.DEFAULT_FONT_BOLD);
        titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(titleLabel, BorderLayout.NORTH);

        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        statisticsPanel.setBackground(themeFactory.getBackgroundColor());
        statisticsPanel.setLayout(new BoxLayout(statisticsPanel, BoxLayout.Y_AXIS));

        JLabel goldGainTitleLabel = themeFactory.createLabel("Total Gold Gained", AppConfig.DEFAULT_FONT_BOLD);
        goldGainTitleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        statisticsPanel.add(goldGainTitleLabel);

        goldGainValueLabel = themeFactory.createLabel("0", AppConfig.DEFAULT_FONT_BOLD);
        goldGainValueLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        statisticsPanel.add(goldGainValueLabel);

        statisticsPanel.add(Box.createVerticalStrut(10));

        JLabel damageDoneTitleLabel = themeFactory.createLabel("Total Damage Done", AppConfig.DEFAULT_FONT_BOLD);
        damageDoneTitleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        statisticsPanel.add(damageDoneTitleLabel);

        damageDoneValueLabel = themeFactory.createLabel("0", AppConfig.DEFAULT_FONT_BOLD);
        damageDoneValueLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        statisticsPanel.add(damageDoneValueLabel);

        statisticsPanel.add(Box.createVerticalStrut(10));

        JLabel monsterKillsTitleLabel = themeFactory.createLabel("Total Monster Slain", AppConfig.DEFAULT_FONT_BOLD);
        monsterKillsTitleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        statisticsPanel.add(monsterKillsTitleLabel);

        monsterKillValueLabel = themeFactory.createLabel("0", AppConfig.DEFAULT_FONT_BOLD);
        monsterKillValueLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        statisticsPanel.add(monsterKillValueLabel);

        add(statisticsPanel, BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    }

    /**
     * Refresh total amount of damage done label with new value
     * @param value the new value
     */
    public void setGoldGainValueText(int value) {
        goldGainValueLabel.setText(Integer.toString(value));
    }

    /**
     * Refresh total amount of gold gain label with new value
     * @param value the new value
     */
    public void setDamageDoneValueText(int value) {
        damageDoneValueLabel.setText(Integer.toString(value));
    }

    /**
     * Refresh total amount of monster killed label with new value
     * @param value the new value
     */
    public void setMonsterKillValueText(int value) {
        monsterKillValueLabel.setText(Integer.toString(value));
    }
}
