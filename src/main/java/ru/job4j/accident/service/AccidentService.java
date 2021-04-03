package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<Accident> AccidentGetAll () {
        return accidentMem.getAccidentHashMap().values();
    }

    public void create(Accident accident) {
        accidentMem.create(accident);
    }

    public void update(Accident accident) {
        accidentMem.updateAcc(accident);
    }

    public Accident findAccident(int id) {
        return accidentMem.findById(id);
    }

}
