package com.gl.ceir.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.entity.UserProfile;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile>{
	
	@SuppressWarnings("unchecked")
	public UserProfile save(UserProfile userprofile); 
	public UserProfile findById(long id); 
	public UserProfile findByUser_Id(long id);
	public UserProfile findByUserId(long id);

}