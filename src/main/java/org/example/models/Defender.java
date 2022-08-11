package org.example.models;

import org.example.models.weapons.IWeapon;
import org.example.models.weapons.Weapon;

public class Defender extends Warrior implements HasDefense{
    public static final int ATTACK = 3;
    public static final int INITIAL_HEALTH  = 60;
    public static final int DEFENCE = 2;

    private int health;
    private int attack;
    private int defence;
    private int newInitialHealth;

    public Defender(){
        super(INITIAL_HEALTH, ATTACK);
        this.defence=DEFENCE;
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

    @Override
    public int getDefense() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public void receiveHit(IDamage damageDealer) {
        setHealth(getHealth() - (damageDealer.hitPoints()-getDefense()));
    }

    public IWarrior equipWeapon(IWeapon weapon) {
        newInitialHealth+= weapon.getHealth();
        setDefence(getDefense()+ weapon.getDefense());
        super.equipWeapon(weapon);

        return this;
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
