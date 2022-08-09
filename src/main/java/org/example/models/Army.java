package org.example.models;

import java.util.*;
import java.util.function.Supplier;

public class Army {
    private List<IWarrior> troops = new ArrayList<>();

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

    //fluent interface
    public Army addUnits(Supplier<IWarrior> factory, int quantity) {
        for(int i=0 ;i<quantity;i++){
            IWarrior next = factory.get();
            troops.add(next);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Army{" +
                "troops=" + troops +
                '}';
    }
}
