package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.WebActionTbl;

public interface WebActionRepo extends JpaRepository<WebActionTbl, Long> ,JpaSpecificationExecutor<WebActionTbl>{ 

	
}
