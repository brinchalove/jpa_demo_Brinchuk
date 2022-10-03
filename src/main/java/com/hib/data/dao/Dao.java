package com.hib.data.dao;

import java.util.List;

public interface Dao<K, T> {
    T findById(K id);

    List<T> findAll(int limit, long offset);
    
    long count();

    T save(T object);

    T update(T object);

    boolean delete(K id);
}
