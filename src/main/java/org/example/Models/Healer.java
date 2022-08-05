package org.example.Models;

public class Healer extends Warrior {
    public static final int ATTACK = 0;

    public static final int INITIAL_HEALTH  = 60;

    private int health;

    private int attack;

    public Healer(){
        super(INITIAL_HEALTH, ATTACK);
        this.health=INITIAL_HEALTH;
        this.attack=ATTACK;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public void heal(IWarrior warrior) {
        warrior.setHealth(warrior.getHealth() + 2);
    }


    @Override
    public void processCommand(ICommand command, IWarrior sender) {
        if (command instanceof HealCommand) {
            heal(sender);
        }
        super.processCommand(command, sender);
    }

    @Override
    public String toString() {
        return "Healer{" +
                "health=" + health +
                ", attack=" + attack +
                '}';
    }




}
