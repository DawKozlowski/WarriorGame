package org.example.models;

import org.example.models.weapons.IWeapon;

public class Lancer extends Warrior{

    public static final int INITIAL_ATTACK = 6;
    public static final int INITIAL_HEALTH  = 50;
    private int health;
    private int attack;
    private int newInitialHealth;

    public Lancer(){
        super(INITIAL_HEALTH, INITIAL_ATTACK);
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
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int bombAttack) {
        this.attack = bombAttack;
    }

    @Override
    public void hit(IWarrior opponent) {
        int healthBefore = opponent.getHealth();
        super.hit(opponent);
        int damageDealtToTheFirst =  healthBefore - opponent.getHealth();
        if(opponent.getBehind().isPresent()){
            IWarrior nextBehind = opponent.getBehind().get();
            int damageToSecond = damageDealtToTheFirst * 50 / 100;
            nextBehind.receiveHit(new SimpleDamage(damageToSecond, this));
        }
    }

    public IWarrior equipWeapon(IWeapon weapon) {
        newInitialHealth+=weapon.getHealth();
        super.equipWeapon(weapon);
        return this;
    }

    @Override
    public String toString() {
        return "Lancer{" +
                "health=" + health +
                ", attack=" + attack +
                '}';
    }
}
