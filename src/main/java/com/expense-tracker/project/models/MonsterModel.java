package com.dt181g.project.models;

import com.dt181g.project.models.listeners.MonsterDeathListener;
import com.dt181g.project.models.listeners.MonsterHealthChangeListener;
import com.dt181g.project.models.monsters.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Business logic for handling monster encounter interaction and information in the game
 * Responsible for storing a reference to the current monster encounter in the game, allow setting the monster and
 * activating it to refreshing the monster health. Also contain a method to damage the monster and notify listeners
 * when monster dies or health is altered.
 *
 * @author Jakob Cederblad
 */
public class MonsterModel {
    private Monster currentMonster;
    private final List<MonsterDeathListener> monsterDeathListeners = new ArrayList<>();
    private final List<MonsterHealthChangeListener> monsterHealthChangeListeners = new ArrayList<>();

    /**
     * Construct a new monster model with configured start level
     */
    public MonsterModel() { }

    /**
     * Will set a new monster to the model to interact with
     * @param monster the monster to set
     */
    public void setNewMonster(Monster monster) {
        monster.activateMonster();
        currentMonster = monster;
    }

    /**
     * Will perform an action damaging the monster
     * @param damage the damage to the monster
     */
    public synchronized void damageMonster(int damage) {
        if (currentMonster.IsDead())
            return;

        currentMonster.takeDamage(damage);
        notifyHealthChangeListener();

        if (currentMonster.IsDead()) {
            notifyDeathListener(currentMonster);
        }
    }

    /**
     * Add listener to when monsters die
     * @param listener the listener to add
     */
    public void addDeathListener(MonsterDeathListener listener) {
        monsterDeathListeners.add(listener);
    }

    /**
     * Add listener to when monsters health changes
     * @param listener the listener to add
     */
    public void addHealthChangeListener(MonsterHealthChangeListener listener) {
        monsterHealthChangeListeners.add(listener);
    }

    /**
     * Notifies listeners to when monsters dies
     * @param deadMonster the dead monster
     */
    private void notifyDeathListener(Monster deadMonster) {
        for (MonsterDeathListener listener : monsterDeathListeners) {
            listener.onMonsterDeath(deadMonster);
        }
    }

    /**
     * Notifies listeners to when monsters health are changed
     */
    private void notifyHealthChangeListener() {
        for (MonsterHealthChangeListener listener : monsterHealthChangeListeners) {
            listener.onMonsterHealthChange(currentMonster.getHealth());
        }
    }
}
