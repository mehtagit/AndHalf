package com.ceir.CeirCode.repo;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserProfile;
public interface UserProfileRepo extends JpaRepository<UserProfile, Long> ,JpaSpecificationExecutor<UserProfile>{
	
	
	public UserProfile save(UserProfile userprofile); 
	public UserProfile findById(long id); 
	public UserProfile findByUser_Id(long id);                            
 

} 
