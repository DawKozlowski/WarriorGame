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

        Warrior warrior = (Warrior) army1.getTroops().get(sizeOfFirstArmy-1);
        return warrior.isAlive();
    }
}
