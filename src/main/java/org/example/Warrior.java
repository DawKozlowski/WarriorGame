package org.example;

public class Warrior {
    private static final int ATTACK = 5;
    static final int INITIAL_HEALTH  = 50;
    private int health =INITIAL_HEALTH;
    public boolean isAlive() {
        return health >0;
    }

    public int getAttack() {
        return ATTACK;
    }

    public int getHealth() {
        return health;
    }

    public void hit(Warrior opponent){
        opponent.health -= getAttack();
    }
}
