package com.dt181g.project.models;

import com.dt181g.project.models.listeners.DamageDoneListener;
import com.dt181g.project.models.listeners.MonsterKillListChangeListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Statistics model containing information and logic about statistics during the game
 * Will store how much damage player has done, the kill count for each monster and how much gold the player has
 * gained during the playthrough. Also provide methods to sort the kill count list by most kills and calculate
 * the total amount of monsters killed.
 *
 * @author Jakob Cederblad
 */
public class StatisticsModel {
    private final Map<String, Integer> monsterKills = new HashMap<>();
    private final List<MonsterKillListChangeListener> monsterKillListChanges = new ArrayList<>();
    private final List<DamageDoneListener> damageDoneChanges = new ArrayList<>();
    private final AtomicInteger totalPlayerGoldGained = new AtomicInteger(0);
    private final AtomicInteger totalDamageDone = new AtomicInteger(0);

    /**
     * Construct statistics model
     */
    public StatisticsModel() { }

    /**
     * Add a kill of a monster to the statistics
     * @param monsterName monster that has been killed
     */
    public void addKill(String monsterName) {
        monsterKills.put(monsterName, monsterKills.getOrDefault(monsterName, 0) + 1);
        notifyMonsterKillListChangeListener();
    }

    /**
     * Add to the total amount of damage done statistics
     * @param damage the damage amount to add
     */
    public void addDamageDone(int damage) {
        totalDamageDone.addAndGet(damage);
        notifyDamageDoneChangeListener();
    }

    /**
     * Get the total amount of damage done during the game
     * @return the total amount of damage done
     */
    public int getDamageDone() {
        return totalDamageDone.get();
    }

    /**
     * Add to the total amount of gold gained statistics
     * @param gold the gold added
     */
    public void addGoldGained(int gold) {
        totalPlayerGoldGained.addAndGet(gold);
    }

    /**
     * Get the total amount of gold gained during the game
     * @return the total amount of gold gain
     */
    public int getGoldGain() {
        return totalPlayerGoldGained.get();
    }

    /**
     * Will calculate and get the total amount of monster kills
     * @return the total amount of monster kills
     */
    public int getTotalMonsterKills() {
        return monsterKills.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    /**
     * Sort a monster kill list in descending order from most kill to the least and the alphabetical
     * @return the sorted list
     */
    public LinkedHashMap<String, Integer> getSortedKillList() {
        return monsterKills.entrySet()
                .stream()
                .sorted(
                        Comparator.comparing(Map.Entry<String, Integer>::getValue, Comparator.reverseOrder())
                                .thenComparing(Map.Entry::getKey)
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    /**
     * Add listener to changes in the kill list
     * @param listener the listener to add
     */
    public void addKillListChangeListener(MonsterKillListChangeListener listener) {
        monsterKillListChanges.add(listener);
    }

    /**
     * Notify listeners about changes in the kill list
     */
    public void notifyMonsterKillListChangeListener() {
        for (MonsterKillListChangeListener listener : monsterKillListChanges) {
            listener.onKillListChange(getSortedKillList());
        }
    }

    /**
     * Add listener to changes in damage done
     * @param listener the listener to add
     */
    public void addDamageDoneChangeListener(DamageDoneListener listener) {
        damageDoneChanges.add(listener);
    }

    /**
     * Notify listeners about changes in damage done
     */
    public void notifyDamageDoneChangeListener() {
        for (DamageDoneListener listener : damageDoneChanges) {
            listener.onDamageDone();
        }
    }
}
