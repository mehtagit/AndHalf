package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.UserPasswordHistory;

public interface UserPasswordHistoryRepo extends JpaRepository<UserPasswordHistory, Long>{
	public boolean existsByPasswordAndUserPassword_Id(String password,long id);
	public UserPasswordHistory findTopByUserPassword_IdOrderByIdAsc(long id);
	public long countByUserPassword_Id(long id);
}
