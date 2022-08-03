package org.example.Models;

public interface IWarrior extends CanAttack, HasHealth {
    default void hit(IWarrior opponent) {
        opponent.receiveHit(this);
    }
    default void receiveHit(CanAttack damageDealer) {
         reduceHealthBasedOnDamage(damageDealer.getAttack());
    }

}

interface  HasHealth {
    int getHealth();
    void reduceHealthBasedOnDamage(int damage);
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