package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.ConsignmentMgmt;


public interface ConsignmentRepository extends JpaRepository<ConsignmentMgmt, Long>, JpaSpecificationExecutor<ConsignmentMgmt> {

	public ConsignmentMgmt save(ConsignmentMgmt consignment);

	public ConsignmentMgmt getByConsignmentNumber(String consignmEntNumber);

	public List<ConsignmentMgmt> getByUserIdOrderByIdDesc(Long userId);

	public ConsignmentMgmt getByTxnId(String txnId);


	public List<ConsignmentMgmt> findByUser_id(int id);


}
