package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Accident> getAll() {
        return jdbc.query(
                "select a.id, a.name AS a_name, a.text, a.address, a.type_id, t.name AS t_name "
                        + " from accidents a left join types t ON "
                        + "a.type_id = t.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("a_name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("type_id"));
                    accidentType.setName(rs.getString("t_name"));
                    accident.setType(accidentType);
                    Set<Rule> rules = new HashSet<>(getRulesForAccident(accident.getId()));
                    accident.setRules(rules);
                    return accident;
                });
    }

    public Accident findById(int id) {
        return  jdbc.queryForObject(
                "select * from accidents where id = ?", new Object[]{id},
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setText(rs.getString("text"));
                    accident.setName(rs.getString("name"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                });
    }

    public List<Rule> getRulesForAccident(int id) {
        return jdbc.query(
                "select a_r.rule_id, r.name from accident_rules a_r"
                        + " join rules r ON r.id = a_r.rule_id where a_r.accident_id= ?", new Object[]{id},
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("rule_id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }


    public void create(Accident accident) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        String msg_sql =  "insert into accidents (name, text, address, type_id) values (?, ?, ?, ?)";
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(msg_sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
                 return ps; }, keyHolder);
        accident.setId((int) keyHolder.getKeys().get("id"));
        accident.getRules().forEach(rule -> jdbc.update(
                "INSERT INTO accident_rules (accident_id, rule_id) VALUES (?, ?)",
                accident.getId(), rule.getId()
        ));
    }

    public void updateAcc(Accident accident) {
        jdbc.update("update accidents set name = ?, text = ?, address = ?,  "
                + "type_id = ? where id = ?",
                accident.getName(), accident.getText(),
                accident.getAddress(), accident.getType().getId(), accident.getId());
        jdbc.update(
                "delete from accident_rules where accident_id = ?",
                accident.getId());
        accident.getRules().forEach(rule -> jdbc.update(
                "INSERT INTO accident_rules (accident_id, rule_id) VALUES (?, ?)",
                accident.getId(), rule.getId()
        ));
    }

    public List<AccidentType> getAccidentTypes() {
        return jdbc.query("select * from types",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public Collection<Rule> getRules() {
        return jdbc.query("select * from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Rule findRule(int id) {
        return jdbc.queryForObject("select * from rules where id = ? ", new Object[]{id},
                (rs, row) -> {
                    Rule rule = new Rule();
                    System.out.println(rs.getInt("id"));
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public AccidentType findType(int id) {
        return jdbc.queryForObject("select * from types where id = ? ", new Object[]{id},
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }
}
