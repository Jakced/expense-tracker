package com.dt181g.project.models.listeners;

/**
 * Listener interface to player kill change events
 *
 * @author Jakob Cederblad
 */
public interface PlayerKillCountChangeListener {
    /**
     * Called when the players kill count is changed
     * @param dungeonLevel the current dungeon level
     * @param killCount the current kill count for this dungeon level
     * @param nextDungeonLevel kills to next level
     */
    void onPlayerKillChange(int dungeonLevel, int killCount, int nextDungeonLevel);
}
