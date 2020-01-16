package com.ceir.CeirCode.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.Userrole;
public interface UserRoleRepo extends JpaRepository<Userrole, Integer>{
	public Userrole save(Userrole role);
	public List<Userrole> findAll(); 
	public List<Userrole> findByUserData_Id(long id);   
	public Userrole findByUserData_IdAndUsertypeData_Id(long userid,long usertypeId);
}
 