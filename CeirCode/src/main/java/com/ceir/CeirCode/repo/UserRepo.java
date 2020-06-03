package com.ceir.CeirCode.repo;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ceir.CeirCode.model.User;
public interface UserRepo extends JpaRepository<User, Long> ,JpaSpecificationExecutor<User>{
    
	@Transactional
	public User  save(User u); 
	public User findById(long id);
	public User findByUsernameAndPassword(String username,String password);
	public User findByUsername(String user); 
	public User findByUserProfile_Id(long id);
	long countByUsertype_Id(long id);
	public void deleteById(long id);
	public List<User> findByUsertype_IdAndCurrentStatus(long usertypeId,Integer status); 	
	public List<User> findByUsertype_IdAndCurrentStatusAndUserProfile_ArrivalPortAndUserProfile_PortAddress(long usertypeId,Integer status,Integer port,Integer address); 	
	
}
