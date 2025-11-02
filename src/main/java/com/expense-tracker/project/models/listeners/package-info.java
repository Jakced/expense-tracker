/**
 * Contains functional interfaces to handle events in the game.
 *
 * <p>Interfaces included in package:</p>
 * <ul>
 *     <li>AttackUpgradeListener: to be called whenever an attack upgrade has been made.</li>
 *     <li>DamageDoneListener: to be called whenever the player deals damage to the monster.</li>
 *     <li>MaxDungeonLevelListener: to be called whenever player reaches the maximum dungeon level.</li>
 *     <li>MonsterDeathListener: to be called whenever a monster has been killed (reached zero or less health).</li>
 *     <li>MonsterHealthChangeListener: to be called whenever a monster health has been changed.</li>
 *     <li>MonsterKillListChangeListener: to be called whenever a new update to the kill list has been made.</li>
 *     <li>PlayerGoldChangeListener: to be called whenever player gold is changed.</li>
 *     <li>PlayerKillCountChangeListener: to be called whenever the player kill count is changed.</li>

 * </ul>
 *
 * @author Jakob Cederblad
 */
package com.dt181g.project.models.listeners;