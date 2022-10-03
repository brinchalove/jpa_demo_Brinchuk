package com.hib.data.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.hib.data.entity.User;
import com.hib.data.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private static final String GET_ALL = "from User";
    private final EntityManager manager;

    public UserRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    public User findById(Long id) {
        return manager.find(User.class, id);
    }

    public List<User> findAll() {
        manager.getTransaction().begin();
        List<User> users = manager.createQuery(GET_ALL, User.class).getResultList();
        manager.getTransaction().commit();
        return users;
    }

    public User create(User user) {
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        return user;
    }

    public User update(User user) {
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
        return user;
    }

    public User save(User user) {
        manager.getTransaction().begin();
        if (user.getId() != null) {
            manager.merge(user);
        } else {
            manager.persist(user);
        }
        manager.getTransaction().commit();
        return user;
    }

    public boolean delete(Long id) {
        manager.getTransaction().begin();
        User user = manager.find(User.class, id);
        boolean deleted = false;
        if (user != null) {
            manager.remove(user);
            deleted = true;
        }
        manager.getTransaction().commit();
        manager.clear();
        return deleted;
    }

}
