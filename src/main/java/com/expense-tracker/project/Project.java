package com.dt181g.project;

import com.dt181g.project.controllers.MonsterController;
import com.dt181g.project.controllers.PlayerController;
import com.dt181g.project.controllers.ThemeController;
import com.dt181g.project.controllers.UpgradeController;
import com.dt181g.project.models.MonsterModel;
import com.dt181g.project.models.PlayerModel;
import com.dt181g.project.models.StatisticsModel;
import com.dt181g.project.models.monsters.MonsterPool;
import com.dt181g.project.models.upgrades.AttackUpgradeModel;
import com.dt181g.project.models.upgrades.KnightUpgradeModel;
import com.dt181g.project.models.upgrades.PeonUpgradeModel;
import com.dt181g.project.models.upgrades.SorcererUpgradeModel;
import com.dt181g.project.models.upgrades.UpgradeModel;
import com.dt181g.project.support.AppConfig;
import com.dt181g.project.views.MainFrame;
import com.dt181g.project.views.themes.ThemeFactory;

import javax.swing.SwingUtilities;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The main starting point for Project Assignment.
 *
 * @author Jakob Cederblad
 */
public final class Project {
    private Project() { // Utility classes should not have a public or default constructor
        throw new IllegalStateException("Utility class");
    }

    /**
     * Main entry point for the game.
     * Will instantiate all the controller, models and views used in the game.
     * The Swing components are initialized on the EDT.
     * Will show the MainFrame and show a popup dialog with a tutorial if how the game is played.
     * @param args command arguments.
     */
    public static void main(final String... args) {
        MonsterModel monsterModel = new MonsterModel();
        StatisticsModel statisticsModel = new StatisticsModel();

        ThemeController themeController = new ThemeController(AppConfig.DISPLAY_THEME);
        ThemeFactory themeFactory = themeController.getFactory();

        PlayerModel playerModel = new PlayerModel(
                AppConfig.PLAYER_BASE_DAMAGE,
                AppConfig.PLAYER_START_GOLD
        );

        Map<Class<? extends UpgradeModel>, UpgradeModel> upgradeModelsMap = new LinkedHashMap<>();
        upgradeModelsMap.put(AttackUpgradeModel.class, new AttackUpgradeModel());
        upgradeModelsMap.put(PeonUpgradeModel.class, new PeonUpgradeModel(playerModel));
        upgradeModelsMap.put(KnightUpgradeModel.class, new KnightUpgradeModel(monsterModel));
        upgradeModelsMap.put(SorcererUpgradeModel.class, new SorcererUpgradeModel(playerModel, monsterModel));

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(themeFactory);

            for (UpgradeModel upgradeModel : upgradeModelsMap.values()) {
                new UpgradeController(
                        playerModel,
                        upgradeModel,
                        mainFrame.getUpgradePanel()
                );
            }

            new PlayerController(
                    playerModel,
                    monsterModel,
                    statisticsModel,
                    (AttackUpgradeModel) upgradeModelsMap.get(AttackUpgradeModel.class),
                    mainFrame
            );

            new MonsterController(
                    playerModel,
                    monsterModel,
                    new MonsterPool(),
                    statisticsModel,
                    mainFrame
            );

            mainFrame.setVisible(true);
            mainFrame.showTutorialDialog();
        });
    }
}
