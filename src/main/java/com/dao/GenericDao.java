package com.dao;

/**
 * Created by akrantan on 5.4.2017.
 */
public interface GenericDao<T> {
    public T create(T t);
    public void delete(Object id);
    public T find(Object id);
    public T update(T t);

}
