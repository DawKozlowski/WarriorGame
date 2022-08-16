package org.example.models;

import org.example.models.strategies.Strategy;
import org.example.models.strategies.WarlordStartegy;
import org.example.models.weapons.IWeapon;
import org.example.models.weapons.Weapon;

import java.util.*;
import java.util.function.Supplier;

public class Army {
    private List<IWarrior> troops = new ArrayList<>();
    private Warlord warlord;
    private Strategy strategy=new WarlordStartegy();


    public List<IWarrior> getTroops() {
        return troops;
    }

    public void setTroops(List<IWarrior> troops) {
        this.troops = troops;
    }

    public Iterator<IWarrior> firstAlive() {
        return new FirstAliveIterator();
    }

    private class FirstAliveIterator implements Iterator<IWarrior> {
        int cursor = 0;
        @Override
        public boolean hasNext() {
            while (cursor < troops.size() && !troops.get(cursor).isAlive()){
                cursor++;
            }
            return cursor < troops.size();
        }

        @Override
        public IWarrior next() {
            if(!hasNext()){
                throw  new NoSuchElementException();
            }
            return troops.get(cursor);
        }
    }

    public int size() {
        return troops.size();
    }

    public IWarrior getWarrior(int index) {
        return troops.get(index);
    }

    public void removeDeadWarrior() {
        troops.removeIf(IWarrior -> !IWarrior.isAlive());
    }

    public boolean isAlive() {
        return !troops.isEmpty();
    }

    public Army lineUp() {
        for (int i = 1; i < troops.size(); i++) {
            troops.get(i - 1).setBehind(troops.get(i));
        }
        return this;
    }

    public Army addUnits(Supplier<IWarrior> factory, int quantity) {
        if(factory.get() instanceof Warlord) {
            if (troops.stream().noneMatch(Warlord.class::isInstance)) {
                troops.add(factory.get());
            }
            return this;
        }

        for(int i=0 ;i<quantity;i++){
            IWarrior next = factory.get();
            troops.add(next);
        }
        return this;
    }

    public Army equipWarriorAtPosition(int position, IWeapon weapon){
        getWarrior(position).equipWeapon(weapon);
        return this;
    }

    public void moveUnits() {
        if (troops.stream().filter(HasHealth::isAlive).anyMatch(Warlord.class::isInstance)) {
            strategy.moveUnits(this);
        }
    }


    @Override
    public String toString() {
        return "Army{" +
                "troops=" + troops +
                '}';
    }
}
