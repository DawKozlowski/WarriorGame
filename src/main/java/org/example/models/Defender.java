package org.example.models;

public class Defender extends Warrior implements HasDefense{
    public static final int ATTACK = 3;
    public static final int INITIAL_HEALTH  = 60;
    public static final int DEFENCE = 2;

    private int health;
    private int attack;
    private int defence;
    public Defender(){
        super(INITIAL_HEALTH, ATTACK);
        this.defence=DEFENCE;
        this.health=INITIAL_HEALTH;
        this.attack=ATTACK;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = Math.min(health, INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return defence;
    }

    @Override
    public void receiveHit(IDamage damageDealer) {
        setHealth(getHealth() - (damageDealer.hitPoints()-getDefense()));
    }

    @Override
    public String toString() {
        return "Defender{" +
                "health=" + health +
                ", attack=" + attack +
                ", defence=" + defence +
                '}';
    }
}
