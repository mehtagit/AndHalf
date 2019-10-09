package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.StolenandRecoveryDetails;


public interface StolenAndRecoveryRepository extends JpaRepository<StolenandRecoveryDetails, Long> {


	public StolenandRecoveryDetails save(StolenandRecoveryDetails stolenandRecoveryDetails);

	public List<StolenandRecoveryDetails> findByUserIdAndSourceType(Long userId,String sourceType);



}
