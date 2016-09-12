package edu.learn.market.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

    List<T> findAll();

    T save(T var1);

    void delete(PK var1);

    T findOne(PK var1);

    void flush();

    T saveAndFlush(T var1);
}
