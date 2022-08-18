package org.example.models.strategies;

import org.example.models.Army;
import org.example.models.IWarrior;


import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;

public class WarlordStrategy implements Strategy{
    @Override
    public void moveUnits(Army army) {
        var itArmy = army.getTroops().iterator();
        List<IWarrior> res = sortArmy(itArmy);
        army.setTroops(res);
    }

    private static List<IWarrior> sortArmy(Iterator<IWarrior> itArmy) {
        var groups = Stream.generate(itArmy::hasNext).takeWhile(b -> b).map(b -> itArmy.next())
                .collect(groupingBy(WarlordStrategy::classify, toCollection(ArrayDeque::new)));
        int total = groups.values().stream().mapToInt(Collection::size).sum();
        var res = new ArrayList<IWarrior>(total);
        // first:
        Stream.of("Lancer", "Fighter")
                .map(groups::get).filter(Objects::nonNull)
                .map(ArrayDeque::pollFirst).filter(Objects::nonNull)
                .findFirst()
                .ifPresent(res::add);
        // rest
        Stream.of("Healer", "Lancer", "Fighter", "Bomber", "Warlord", "Dead")
                .map(groups::get).filter(Objects::nonNull)
                .forEach(res::addAll);
        return res;
    }

    private static String classify(IWarrior warrior) {
        if (!warrior.isAlive()) return "Dead";
        String name = warrior.getClass().getSimpleName();
        return switch (name) {
            case "Warlord", "Healer", "Lancer", "Bomber" -> name;
            default -> "Fighter";
        };
    }
}
