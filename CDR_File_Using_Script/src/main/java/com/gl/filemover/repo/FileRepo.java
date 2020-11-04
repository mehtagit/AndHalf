package com.gl.filemover.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gl.filemover.entity.CDRFileRecords;
public interface FileRepo extends JpaRepository<CDRFileRecords, Long>{

	public List<CDRFileRecords> findByOperatorAndStatusSIG1AndStatusSIG2(String operator,String statusSig1,String statusSig2);
//	public List<CDRFileRecords> findByOperatorAndStatusSIG1OrStatusSIG2(String operator,String statusSig1,String statusSig2);
	
	public List<CDRFileRecords> findByOperatorAndStatusSIG1AndCdrRecdServer(String operator,String statusSig1,String cdrRecdServer);
	public List<CDRFileRecords> findByOperatorAndStatusSIG2AndCdrRecdServer(String operator,String statusSig1,String cdrRecdServer);
	
	@Transactional
	@Modifying
	@Query(value="update cdr_file_records_db  set STATUS_SIG1 = :sig1Status ,STATUS_SIG2 = :sig2Status, STS_SIG1_UTIME=:sig1_utime , STS_SIG2_UTIME=:sig2_utime where ID = :id",nativeQuery = true)
	public int modifyFileStatus(String sig1Status,String sig2Status,String sig1_utime,String sig2_utime,long id);
	 

	
}
