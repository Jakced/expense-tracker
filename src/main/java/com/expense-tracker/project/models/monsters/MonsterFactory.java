package com.dt181g.project.models.monsters;

import java.util.List;
import java.util.Random;

/**
 * Factory to create a randomized Monster based on dungeon level tier
 * Contains a list of monster for each dungeon level tier.
 * Responsible for getting a monster class for a specific dungeon tier and creating new instances of monster
 * for a specified monster class.
 *
 * @author Jakob Cederblad
 */
public class MonsterFactory {
    private static final Random random = new Random();

    /** Dungeon level tier 0, will contain tier after final boss is defeated for player to keep playing */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_0 = List.of(
            MonsterRat.class,
            MonsterSpider.class,
            MonsterFrog.class,
            MonsterSnail.class,
            MonsterMutatedRat.class,
            MonsterAnimatedPumpkin.class,
            MonsterCarnivorousVine.class,
            MonsterSlime.class,
            MonsterIceGolem.class,
            MonsterPumpkinQueen.class,
            MonsterMushroomCreeper.class,
            MonsterMushroomScorpion.class,
            MonsterMushroomPlant.class,
            MonsterMushroomCap.class,
            MonsterMushroomElder.class,
            MonsterMushroomKing.class
    );

    /** Dungeon level tier 1 monsters */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_1 = List.of(
            MonsterRat.class,
            MonsterSpider.class
    );

    /** Dungeon level tier 2 monsters */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_2 = List.of(
            MonsterSpider.class,
            MonsterFrog.class,
            MonsterSnail.class
    );

    /** Dungeon level tier 3 monsters */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_3 = List.of(
            MonsterMutatedRat.class
    );

    /** Dungeon level tier 4 monsters */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_4 = List.of(
            MonsterAnimatedPumpkin.class,
            MonsterCarnivorousVine.class,
            MonsterSlime.class
    );

    /** Dungeon level tier 5 monsters */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_5 = List.of(
            MonsterCarnivorousVine.class,
            MonsterAnimatedPumpkin.class,
            MonsterIceGolem.class
    );

    /** Dungeon level tier 6 monsters */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_6 = List.of(
            MonsterPumpkinQueen.class
    );

    /** Dungeon level tier 7 monsters */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_7 = List.of(
            MonsterMushroomCap.class,
            MonsterMushroomCreeper.class
    );

    /** Dungeon level tier 8 monsters */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_8 = List.of(
            MonsterMushroomCap.class,
            MonsterMushroomCreeper.class,
            MonsterMushroomPlant.class,
            MonsterMushroomScorpion.class
    );

    /** Dungeon level tier 9 monsters */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_9 = List.of(
            MonsterMushroomPlant.class,
            MonsterMushroomScorpion.class,
            MonsterMushroomElder.class
    );

    /** Dungeon level tier 10 monsters */
    private static final List<Class<? extends Monster>> DUNGEON_LEVEL_TIER_10 = List.of(
            MonsterMushroomKing.class
    );

    /**
     * Private constructor to prevent instantiation of the factory
     */
    private MonsterFactory() {
        throw new IllegalStateException("Factory class");
    }

    /**
     * Will get a randomized monster class for dungeon level
     * @param dungeonLevel the dungeon level
     * @return the monster class
     */
    public static Class<? extends Monster> findMonsterClass(int dungeonLevel) {
        dungeonLevel = Math.max(0, dungeonLevel);
        List<Class<? extends Monster>> monsterTier = switch (dungeonLevel) {
            case 1 -> DUNGEON_LEVEL_TIER_1;
            case 2 -> DUNGEON_LEVEL_TIER_2;
            case 3 -> DUNGEON_LEVEL_TIER_3;
            case 4 -> DUNGEON_LEVEL_TIER_4;
            case 5 -> DUNGEON_LEVEL_TIER_5;
            case 6 -> DUNGEON_LEVEL_TIER_6;
            case 7 -> DUNGEON_LEVEL_TIER_7;
            case 8 -> DUNGEON_LEVEL_TIER_8;
            case 9 -> DUNGEON_LEVEL_TIER_9;
            case 10 -> DUNGEON_LEVEL_TIER_10;
            default -> DUNGEON_LEVEL_TIER_0;
        };
        return monsterTier.get(random.nextInt(monsterTier.size()));
    }

    /**
     * Will create a new instance of monster based on passed Monster class
     * @param monsterClass the monster class to create
     * @return a randomized monster for dungeon level tier
     */
    public static Monster createMonster(Class<? extends Monster> monsterClass) {
        try {
            return monsterClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create monster: " + monsterClass.getSimpleName(), e);
        }
    }
}