package org.example.models;

import org.example.models.weapons.IWeapon;

public class Defender extends Warrior implements HasDefense{
    public static final int INITIAL_ATTACK = 3;
    public static final int INITIAL_HEALTH  = 60;
    public static final int INITIAL_DEFENCE = 2;
    private int health;
    private int attack;
    private int defence;
    private int newInitialHealth;

    public Defender() {
        this(INITIAL_HEALTH, INITIAL_ATTACK);
    }

    public Defender(int health, int attack) {
        super(health, attack);
        this.defence= INITIAL_DEFENCE;
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

    @Override
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
