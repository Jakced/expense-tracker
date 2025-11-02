package com.dt181g.project.controllers;

import com.dt181g.project.models.MonsterModel;
import com.dt181g.project.models.PlayerModel;
import com.dt181g.project.models.StatisticsModel;
import com.dt181g.project.models.listeners.AttackUpgradeListener;
import com.dt181g.project.models.listeners.DamageDoneListener;
import com.dt181g.project.models.listeners.MonsterDeathListener;
import com.dt181g.project.models.listeners.MonsterKillListChangeListener;
import com.dt181g.project.models.listeners.PlayerGoldChangeListener;
import com.dt181g.project.models.monsters.Monster;
import com.dt181g.project.models.upgrades.AttackUpgradeModel;
import com.dt181g.project.views.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

/**
 * Handles interaction between the player model and the view components.
 * Responsible for performing action when enemy shall receive damage from auto attack, will
 * get the current player attack damage from the player model and call the damage function on the monster.
 * Also, responsible for updating statistics to the models and view.
 *
 * @author Jakob Cederblad
 */
public class PlayerController implements ActionListener, MonsterDeathListener, PlayerGoldChangeListener, MonsterKillListChangeListener, AttackUpgradeListener, DamageDoneListener{
    private final PlayerModel playerModel;
    private final MonsterModel monsterModel;
    private final AttackUpgradeModel attackUpgradeModel;
    private final StatisticsModel statisticsModel;
    private final MainFrame mainFrame;

    /**
     * Constructor to create a player controller to interact between player model and view components
     * @param playerModel        the player model containing player information
     * @param monsterModel       the monster model containing information about the monster
     * @param statisticsModel    the statistics model containing information about statistics gathered during the game
     * @param attackUpgradeModel the attack upgrade model containing information about bonus attack damage
     * @param mainFrame          the main view of the application
     */
    public PlayerController(PlayerModel playerModel, MonsterModel monsterModel, StatisticsModel statisticsModel, AttackUpgradeModel attackUpgradeModel, MainFrame mainFrame) {
        this.playerModel = playerModel;
        this.monsterModel = monsterModel;
        this.statisticsModel = statisticsModel;
        this.attackUpgradeModel = attackUpgradeModel;
        this.mainFrame = mainFrame;
        mainFrame.getMonsterPanel().addMonsterClickListener(this);
        monsterModel.addDeathListener(this);
        playerModel.addGoldChangeListener(this);
        statisticsModel.addKillListChangeListener(this);
        statisticsModel.addDamageDoneChangeListener(this);
        attackUpgradeModel.addAttackUpgradeListeners(this);
        playerModel.notifyGoldChangeListener(0);
    }

    /**
     * Will get the current player attack damage
     * @return the player attack damage
     */
    private int getAttackDamage() {
        return playerModel.getBaseDamage() + attackUpgradeModel.getUpgradeValue();
    }

    /**
     * Triggered when a monster dies
     * @param monster the monster that died
     */
    @Override
    public void onMonsterDeath(Monster monster) {
        playerModel.gainGold(monster.getGoldGain());
        playerModel.gainKill();
    }

    /**
     * Triggered when monster is clicked performing the action for monster to take damage
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int playerDamage = getAttackDamage();
        monsterModel.damageMonster(playerDamage);
        statisticsModel.addDamageDone(playerDamage);
    }

    /**
     * Triggered when player gold has changed, will refresh the UI to reflect changes
     * @param currentGold the new gold amount of player
     */
    @Override
    public void onPlayerGoldChange(int currentGold, int addedGold) {
        mainFrame.getPlayerPanel().setPlayerGoldTextLabel(currentGold);
        statisticsModel.addGoldGained(addedGold);
        mainFrame.getPlayerStatisticsPanel().setGoldGainValueText(statisticsModel.getGoldGain());
    }

    /**
     * Triggered when a monster value has been changed on the monster kill list and refresh the UI for the changes
     * @param sortedMonsterKillList the list to fresh the UI with
     */
    @Override
    public void onKillListChange(LinkedHashMap<String, Integer> sortedMonsterKillList) {
        mainFrame.getKillStatisticsPanel().refreshKillListText(sortedMonsterKillList);
        mainFrame.getPlayerStatisticsPanel().setMonsterKillValueText(
                statisticsModel.getTotalMonsterKills()
        );
    }

    /**
     * Triggered when the attack upgrade has been bought from the upgrade shop, will refresh the attack damage label
     * with the new attack value
     */
    @Override
    public void onAttackUpgrade() {
        mainFrame.getPlayerPanel().setAttackDamageLabel(getAttackDamage());
    }

    /**
     * Triggered when player deal any kind of damage to the monster, will refresh the statistics list with the new
     * total damage done.
     */
    @Override
    public void onDamageDone() {
        mainFrame.getPlayerStatisticsPanel().setDamageDoneValueText(
                statisticsModel.getDamageDone()
        );
    }
}
