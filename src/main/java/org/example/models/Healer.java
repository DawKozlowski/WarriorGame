package org.example.models;

import org.example.models.weapons.IWeapon;

public class Healer extends Warrior {
    public static final int INITIAL_ATTACK = 0;
    public static final int INITIAL_HEALTH  = 60;
    public static final int INITIAL_HEALPOWER  = 2;
    private int health;
    private int attack;
    private int healPower;
    private int newInitialHealth;

    public Healer() {
        super(INITIAL_HEALTH, INITIAL_ATTACK);
        this.health=INITIAL_HEALTH;
        this.attack= INITIAL_ATTACK;
        this.healPower=INITIAL_HEALPOWER;
        this.newInitialHealth=INITIAL_HEALTH;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = Math.min(health, newInitialHealth);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int bombAttack) {
        this.attack = bombAttack;
    }

    public int getHealPower() {
        return healPower;
    }

    public void setHealPower(int healPower) {
        this.healPower = healPower;
    }

    public void heal(IWarrior warrior) {
        if(warrior.isAlive()) {
            warrior.setHealth(warrior.getHealth() + getHealPower());
        }
    }

    @Override
    public void hit(IWarrior opponent) {
        // Do nothing because Healer doesn't attack
    }

    @Override
    public void processCommand(ICommand command, IWarrior sender) {
        if (command instanceof HealCommand) {
            heal(sender);
        }
        super.processCommand(command, sender);
    }

    @Override
    public IWarrior equipWeapon(IWeapon weapon) {
        newInitialHealth+=weapon.getHealth();
        super.equipWeapon(weapon);
        setHealPower(getHealPower()+weapon.getHealPower());
        return this;
    }

    @Override
    public String toString() {
        return "Healer{" +
                "health=" + health +
                ", attack=" + attack +
                ", healPower=" + healPower +
                '}';
    }
}
