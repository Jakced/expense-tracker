package com.dt181g.project.models.listeners;

/**
 * Listener interface to handle monster health change events
 *
 * @author Jakob Cederblad
 */
public interface MonsterHealthChangeListener {
    /**
     * Called when monster health is changed
     * @param newHealth the new health of the monster
     */
    void onMonsterHealthChange(int newHealth);
}
