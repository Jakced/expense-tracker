package com.dt181g.project.models;

import com.dt181g.project.models.listeners.MaxDungeonLevelListener;
import com.dt181g.project.models.listeners.PlayerGoldChangeListener;
import com.dt181g.project.models.listeners.PlayerKillCountChangeListener;
import com.dt181g.project.support.AppConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Business logic for handling player information in the game such as player base damage, current player gold, dungeon
 * level, kills on current level. It is also responsible for adding and informing listeners to changes in gold,
 * kills and reaching the maximum dungeon level.
 *
 * @author Jakob Cederblad
 */
public class PlayerModel {
    private final int playerBaseDamage;
    private final AtomicInteger playerGold;
    private final List<PlayerGoldChangeListener> playerGoldChangeListeners = new ArrayList<>();
    private final List<PlayerKillCountChangeListener> playerKillChangeListeners = new ArrayList<>();
    private final List<MaxDungeonLevelListener> maxDungeonLevelListeners = new ArrayList<>();

    private int currentDungeonLevel;
    private int killsOnCurrentLevel;

    private final DungeonLevelTable dungeonLevelTable;

    /**
     * Construct a new player model with passed base damage and start gold
     * @param playerDamage the player base damage
     * @param playerGold the player start gold
     */
    public PlayerModel(int playerDamage, int playerGold) {
        this.playerGold = new AtomicInteger();
        this.playerBaseDamage = playerDamage;
        this.dungeonLevelTable = new DungeonLevelTable(AppConfig.DUNGEON_THRESHOLDS);
        gainGold(playerGold);
        setDungeonLevel(AppConfig.PLAYER_DUNGEON_START_LEVEL);
    }

    /**
     * Sets the dungeon level to passed level unless maximum dungeon level has been reached
     * @param level the dungeon level to set to
     */
    private void setDungeonLevel(int level) {
        currentDungeonLevel = Math.max(1, Math.min(level, dungeonLevelTable.getMaxDungeonLevel()));
        killsOnCurrentLevel = 0;
    }

    /**
     * Get current dungeon level
     * @return the current dungeon level
     */
    public int getCurrentDungeonLevel() {
        return currentDungeonLevel;
    }

    /**
     * Goto next dungeon level and notify listeners if maximum dungeon level has been reached
     */
    private void nextDungeonLevel() {
        setDungeonLevel(currentDungeonLevel + 1);
        if (dungeonLevelTable.isMaxDungeonLevel(currentDungeonLevel)) {
            notifyMaxDungeonLevelListener();
        }
    }

    /**
     * Gain a monster kill and notify kill listeners
     * Go to next dungeon level if threshold has been reached.
     */
    public void gainKill() {
        killsOnCurrentLevel++;
        if (!dungeonLevelTable.isMaxDungeonLevel(currentDungeonLevel)){
            if (dungeonLevelTable.moveNextLevel(killsOnCurrentLevel, currentDungeonLevel)) {
                nextDungeonLevel();
            }
        }
        notifyKillChangeListener();
    }

    /**
     * Will add gold to the player
     * @param amount the amount of player gold to gain
     */
    public void gainGold(int amount) {
        if (amount < 0)
            return;
        playerGold.addAndGet(amount);
        notifyGoldChangeListener(amount);
    }

    /**
     * Will return the player gold
     * @return the current player gold
     */
    public int getGold() {
        return playerGold.get();
    }

    /**
     * Will return player base damage
     * @return the player base damage
     */
    public int getBaseDamage() {
        return playerBaseDamage;
    }

    /**
     * Will try to buy an upgrade and remove the gold from the player
     * @param cost the cost of the upgrade
     * @return if upgrade was bought
     */
    public boolean buyUpgrade(int cost) {
        int currentGold = playerGold.get();
        if (currentGold < cost) {
            return false;
        }
        playerGold.addAndGet(-cost);
        notifyGoldChangeListener(0);
        return true;
    }

    /**
     * Add listener which will be called when player gold changes
     * @param listener the listener to add
     */
    public void addGoldChangeListener(PlayerGoldChangeListener listener) {
        playerGoldChangeListeners.add(listener);
    }

    /**
     * Add listener which will be called when a player kills a monster
     * @param listener the listener to add
     */
    public void addKillChangeListener(PlayerKillCountChangeListener listener) {
        playerKillChangeListeners.add(listener);
    }

    /**
     * Add listener which will be called when maximum dungeon level is reached
     * @param listener the listener to add
     */
    public void addMaxDungeonLevelListener(MaxDungeonLevelListener listener) {
        maxDungeonLevelListeners.add(listener);
    }


    /**
     * Notifies listeners to when player gold changes
     * @param goldGained amount of gold gained
     */
    public void notifyGoldChangeListener(int goldGained) {
        for (PlayerGoldChangeListener listener : playerGoldChangeListeners) {
            listener.onPlayerGoldChange(playerGold.get(), goldGained);
        }
    }

    /**
     * Notifies listeners to when a monster is killed
     */
    public void notifyKillChangeListener() {
        for (PlayerKillCountChangeListener listener : playerKillChangeListeners) {
            listener.onPlayerKillChange(
                    currentDungeonLevel,
                    killsOnCurrentLevel,
                    dungeonLevelTable.getLevelUpThreshold(currentDungeonLevel)
            );
        }
    }

    /**
     * Notify listeners that maximum dungeon level has been reached
     */
    private void notifyMaxDungeonLevelListener() {
        for (MaxDungeonLevelListener listener : maxDungeonLevelListeners) {
            listener.onMaxDungeonLevel();
        }
    }
}
