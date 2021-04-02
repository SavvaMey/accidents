package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.control.IndexControl;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private final HashMap<Integer, Accident> accidentHashMap = new HashMap<>();

    public AccidentMem() {
        accidentHashMap.put(1, new Accident(1, "accident", "bmw-lada", "moscow"));
        accidentHashMap.put(2, new Accident(2, "accident", "bmw-men", "spb"));
        accidentHashMap.put(3, new Accident(3, "accident", "bike-track", "moscow"));
    }


    public HashMap<Integer, Accident> getAccidentHashMap() {
        return accidentHashMap;
    }
}
