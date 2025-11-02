package com.dt181g.project.models.listeners;

/**
 * Listener interface to player gold change events
 *
 * @author Jakob Cederblad
 */
public interface PlayerGoldChangeListener {
    /**
     * Called when the players gold is changed
     * @param currentGold the new gold amount of player
     * @param addedGold the gold amount added
     */
    void onPlayerGoldChange(int currentGold, int addedGold);
}
