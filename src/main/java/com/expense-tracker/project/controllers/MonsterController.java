package com.dt181g.project.controllers;

import com.dt181g.project.models.PlayerModel;
import com.dt181g.project.models.StatisticsModel;
import com.dt181g.project.models.listeners.MaxDungeonLevelListener;
import com.dt181g.project.models.listeners.MonsterDeathListener;
import com.dt181g.project.models.MonsterModel;
import com.dt181g.project.models.listeners.MonsterHealthChangeListener;
import com.dt181g.project.models.listeners.PlayerKillCountChangeListener;
import com.dt181g.project.models.monsters.Monster;
import com.dt181g.project.models.monsters.MonsterPool;
import com.dt181g.project.views.MainFrame;

/**
 * Handles interaction between the monster model and the view components.
 * Will handle the need for new monsters encounters in regard to the current dungeon level and events regarding
 * monster death such as sending statistics about the monster killed to the statistics model and refreshing the
 * view with the new monster health when damage has been received.
 *
 * @author Jakob Cederblad
 */
public class MonsterController implements MonsterDeathListener, MaxDungeonLevelListener, PlayerKillCountChangeListener, MonsterHealthChangeListener {
    private final PlayerModel playerModel;
    private final MonsterModel monsterModel;
    private final MonsterPool monsterPool;
    private final StatisticsModel statisticsModel;
    private final MainFrame mainFrame;

    /**
     * Constructs a controller with passed monster model and mainframe
     * @param playerModel the model holding player data
     * @param monsterModel the model holding monster data
     * @param monsterPool the monster resource pool to retrieve monster from
     * @param statisticsModel the model holding game statistics
     * @param mainFrame the main application frame
     */
    public MonsterController(final PlayerModel playerModel, MonsterModel monsterModel, MonsterPool monsterPool, StatisticsModel statisticsModel, MainFrame mainFrame) {
        this.playerModel = playerModel;
        this.monsterModel = monsterModel;
        this.monsterPool = monsterPool;
        this.statisticsModel = statisticsModel;
        this.mainFrame = mainFrame;

        monsterModel.addDeathListener(this);
        playerModel.addMaxDungeonLevelListener(this);
        playerModel.addKillChangeListener(this);
        monsterModel.addHealthChangeListener(this);
        getNewMonster();
    }

    /**
     * Will get a new monster from the resource pool, assign it to the monster model and pass the information of the new monster to the view
     */
    public void getNewMonster() {
        Monster monster = monsterPool.aquireMonster(playerModel.getCurrentDungeonLevel());
        monsterModel.setNewMonster(monster);
        mainFrame.getMonsterPanel().setMonsterInformation(
                monster.getShowcaseName(),
                monster.getImagePath(),
                monster.getHealth()
        );
    }

    /**
     * Will be triggered at a monster death
     * Add monster kill to statistics model and return the monster to the resource pool
     * then gets a new monster from the pool
     * @param monster the monster that died
     */
    @Override
    public void onMonsterDeath(Monster monster) {
        statisticsModel.addKill(monster.getBaseName());
        monsterPool.returnMonsterToPool(monster);
        getNewMonster();
    }

    /**
     * Called when max dungeon level is reached, will show win message
     */
    @Override
    public void onMaxDungeonLevel() {
        mainFrame.showDialogMessage(
                "Congratulations!",
                """
                        You have beaten the final boss and the game!
                        
                        Don't worry, you can keep battling all the monsters endlessly on the Endless Floor if you like!
                        """
        );
    }

    /**
     * Triggered when player kills a monster, will refresh the dungeon progress
     * @param dungeonLevel the current dungeon level
     * @param killCount the current kill count for this dungeon level
     * @param nextDungeonLevel kills to next level
     */
    @Override
    public void onPlayerKillChange(int dungeonLevel, int killCount, int nextDungeonLevel) {
        mainFrame.getPlayerPanel().setLevelProgressBar(dungeonLevel, killCount, nextDungeonLevel);
    }

    /**
     * Triggered when monster health is changed, will update the UI to reflect the change
     * @param newHealth the new health of the monster
     */
    @Override
    public void onMonsterHealthChange(int newHealth) {
        mainFrame.getMonsterPanel().setMonsterHealthText(newHealth);
    }
}

