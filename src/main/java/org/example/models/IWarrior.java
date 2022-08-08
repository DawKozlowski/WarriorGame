package org.example.models;

public interface IWarrior extends CanAttack, HasHealth{

    default void hit(IWarrior opponent) {
        opponent.receiveHit(new SimpleDamage(getAttack(), this));
        processCommand(new HealCommand(), this);
    }

    default void receiveHit(IDamage damageDealer) {
        setHealth(getHealth()- damageDealer.hitPoints());
    }

    default void processCommand(ICommand command, IWarrior sender) {
        var behind = getBehind();
        if (behind != null) {
            behind.processCommand(command, this);
        }
    }

    IWarrior getBehind();

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

