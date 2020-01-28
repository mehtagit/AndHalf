package com.ceir.CeirCode.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ceir.CeirCode.model.UserProfile;
public interface UserProfileRepo extends JpaRepository<UserProfile, Long> ,JpaSpecificationExecutor<UserProfile>{

	public UserProfile findById(long id); 
	public UserProfile findByUser_Id(long id);                            
    public UserProfile findByPhoneNo(String phoneNo);
    public UserProfile findByEmail(String email);
    

} 
