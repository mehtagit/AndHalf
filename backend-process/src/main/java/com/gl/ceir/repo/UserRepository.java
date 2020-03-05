package com.gl.ceir.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.ceir.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User getByUsername(String userName);
	public User getById(long id);

}
