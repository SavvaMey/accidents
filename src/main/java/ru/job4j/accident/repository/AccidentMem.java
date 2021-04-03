package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.control.IndexControl;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidentHashMap = new HashMap<>();
    private static int idAcc;
    private final List<AccidentType> types = List.of(
            AccidentType.of(1, "Две машины"),
            AccidentType.of(2, "Машина и человек"),
            AccidentType.of(3, "Машина и велосипед")
    );

    public AccidentMem() {
        accidentHashMap.put(++idAcc, new Accident(1, "accident", "bmw-lada", "moscow",
                types.get(0))
        );
        accidentHashMap.put(++idAcc, new Accident(2, "accident", "bmw-men", "spb",
                types.get(1))
        );
        accidentHashMap.put(++idAcc, new Accident(3, "accident", "bike-track", "moscow",
                types.get(2))
        );
    }


    public HashMap<Integer, Accident> getAccidentHashMap() {
        return accidentHashMap;
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
}
