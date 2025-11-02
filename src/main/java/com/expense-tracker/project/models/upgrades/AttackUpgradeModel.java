package com.dt181g.project.models.upgrades;

import com.dt181g.project.models.listeners.AttackUpgradeListener;
import com.dt181g.project.support.AppConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete class for upgrading weapon damage for player.
 *
 * @author Jakob Cederblad
 */
public class AttackUpgradeModel extends UpgradeModel {
    private int bonusDamage = 0;
    private final List<AttackUpgradeListener> attackUpgradeListeners = new ArrayList<>();

    /**
     * Construct a weapon attack upgrade
     */
    public AttackUpgradeModel() {
        super(UpgradeType.ATTACK);
    }

    /**
     * Will trigger an update to player bonus damage
     * upgrade level multiplied by weapon upgrade modifier set in configuration
     */
    @Override
    void triggerUpgradeEffect() {
        bonusDamage += AppConfig.UPGRADE_WEAPON_BONUS_DAMAGE;
        notifyAttackUpgradeListeners();
    }

    /**
     * Will get the current upgrade value.
     * @return the current upgrade value
     */
    @Override
    public int getUpgradeValue() {
        return bonusDamage;
    }

    /**
     * Add listener to when attack upgrade is triggered
     * @param listener the listener to add
     */
    public void addAttackUpgradeListeners(AttackUpgradeListener listener) {
        attackUpgradeListeners.add(listener);
    }

    /**
     * Notifies listeners to when attack upgrade is triggered
     */
    private void notifyAttackUpgradeListeners() {
        for (AttackUpgradeListener listener : attackUpgradeListeners) {
            listener.onAttackUpgrade();
        }
    }
}
