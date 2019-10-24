package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.gl.ceir.CeirPannelCode.config.Model.Consignment;
import org.gl.ceir.CeirPannelCode.config.Model.Documents;


public interface ConsignmentRepository extends JpaRepository<Consignment, Long> {


	public Consignment save(Consignment consignment);

	
	
	
	@Transactional
	@Modifying
	@Query(value = "update consignment set consignment_number=?1,importer_name=?2,modified_on=now(),supplier_id=?3,supplier_name=?4,expected_arrival_port=?5,expected_arrivaldate=?6,"
			+ "expected_dispatche_date=?7,organisation_country=?8,quantity=?9 where txn_id=?10",
			nativeQuery = true)
	void updateConsignment(String consignmentNumber,String importerName,String supplierId,String supplierName,
			String arraivalport,String arrivaelDate,String dispatchdate ,String orgCountry,int quantity, String txnId);


	@Transactional
	@Modifying
	@Query(value = " update consignment set consignment_number=?1,importer_name=?2,modified_on=now(),supplier_id=?3,supplier_name=?4,expected_arrival_port=?5,expected_arrivaldate=?6,expected_dispatche_date=?7,organisation_country=?8,consignment_status=?9,"
			+ "file_name=?10,file_status=?11,quantity=?12 where txn_id=?13",
			nativeQuery = true)
	void updateConsignmentFileStatus(String consignmentNumber,String importerName,String supplierId,String supplierName,
			String arraivalport,String arrivaelDate,String dispatchdate ,String orgCountry,String consignmentStatus,String fileName ,String fileSatus,
			int quantity ,String txnId);



	public List<Consignment> getByImporterIdOrderByIdDesc(Long importerId);

	public Consignment getByTxnId(String txnId);


	public void deleteByTxnId(String txnId);

	public List<Consignment> findBycreatedOnBetweenAndImporterId(Date from, Date to,Long importerId);

	public List<Consignment> getByFileStatusOrderByIdDesc(String fileStatus);


	@Transactional
	@Modifying
	@Query(value = "UPDATE consignment  set consignment_status= ?1  where txn_id= ?2",
	nativeQuery = true)
	void withdrawStatus(String txnStatus,String txnId);

	
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE consignment  set tax_paid_status= ?1,consignment_status= ?2  where txn_id= ?3",
	nativeQuery = true)
	void updateUser(String taxPaidStatus,String consigmentStatus,String txnId);


	@Query(value = "select id, consignment_number,consignment_status,created_on,file_name,file_status,importer_id,importer_name,modified_on,supplier_id,supplier_name,tax_paid_status,total_price,txn_id,expected_arrival_port,expected_arrivaldate,expected_dispatche_date,organisation_country  from consignment where importer_id=?1 OR date(modified_on) between ?2 and ?3 OR tax_paid_status =?4 OR "
			+ "file_status = ?5 OR consignment_status = ?6 ",
			nativeQuery = true)
	List<Consignment> viewUser(Long importerId,String  startDate,String endDate,String taxPaidStatus,String fileStatus,String consignmentStatus);



}
