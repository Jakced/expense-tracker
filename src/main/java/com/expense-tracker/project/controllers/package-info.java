/**
 * Package containing all the controllers in an MVC pattern used in the game.
 *
 * <p>The package contain controllers with following responsibilities:</p>
 * <ul>
 *     <li>
 *         The monster controller controls interaction with the monster model and the view components to refresh health,
 *         get and return monsters from the monster pool, informing view on reaching maximum dungeon level and adding
 *         statistics regarding monster kills.
 *     </li>
 *     <li>
 *         The player controller controls the interaction between the player model and the view. Handles updates in
 *         gold changes for the model and the view. Handles the interaction when player performs action on a monster
 *         and retrieves the current player damage from model and damages the monster. Also updates the player
 *         statistic model with damage done, changes in kill list.
 *     </li>
 *     <li>
 *         The theme controller handles theme management for the game and contains reference to the theme model.
 *         Provides the factory of the current theme used in the game
 *     </li>
 *     <li>
 *         The upgrade controller handles the interaction between upgrade model and view. it handles purchases of
 *         upgrades and makes sure visuals are updated to reflect changes in upgrades in the view.
 *     </li>
 * </ul>
 * @author Jakob Cederblad
 */
package com.dt181g.project.controllers;