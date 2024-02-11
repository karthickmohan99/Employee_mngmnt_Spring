package com.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	public boolean existsByempMail(String empMail );
}