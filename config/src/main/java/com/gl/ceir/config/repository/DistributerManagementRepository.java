package com.gl.ceir.config.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.model.StockMgmt;

public interface DistributerManagementRepository extends JpaRepository<StockMgmt, Long> {



	public StockMgmt save(StockMgmt distributerManagement);

	public List<StockMgmt> findByRoleTypeAndUserId(String moduleType,Long userId);


	public StockMgmt findByRoleTypeAndTxnId(String moduleType,String txnId);


	public void deleteByTxnId(String txnId);



	
	
	
}
