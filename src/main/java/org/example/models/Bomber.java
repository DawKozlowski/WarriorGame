package org.example.models;

import org.example.models.weapons.IWeapon;

public class Bomber extends Warrior{

    public static final int INITIAL_BOMBATTACK = 100;
    public static final int INITIAL_HEALTH  = 50;
    private int health;
    private int bombAttack;
    private int newInitialHealth;

    public Bomber() {
        super(INITIAL_HEALTH, INITIAL_BOMBATTACK);
        this.health=INITIAL_HEALTH;
        this.bombAttack = INITIAL_BOMBATTACK;
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
    public int getBombAttack() {
        return bombAttack;
    }

    @Override
    public void setBombAttack(int bombAttack) {
        this.bombAttack = bombAttack;
    }

    @Override
    public void hit(IWarrior opponent) {
        opponent.processCommand(new BombCommand(bombAttack), this);
        setHealth(getHealth()- bombAttack);
    }

    @Override
    public IWarrior equipWeapon(IWeapon weapon) {
        newInitialHealth+=weapon.getHealth();
        setHealth(getHealth()+weapon.getHealth());
        setBombAttack(getBombAttack()+weapon.getBombAttack());
        return this;
    }

    @Override
    public String toString() {
        return "Bomber{" +
                "health=" + health +
                ", bomb attack=" + bombAttack +
                '}';
    }
}
