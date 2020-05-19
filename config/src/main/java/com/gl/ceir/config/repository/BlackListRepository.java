package com.gl.ceir.config.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.BlackList;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> , JpaSpecificationExecutor<BlackList> {
	
	@SuppressWarnings("unchecked")
	public BlackList save(BlackList blackList);
	
	public BlackList findByMsisdn(Long msisdn);

	public BlackList findByImei(String imei);
	
	public Optional<BlackList> findById(Long id);
	 
}