package com.dt181g.project.views;

import com.dt181g.project.support.AppConfig;
import com.dt181g.project.views.panels.KillStatisticsPanel;
import com.dt181g.project.views.panels.MonsterPanel;
import com.dt181g.project.views.panels.PlayerPanel;
import com.dt181g.project.views.panels.PlayerStatisticsPanel;
import com.dt181g.project.views.panels.UpgradePanel;
import com.dt181g.project.views.themes.ThemeFactory;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Mainframe for the application to create and manage all panels used in the game.
 * Also provides methods to create dialogs with a title and a message and a default tutorial dialog to show. It also
 * creates a file menu with a button to show the tutorial and exit the game.
 *
 * @author Jakob Cederblad
 */
public class MainFrame extends JFrame {
    /** Monster panel containing information and UI the monster */
    private final MonsterPanel monsterPanel;
    /** Player panel containing information and UI about the player*/
    private final PlayerPanel playerPanel;
    /** Upgrade panel containing information and UI to upgrade upgrades */
    private final UpgradePanel upgradePanel;
    /** Kill statistics panel containing information about how many of each monster has been killed */
    private final KillStatisticsPanel killStatisticPanel;
    /** Player statistics panel containing information about player statistics during the game */
    private final PlayerStatisticsPanel playerStatisticsPanel;
    /** Visual theme factory chosen */
    private final ThemeFactory themeFactory;

    /**
     * Construct a new main frame for the game
     * @param themeFactory the theme to use in the game
     */
    public MainFrame(ThemeFactory themeFactory) {
        this.themeFactory = themeFactory;

        initUI();
        createMenu();

        monsterPanel = new MonsterPanel(themeFactory);
        add(monsterPanel, BorderLayout.CENTER);

        playerPanel = new PlayerPanel(themeFactory);
        add(playerPanel, BorderLayout.NORTH);

        upgradePanel = new UpgradePanel(themeFactory);
        add(upgradePanel, BorderLayout.SOUTH);

        killStatisticPanel = new KillStatisticsPanel(themeFactory);
        add(killStatisticPanel, BorderLayout.WEST);

        playerStatisticsPanel = new PlayerStatisticsPanel(themeFactory);
        add(playerStatisticsPanel, BorderLayout.EAST);
    }

    /**
     * Will get the monster panel
     * @return the monster panel
     */
    public MonsterPanel getMonsterPanel() {
        return monsterPanel;
    }

    /**
     * Will get the player panel
     * @return the player panel
     */
    public PlayerPanel getPlayerPanel() {
        return playerPanel;
    }

    /**
     * Will get the upgrade panel
     * @return the upgrade panel
     */
    public UpgradePanel getUpgradePanel() {
        return upgradePanel;
    }

    /**
     * Will get the kill statistics panel
     * @return the kill statistics panel
     */
    public KillStatisticsPanel getKillStatisticsPanel() {
        return killStatisticPanel;
    }

    /**
     * Will get the player statistics panel
     * @return the player statistics panel
     */
    public PlayerStatisticsPanel getPlayerStatisticsPanel() {
        return playerStatisticsPanel;
    }

    /**
     * Initialize the UI with configured settings, will set title, layout, minimum and start size,
     * default close operation, center game for user, set game icon and then show the frame
     */
    private void initUI() {
        setTitle(AppConfig.APPLICATION_TITLE);
        setLayout(new BorderLayout());
        setSize(AppConfig.APPLICATION_SIZE);
        setMinimumSize(AppConfig.APPLICATION_MINIMUM_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage(AppConfig.APPLICATION_ICON_PATH);
        setIconImage(icon);
        setVisible(true);
    }

    /**
     * Create JMenu bar for application
     * Will include a tutorial item to show how to play the game
     * and an exit item to exit the game.
     */
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem tutorialItem = new JMenuItem("How to play?");
        tutorialItem.addActionListener(event -> showTutorialDialog());
        fileMenu.add(tutorialItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit Game");
        exitItem.addActionListener(event -> System.exit(0));
        fileMenu.add(exitItem);
    }

    /**
     * Will show a non-modal dialog information window with title and message and a close window button
     * @param title the title to show
     * @param message the message to show
     */
    public void showDialogMessage(String title, String message) {
        String dialogCloseText = "Close";

        JDialog dialog = new JDialog(this, title, false);

        JTextArea messageTextArea = themeFactory.createTextArea();
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);

        JScrollPane tableScroller = new JScrollPane(messageTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        dialog.add(tableScroller, BorderLayout.CENTER);

        messageTextArea.setText(message);
        messageTextArea.setEditable(false);

        JButton closeButton = themeFactory.createButton(dialogCloseText);
        closeButton.addActionListener(e -> dialog.dispose());
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setSize(AppConfig.APPLICATION_DIALOG_SIZE);
        dialog.setLocationRelativeTo(null);

        dialog.setVisible(true);
    }

    /**
     * Show a dialog message describing how to play the game
     */
    public void showTutorialDialog() {
        showDialogMessage(
                "How to Play",
                "Welcome to " + AppConfig.APPLICATION_TITLE + "!\n\n"
                        + "Your goal is to click on monsters to defeat them, eventually taking down the final boss and then go beyond, to infinity and maybe even madness!\n\n"
                        + "To play, simply click the monster in the center of the screen. Each click deals damage, and the monster will die when its health reaches zero.\n\n"
                        + "You earn gold for defeating enemies, which can be used to upgrade your arsenal and hire helpful NPCs along your journey. "
                        + "Hover over each upgrade icon to see its effects. If you have enough gold, click the 'Buy' button to purchase the upgrade. "
                        + "Your current gold and the progress of the current dungeon level are displayed at the top of the game."
        );
    }
}
