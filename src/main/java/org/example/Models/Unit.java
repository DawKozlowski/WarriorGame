package org.example.Models;

public interface Unit {
    enum UnitType{
        KNIGHT, WARRIOR, DEFENDER, VAMPIRE;
    }

    static Unit newUnit(UnitType type) {
        return switch (type) {
            case KNIGHT -> new Knight();
            case WARRIOR ->new Warrior();
            case DEFENDER ->new Defender();
            case VAMPIRE ->new Vampire();
            default ->throw  new IllegalArgumentException();
        };
    }

}
