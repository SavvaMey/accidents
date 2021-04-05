package ru.job4j.accident.service;


import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.Collection;
import java.util.List;

@Service
public class AccidentHbmService {
    private final AccidentHibernate accidentHibernate;

    public AccidentHbmService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }


    public Collection<Accident> accidentGetAll() {
        return accidentHibernate.getAll();
    }

    public List<AccidentType> getAccidentTypesList() {
        return accidentHibernate.getAccidentTypes();
    }

    public Collection<Rule> getAllRule() {
        return accidentHibernate.getRules();
    }
//
    public Accident findAccident(int id) {
        return accidentHibernate.findById(id);
    }
//
    public Rule findByIdRule(int id) {
        return accidentHibernate.findRule(id);
    }
//
//    public AccidentType findTypeById(int id) {
//        return accidentHibernate.findType(id);
//    }
//
    public void createOrUpdate(Accident accident) {
        if (accident.getId() == 0) {
            this.create(accident);
        } else {
            this.update(accident);
        }
    }

    public void create(Accident accident) {
        accidentHibernate.create(accident);
    }

    public void update(Accident accident) {
        accidentHibernate.updateAcc(accident);
    }
}
