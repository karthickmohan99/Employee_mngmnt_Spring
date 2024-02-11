package com.task.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.task.model.Employee;
import com.task.repo.EmployeeRepo;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepo employeeRepository;
    
    
    public ResponseEntity<Employee> addEmployee(Employee employee) {
        boolean exists = employeeRepository.existsByempMail(employee.getEmpMail());
        
        if (!exists) {
            employeeRepository.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.CREATED);
        } else {
        	 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    public List<Employee> listAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public ResponseEntity<Employee> findEmployeeById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        
        if (employeeOptional.isPresent()) {
            return new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
    
    public ResponseEntity<Employee> updateEmployee(Employee employee) {
        boolean exists = employeeRepository.existsById(employee.getId());
        
        if (exists) {
            employeeRepository.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
    
    public ResponseEntity<Employee> deleteEmployee(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        
        if (employeeOptional.isPresent()) {
            employeeRepository.delete(employeeOptional.get());
            return new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
}
