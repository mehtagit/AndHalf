package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.ConsignmentMgmt;


public interface ConsignmentRepository extends JpaRepository<ConsignmentMgmt, Long>, JpaSpecificationExecutor<ConsignmentMgmt> {

	public ConsignmentMgmt save(ConsignmentMgmt consignment);

	public ConsignmentMgmt getByConsignmentNumber(String consignmEntNumber);

	public List<ConsignmentMgmt> getByUserIdOrderByIdDesc(Long userId);

	public ConsignmentMgmt getByTxnId(String txnId);


	public Page<ConsignmentMgmt> findByUser_id(Specification<ConsignmentMgmt> specification, Pageable pg);


}
