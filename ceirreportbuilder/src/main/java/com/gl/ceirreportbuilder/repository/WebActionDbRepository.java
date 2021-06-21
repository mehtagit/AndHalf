package com.gl.ceirreportbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.gl.ceirreportbuilder.model.WebActionDb;

@Component
public interface WebActionDbRepository extends JpaRepository<WebActionDb, Long> {

	@SuppressWarnings("unchecked")
	public WebActionDb save(WebActionDb webActionDb);

	public WebActionDb getByState(int state);

	public WebActionDb findFirstByState(int state);
	
}
