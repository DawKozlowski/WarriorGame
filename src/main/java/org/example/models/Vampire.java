package org.example.models;

import org.example.models.weapons.IWeapon;

public class Vampire extends Warrior{
    public static final int INITIAL_ATTACK = 4;
    public static final int INITIAL_HEALTH  = 40;
    public static final int INITIAL_VAMPIRISM = 50;
    private int health;
    private int attack;
    private int vampirism;
    private int newInitialHealth;

    public Vampire(){
        super(INITIAL_HEALTH, INITIAL_ATTACK);
        this.vampirism= INITIAL_VAMPIRISM;
        this.health=INITIAL_HEALTH;
        this.attack= INITIAL_ATTACK;
        this.newInitialHealth=INITIAL_HEALTH;
    }

    @Override
    public void setHealth(int health) {
        this.health = Math.min(health, newInitialHealth);
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
    public void setAttack(int bombAttack) {
        this.attack = bombAttack;
    }

    public int getVampirism() {
        return vampirism;
    }

    public void setVampirism(int vampirism) {
        this.vampirism = Math.max(vampirism, 0);
    }

    @Override
    public void hit(IWarrior opponent) {
        int opponentHealthBefore=opponent.getHealth();
        super.hit(opponent);
        int opponentHealthAfter=opponent.getHealth();
        int damage=opponentHealthBefore - opponentHealthAfter;
        setHealth(getHealth() + damage*getVampirism()/100);
    }

    @Override
    public IWarrior equipWeapon(IWeapon weapon) {
        newInitialHealth+=weapon.getHealth();
        super.equipWeapon(weapon);
        setVampirism(getVampirism()+ weapon.getVampirism());
        return this;
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
