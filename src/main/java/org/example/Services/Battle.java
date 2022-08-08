package org.example.Services;

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
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();


        while(it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }

        return  it1.hasNext();
    }



}
