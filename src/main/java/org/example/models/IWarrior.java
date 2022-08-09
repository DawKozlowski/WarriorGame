package org.example.models;

import java.util.Optional;

public interface IWarrior extends CanAttack, HasHealth{

    void hit(IWarrior opponent);

    void receiveHit(IDamage damageDealer);

    default void processCommand(ICommand command, IWarrior sender) {
        getBehind().ifPresent(IWarrior -> IWarrior.processCommand(command, this));
    }

    Optional<IWarrior> getBehind();

    void setBehind(IWarrior behind);

}

interface ICommand{}

class HealCommand implements ICommand {}


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
}

interface HasDefense {
    int getDefense();
}

