package org.example.models;

import org.example.models.weapons.IWeapon;
import org.example.models.weapons.Weapon;

public class Knight extends Warrior{

    public static final int ATTACK = 7;
    public static final int INITIAL_HEALTH  = 50;

    private int health;

    private int attack;
    private int newInitialHealth;

    public Knight(){
         super(INITIAL_HEALTH, ATTACK);
         this.health=INITIAL_HEALTH;
         this.attack=ATTACK;
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
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public IWarrior equipWeapon(IWeapon weapon) {
        newInitialHealth+=weapon.getHealth();
        super.equipWeapon(weapon);
        return this;
    }

    @Override
    public String toString() {
        return "Knight{" +
                "health=" + health +
                ", attack=" + attack +
                '}';
    }
}
