package edu.learn.market.repository.impl;

import edu.learn.market.repository.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

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
        return session.get(type, session.save(var1));
    }

    @Override
    public void delete(PK var1) {
        getSession().delete(var1);
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


    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }
}
