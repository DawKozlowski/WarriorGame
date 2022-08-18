package org.example.models;

import org.example.models.weapons.IWeapon;

public class Bomber extends Warrior{

    public static final int ATTACK = 100;
    public static final int INITIAL_HEALTH  = 50;
    private int health;
    private int attack;
    private int newInitialHealth;

    public Bomber(){
        super(INITIAL_HEALTH, ATTACK);
        this.health=INITIAL_HEALTH;
        this.attack=ATTACK;
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
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void hit(IWarrior opponent) {
        opponent.processCommand(new BombCommand(ATTACK), this);
        setHealth(getHealth()-ATTACK);
    }

    public IWarrior equipWeapon(IWeapon weapon) {
        newInitialHealth+=weapon.getHealth();
        setHealth(getHealth()+weapon.getHealth());
        return this;
    }

    @Override
    public String toString() {
        return "Bomber{" +
                "health=" + health +
                ", attack=" + attack +
                '}';
    }
}
