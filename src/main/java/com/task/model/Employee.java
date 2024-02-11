package com.task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String empName;
    private String empDepartment;
    private String empDesignation;
    private String empMail;
    private String empPhoneNumber;
}
