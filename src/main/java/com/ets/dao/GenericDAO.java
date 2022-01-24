package com.ets.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Yusuf
 * @param <T>
 * @param <Long>
 */
public interface GenericDAO<T, Long extends Serializable> {

    public void save(T entity);

    public void saveBulk(List<T> entityList);

    public List findAll(Class clazz);

    public T findByID(Class clazz, Long id);
}
