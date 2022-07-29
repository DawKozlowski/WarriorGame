package org.example;

public class Warrior implements Unit, Cloneable /*CanFight*/{
    static final int ATTACK = 5;
    static final int INITIAL_HEALTH  = 50;
    private int health;
    private int attack;

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    protected Warrior(int health, int attack) {
        this.health=health;
        this.attack=attack;
    }

    public boolean isAlive() {
        return health >0;
    }

    public int getAttack() {
        return attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }


    public void hit(Warrior opponent){
        if(opponent instanceof Defender){
            if(getAttack()<=Defender.DEFENCE) opponent.health -= 0;
            if(getAttack()>Defender.DEFENCE) opponent.health -= getAttack()-Defender.DEFENCE;
        }else {
            opponent.health -= getAttack();
        }
    }


    @Override
    public Warrior clone() {
        try {
            return (Warrior) super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return null;
    }


}
