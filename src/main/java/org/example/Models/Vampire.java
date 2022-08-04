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

    @Override
    public void setHealth(int health) {
        this.health = health;
        if(health>INITIAL_HEALTH) {
            this.health = INITIAL_HEALTH;
        }
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void hit(IWarrior opponent) {
        super.hit(opponent);
        setHealth(getHealth() + opponent.getAttack()*VAMPIRISM/100);
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
