package org.example.models;


public class Warlord extends Defender {

    public static final int INITIAL_ATTACK = 4;
    public static final int INITIAL_HEALTH  = 100;
    public static final int INITIAL_DEFENCE = 2;
    private int health;
    private int attack;
    private int defence;
    private int newInitialHealth;

    public Warlord(){
        super(INITIAL_HEALTH, INITIAL_ATTACK);
        this.defence= INITIAL_DEFENCE;
        this.health=INITIAL_HEALTH;
        this.attack= INITIAL_ATTACK;
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
    public int getBombAttack() {
        return attack;
    }

    @Override
    public void setBombAttack(int bombAttack) {
        this.attack = bombAttack;
    }

    @Override
    public int getDefense() {
        return defence;
    }

    @Override
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
