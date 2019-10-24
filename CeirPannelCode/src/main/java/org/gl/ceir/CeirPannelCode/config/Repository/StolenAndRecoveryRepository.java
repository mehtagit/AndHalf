package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.StolenandRecoveryDetails;


public interface StolenAndRecoveryRepository extends JpaRepository<StolenandRecoveryDetails, Long> {


	public StolenandRecoveryDetails save(StolenandRecoveryDetails stolenandRecoveryDetails);

	public List<StolenandRecoveryDetails> findByUserIdAndSourceType(Long userId,String sourceType);



}
