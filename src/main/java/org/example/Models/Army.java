package org.example.Models;

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


    //reflection example
    public void addUnits(Class<? extends Unit> cls, int quantity){
        try {
            var constractor =  cls.getDeclaredConstructor();
            for(int i=0; i< quantity; i++) {
                var o = constractor.newInstance();
                troops.add((Warrior) o);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    // enum example
    public void addUnits(Unit.UnitType type, int quantity) {
       for(int i=0 ;i<quantity;i++){
           troops.add((Warrior) Unit.newUnit(type));
       }
    }

    // cloning example
    public void addUnits(Warrior prototype, int quantity) {
        for(int i=0 ;i<quantity;i++){
            troops.add(prototype.clone());
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
