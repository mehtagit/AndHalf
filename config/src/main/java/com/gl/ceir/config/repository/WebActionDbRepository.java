package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.WebActionDb;


public interface WebActionDbRepository extends JpaRepository<WebActionDb, Long> {



	public WebActionDb save(WebActionDb webActionDb);

	public WebActionDb getByState(int state);


	
	

}
