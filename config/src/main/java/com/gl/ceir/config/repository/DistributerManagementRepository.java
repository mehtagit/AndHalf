package com.gl.ceir.config.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.model.DistributerManagement;

public interface DistributerManagementRepository extends JpaRepository<DistributerManagement, Long> {



	public DistributerManagement save(DistributerManagement distributerManagement);

	public List<DistributerManagement> findByModuleTypeAndUserId(String moduleType,Long userId);


	public DistributerManagement findByModuleTypeAndTxnId(String moduleType,String txnId);


	public void deleteByTxnId(String txnId);



	@Transactional
	@Modifying
	@Query(value = "UPDATE distributer_management  set importer_id=?1 ,user_name=?2 ,invoice_number=?3 ,updated_on= ?4  where txn_id= ?5 ",
	nativeQuery = true)
	void updateUser(Long importerId,String userName,String invoiceNumber,Date updateOn,String txnId);



}
