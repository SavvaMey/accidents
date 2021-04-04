package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;
import java.util.List;

@Service
public class JdbcService {
    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public JdbcService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public Collection<Accident> accidentGetAll() {
        return accidentJdbcTemplate.getAll();
    }

    public List<AccidentType> getAccidentTypesList() {
        return accidentJdbcTemplate.getAccidentTypes();
    }

    public Collection<Rule> getAllRule() {
        return accidentJdbcTemplate.getRules();
    }

    public Accident findAccident(int id) {
        return accidentJdbcTemplate.findById(id);
    }

    public Rule findByIdRule(int id) {
        return accidentJdbcTemplate.findRule(id);
    }

    public AccidentType findTypeById(int id) {
        return accidentJdbcTemplate.findType(id);
    }

    public void createOrUpdate(Accident accident) {
        if (accident.getId() == 0) {
            this.create(accident);
        } else {
            this.update(accident);
        }
    }

    public void create(Accident accident) {
        accidentJdbcTemplate.create(accident);
    }

    public void update(Accident accident) {
        accidentJdbcTemplate.updateAcc(accident);
    }
}
