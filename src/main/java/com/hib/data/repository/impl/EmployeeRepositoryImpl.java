package com.hib.data.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.hib.data.entity.Employee;
import com.hib.data.repository.EmployeeRepository;

public class EmployeeRepositoryImpl implements EmployeeRepository{
    private static final String GET_ALL = "from Employee";
    private final EntityManager manager;
    
    public EmployeeRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    public Employee findById(Long id) {
        return manager.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        manager.getTransaction().begin();
        List<Employee> employees = manager.createQuery(GET_ALL, Employee.class).getResultList();
        manager.getTransaction().commit();
        return employees;
    }

    public Employee create(Employee employee) {
        manager.getTransaction().begin();
        manager.persist(employee);
        manager.getTransaction().commit();
        return employee;
    }

    public Employee update(Employee employee) {
        manager.getTransaction().begin();
        manager.merge(employee);
        manager.getTransaction().commit();
        return employee;
    }
    
    public Employee save(Employee employee) {
        manager.getTransaction().begin();
        if (employee.getId() != null) {
            manager.merge(employee);
        } else {
            manager.persist(employee);
        }
        manager.getTransaction().commit();
        return employee;
    }

    public boolean delete(Long id) {
        manager.getTransaction().begin();
        Employee employee = manager.find(Employee.class, id);
        boolean deleted = false;
        if (employee != null) {
            manager.remove(employee);
            deleted = true;
        }
        manager.getTransaction().commit();
        manager.clear();
        return deleted;
    }

}
