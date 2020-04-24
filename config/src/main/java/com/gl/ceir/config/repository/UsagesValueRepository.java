package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.GsmaValueModel;
import com.gl.ceir.config.model.UsagesValueModel;

public interface UsagesValueRepository    extends JpaRepository<UsagesValueModel, Long>, JpaSpecificationExecutor<UsagesValueModel> {

	 public UsagesValueModel getByMsisdn(long msisdn);
	 
	 public UsagesValueModel getByImei(long imei);

//	public UsagesValueModel getByImei(String imei);

}
