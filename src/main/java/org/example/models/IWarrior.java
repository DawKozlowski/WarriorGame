package org.example.models;

import org.example.models.weapons.IWeapon;


import java.util.Optional;

public interface IWarrior extends CanAttack, HasHealth{

    void hit(IWarrior opponent);

    void receiveHit(IDamage damageDealer);

    default void processCommand(ICommand command, IWarrior sender) {
        if (command instanceof BombCommand bombCommand) {
            if(bombCommand.damage>0) {
                int health = getHealth();
                setHealth(getHealth() - bombCommand.damage);
                bombCommand.setDamage(bombCommand.damage - health);
            }
        }
        getBehind().ifPresent(IWarrior -> IWarrior.processCommand(command, this));
    }

    public IWarrior equipWeapon(IWeapon weapon);

    Optional<IWarrior> getBehind();

    void setBehind(IWarrior behind);

}

interface ICommand{}

class HealCommand implements ICommand {}

class BombCommand implements ICommand {
    int damage;

    public BombCommand(int damage) {
        this.damage = damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

interface IDamage {
    int hitPoints();
    IWarrior damageDealer();
}

record SimpleDamage (int hitPoints, IWarrior damageDealer) implements IDamage{};

interface  HasHealth {
    int getHealth();

    void setHealth(int health);

    default boolean isAlive() {
       return  getHealth() > 0;
    }
}

interface CanAttack {
    int getAttack();

    void setAttack(int attack);
}

interface HasDefense {
    int getDefense();
}

