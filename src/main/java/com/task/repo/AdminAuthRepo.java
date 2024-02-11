package com.task.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.model.AdminAuth;

public interface AdminAuthRepo extends JpaRepository<AdminAuth,Integer>  {

    public AdminAuth findBymail(String mail);
	
	public AdminAuth findByphoneNumber(Long phoneNumber);
	
    public Optional<AdminAuth> findByPassword(String password);
	
   public Optional<AdminAuth> findByMailAndPassword(String email,String password);
	
}
