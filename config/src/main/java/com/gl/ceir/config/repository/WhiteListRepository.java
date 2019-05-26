package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.WhiteList;

public interface WhiteListRepository extends JpaRepository<WhiteList, Long> {
	public WhiteList findByMsisdn(Long msisdn);

	public WhiteList findByImei(Long imei);

	public WhiteList findByMsisdnAndImei(Long msisdn, Long imei);

	public void deleteByMsisdnAndImei(Long msisdn, Long imei);
}
