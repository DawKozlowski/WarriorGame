package org.example.models;

import org.example.models.weapons.IWeapon;
import org.example.models.weapons.Weapon;

public class Healer extends Warrior {
    public static final int ATTACK = 0;

    public static final int INITIAL_HEALTH  = 60;

    public static final int INITIAL_HEALPOWER  = 2;

    private int health;

    private int attack;

    private int healPower;
    private int newInitialHealth;

    public Healer(){
        super(INITIAL_HEALTH, ATTACK);
        this.health=INITIAL_HEALTH;
        this.attack=ATTACK;
        this.healPower=INITIAL_HEALPOWER;
        this.newInitialHealth=INITIAL_HEALTH;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealPower() {
        return healPower;
    }

    public void setHealPower(int healPower) {
        this.healPower = healPower;
    }

    public void heal(IWarrior warrior) {
        warrior.setHealth(warrior.getHealth() + healPower);
    }

    @Override
    public void hit(IWarrior opponent) {}

    @Override
    public void processCommand(ICommand command, IWarrior sender) {
        if (command instanceof HealCommand) {
            heal(sender);
        }
        super.processCommand(command, sender);
    }

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
