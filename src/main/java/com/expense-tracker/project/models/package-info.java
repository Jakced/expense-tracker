/**
 * Package containing all the top-level models with business logic and data in an MVC pattern used in the game.
 *
 * <p>The package contain models with following responsibilities:</p>
 * <ul>
 *     <li>
 *         The DungeonLevelTable responsible for storing information about dungeon level thresholds. Responsible for
 *         determining when a player can move to the next dungeon level and when maximum level has been reached.
 *     </li>
 *     <li>
 *         The MonsterModel which handles and stores business logic for monster encounters during the playthrough,
 *         which include the current monster and dealing damage to the monster, setting new monsters when killed and
 *         notifying listener about monster health and death.
 *     </li>
 *     <li>
 *         The PlayerModel which handles and stores business logic and data for handling player damage, gold, current
 *         dungeon level and kills on current dungeon floor.
 *     </li>
 *     <li>
 *         The StatisticsModel which stores statistics regarding total player damage, gold gained and monsters killed
 *         during the playthrough.
 *     </li>
 *     <li>
 *         The ThemeModel which stores and returns the current theme used in the game.
 *     </li>
 * </ul>
 * @author Jakob Cederblad
 */
package com.dt181g.project.models;