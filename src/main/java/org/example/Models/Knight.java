package org.example.Models;

public class Knight extends Warrior{

    public static final int ATTACK = 7;
    public static final int INITIAL_HEALTH  = 50;

    private int health;

    private int attack;

     public Knight(){
         super(INITIAL_HEALTH, ATTACK);
         this.health=INITIAL_HEALTH;
         this.attack=ATTACK;
     }


    @Override
    public String toString() {
        return "Knight{" +
                "health=" + health +
                ", attack=" + attack +
                '}';
    }
}