package com.hib.data.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.hib.data.entity.Section;
import com.hib.data.repository.SectionRepository;

public class SectionRepositoryImpl implements SectionRepository{
    
    private static final String GET_ALL = "from Section";
    private final EntityManager manager;
    
    public SectionRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    public Section findById(Long id) {
        return manager.find(Section.class, id);
    }

    public List<Section> findAll() {
        manager.getTransaction().begin();
        List<Section> sections = manager.createQuery(GET_ALL, Section.class).getResultList();
        manager.getTransaction().commit();
        return sections;
    }

    public Section create(Section section) {
        manager.getTransaction().begin();
        manager.persist(section);
        manager.getTransaction().commit();
        return section;
    }

    public Section update(Section section) {
        manager.getTransaction().begin();
        manager.merge(section);
        manager.getTransaction().commit();
        return section;
    }
    
    public Section save(Section section) {
        manager.getTransaction().begin();
        if (section.getId() != null) {
            manager.merge(section);
        } else {
            manager.persist(section);
        }
        manager.getTransaction().commit();
        return section;
    }

    public boolean delete(Long id) {
        manager.getTransaction().begin();
        Section section = manager.find(Section.class, id);
        boolean deleted = false;
        if (section != null) {
            manager.remove(section);
            deleted = true;
        }
        manager.getTransaction().commit();
        manager.clear();
        return deleted;
    }

}
