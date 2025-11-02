package com.dt181g.project.models.monsters;

/**
 * abstract class for which all monsters in the game derives from.
 * contains references to the monster base and showcase name, current monster health, image path and
 * how much gold the monster with yield.
 * Includes functionality to activate monster once spawned to reset health and create potential enchantment.
 * Also contains methods for monster to receive damage and check if monster is currently dead.
 *
 * @author Jakob Cederblad
 */
public abstract class Monster {
    /** Base name of monster (without enchantment) */
    private final String monsterBaseName;
    /** Name of current monster to show (with enchantment) */
    protected String monsterShowcaseName;
    /** Health of monster */
    protected int monsterHealth;
    /** Image to render */
    private final String monsterImagePath;
    /** How much gold killing monster will yield */
    protected int monsterGoldYield;

    /**
     * Constructor to create a new monster
     * @param baseMonsterName the monster base name
     * @param monsterImagePath the image path to the monster image to show
     */
    public Monster(String baseMonsterName, String monsterImagePath) {
        this.monsterBaseName = baseMonsterName;
        this.monsterImagePath = monsterImagePath;
    }

    /**
     * Will activate a monster, will randomize a new enchantment and set the
     * showcase name, gold gain and health thereafter
     *
     */
    public final void activateMonster() {
        setEnchant();
        setShowcaseName();
        setHealth();
        setGoldYield();
    }

    /**
     * Hook for setting the enchantment for monster
     */
    void setEnchant() { }

    /**
     * Set showcase name for monster
     */
    abstract void setShowcaseName();

    /**
     * Set the current health for the monster
     */
    abstract void setHealth();

    /**
     * Set the gold yield for how much money monster will provide when killed
     */
    abstract void setGoldYield();

    /**
     * Will get the monsters showcase name (with enchantment name)
     * @return the name of the monster
     */
    public String getShowcaseName() {
        return monsterShowcaseName;
    }

    /**
     * Will get the monsters base name (without enchantment name)
     * @return the name of the monster
     */
    public String getBaseName() {
        return monsterBaseName;
    }

    /**
     * Will get monsters current health
     * @return the current monster health
     */
    public synchronized int getHealth() {
        return monsterHealth;
    }

    /**
     * Will get the ImageIcon to render
     * @return the ImageIcon
     */
    public String getImagePath() {
        return monsterImagePath;
    }

    /**
     * Check if monster is dead
     * @return true if monster is dead
     */
    public boolean IsDead() {
        return monsterHealth < 1;
    }

    /**
     * Will get how much gold is awarded to kill monster
     * @return the gold yield
     */
    public int getGoldGain() {
        return monsterGoldYield;
    }

    /**
     * Will remove damage from monster if monster is not dead yet
     * @param amount the amount of damage to remove from monsters health
     */
    public synchronized void takeDamage(int amount) {
        if (IsDead())
            return;
        monsterHealth = Math.max(monsterHealth - amount, 0);
    }
}
