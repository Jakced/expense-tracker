package com.dt181g.project.support;

import com.dt181g.project.views.themes.DisplayTheme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * The AppConfig class provides a collection of configuration constants
 *
 * @author Jakob Cederblad
 */
public class AppConfig {
    /** Utility class, not to be initialized. */
    private AppConfig() {
        throw new UnsupportedOperationException("Utility class");
    }

    /** Experience or kill thresholds required at each dungeon level to advance to next dungeon level */
    public static final List<Integer> DUNGEON_THRESHOLDS = List.of(
            0, //Dungeon Level  0 -> 1 (Start)
            10, //Dungeon Level 1 -> 2
            20, //Dungeon Level 2 -> 3
            1, //Dungeon Level  3 -> 4 Rat Boss
            30, //Dungeon Level  4 -> 5
            40,  //Dungeon Level  5 -> 6
            1,  //Dungeon Level  6 -> 7 Pumpking boss
            50,  //Dungeon Level  7 -> 8
            75, //Dungeon Level  8 -> 9
            100, //Dungeon Level  9 -> 10
            1  //Dungeon Level  10 Mushroom Boss (Final Boss)
    );

    /** Which theme to use in the application in choose between DARK and LIGHT */
    public static DisplayTheme DISPLAY_THEME = DisplayTheme.DARK;

    /** Title of application */
    public static final String APPLICATION_TITLE = "Monster Clicker Dungeon Madness";
    /** Application icon image path */
    public static final String APPLICATION_ICON_PATH = "src/main/resources/application-icon.png";

    /** Player dungeon start level */
    public static final int PLAYER_DUNGEON_START_LEVEL = 1;
    /** Player start gold */
    public static final int PLAYER_START_GOLD = 0;
    /** Player start damage */
    public static final int PLAYER_BASE_DAMAGE = 2;

    /** How much damage each upgrade will power the weapon with */
    public static final int UPGRADE_WEAPON_BONUS_DAMAGE = 6;

    /** Default window size for the application (pixels) */
    public static final Dimension APPLICATION_SIZE = new Dimension(650, 710);
    /** Minimum window size for the application (pixels) */
    public static final Dimension APPLICATION_MINIMUM_SIZE = new Dimension(650, 710);
    /** Minimum window size for the application (pixels) */
    public static final Dimension APPLICATION_DIALOG_SIZE = new Dimension(400, 300);
    /** Size of the image to render the monster */
    public static final Dimension MONSTER_SIZE = new Dimension(192, 192);
    /** Icon upgrade size */
    public static final Dimension UPGRADE_ICON_SIZE = new Dimension(32, 32);
    /** Dimension of statistics panels */
    public static final Dimension STATISTICS_PANEL_DIMENSION = new Dimension(170, 0);

    /** Default plain font to use */
    public static final Font DEFAULT_FONT_PLAIN = new Font("Calibri", Font.PLAIN, 12);
    /** Default plain font to use */
    public static final Font DEFAULT_FONT_BOLD = new Font("Calibri", Font.BOLD, 12);
    /** Big plain font to use for important titles */
    public static final Font BIG_FONT_BOLD = new Font("Calibri", Font.BOLD, 20);

    /** Red color to use in application */
    public static final Color COLOR_RED = new Color(220, 80, 80);
    /** Magenta color to use in application */
    public static final Color COLOR_MAGENTA = new Color(220, 90, 160);
    /** Blue color to use in application */
    public static final Color COLOR_BLUE = new Color(90, 140, 230);
    /** Yellow color to use in application */
    public static final Color COLOR_YELLOW = new Color(240, 220, 130);
    /** Green color to use in application */
    public static final Color COLOR_GREEN = new Color(60, 179, 113);

    /** Peon gold generation amount */
    public static final int UPGRADE_PEON_GOLD_GAIN = 200;
    /** Peon action interval */
    public static final int UPGRADE_PEON_GOLD_INTERVAL = 4;
    /** Knight damage amount */
    public static final int UPGRADE_KNIGHT_DAMAGE_POWER = 100;
    /** Knight action interval */
    public static final int UPGRADE_KNIGHT_DAMAGE_INTERVAL = 3;
    /** Sorcerer gold generation amount */
    public static final int UPGRADE_SORCERER_GOLD_GAIN = 120;
    /** Sorcerer damage amount */
    public static final int UPGRADE_SORCERER_DAMAGE_POWER = 70;
    /** Sorcerer action interval */
    public static final int UPGRADE_SORCERER_INTERVAL = 3;

    /** Title of monster kill list */
    public static final String KILL_LIST_STATISTICS_TITLE = "Monster Kills";
    /** Title of player statistics list */
    public static final String PLAYER_STATISTICS_TITLE = "Player Statistic";

    /** Costs for each upgrade of attack damage, maximum level is decided by the entries in the list */
    public static final List<Integer> UPGRADE_ATTACK_COST_LIST = new ArrayList<>(List.of(
            10, 20, 50, 80, 100, 150, 200, 250, 300,
            350, 400, 500, 600, 700, 800, 900, 1000, 1150,
            1300, 1500, 1750, 2000, 2250, 2500, 3000, 4000, 5000
    ));
    /** Costs for each upgrade of peon, maximum level is decided by the entries in the list */
    public static final List<Integer> UPGRADE_PEON_COST_LIST = new ArrayList<>(List.of(100, 200, 500, 1000, 2000, 3000, 4000, 5000, 7500, 10000));
    /** Costs for each upgrade of knight, maximum level is decided by the entries in the list */
    public static final List<Integer> UPGRADE_KNIGHT_COST_LIST = new ArrayList<>(List.of(500, 1000, 1500, 2000, 4000, 5000, 6000, 8000, 10000, 15000));
    /** Costs for each upgrade of attack damage, maximum level is decided by the entries in the list */
    public static final List<Integer> UPGRADE_SORCERER_COST_LIST = new ArrayList<>(List.of(1000, 2000, 3000, 5000, 10000, 15000, 20000, 30000, 50000, 100000));
}