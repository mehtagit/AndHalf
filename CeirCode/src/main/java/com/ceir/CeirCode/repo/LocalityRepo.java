package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.Locality;

public interface LocalityRepo extends JpaRepository<Locality, Long>,JpaSpecificationExecutor<Locality>{

	

}
