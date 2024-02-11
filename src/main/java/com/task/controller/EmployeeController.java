package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Employee;
import com.task.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;

	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> create(@RequestBody Employee employee){
	    return service.addEmployee(employee);
	}

	@GetMapping("/listEmployees")
	public List<Employee> listAll(){
	    return service.listAllEmployees();
	}

	@GetMapping("/listById/{id}")
	public ResponseEntity<Employee> listById(@PathVariable int id){
	    return service.findEmployeeById(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable int id){
	    employee.setId(id);
	    return service.updateEmployee(employee);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Employee> delete(@PathVariable int id){
	    return service.deleteEmployee(id);
	}


}
