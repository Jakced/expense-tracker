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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * View to show monster information and provide an event to attack the monster.
 * Will show the monster as a button with an image to click, it will also provide labels about the monsters name and
 * current health.
 *
 * @author Jakob Cederblad
 */
public class MonsterPanel extends JPanel {
    /** Label which shows the current monster name */
    private final JLabel monsterNameLabel;
    /** Label which shows the current monsters health */
    private final JLabel monsterHealthLabel;
    /** Button which shows the monsters image as a clickable button to do interact with monster */
    private final JButton monsterImageButton;

    /**
     * Construct a new monster panel with set theme
     * Will show image as a button to click, name and current health
     * @param themeFactory the visual theme of the game
     */
    public MonsterPanel(ThemeFactory themeFactory) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(themeFactory.getBackgroundColor());

        monsterNameLabel = themeFactory.createLabel("", AppConfig.BIG_FONT_BOLD, AppConfig.COLOR_BLUE);
        add(monsterNameLabel);

        monsterImageButton = themeFactory.createButton("");
        monsterImageButton.setBackground(themeFactory.getBackgroundColor());
        monsterImageButton.setPreferredSize(AppConfig.MONSTER_SIZE);
        monsterImageButton.setContentAreaFilled(false);
        monsterImageButton.setOpaque(true);

        monsterImageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                monsterImageButton.setBackground(AppConfig.COLOR_RED);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                monsterImageButton.setBackground(themeFactory.getBackgroundColor());
            }
        });

        add(monsterImageButton);

        add(Box.createRigidArea(new Dimension(0, 10)));

        monsterHealthLabel = themeFactory.createLabel("", AppConfig.BIG_FONT_BOLD, AppConfig.COLOR_RED);
        add(monsterHealthLabel);
    }

    /**
     * Will set the monster information
     * @param monsterName the monster name to show
     * @param monsterIconPath the path to the monster image icon to show
     * @param monsterHealth the monster health to show
     */
    public void setMonsterInformation(String monsterName, String monsterIconPath, int monsterHealth) {
        setMonsterNameText(monsterName);
        setMonsterImageIcon(new ImageIcon(monsterIconPath));
        setMonsterHealthText(monsterHealth);
    }

    /**
     * Will change the monster name label to passed String
     * @param monsterName the monster name to show
     */
    public void setMonsterNameText(String monsterName) {
        monsterNameLabel.setText(monsterName);
    }

    /**
     * Will change the monster icon label to passed ImageIcon
     * @param monsterImage the image to show
     */
    private void setMonsterImageIcon(ImageIcon monsterImage) {
        ImageIcon monsterImageIcon = new ImageIcon(
                monsterImage
                        .getImage()
                        .getScaledInstance(AppConfig.MONSTER_SIZE.width, AppConfig.MONSTER_SIZE.height, Image.SCALE_SMOOTH)
        );
        monsterImageButton.setIcon(monsterImageIcon);
    }

    /**
     * Will change the monster health label to passed int
     * @param monsterHealth the monster health to show
     */
    public void setMonsterHealthText(int monsterHealth) {
        monsterHealthLabel.setText("Health: " + monsterHealth);
    }

    /**
     * Add action listener to the monster image button.
     * @param listener the listener to add
     */
    public void addMonsterClickListener(ActionListener listener) {
        monsterImageButton.addActionListener(listener);
    }
}
