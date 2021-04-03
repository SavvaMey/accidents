package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.control.IndexControl;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidentHashMap = new HashMap<>();
    private static int idAcc;

    public AccidentMem() {
        accidentHashMap.put(++idAcc, new Accident(1, "accident", "bmw-lada", "moscow"));
        accidentHashMap.put(++idAcc, new Accident(2, "accident", "bmw-men", "spb"));
        accidentHashMap.put(++idAcc, new Accident(3, "accident", "bike-track", "moscow"));
    }


    public HashMap<Integer, Accident> getAccidentHashMap() {
        return accidentHashMap;
    }

    public void  create(Accident accident) {
        accident.setId(++idAcc);
        accidentHashMap.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidentHashMap.get(id);
    }

    public Accident updateAcc(Accident accident) {
        return accidentHashMap.put(accident.getId(), accident);
    }
}
