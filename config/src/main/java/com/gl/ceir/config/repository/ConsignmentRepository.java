package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.RequestCountAndQuantity;

import io.lettuce.core.dynamic.annotation.Param;


public interface ConsignmentRepository extends JpaRepository<ConsignmentMgmt, Long>, JpaSpecificationExecutor<ConsignmentMgmt> {

	public ConsignmentMgmt save(ConsignmentMgmt consignment);

	public ConsignmentMgmt getByConsignmentNumber(String consignmEntNumber);

	public List<ConsignmentMgmt> getByUserIdOrderByIdDesc(Long userId);

	public ConsignmentMgmt getByTxnId(String txnId);

	public List<ConsignmentMgmt> findByUser_id(int id);
	

	@Query(value="select new com.gl.ceir.config.model.RequestCountAndQuantity(count(c.id) as count, sum(c.quantity) as quantity) from ConsignmentMgmt c "
			+ "where c.userId =:userId and c.consignmentStatus =:consignmentStatus")
	public RequestCountAndQuantity getConsignmentCountAndQuantity( @Param("userId") Integer userId, @Param("consignmentStatus") Integer consignmentStatus);

}
