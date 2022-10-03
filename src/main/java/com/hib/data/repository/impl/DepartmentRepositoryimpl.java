package com.hib.data.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.hib.data.entity.Department;
import com.hib.data.repository.DepartmentRepository;

public class DepartmentRepositoryimpl implements DepartmentRepository{
    
    private static final String GET_ALL = "from Department";
    private final EntityManager manager;

    public DepartmentRepositoryimpl(EntityManager manager) {
        this.manager = manager;
    }

    public Department findById(Long id) {
        return manager.find(Department.class, id);
    }

    public List<Department> findAll() {
        manager.getTransaction().begin();
        List<Department> departments = manager.createQuery(GET_ALL, Department.class).getResultList();
        manager.getTransaction().commit();
        return departments;
    }

    public Department create(Department department) {
        manager.getTransaction().begin();
        manager.persist(department);
        manager.getTransaction().commit();
        return department;
    }

    public Department update(Department department) {
        manager.getTransaction().begin();
        manager.merge(department);
        manager.getTransaction().commit();
        return department;
    }
    
    public Department save(Department department) {
        manager.getTransaction().begin();
        if (department.getId() != null) {
            manager.merge(department);
        } else {
            manager.persist(department);
        }
        manager.getTransaction().commit();
        return department;
    }

    public boolean delete(Long id) {
        manager.getTransaction().begin();
        Department department = manager.find(Department.class, id);
        boolean deleted = false;
        if (department != null) {
            manager.remove(department);
            deleted = true;
        }
        manager.getTransaction().commit();
        manager.clear();
        return deleted;
    }

}
