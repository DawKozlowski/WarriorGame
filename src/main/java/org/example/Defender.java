package org.example;

public class Defender extends Warrior{
    static final int ATTACK = 3;
    static final int INITIAL_HEALTH  = 60;
    static final int DEFENCE = 2;

    public Defender(){
        super(INITIAL_HEALTH, ATTACK);
    }

}
