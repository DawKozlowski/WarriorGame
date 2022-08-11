package org.example.models;

import org.example.models.weapons.IWeapon;
import org.example.models.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Warrior implements IWarrior{
    public static final int ATTACK = 5;
    public static final int INITIAL_HEALTH  = 50;
    private int health;
    private int newInitialHealth;
    private int attack;
    private IWarrior nextBehind;

    private final List<Weapon> weapons = new ArrayList<>();

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    protected Warrior(int health, int attack) {
        this.health=health;
        this.attack=attack;
        this.newInitialHealth=health;
    }

    @Override
    public void setHealth(int health) {
        this.health=Math.min(health, newInitialHealth);
    }

    @Override
    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public void hit(IWarrior opponent) {
        opponent.receiveHit(new SimpleDamage(getAttack(), this));
        processCommand(new HealCommand(), this);
    }

    @Override
    public void receiveHit(IDamage damageDealer) {
        setHealth(getHealth()- damageDealer.hitPoints());
    }

    @Override
    public Optional<IWarrior> getBehind() {
        return Optional.ofNullable(nextBehind);
    }

    @Override
    public void setBehind(IWarrior behind) {
        this.nextBehind=behind;
    }

    public IWarrior equipWeapon(IWeapon weapon) {
        newInitialHealth+=weapon.getHealth();
        setHealth(getHealth()+weapon.getHealth());
        setAttack(getAttack()+ weapon.getAttack());
        return this;
    }



    @Override
    public String toString() {
        return "Warrior{" +
                "health=" + health +
                ", attack=" + attack +
                '}';
    }
}
