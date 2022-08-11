package org.example.services;

import org.example.models.Army;
import org.example.models.IWarrior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Battle {

    static Logger log = LoggerFactory.getLogger(Battle.class);

    public static boolean fight(IWarrior warrior1, IWarrior warrior2) {
        if(log.isTraceEnabled()) {
            log.trace("Warrior 1 {} fights Warrior 2 {}", warrior1, warrior2);
        }
        while(warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
            if(log.isTraceEnabled()) {
                log.trace("Fight between {} Health: {} and {} Health: {}", warrior1.getClass().getSimpleName(), warrior1.getHealth(),
                        warrior2.getClass().getSimpleName(), warrior2.getHealth());
            }
        }
        return warrior1.isAlive();
    }


    public static boolean fight(Army army1, Army army2) {
        army1.lineUp();
        army2.lineUp();

        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();

        while(it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }

        return  it1.hasNext();
    }

    public static boolean straightFight(Army army1, Army army2) {
        while (army1.isAlive() && army2.isAlive()) {

            for (int i = 0; i < Math.min(army1.size(), army2.size()); i++) {
                fight(army1.getWarrior(i), army2.getWarrior(i));
            }

            army1.removeDeadWarrior();
            army2.removeDeadWarrior();
        }
        return army1.isAlive();
    }

}
