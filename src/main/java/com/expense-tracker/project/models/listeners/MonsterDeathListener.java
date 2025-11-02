package com.dt181g.project.models.listeners;

import com.dt181g.project.models.monsters.Monster;

/**
 * Listener interface to handle monster death events
 *
 * @author Jakob Cederblad
 */
public interface MonsterDeathListener {
    /**
     * Called when a monster death is triggered
     * @param monster the monster that died
     */
    void onMonsterDeath(Monster monster);
}
