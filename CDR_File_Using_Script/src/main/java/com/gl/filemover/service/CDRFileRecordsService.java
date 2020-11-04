package com.gl.filemover.service;

import java.util.List;
import java.util.Optional;

import com.gl.filemover.entity.CDRFileRecords;

public interface CDRFileRecordsService {
	public CDRFileRecords save(CDRFileRecords entity);
	public Optional<List<CDRFileRecords>> findByOperatorAndStatusSIG1AndStatusSIG2(String operator,String statusSig1,String statusSig2);
	public Optional<List<CDRFileRecords>> findByOperatorAndStatusSIG1AndCdrRecdServer(String operator,String statusSig1,String cdrRecdServer);
	public Optional<List<CDRFileRecords>> findByOperatorAndStatusSIG2AndCdrRecdServer(String operator,String statusSig2,String cdrRecdServer);
	public boolean modifyFileStatus(String sig1Status,String sig2Status,String sig1_utime,String sig2_utime,long id);
	/*	public Optional<List<CDRFileRecords>> findByStatusSIG1AndStatusSIG2(String statusSig1,String statusSig2); */
	
}
