package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import javax.persistence.FetchType;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentJPAService {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository accidentRuleRepository;

    public AccidentJPAService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository, RuleRepository accidentRuleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.accidentRuleRepository = accidentRuleRepository;
    }


    public List<AccidentType> getAccidentTypesList() {
        List<AccidentType> accidentTypes = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(accidentTypes::add);
        return accidentTypes;
    }

    public List<Rule> getAllRule() {
        List<Rule> rules = new ArrayList<>();
        accidentRuleRepository.findAll().forEach(rules::add);
        return rules;
    }

    public Accident findAccident(int id) {
        return accidentRepository.findById(id).orElse(null);
    }

    public Rule findByIdRule(int id) {
        return accidentRuleRepository.findById(id).orElse(null);
    }

    public List<Accident> accidentGetAll() {
        List<Accident> accidents = new ArrayList<>();
        accidentRepository.findAll().forEach(accidents::add);
        return accidents;
    }

    public void createOrUpdate(Accident accident) {
        accidentRepository.save(accident);
    }

    public void create(Accident accident) {
        accidentRepository.save(accident);
    }
}
