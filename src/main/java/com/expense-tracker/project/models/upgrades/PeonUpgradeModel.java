package com.dt181g.project.models.upgrades;

import com.dt181g.project.models.PlayerModel;
import com.dt181g.project.support.AppConfig;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Concrete class for upgrading peon quantity for player.
 *
 * @author Jakob Cederblad
 */
public class PeonUpgradeModel extends UpgradeModel {
    private final PlayerModel playerModel;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(UpgradeType.getMaxUpgrade(UpgradeType.PEON));

    /**
     * Construct a peon upgrade
     * @param playerModel the player model to gain money for
     */
    public PeonUpgradeModel(PlayerModel playerModel) {
        super(UpgradeType.PEON);
        this.playerModel = playerModel;
    }

    /**
     * Will trigger when upgrading a peon
     * Starts a new thread which will gain money in a configured interval
     */
    @Override
    void triggerUpgradeEffect() {
        executorService.scheduleAtFixedRate(() -> playerModel.gainGold(
                AppConfig.UPGRADE_PEON_GOLD_GAIN),
                AppConfig.UPGRADE_PEON_GOLD_INTERVAL,
                AppConfig.UPGRADE_PEON_GOLD_INTERVAL,
                TimeUnit.SECONDS);
    }
}
