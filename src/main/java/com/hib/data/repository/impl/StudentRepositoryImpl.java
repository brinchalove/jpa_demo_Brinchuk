package com.hib.data.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.hib.data.entity.Student;
import com.hib.data.repository.StudentRepository;

public class StudentRepositoryImpl implements StudentRepository{
    private static final String GET_ALL = "from Student";
    private final EntityManager manager;
    
    public StudentRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    public Student findById(Long id) {
        return manager.find(Student.class, id);
    }

    public List<Student> findAll() {
        manager.getTransaction().begin();
        List<Student> students = manager.createQuery(GET_ALL, Student.class).getResultList();
        manager.getTransaction().commit();
        return students;
    }

    public Student create(Student student) {
        manager.getTransaction().begin();
        manager.persist(student);
        manager.getTransaction().commit();
        return student;
    }

    public Student update(Student student) {
        manager.getTransaction().begin();
        manager.merge(student);
        manager.getTransaction().commit();
        return student;
    }
    
    public Student save(Student student) {
        manager.getTransaction().begin();
        if (student.getId() != null) {
            manager.merge(student);
        } else {
            manager.persist(student);
        }
        manager.getTransaction().commit();
        return student;
    }

    public boolean delete(Long id) {
        manager.getTransaction().begin();
        Student student = manager.find(Student.class, id);
        boolean deleted = false;
        if (student != null) {
            manager.remove(student);
            deleted = true;
        }
        manager.getTransaction().commit();
        manager.clear();
        return deleted;
    }

}
