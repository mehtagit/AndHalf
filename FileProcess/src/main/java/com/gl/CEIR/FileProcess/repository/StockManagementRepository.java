package com.gl.CEIR.FileProcess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.CEIR.FileProcess.model.entity.StockMgmt;

public interface StockManagementRepository extends JpaRepository<StockMgmt, Long>, JpaSpecificationExecutor<StockMgmt> {

	public StockMgmt save(StockMgmt distributerManagement);
	
	public StockMgmt getByTxnId(String txnId);

	public List<StockMgmt> findByRoleTypeAndUserId(String moduleType, Long userId);

	public StockMgmt findByRoleTypeAndTxnId(String moduleType, String txnId);

	public void deleteByTxnId(String txnId);

}
