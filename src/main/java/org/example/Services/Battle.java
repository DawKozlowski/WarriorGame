package org.example.Services;

import org.example.Models.Army;
import org.example.Models.IWarrior;
import org.example.Models.Warrior;

public class Battle {

    public static boolean fight(IWarrior warrior1, IWarrior warrior2) {
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



}
