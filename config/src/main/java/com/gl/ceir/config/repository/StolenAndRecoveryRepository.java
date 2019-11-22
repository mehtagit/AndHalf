package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.StolenandRecoveryMgmt;


public interface StolenAndRecoveryRepository extends JpaRepository<StolenandRecoveryMgmt, Long>,JpaSpecificationExecutor<StolenandRecoveryMgmt> {


	public StolenandRecoveryMgmt save(StolenandRecoveryMgmt stolenandRecoveryDetails);

	public List<StolenandRecoveryMgmt> findByUserIdAndRoleType(Long userId,String roleType);

	public void deleteByTxnId(String txnId);

	public StolenandRecoveryMgmt getByTxnId(String txnid);

	public StolenandRecoveryMgmt getById(Long id);

	public void deleteById(Long id);

}
