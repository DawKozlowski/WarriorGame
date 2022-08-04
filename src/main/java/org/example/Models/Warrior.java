package org.example.Models;

public class Warrior implements Unit, Cloneable, IWarrior{
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
        this.health = health;
        if(health>INITIAL_HEALTH) {
            this.health = INITIAL_HEALTH;
        }
    }

    public int getAttack() {
        return attack;
    }


    public int getHealth() {
        return health;
    }


    @Override
    public void hit(IWarrior opponent) {
        opponent.receiveHit(new SimpleDamage(getAttack(), this));
    }

    @Override
    public void receiveHit(IDamage damageDealer) {
        setHealth(getHealth()- damageDealer.hitPoints());
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
