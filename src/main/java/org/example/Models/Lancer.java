package org.example.Models;

public class Lancer extends Warrior{

    public static final int ATTACK = 6;
    public static final int INITIAL_HEALTH  = 50;

    private int health;

    private int attack;

    public Lancer(){
        super(INITIAL_HEALTH, ATTACK);
        this.health=INITIAL_HEALTH;
        this.attack=ATTACK;
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
    public String toString() {
        return "Lancer{" +
                "health=" + health +
                ", attack=" + attack +
                '}';
    }


}
