package com.gl.CEIR.FileProcess.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.CEIR.FileProcess.model.WebActionDb;




public interface WebActionDbRepository extends JpaRepository<WebActionDb, Long> {



	public WebActionDb save(WebActionDb webActionDb);

	public WebActionDb findFirstByState(int state);


	
	

}
