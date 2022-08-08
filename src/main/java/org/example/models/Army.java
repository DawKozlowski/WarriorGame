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


    //fluent interface
    public Army addUnits(Supplier<IWarrior> factory, int quantity) {
        for(int i=0 ;i<quantity;i++){
            IWarrior next = factory.get();
            if(!troops.isEmpty()) {
                troops.get(troops.size() - 1).setBehind(next);
            }
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
