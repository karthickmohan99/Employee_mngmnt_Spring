package com.task.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.task.exception.AlreadyExistException;
import com.task.exception.NotFound;
import com.task.model.AdminAuth;
import com.task.repo.AdminAuthRepo;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AdminAuthService {
	
	@Autowired 
	AdminAuthRepo authRepo;
	
	@Autowired
	PasswordEncoder PasswordEncoder;
	
	
	public ResponseEntity<Object> register(AdminAuth admin) {
		 System.out.println("name from service"+" "+admin.getName());
		AdminAuth isNumberExists= authRepo.findByphoneNumber(admin.getPhoneNumber());
		AdminAuth isEmailExists = authRepo.findBymail(admin.getMail());
		System.out.println("email exists"+isEmailExists);
		if(isEmailExists!=null) {
//			throw new AlreadyExistException("Email already exist");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
		}
		else if(isNumberExists!=null){
			//throw new AlreadyExistException("PhoneNumber already exist");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone Number already exists");
			
		}else {
			String encodedPasssword =  PasswordEncoder.encode(admin.getPassword());
			admin.setPassword(encodedPasssword);
			authRepo.save(admin);
			 return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Successfully registered\"}");

		}
		
		
	}
	
	public  ResponseEntity<Object> login (AdminAuth admin){
		
		AdminAuth userByEmail = authRepo.findBymail(admin.getMail());
		
		if(userByEmail !=null) {
			String userpassword=admin.getPassword();
			String encodedPassword=userByEmail.getPassword();
			Boolean isPasswordMatch= PasswordEncoder.matches(userpassword, encodedPassword);
			
			if(isPasswordMatch) {
				Optional<AdminAuth>checkCredentials=authRepo.findByMailAndPassword(admin.getMail(), encodedPassword);
				if(checkCredentials.isPresent()) {
					return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Successfully Logged In\"}");
				}else {
					//throw new NotFound("Invalid Credentials");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Credentials");
					
				}
			}else {
				//throw new NotFound("User with this Password doesn't exists");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this Password doesn't exists");
			}
		}else {
//			throw new NotFound("Email does not Exists");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email does not Exists");
		}
	}

}
