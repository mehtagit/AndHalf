package com.gl.ceir.config.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.RequestCountAndQuantity;
import com.gl.ceir.config.model.StockMgmt;

import io.lettuce.core.dynamic.annotation.Param;

public interface DistributerManagementRepository extends JpaRepository<StockMgmt, Long>, JpaSpecificationExecutor<StockMgmt> {



	public StockMgmt save(StockMgmt distributerManagement);

	public List<StockMgmt> findByRoleTypeAndUserId(String moduleType,Long userId);


	public StockMgmt findByRoleTypeAndTxnId(String moduleType,String txnId);


	public void deleteByTxnId(String txnId);

	@Query(value="select new com.gl.ceir.config.model.RequestCountAndQuantity(count(sm.id) as count, sum(sm.quantity) as quantity) from StockMgmt sm "
			+ "where sm.userId =:userId and sm.stockStatus =:stockStatus")
	public RequestCountAndQuantity getStockCountAndQuantity( @Param("userId") long userId, @Param("stockStatus") Integer stockStatus);
	
}
