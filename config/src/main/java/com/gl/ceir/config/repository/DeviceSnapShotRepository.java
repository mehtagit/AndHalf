package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.DeviceSnapShot;

public interface DeviceSnapShotRepository extends JpaRepository<DeviceSnapShot, Long> {

	public List<DeviceSnapShot> findByMsisdn(Long msisdn);
}
