package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.BlackList;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
	public BlackList findByMsisdn(Long msisdn);

	public BlackList findByImei(Long imei);

	public BlackList findByMsisdnAndImei(Long msisdn, Long imei);
}
