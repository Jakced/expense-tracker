package com.dt181g.project.views.panels;

import com.dt181g.project.support.AppConfig;
import com.dt181g.project.views.themes.ThemeFactory;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedHashMap;

/**
 * Kill statistics panel which will show a list of how many of each monster has been killed during the game.
 *
 * @author Jakob Cederblad
 */
public class KillStatisticsPanel extends JPanel {
    /** JTextArea to show monster kill statistics*/
    private final JTextArea killStatisticsTextArea;

    /**
     * Construct a new kill statistics panel
     * @param themeFactory the theme of the game
     */
    public KillStatisticsPanel(ThemeFactory themeFactory) {
        setLayout(new BorderLayout());
        setBackground(themeFactory.getBackgroundColor());
        Dimension panelDimension = new Dimension(AppConfig.STATISTICS_PANEL_DIMENSION);
        setPreferredSize(panelDimension);

        JLabel killListTitleLabel = themeFactory.createLabel(AppConfig.KILL_LIST_STATISTICS_TITLE, AppConfig.DEFAULT_FONT_BOLD);
        add(killListTitleLabel, BorderLayout.NORTH);
        killStatisticsTextArea = themeFactory.createTextArea();
        killStatisticsTextArea.setBackground(themeFactory.getBackgroundColor());
        killStatisticsTextArea.setBorder(BorderFactory.createEmptyBorder());

        JScrollPane killTableScroller = new JScrollPane(killStatisticsTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        killTableScroller.setBorder(BorderFactory.createEmptyBorder());

        add(killTableScroller, BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    }

    /**
     * Will refresh the kill list text area with a passed list
     * @param sortedMonsterKillList a sorted list containing all the count of all the monsters killed during the game
     */
    public void refreshKillListText(LinkedHashMap<String, Integer> sortedMonsterKillList) {
        String killStatistic = buildKillStatisticText(sortedMonsterKillList);
        killStatisticsTextArea.setText(killStatistic);
    }

    /**
     * Will build a new kill text as a String from a list
     * @param sortedMonsterKillList the list to build the list from
     * @return the kill list as a string
     */
    private String buildKillStatisticText(LinkedHashMap<String, Integer> sortedMonsterKillList) {
        StringBuilder killList = new StringBuilder();

        sortedMonsterKillList.forEach((monster, count) ->
                killList.append(monster)
                        .append(": ")
                        .append(count)
                        .append("\n")
        );

        return killList.toString();
    }
}
