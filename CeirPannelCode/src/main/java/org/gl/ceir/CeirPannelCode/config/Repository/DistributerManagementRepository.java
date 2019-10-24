package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.gl.ceir.CeirPannelCode.config.Model.DistributerManagement;

public interface DistributerManagementRepository extends JpaRepository<DistributerManagement, Long> {



	public DistributerManagement save(DistributerManagement distributerManagement);

	public List<DistributerManagement> findByModuleTypeAndUserId(String moduleType,Long userId);


	public DistributerManagement findByModuleTypeAndTxnId(String moduleType,String txnId);


	public void deleteByTxnId(String txnId);



	@Transactional
	@Modifying
	@Query(value = "UPDATE distributer_management  set importer_id=?1 ,user_name=?2 ,invoice_number=?3 ,updated_on= ?4,quantity=?5  where txn_id= ?6 ",
	nativeQuery = true)
	void updateUser(Long importerId,String userName,String invoiceNumber,Date updateOn,int quantity,String txnId);


	@Transactional
	@Modifying
	@Query(value = "UPDATE distributer_management  set importer_id=?1 ,user_name=?2 ,invoice_number=?3 ,updated_on= ?4,quantity=?5,file_status=?6,file_name=?7 where txn_id= ?8 ",
	nativeQuery = true)
	void updateUserFileStatus(Long importerId,String userName,String invoiceNumber,Date updateOn,int quantity,String fileStatus,String fileName,String txnId);

	
	
}
