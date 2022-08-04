package org.example.Models;

public interface IWarrior extends CanAttack, HasHealth{
    void hit(IWarrior opponent);

    void receiveHit(IDamage damageDealer);

    IWarrior getBehind();

    void setBehind(IWarrior behind);

}

interface IDamage {
    int hitPoints();
    IWarrior damageDealer();
}

record SimpleDamage (int hitPoints, IWarrior damageDealer) implements IDamage{};

interface  HasHealth {
    int getHealth();
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

