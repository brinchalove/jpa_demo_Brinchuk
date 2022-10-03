package com.hib.data.repository;

import java.util.List;

public interface Repository<K, T> {
    T findById(K id);

    List<T> findAll(int limit, long offset);

    long count();

    T save(T object);

    T update(T object);

    boolean delete(K id);
}
