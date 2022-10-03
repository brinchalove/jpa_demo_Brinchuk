package com.hib.data.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.hib.data.entity.PersonalInfo;
import com.hib.data.repository.PersonalInfoRepository;

public class PersonalInfoRepositoryImpl implements PersonalInfoRepository {
    private static final String GET_ALL = "from PersonalInfo";
    private final EntityManager manager;

    public PersonalInfoRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    public PersonalInfo findById(Long id) {
        return manager.find(PersonalInfo.class, id);
    }

    public List<PersonalInfo> findAll() {
        manager.getTransaction().begin();
        List<PersonalInfo> personalInfos = manager.createQuery(GET_ALL, PersonalInfo.class).getResultList();
        manager.getTransaction().commit();
        return personalInfos;
    }

    public PersonalInfo create(PersonalInfo personalInfo) {
        manager.getTransaction().begin();
        manager.persist(personalInfo);
        manager.getTransaction().commit();
        return personalInfo;
    }

    public PersonalInfo update(PersonalInfo personalInfo) {
        manager.getTransaction().begin();
        manager.merge(personalInfo);
        manager.getTransaction().commit();
        return personalInfo;
    }

    public PersonalInfo save(PersonalInfo personalInfo) {
        manager.getTransaction().begin();
        if (personalInfo.getId() != null) {
            manager.merge(personalInfo);
        } else {
            manager.persist(personalInfo);
        }
        manager.getTransaction().commit();
        return personalInfo;
    }

    public boolean delete(Long id) {
        manager.getTransaction().begin();
        PersonalInfo personalInfo = manager.find(PersonalInfo.class, id);
        boolean deleted = false;
        if (personalInfo != null) {
            manager.remove(personalInfo);
            deleted = true;
        }
        manager.getTransaction().commit();
        manager.clear();
        return deleted;
    }

}
