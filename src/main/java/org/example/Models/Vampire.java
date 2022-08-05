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
        this.health = Math.min(health, INITIAL_HEALTH);
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
        int opponentHealthBefore=opponent.getHealth();
        super.hit(opponent);
        int opponentHealthAfter=opponent.getHealth();
        int damage=opponentHealthBefore - opponentHealthAfter;
        setHealth(getHealth() + damage*VAMPIRISM/100);
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
