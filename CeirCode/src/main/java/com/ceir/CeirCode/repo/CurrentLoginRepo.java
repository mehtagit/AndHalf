package com.ceir.CeirCode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.CurrentLogin;

public interface CurrentLoginRepo extends JpaRepository<CurrentLogin,Long>{

	public boolean existsByCurrentUserLogin_Id(long id);
	public List<CurrentLogin> findByCurrentUserLogin_Id(long id);
	public void deleteByCurrentUserLogin_Id(long id);
}
