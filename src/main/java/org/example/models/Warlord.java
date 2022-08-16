package org.example.models;

import org.example.models.Defender;
import org.example.models.strategies.Strategy;
import org.example.models.strategies.WarlordStartegy;
import org.example.models.weapons.IWeapon;

import java.util.Collection;

public class Warlord extends Defender {

    public static final int ATTACK = 4;
    public static final int INITIAL_HEALTH  = 100;
    public static final int DEFENCE = 2;

    private int health;
    private int attack;
    private int defence;
    private int newInitialHealth;

    public Warlord(){
        super(INITIAL_HEALTH, ATTACK);
        this.defence=DEFENCE;
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
    public int getDefense() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public String toString() {
        return "Warlord{" +
                "health=" + health +
                ", attack=" + attack +
                ", defence=" + defence +
                '}';
    }
}
