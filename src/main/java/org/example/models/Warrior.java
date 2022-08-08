package org.example.models;

public class Warrior implements IWarrior{
    public static final int ATTACK = 5;
    public static final int INITIAL_HEALTH  = 50;
    private int health;
    private int attack;
    private IWarrior nextBehind;

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    protected Warrior(int health, int attack) {
        this.health=health;
        this.attack=attack;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, INITIAL_HEALTH);
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public IWarrior getBehind() {
        return nextBehind;
    }

    @Override
    public void setBehind(IWarrior behind) {
        this.nextBehind=behind;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "health=" + health +
                ", attack=" + attack +
                '}';
    }
}
