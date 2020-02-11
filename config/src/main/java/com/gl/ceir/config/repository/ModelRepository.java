package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.modelRepoPojo;





public  interface ModelRepository extends JpaRepository<modelRepoPojo, Long>, JpaSpecificationExecutor<modelRepoPojo>  {

//	 public List<modelRepoPojo> getByIdOrderByIdDesc(Long id);


	public List<modelRepoPojo> getByBrandNameId(int brandNameId);

}
