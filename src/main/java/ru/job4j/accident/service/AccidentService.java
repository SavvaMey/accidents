package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<Accident> accidentGetAll() {
        return accidentMem.getAccidentHashMap().values();
    }

    public void create(Accident accident) {
        accidentMem.create(accident);
    }

    public void createOrUpdate(Accident accident) {
        if (accident.getId() == 0) {
            this.create(accident);
        } else {
            this.update(accident);
        }
    }

    public void update(Accident accident) {
        accidentMem.updateAcc(accident);
    }

    public Accident findAccident(int id) {
        return accidentMem.findById(id);
    }

    public List<AccidentType> getAccidentTypesList() {
        return accidentMem.getAccidentTypes();
    }

    public Collection<Rule> getAllRule() {
        return accidentMem.getRules();
    }

    public Rule findByIdRule(int id) {
        return accidentMem.findRule(id);
    }

}
