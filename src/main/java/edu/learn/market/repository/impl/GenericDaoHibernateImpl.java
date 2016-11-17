package edu.learn.market.repository.impl;

import edu.learn.market.repository.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class GenericDaoHibernateImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    private Class<T> type;
    private SessionFactory factory;

    public GenericDaoHibernateImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<T> findAll() {
        return getSession().createCriteria(type).list();
    }

    @Override
    public T save(T var1) {
        Session session = getSession();
        session.saveOrUpdate(var1);
        session.flush();
        return var1;
    }

    @Override
    public void delete(PK id) {
        Session session = getSession();
        Optional.ofNullable(session.load(type, id))
                .ifPresent(session::delete);
        session.flush();
    }

    @Override
    public T findOne(PK var1) {
        return getSession().get(type, var1);
    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public T saveAndFlush(T var1) {
        Session session = getSession();
        Serializable pk = session.save(var1);
        session.flush();
        return session.get(type, pk);
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }
}
