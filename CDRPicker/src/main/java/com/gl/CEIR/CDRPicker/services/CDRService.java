package com.gl.CEIR.CDRPicker.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gl.CEIR.CDRPicker.Repository.CdrRepository;
import com.gl.CEIR.CDRPicker.beans.CDRFile;
import com.gl.CEIR.CDRPicker.beans.ConfigurationDetails;
import com.gl.CEIR.CDRPicker.beans.HashDetails;
import com.gl.CEIR.CDRPicker.beans.ImeiHashRecord;
import com.gl.CEIR.CDRPicker.beans.RedisParams;
import com.gl.CEIR.CDRPicker.beans.conterDetails;
import com.gl.CEIR.CDRPicker.util.Utility;

@Service
public class CDRService implements Runnable {



	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	CSVService cSVService;


	@Autowired
	Utility util;

	@Autowired
	CdrRepository cdrRepository;


	@Override
	public void run() 
	{
		int resp= 1;

		while(resp ==1 ) {
			resp = process();
		}

	}

	public int process() throws IndexOutOfBoundsException {

		//	ConfigurationDetails configurationDetails  = cdrRepository.getConfigDetails();
		ConfigurationDetails configurationDetails = new ConfigurationDetails();
		configurationDetails.setAggregationType("file");
		configurationDetails.setAggregationValue(1);
		log.info("ConifgDetails="+configurationDetails.toString());
		int runnaingVariable=0;
		int invaidCount=0;
		int duplicateCount=0;
		int validCount =0;
		int actualCounter=0;
		int repetedCount =0;

		ConcurrentHashMap<String, ImeiHashRecord>  hashTable = new ConcurrentHashMap<>();

		ConcurrentHashMap<String, List<RedisParams> > imeiDetails = new ConcurrentHashMap<>();

		try {
			conterDetails conterDetails = new conterDetails();
			String readingFileName=null;

			final File dir = new File("/home/ubuntu/cdrPicker/datafile/");

			long minDate=System.currentTimeMillis();

			String fileName=null;
			String date= null;
			String operator =null;
			if (dir.isDirectory()) { 

				File [] fileList =dir.listFiles(util.IMAGE_FILTER);


				if(fileList.length >0) {

					for (final File f : fileList ) {
						log.info("File Name "+f.getName());
						fileName =f.getName();
						date =f.getName().substring(9, 21);

						operator = f.getName().substring(0, 2);
						/*String[] splitfile =	f.getName().split("_");
						int index =splitfile.length-1;
						String lastIndexValue = splitfile[index];
						String[] dotSplit =	lastIndexValue.split("\\.");
						String codeName=dotSplit[0];
						// codeName =codeName.substring(4,18);
						log.info("DAte for parsing="+codeName);
						long millis =util.getMillis(codeName);

						if(minDate > millis) {
							minDate = millis;
						}*/

						readingFileName=f.getName();
					}

					/*String orignalDate = util.parseMillisTodate(minDate);

					readingFileName = cSVService.getActualFileName(orignalDate);*/


					log.info("FileName For Reading"+readingFileName);




					if(configurationDetails != null) {

						if(configurationDetails.getAggregationType().equals("time") ){

							while(runnaingVariable <= configurationDetails.getAggregationValue()) {

								log.info("Runnainvarable value"+runnaingVariable);
								String csvFile = "/home/ubuntu/cdrPicker/datafile//"+readingFileName;
								BufferedReader br = null;
								String line = "";
								String cvsSplitBy = ",";

								br = new BufferedReader(new FileReader(csvFile));


								int firstrow=0;

								while ((line = br.readLine()) != null) {

									if(firstrow != 0) {

										String[] csvDetails = line.split(cvsSplitBy);

										log.info("CSV araay Length ="+csvDetails.length);

										String  recordType=null;

										if(csvDetails.length > 0  && 1 < csvDetails.length) {
											recordType=csvDetails[1];
										}else {
											recordType="";
										}

										String servedImsi=null;

										if(csvDetails.length > 0  && 3 < csvDetails.length) {
											servedImsi=csvDetails[3];
										}else {
											servedImsi="";
										}


										String servedImei=null;

										if(csvDetails.length > 0  && 4 < csvDetails.length) {
											servedImei=csvDetails[4];

										}else {
											servedImei="";
										}


										String servedMsisdn=null;
										if(csvDetails.length > 0  && 2 < csvDetails.length) {
											servedMsisdn=csvDetails[2];

										}else {
											servedMsisdn="";
										}												

										String location =null;
										/*	if(csvDetails.length > 0  && 10 < csvDetails.length) {
											location=csvDetails[10];

										}else {
											location="";
										}*/

										String userType=null;

										/*	if(csvDetails.length > 0  && 13 < csvDetails.length) {
											userType=csvDetails[13];

										}else {
											userType="";
										}
										 */

										String changeTime=null;

										String exchangeIdentity=null;

										String logicalFileName=null;


										actualCounter=actualCounter+1;

										RedisParams redisParams = new RedisParams();
										redisParams.setDate(changeTime);
										redisParams.setFileName(logicalFileName);
										redisParams.setImei(servedImei);
										redisParams.setImsi(servedImsi);
										redisParams.setmSSIDN(servedMsisdn);
										redisParams.setOperator(exchangeIdentity);
										redisParams.setrATType(userType);
										redisParams.setLocation(location);

										List<RedisParams> jsonData=imeiDetails.get(servedImei);

										log.info("KEY Data------"+jsonData);

										if(jsonData == null) {

											List<RedisParams> objectList=new ArrayList<RedisParams>();
											objectList.add(redisParams);

											if(servedImei.length() >0) {
												validCount =validCount+1;
												redisParams.setCountType("Valid Count");
												log.info("Insert data in Map Imei="+servedImei+"Value="+objectList);
												imeiDetails.put(servedImei, objectList);

											}else {

												invaidCount = invaidCount+ 1;
												redisParams.setCountType("InvaildCount");
												String header="ServedIMEI,ServedIMSI,ServedMSISDN,,FileName,RATType,Location,Operator,Date,DataType";
												String record =redisParams.getImei()+","+redisParams.getImsi()+","+redisParams.getmSSIDN()+","+redisParams.getFileName()+","+redisParams.getrATType()+","+redisParams.getLocation()+","+redisParams.getOperator()+","+redisParams.getDate()+","+0;

												util.writeInFile("/home/ceirapp/cdrPrcess/outputFile//"+readingFileName+"out", header, record);
											}


										}

										else {

											List<String> msisdnList =null;	
											List<String> imsiList =null;	
											for(RedisParams params:jsonData)

											{
												msisdnList= new ArrayList<String>();
												imsiList =new ArrayList<String>();
												imsiList.add(params.getImsi());
												msisdnList.add(params.getmSSIDN());
											}

											if(msisdnList.contains(redisParams.getmSSIDN())) {

												if(imsiList.contains(redisParams.getImsi())) {

												}else {
													duplicateCount =duplicateCount+1;
													String header="ServedIMEI,ServedIMSI,ServedMSISDN,,FileName,RATType,Location,Operator,Date,DataType";
													String record =redisParams.getImei()+","+redisParams.getImsi()+","+redisParams.getmSSIDN()+","+redisParams.getFileName()+","+redisParams.getrATType()+","+redisParams.getLocation()+","+redisParams.getOperator()+","+redisParams.getDate()+","+redisParams.getCountType();

													util.writeInFile("/home/ceirapp/cdrPrcess/dummyFile//"+readingFileName+"duplicateount", header, record);

													jsonData.add(redisParams);
													validCount =validCount+1;
													redisParams.setCountType("Valid Count");
													log.info("Insert data in Map Imei="+servedImei+"Value="+jsonData);
													imeiDetails.put(servedImei, jsonData);
												}
											}

											else
											{
												jsonData.add(redisParams);

												duplicateCount =duplicateCount+1;
												String header="ServedIMEI,ServedIMSI,ServedMSISDN,,FileName,RATType,Location,Operator,Date,DataType";
												String record =redisParams.getImei()+","+redisParams.getImsi()+","+redisParams.getmSSIDN()+","+redisParams.getFileName()+","+redisParams.getrATType()+","+redisParams.getLocation()+","+redisParams.getOperator()+","+redisParams.getDate()+","+redisParams.getCountType();

												util.writeInFile("/home/ceirapp/cdrPrcess/dummyFile//"+readingFileName+"duplicateount", header, record);

												if(servedImei.length() >0) {
													validCount =validCount+1;
													redisParams.setCountType("Valid Count");
													log.info("Insert data in Map Imei="+servedImei+"Value="+jsonData);
													imeiDetails.put(servedImei, jsonData);


												}
											}
										}

									} 
									firstrow ++;

									String response =cSVService.fileMove("/home/ubuntu/cdrPicker/datafile/"+readingFileName, "/home/ubuntu/cdrPicker/movedfile/"+readingFileName);

									log.info("MoveFile Response"+response);

									runnaingVariable = runnaingVariable+2;

								}
							}

							for (String key : imeiDetails.keySet()) {

								List<RedisParams> actualdata =	imeiDetails.get(key);
								log.info("KEY----"+key);
								for(RedisParams data:actualdata) {

									String header="ServedIMEI,ServedIMSI,ServedMSISDN,,FileName,RATType,Location,Operator,Date,DataType";
									String record =data.getImei()+","+data.getImsi()+","+data.getmSSIDN()+","+data.getFileName()+","+data.getrATType()+","+data.getLocation()+","+data.getOperator()+","+data.getDate()+","+data.getCountType();

									util.writeInFile("/home/ceirapp/cdrPrcess/outputFile//"+readingFileName+"out", header, record);
									//cSVService.csvMaker(actualdata.toString(), "/home/ubuntu/cdrPicker//"+readingFileName+"out");
								}
							}


							conterDetails.setInvaidCount(invaidCount);
							conterDetails.setDuplicateCount(duplicateCount);
							conterDetails.setActualCount(validCount);
							conterDetails.setTotoaCount(actualCounter);

							cSVService.csvMaker(conterDetails.toString(), "/home/ceirapp/cdrPrcess//CounterDetails.csv");

							invaidCount=0;
							duplicateCount=0;
							validCount =0;
							runnaingVariable=0;
							actualCounter=0;
							imeiDetails.clear();

							return 1 ;
						}


						else {

							log.info("Counter Value");
							while(runnaingVariable < configurationDetails.getAggregationValue()) {

								log.info("Runnainvarable value"+runnaingVariable);
								String csvFile = "/home/ubuntu/cdrPicker/datafile//"+readingFileName;
								BufferedReader br = null;
								String line = "";
								String cvsSplitBy = ",";

								br = new BufferedReader(new FileReader(csvFile));


								int firstrow=0;

								while ((line = br.readLine()) != null) {

									if(firstrow != 0) {

										String[] csvDetails = line.split(cvsSplitBy);

										log.info("CSV araay Length ="+csvDetails.length);

										String  recordType=null;

										if(csvDetails.length > 0  && 1 < csvDetails.length) {
											recordType=csvDetails[1];
										}else {
											recordType="";
										}


										String servedImsi=csvDetails[2];

										/*if(csvDetails.length > 0  && 2 < csvDetails.length) {
											servedImsi=csvDetails[2];
										}else {
											servedImsi="";
										}*/


										String servedImei=null;

										if(csvDetails.length > 0  && 3 < csvDetails.length) {
											servedImei=csvDetails[3];

										}else {
											servedImei="";
										}

										String servedMsisdn=null;
										if(csvDetails.length > 0  && 4 < csvDetails.length) {
											servedMsisdn=csvDetails[4];

										}else {
											servedMsisdn="";
										}												

										String location =null;
										if(csvDetails.length > 0  && 22 < csvDetails.length) {
											location=csvDetails[22];

										}else {
											location="";
										}

										String userType=null;

										if(csvDetails.length > 0  && 13 < csvDetails.length) {
											userType=csvDetails[13];

										}else {
											userType="";
										}


										String changeTime=date;
										String exchangeIdentity=operator;
										String logicalFileName=fileName;

										actualCounter=actualCounter+1;

										RedisParams redisParams = new RedisParams();
										redisParams.setDate(changeTime);
										redisParams.setFileName(logicalFileName);
										redisParams.setImei(servedImei);
										redisParams.setImsi(servedImsi);
										redisParams.setmSSIDN(servedMsisdn);
										redisParams.setOperator(exchangeIdentity);
										redisParams.setrATType(userType);
										redisParams.setLocation(location);
										redisParams.setRecordType(recordType);

										List<RedisParams> jsonData=imeiDetails.get(servedImei);

										log.info("KEY Data------"+jsonData);

										if(jsonData == null) {

											List<RedisParams> objectList=new ArrayList<RedisParams>();
											objectList.add(redisParams);

											if(servedImei.length() >0 && servedImsi != null ) {
												validCount =validCount+1;
												redisParams.setCountType("ValidCount");
												log.info("Insert data in Map Imei="+servedImei+"Value="+objectList);
												imeiDetails.put(servedImei, objectList);

												String header="ServedIMEI,ServedIMSI,ServedMSISDN,,FileName,RATType,Location,Operator,Date,DataType,RecordType";
												String record =redisParams.getImei()+","+redisParams.getImsi()+","+redisParams.getmSSIDN()+","+redisParams.getFileName()+","+redisParams.getrATType()+","+redisParams.getLocation()+","+redisParams.getOperator()+","+redisParams.getDate()+","+redisParams.getCountType()+","+redisParams.getRecordType();
												util.writeInFile("/home/ceirapp/cdrPrcess/locationFile//"+readingFileName+"LocationFile", header, record);


											}else {

												invaidCount = invaidCount+ 1;
												redisParams.setCountType("InvalidCount");
												String header="ServedIMEI,ServedIMSI,ServedMSISDN,FileName,RATType,Location,Operator,Date,DataType,RecordType";
												String record =redisParams.getImei()+","+redisParams.getImsi()+","+redisParams.getmSSIDN()+","+redisParams.getFileName()+","+redisParams.getrATType()+","+redisParams.getLocation()+","+redisParams.getOperator()+","+redisParams.getDate()+","+redisParams.getCountType()+","+redisParams.getRecordType();

												util.writeInFile("/home/ceirapp/cdrPrcess/outputFile//"+readingFileName+"out", header, record);
											}


										}
										else {

											List<String> locationList =null;
											List<String> msisdnList =null;	
											List<String> imsiList =null;	
											for(RedisParams params:jsonData)

											{
												msisdnList= new ArrayList<String>();
												imsiList =new ArrayList<String>();
												locationList=new ArrayList<String>();

												imsiList.add(params.getImsi());
												msisdnList.add(params.getmSSIDN());
												locationList.add(params.getLocation());
											}

											if(! locationList.contains(redisParams.getLocation())) {

												String header="ServedIMEI,ServedIMSI,ServedMSISDN,FileName,RATType,Location,Operator,Date,DataType,RecordType";
												String record =redisParams.getImei()+","+redisParams.getImsi()+","+redisParams.getmSSIDN()+","+redisParams.getFileName()+","+redisParams.getrATType()+","+redisParams.getLocation()+","+redisParams.getOperator()+","+redisParams.getDate()+","+redisParams.getCountType()+","+redisParams.getRecordType();

												util.writeInFile("/home/ceirapp/cdrPrcess/locationFile//"+readingFileName+"LocationFile", header, record);
											}



											if(msisdnList.contains(redisParams.getmSSIDN())) {

												if(imsiList.contains(redisParams.getImsi())) {
													repetedCount =repetedCount+1;
												}else {
													duplicateCount =duplicateCount+1;
													String header="ServedIMEI,ServedIMSI,ServedMSISDN,FileName,RATType,Location,Operator,Date,DataType,RecordType";
													String record =redisParams.getImei()+","+redisParams.getImsi()+","+redisParams.getmSSIDN()+","+redisParams.getFileName()+","+redisParams.getrATType()+","+redisParams.getLocation()+","+redisParams.getOperator()+","+redisParams.getDate()+","+"DuplicateCount"+","+redisParams.getRecordType();

													util.writeInFile("/home/ceirapp/cdrPrcess/outputfile//"+readingFileName+"out", header, record);

													jsonData.add(redisParams);
													validCount =validCount+1;
													redisParams.setCountType("Valid Count");
													log.info("Insert data in Map Imei="+servedImei+"Value="+jsonData);
													imeiDetails.put(servedImei, jsonData);
												}
											}

											else
											{
												jsonData.add(redisParams);

												duplicateCount =duplicateCount+1;
												String header="ServedIMEI,ServedIMSI,ServedMSISDN,FileName,RATType,Location,Operator,Date,DataType,RecordType";
												String record =redisParams.getImei()+","+redisParams.getImsi()+","+redisParams.getmSSIDN()+","+redisParams.getFileName()+","+redisParams.getrATType()+","+redisParams.getLocation()+","+redisParams.getOperator()+","+redisParams.getDate()+","+"DuplicateCount"+","+redisParams.getRecordType();

												util.writeInFile("/home/ceirapp/cdrPrcess/outputfile//"+readingFileName+"out", header, record);

												if(servedImei.length() >0) {
													validCount =validCount+1;
													redisParams.setCountType("Valid Count");
													log.info("Insert data in Map Imei="+servedImei+"Value="+jsonData);
													imeiDetails.put(servedImei, jsonData);

												}
											}
										}
									}
									firstrow ++;
								}
								String response =cSVService.fileMove("/home/ubuntu/cdrPicker/datafile/"+readingFileName, "/home/ubuntu/cdrPicker/movedfile/"+readingFileName);

								log.info("MoveFile Response"+response);

								runnaingVariable = runnaingVariable+1;

							}



						}


						for (String key : imeiDetails.keySet()) {

							List<RedisParams> actualdata =	imeiDetails.get(key);
							log.info("KEY----"+key);

							for(RedisParams data:actualdata) {

								String header="ServedIMEI,ServedIMSI,ServedMSISDN,FileName,RATType,Location,Operator,Date,DataType,RecordType";
								String record =data.getImei()+","+data.getImsi()+","+data.getmSSIDN()+","+data.getFileName()+","+data.getrATType()+","+data.getLocation()+","+data.getOperator()+","+data.getDate()+","+data.getCountType()+","+data.getRecordType();

								util.writeInFile("/home/ceirapp/cdrPrcess/outputFile//"+readingFileName+"out", header, record);
							}
						}


						conterDetails.setInvaidCount(invaidCount);
						conterDetails.setDuplicateCount(duplicateCount);
						conterDetails.setActualCount(validCount);
						conterDetails.setTotoaCount(actualCounter);

						String header="InvalidCount,ValidCount,RepetedCount,DuplicateCount,TotalCount,FileName";
						String record =""+invaidCount+","+validCount+","+repetedCount+","+duplicateCount+","+actualCounter+","+readingFileName;

						util.writeInFile("/home/ceirapp/cdrPrcess/counterDetails.csv", header, record);
						invaidCount=0;
						duplicateCount=0;
						validCount =0;
						runnaingVariable=0;
						actualCounter=0;
						imeiDetails.clear();

						return 1 ;
					}
				}
			}
			else {
				log.info("No file Present Here");

				if(configurationDetails.getAggregationType() == null || configurationDetails.getAggregationType().equals(null)) {
					log.info("Type is Null");
					Thread.sleep(300000);
				}

				else if(configurationDetails.getAggregationType().equalsIgnoreCase("time")) {

					runnaingVariable = runnaingVariable+5;
					log.info("Running Variable="+runnaingVariable);

					if(runnaingVariable >= configurationDetails.getAggregationValue()){

						for (String key : imeiDetails.keySet()) {
							log.info(key);
							List<RedisParams> actualdata =	imeiDetails.get(key);
							cSVService.csvMaker(actualdata.toString(), "/home/ceirapp/cdrPrcess/outputFile//"+readingFileName+"out");
						}

						conterDetails.setInvaidCount(invaidCount);
						conterDetails.setDuplicateCount(duplicateCount);
						conterDetails.setActualCount(validCount);
						conterDetails.setTotoaCount(actualCounter);

						String header="InvalidCount,ValidCount,,RepetedCount,DuplicateCount,TotalCount,FileName";
						String record =""+invaidCount+","+validCount+","+repetedCount+","+duplicateCount+","+actualCounter+","+readingFileName;

						util.writeInFile("/home/ceirapp/cdrPrcess/counterDetails.csv", header, record);
						invaidCount=0;
						duplicateCount=0;
						validCount =0;
						runnaingVariable=0;
						actualCounter=actualCounter+1;
						imeiDetails.clear();
						return 1;
					}
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Error Occuree="+e);
			return 1;
		}
		return 1;

	}



}
