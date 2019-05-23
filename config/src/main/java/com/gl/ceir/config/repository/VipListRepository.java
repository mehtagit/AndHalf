package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.VipList;

public interface VipListRepository extends JpaRepository<VipList, Long> {
	public VipList findByMsisdn(Long msisdn);

	public VipList findByImei(Long imei);

	public VipList findByMsisdnAndImei(Long msisdn, Long imei);
}
