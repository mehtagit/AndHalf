package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.model.RequestCountAndQuantity;
import com.gl.ceir.config.model.StolenandRecoveryMgmt;

import io.lettuce.core.dynamic.annotation.Param;


public interface StolenAndRecoveryRepository extends JpaRepository<StolenandRecoveryMgmt, Long>,JpaSpecificationExecutor<StolenandRecoveryMgmt> {


	public StolenandRecoveryMgmt save(StolenandRecoveryMgmt stolenandRecoveryDetails);

	public List<StolenandRecoveryMgmt> findByUserIdAndRoleType(Long userId,String roleType);

	public void deleteByTxnId(String txnId);

	public StolenandRecoveryMgmt getByTxnId(String txnid);
	
	@Query(value="select new com.gl.ceir.config.model.RequestCountAndQuantity(count(srm.id) as count) from StolenandRecoveryMgmt srm "
			+ "where srm.userId =:userId and srm.fileStatus =:fileStatus and srm.requestType =:requestType")
	public RequestCountAndQuantity getStolenandRecoveryCount( @Param("userId") long userId, @Param("fileStatus") Integer fileStatus,
			@Param("requestType") String requestType);
}
