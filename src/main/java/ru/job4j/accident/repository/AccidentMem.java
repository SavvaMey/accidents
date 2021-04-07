package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.control.IndexControl;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidentHashMap = new ConcurrentHashMap<>();
    private static int idAcc;
    private final List<AccidentType> types = List.of(
            AccidentType.of(1, "Две машины"),
            AccidentType.of(2, "Машина и человек"),
            AccidentType.of(3, "Машина и велосипед")
    );
    private final Map<Integer, Rule> rules = Map.of(
            1, Rule.of(1, "Статья 1"),
            2, Rule.of(2, "Статья 2"),
            3, Rule.of(3, "Статья 3")
    );

    public AccidentMem() {
        Accident accOne = new Accident(1, "accident", "bmw-lada", "moscow",
                types.get(0));

        accOne.addRule(rules.get(1));
        Accident accTwo = new Accident(2, "accident", "bmw-men", "spb",
                types.get(1));

        accTwo.addRule(rules.get(1));
        accTwo.addRule(rules.get(2));

        Accident accThree = new Accident(3, "accident", "bike-track", "moscow",
                types.get(2));
        accThree.addRule(rules.get(3));

        accidentHashMap.put(++idAcc, accOne);
        accidentHashMap.put(++idAcc, accTwo);
        accidentHashMap.put(++idAcc, accThree);
    }


    public Collection<Accident> getAccidentHashMap() {
        return accidentHashMap.values();
    }

    public void  create(Accident accident) {
        accident.setType(types.get(accident.getType().getId() - 1));
        accident.setId(++idAcc);
        accidentHashMap.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidentHashMap.get(id);
    }

    public Accident updateAcc(Accident accident) {
        accident.setType(types.get(accident.getType().getId() - 1));
        return accidentHashMap.put(accident.getId(), accident);
    }

    public List<AccidentType> getAccidentTypes() {
        return types;
    }

    public Collection<Rule> getRules() {
        return rules.values();
    }

    public Rule findRule(int id) {
        return rules.get(id);
    }
}
