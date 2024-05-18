package com.example.restapi2.dao;

import com.example.restapi2.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeJpaImpl implements EmployeeDAO {

    //define field for entitymanager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    @Override
    public List<Employee> findAll() {

        //create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        //execute query and get result list
        //return the results
        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int theId) {
        //get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);
        //return employee
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        //save or update the employee
        Employee dbEmployee = entityManager.merge(theEmployee);
        //update with id from db... so we can get generated id for save/insert
        theEmployee.setId(dbEmployee.getId());


    }

    @Override
    public void deleteById(int theId) {
        //delete object with primary key
        var employee =  entityManager.find(Employee.class, theId);

        //delete object
        entityManager.remove(employee);

    }

}
