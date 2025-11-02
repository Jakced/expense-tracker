package com.dt181g.project.models.upgrades;

import com.dt181g.project.models.MonsterModel;
import com.dt181g.project.support.AppConfig;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Concrete class for upgrading knight quantity for player.
 *
 * @author Jakob Cederblad
 */
public class KnightUpgradeModel extends UpgradeModel {
    private final MonsterModel monsterModel;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(UpgradeType.getMaxUpgrade(UpgradeType.KNIGHT));

    /**
     * Construct a knight upgrade
     * @param monsterModel the monster model to make damage on
     */
    public KnightUpgradeModel(MonsterModel monsterModel) {
        super(UpgradeType.KNIGHT);
        this.monsterModel = monsterModel;
    }

    /**
     * Will trigger when upgrading a knight
     * Starts a new thread which will damage monster in a configured interval
     */
    @Override
    void triggerUpgradeEffect() {
        executorService.scheduleAtFixedRate(() -> monsterModel.damageMonster(
                AppConfig.UPGRADE_KNIGHT_DAMAGE_POWER),
                AppConfig.UPGRADE_KNIGHT_DAMAGE_INTERVAL,
                AppConfig.UPGRADE_KNIGHT_DAMAGE_INTERVAL,
                TimeUnit.SECONDS);
    }
}
