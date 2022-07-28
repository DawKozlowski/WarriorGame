package org.example;

public class Battle {

    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        while(warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
        }
        return warrior1.isAlive();
    }


    public static boolean fight(Army army1, Army army2) {
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();

        while(it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }

        return  it1.hasNext();
    }




    /*public static boolean fight(Army army1, Army army2) {
        while(army1.isAlive() && army2.isAlive()) {
            if(fight(army1.getWarrior(), army2.getWarrior())){
                army2.removeWarrior();
            }else {
                army1.removeWarrior();
            }
        }
        return army1.isAlive();
    }*/




    /*public static boolean fight(CanFight side1, CanFight side2) {
        while(side1.isAlive() && side2.isAlive()) {
            side1.hit(side2);
            if (side2.isAlive()) {
                side2.hit(side1);
            }
        }
        return side1.isAlive();
    }*/



    /*public static boolean fight(Army army1, Army army2) {
        int i=0;
        int j=0;
        int sizeOfFirstArmy=army1.getTroops().size();
        int sizeOfSecondArmy=army2.getTroops().size();

        while(sizeOfFirstArmy != i && sizeOfSecondArmy != j) {
            boolean result = fight((Warrior) army1.getTroops().get(i), (Warrior) army2.getTroops().get(j));
            if (result) {
                j++;
            } else {
                i++;
            }
        }

        Warrior lastWarrior = (Warrior) army1.getTroops().get(sizeOfFirstArmy-1);
        return lastWarrior.isAlive();
    }*/





}
