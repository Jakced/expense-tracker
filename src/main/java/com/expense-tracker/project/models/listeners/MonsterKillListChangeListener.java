package com.dt181g.project.models.listeners;

import java.util.LinkedHashMap;

/**
 * Listener interface tto handle monster kill list events
 *
 * @author Jakob Cederblad
 */
public interface MonsterKillListChangeListener {
    /**
     * Called when  monster kill list has been changed
     * @param sorterMonsterKillList new monster kill list
     */
    void onKillListChange(LinkedHashMap<String, Integer> sorterMonsterKillList);
}
