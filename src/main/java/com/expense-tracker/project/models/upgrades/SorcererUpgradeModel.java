package com.dt181g.project.models.upgrades;

import com.dt181g.project.models.MonsterModel;
import com.dt181g.project.models.PlayerModel;
import com.dt181g.project.support.AppConfig;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Concrete class for upgrading sorcerer quantity for player.
 *
 * @author Jakob Cederblad
 */
public class SorcererUpgradeModel extends UpgradeModel {
    private final MonsterModel monsterModel;
    private final PlayerModel playerModel;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(UpgradeType.getMaxUpgrade(UpgradeType.SORCERER));

    /**
     * Construct a sorcerer upgrade
     * @param playerModel the player model to gain money for
     * @param monsterModel the monster model to make damage on
     */
    public SorcererUpgradeModel(PlayerModel playerModel, MonsterModel monsterModel) {
        super(UpgradeType.SORCERER);
        this.playerModel = playerModel;
        this.monsterModel = monsterModel;
    }

    /**
     * Will trigger when upgrading a sorcerer
     * Starts a new thread which will gain money and damage monsters in a configured interval
     */
    @Override
    void triggerUpgradeEffect() {
        executorService.scheduleAtFixedRate(this::triggerSorcererAction,
                AppConfig.UPGRADE_SORCERER_INTERVAL,
                AppConfig.UPGRADE_SORCERER_INTERVAL,
                TimeUnit.SECONDS);
    }

    /**
     * Will damage monster and gain gold for the player
     */
    private void triggerSorcererAction() {
        monsterModel.damageMonster(AppConfig.UPGRADE_SORCERER_DAMAGE_POWER);
        playerModel.gainGold(AppConfig.UPGRADE_SORCERER_GOLD_GAIN);

    }
}
