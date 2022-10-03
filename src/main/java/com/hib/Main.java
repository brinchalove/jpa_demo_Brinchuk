package com.hib;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hib.data.entity.Student;
import com.hib.data.entity.User;
import com.hib.data.repository.DepartmentRepository;
import com.hib.data.repository.EmployeeRepository;
import com.hib.data.repository.PersonalInfoRepository;
import com.hib.data.repository.SectionRepository;
import com.hib.data.repository.StudentRepository;
import com.hib.data.repository.UserRepository;
import com.hib.data.repository.impl.DepartmentRepositoryimpl;
import com.hib.data.repository.impl.EmployeeRepositoryImpl;
import com.hib.data.repository.impl.PersonalInfoRepositoryImpl;
import com.hib.data.repository.impl.SectionRepositoryImpl;
import com.hib.data.repository.impl.StudentRepositoryImpl;
import com.hib.data.repository.impl.UserRepositoryImpl;

public class Main {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
    private static EntityManager manager = factory.createEntityManager();
    private static UserRepository userRepository = new UserRepositoryImpl(manager);
    private static PersonalInfoRepository personalInfoRepository = new PersonalInfoRepositoryImpl(manager);
    private static StudentRepository studentRepository = new StudentRepositoryImpl(manager);
    private static SectionRepository sectionRepository = new SectionRepositoryImpl(manager);
    private static EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(manager);
    private static DepartmentRepository departmentRepository = new DepartmentRepositoryimpl(manager);
    
    
    public static void main(String[] args) {
        
       try {
           //OneToOne
           System.out.println(userRepository.findAll());
           System.out.println();
           System.out.println(userRepository.findById(10L));
           
           System.out.println(personalInfoRepository.findAll());
           System.out.println();
           System.out.println(personalInfoRepository.findById(20L));
           
//           User user = new User();
//           user.setLogin("admin");
//           user.setPassword("admin");
//           user.setPersonalInfo(null);
//           userRepository.create(user);
//           System.out.println(userRepository.findAll());
//           user.setLogin("user");
//           userRepository.update(user);
//           System.out.println(userRepository.findAll());
//           userRepository.delete(1001L);
//           System.out.println(userRepository.findAll());
           
           //OneToMany
//           System.out.println(employeeRepository.findAll());
//           System.out.println();
//           System.out.println(employeeRepository.findById(20L));
//           
//           System.out.println(departmentRepository.findAll());
//           System.out.println();
//           System.out.println(departmentRepository.findById(20L));
           
           //ManyToMany
//           System.out.println(studentRepository.findAll());
//           System.out.println();
//           System.out.println(studentRepository.findById(20L));
//           
//           System.out.println(sectionRepository.findAll());
//           System.out.println();
//           System.out.println(sectionRepository.findById(20L));
           
//           Student student = new Student();
//           student.setFirstName("Ivan");
//           student.setLastName("Perisic");
//           student.setSections(null);
//           studentRepository.create(student);
//           System.out.println(studentRepository.findAll());
//           studentRepository.delete(1002L);
//           System.out.println(studentRepository.findAll());

       }
        finally {
            factory.close();
        }
    }
}
