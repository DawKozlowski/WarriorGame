package org.example.Models;

public class Warrior implements Unit, Cloneable, IWarrior {
    public static final int ATTACK = 5;
    public static final int INITIAL_HEALTH  = 50;
    private int health;
    private int attack;

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    protected Warrior(int health, int attack) {
        this.health=health;
        this.attack=attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }


    public int getHealth() {
        return health;
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        health -=damage;
    }

    @Override
    public Warrior clone() {
        try {
            return (Warrior) super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return null;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "health=" + health +
                ", attack=" + attack +
                '}';
    }
}
