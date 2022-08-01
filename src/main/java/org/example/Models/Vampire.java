package org.example.Models;

public class Vampire extends Warrior{
    public static final int ATTACK = 4;

    public static final int INITIAL_HEALTH  = 40;

    public static final int VAMPIRISM = 50;

    private int health;
    private int attack;
    private int vampirism;

    public Vampire(){
        super(INITIAL_HEALTH, ATTACK);
        this.vampirism=VAMPIRISM;
        this.health=INITIAL_HEALTH;
        this.attack=ATTACK;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void hit(IWarrior opponent) {
        super.hit(opponent);
        health += opponent.getAttack()*VAMPIRISM/100;
        if(health > 40) {
            health=40;
        }
    }

    @Override
    public String toString() {
        return "Vampire{" +
                "health=" + health +
                ", attack=" + attack +
                ", vampirism=" + vampirism +
                '}';
    }
}