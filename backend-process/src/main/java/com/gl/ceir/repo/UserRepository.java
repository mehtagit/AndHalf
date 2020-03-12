package com.gl.ceir.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.ceir.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, 
JpaSpecificationExecutor<User>{
	
	public User getByUsername(String userName);
	public User getById(long id);
	
	@Modifying
	@Query("delete from users u where u.id in ?1")
	void deleteUsersWithIds(List<Long> ids);

}
