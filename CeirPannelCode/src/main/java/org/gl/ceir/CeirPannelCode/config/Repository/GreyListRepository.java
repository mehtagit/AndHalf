package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.GreyList;

public interface GreyListRepository extends JpaRepository<GreyList, Long> {

	public GreyList save (GreyList greyList);

	public void deleteByImei(Long imei);

	
	
	

}
