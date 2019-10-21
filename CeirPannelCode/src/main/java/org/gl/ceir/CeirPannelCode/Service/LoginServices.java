
package org.gl.ceir.CeirPannelCode.Service;

import org.gl.ceir.CeirPannelCode.Model.userTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginServices  extends JpaRepository<userTest, Integer>{
	
	public userTest findByUsertypeAndUsernameAndPassword(String usertype,String username,String password);
	
}
