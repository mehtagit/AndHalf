package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;

public interface DeviceSnapShotRepository extends JpaRepository<DeviceSnapShot, ImeiMsisdnIdentity> {

}
