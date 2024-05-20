package com.example.restapi2.service;

import com.example.restapi2.dao.EmployeeRepository;
import com.example.restapi2.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplWithRepository implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeServiceImplWithRepository(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        return result.get();
    }

    @Override
    @Transactional
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);

    }
}
