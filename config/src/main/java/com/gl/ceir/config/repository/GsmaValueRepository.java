package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.GsmaValueModel;

public  interface GsmaValueRepository extends JpaRepository<GsmaValueModel, Long>, JpaSpecificationExecutor<GsmaValueModel>  {

	public GsmaValueModel getByDeviceId(int deviceId);

}

