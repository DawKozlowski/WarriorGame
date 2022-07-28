package org.example;

public interface CanFight {
    boolean isAlive();

    Damage getDamage();

    default void hit(CanFight opponent) {
        opponent.receiveDamage(this.getDamage(), this);
    };

    void receiveDamage(Damage damage, CanFight damageDealer);
}


interface  Damage {
    int getHitPoints();
}

class SimpleDamage implements Damage {
   private final int hitPoints;

    public SimpleDamage(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }
}
