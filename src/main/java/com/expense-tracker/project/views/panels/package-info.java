/**
 * Package containing all the panels in an MVC pattern used in the game.
 *
 * <p>Panels used:</p>
 * <ul>
 *     <li>
 *     MonsterPanel: To show information about the current monster encounter such as a button with an image of
 *     the monster to click and perform an action to hurt the monster, the current monster health and the name of
 *     the current monster encountered.
 *     </li>
 *     <li>
 *     PlayerPanel: To show information about the player such as current gold, dungeon level and
 *     dungeon level progression
 *     </li>
 *     <li>
 *      UpgradePanel: To show and create the available upgrades and add them to a grid layout to present them to the
 *      player. The UpgradePrefabPanel is used as a blueprint when creating a new upgrade view for an upgrade panel.
 *     </li>
 *     <li>
 *     UpgradePrefabPanel: Prefab to show information about the upgrade and event to upgrade it by clicking a buy
 *     button, will show if the player can afford the item and the current level of it along with a tooltip and an
 *     image representing the upgrade.
 *     </li>
 *     <li>
 *     KillStatisticsPanel: To show information about all the monster killed during the gameplay.
 *     </li>
 *     <li>
 *     PlayerStatisticsPanel: To show information about the player statistics gathered during the game play such
 *     as total damage, gold gained and total monsters killed.
 *     </li>
 * </ul>
 *
 * @author Jakob Cederblad
 */
package com.dt181g.project.views.panels;