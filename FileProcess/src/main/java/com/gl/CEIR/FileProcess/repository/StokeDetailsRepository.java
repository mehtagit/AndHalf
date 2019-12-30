package com.gl.CEIR.FileProcess.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gl.CEIR.FileProcess.model.DeviceDb;


public interface StokeDetailsRepository extends JpaRepository<DeviceDb, Long> {


	public	List<DeviceDb>  getByImporterTxnId(String txnId); 

	public void deleteByTxnId(String txnId);



	public DeviceDb getByImeiEsnMeid(String device);





}
