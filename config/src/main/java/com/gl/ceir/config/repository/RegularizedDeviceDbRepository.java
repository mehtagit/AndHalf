package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.factory.CustomerCareRepo;
import com.gl.ceir.config.model.RegularizeDeviceDb;

public interface RegularizedDeviceDbRepository extends JpaRepository<RegularizeDeviceDb, Long>, 
JpaSpecificationExecutor<RegularizeDeviceDb	>, CustomerCareRepo<RegularizeDeviceDb> {

	public RegularizeDeviceDb getByDeviceSerialNumber(String serialNumber);

	public void deleteByDeviceSerialNumber(String serialNumber);

	public List<RegularizeDeviceDb> getByNid(String nid);

	public RegularizeDeviceDb getByFirstImei(String imei1);

	public Long countByNid(String nid);
	
	public Long countByNidAndDeviceStatus(String nid,int status);
	public Long countByNidAndTaxPaidStatus(String nid,int status);
	
	public Long countByNidIgnoreCaseAndTaxPaidStatus(String nid,int status);

	public RegularizeDeviceDb getByTxnId(String txnid);

	@Query("SELECT r FROM RegularizeDeviceDb r WHERE firstImei = :imei OR secondImei = :imei OR thirdImei = :imei OR fourthImei = :imei") 
	public RegularizeDeviceDb getByImei(String imei);

	@Query("SELECT count(r) FROM RegularizeDeviceDb r WHERE firstImei = :imei OR secondImei = :imei OR thirdImei = :imei OR fourthImei = :imei") 
	long countByImei(String imei);

	
	public List<RegularizeDeviceDb> findByTxnId(String txnid);
	
	//boolean existsByFirstImeiAndSecondImeiAndThirdImeiAndForthImei(); 
}
