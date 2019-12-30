package com.gl.CEIR.FileProcess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.CEIR.FileProcess.model.entity.WebActionDb;

public interface WebActionDbRepository extends JpaRepository<WebActionDb, Long> {

	@SuppressWarnings("unchecked")
	public WebActionDb save(WebActionDb webActionDb);

	public WebActionDb getByState(int state);

	public WebActionDb findFirstByState(int state);
	
}
