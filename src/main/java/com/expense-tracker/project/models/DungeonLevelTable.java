package com.dt181g.project.models;

import java.util.List;

/**
 * Handles logic for dungeon levels in the game
 * Provide methods to get next level threshold, check maximum dungeon level, check if player can move to next
 * dungeon level based on kills on the current floor.
 *
 * @author Jakob Cederblad
 */
public class DungeonLevelTable {
    private final List<Integer> dungeonThresholds;
    /**
     * Constructs a new table which hold dungeon thresholds for all levels
     * @param dungeonThresholds list of dungeon threshold for when to move to next dungeon
     */
    public DungeonLevelTable(List<Integer> dungeonThresholds) {
        this.dungeonThresholds = dungeonThresholds;
    }

    /**
     * Get dungeon threshold which determine when a player can move to next
     * @param level the current dungeon level
     * @return next dungeon level threshold
     */
    public int getLevelUpThreshold(int level) {
        if (isMaxDungeonLevel(level)) {
            return 0;
        }
        return dungeonThresholds.get(Math.min(level, dungeonThresholds.size() - 1));
    }

    /**
     * Will return if passed dungeon level is the maximum dungeon level or not
     * @param level the current dungeon level
     * @return if player is at max dungeon level
     */
    public boolean isMaxDungeonLevel (int level) {
        return level >= getMaxDungeonLevel();
    }

    /**
     * Will return which is the maximum dungeon level
     * @return the maximum dungeon level
     */
    public int getMaxDungeonLevel() {
        return dungeonThresholds.size();
    }

    /**
     * Will check if player can move to next level
     * @param currentKills the current kill amount
     * @param level the current dungeon level
     * @return if player can move to next level
     */
    public boolean moveNextLevel(int currentKills, int level) {
        return !isMaxDungeonLevel(level) && currentKills >= getLevelUpThreshold(level);
    }
}
