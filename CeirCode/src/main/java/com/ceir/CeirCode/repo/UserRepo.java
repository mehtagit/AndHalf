package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
   
	public User  save(User u); 
	public User findById(long id);
	public User findByUsernameAndPassword(String username,String password);
	public User findByUsername(String user); 
	public User findByUserProfile_Id(long id);
	
}
