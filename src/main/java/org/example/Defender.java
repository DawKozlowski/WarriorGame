package org.example;

public class Defender extends Warrior{
    static final int ATTACK = 5;
    static final int INITIAL_HEALTH  = 50;
    static final int DEFENCE = 2;

    public Defender(){
        super(INITIAL_HEALTH, ATTACK);
    }

}
