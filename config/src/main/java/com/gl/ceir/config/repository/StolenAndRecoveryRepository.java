package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.StolenandRecoveryMgmt;


public interface StolenAndRecoveryRepository extends JpaRepository<StolenandRecoveryMgmt, Long> {


	public StolenandRecoveryMgmt save(StolenandRecoveryMgmt stolenandRecoveryDetails);

	//public List<StolenandRecoveryMgmt> findByUserIdAndSourceType(Long userId,String sourceType);



}
