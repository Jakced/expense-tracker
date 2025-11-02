package com.dt181g.project.models.monsters;

import java.util.ArrayList;
import java.util.List;

/**
 * The Monster resource pool stores a single instance of each Monster type for reuse.
 * If a requested Monster type is not in the pool, a new instance is created via the MonsterFactory
 * and added to the pool.
 *
 * @author Jakob Cederblad
 */
public class MonsterPool {
    /** The Monster pool containing all stored instances of created monsters currently in the game */
    private final List<Monster> monsterPool;

    /**
     * Construct a new monster pool to get monsters from.
     */
    public MonsterPool() {
        monsterPool = new ArrayList<>();
    }

    /**
     * Will create a new monster from the monster factory
     * @param monsterClass the monster class to create
     * @return the created monster
     */
    private Monster createMonster(Class<? extends Monster> monsterClass) {
        Monster monster = MonsterFactory.createMonster(monsterClass);
        monsterPool.add(monster);
        return monster;
    }

    /**
     * Will acquire a monster from the pool in regard to the current dungeon level
     * If requested monster doesn't exist create a new one from the MonsterFactory
     * Once acquired it will be removed from the pool
     * @param dungeonLevel the current dungeon level
     * @return the acquired monster
     */
    public Monster aquireMonster(int dungeonLevel) {
        Class<? extends Monster> monsterClass = MonsterFactory.findMonsterClass(dungeonLevel);
        for (int i = 0; i < monsterPool.size(); i++) {
            Monster monster = monsterPool.get(i);
            if (monster.getClass().equals(monsterClass)) {
                return monsterPool.remove(i);
            }
        }
        return createMonster(monsterClass);
    }

    /**
     * Will return passed monster to the monster pool
     * @param monster the monster to return
     */
    public void returnMonsterToPool(Monster monster) {
        monsterPool.add(monster);
    }
}
