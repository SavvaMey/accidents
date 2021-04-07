package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
//@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public void create(Accident accident) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
           session.save(accident);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Accident findById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Accident.class, id);
        }
    }

    public void updateAcc(Accident accident) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            session.update(accident);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Collection<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct a from Accident a join fetch a.rules order by a.id", Accident.class)
                    .list();
        }
    }

    public List<AccidentType> getAccidentTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    public List<Rule> getRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    public Rule findRule(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule where id = :id", Rule.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }
}
