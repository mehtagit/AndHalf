package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;

public interface DeviceSnapShotRepository extends JpaRepository<DeviceSnapShot, ImeiMsisdnIdentity> {

	public List<DeviceSnapShot> findByImeiMsisdnIdentityImei(Long imei);

	public List<DeviceSnapShot> findByImeiMsisdnIdentityMsisdn(Long msisdn);
}
