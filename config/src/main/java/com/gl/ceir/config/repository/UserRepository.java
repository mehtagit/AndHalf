package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User getByUsername(String userName);

}
