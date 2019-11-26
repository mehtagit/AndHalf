package com.gl.CEIR.CDRPicker.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.CEIR.CDRPicker.beans.CDRFile;
import com.gl.CEIR.CDRPicker.beans.HashDetails;
import com.gl.CEIR.CDRPicker.beans.ImeiHashRecord;

@Service
public class TestService implements Runnable{

	@Autowired
	CSVService cSVService;

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void run() {

		ConcurrentHashMap<String, List<HashDetails>>  hashTable = new ConcurrentHashMap<String, List<HashDetails>>();
		int[] counterDetais  = new int[10];
		try {
			/*	String csvFile = "/home/ubuntu/cdrPicker/datafile/PNH01ZMSC71-20181001001048804.dat.csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {



				String[] csvDetails = line.split(cvsSplitBy);

				String csvData.getServedIMSI()=csvDetails[2];
				String csvData.getServedIMEI()=csvDetails[3];
				String csvData.getServedMSISDN()=csvDetails[4];
				String changeTime=csvDetails[14];
				String exchangeIdentity=csvDetails[28];
				String userType=csvDetails[42];
				String logicalFileName=csvDetails[52];
			 */

			List<CDRFile> file = cSVService.readFileFromCSV("/home/ubuntu/cdrPicker/datafile//PNH01ZMSC71-20181001001048804.dat.csv");

			log.info("SIZE of file="+file.size());
			
			for(CDRFile csvData :file) {

				log.info("IMEi ="+csvData.getServedIMEI()+" Msisdn="+csvData.getServedMSISDN()+"IMSI="+csvData.getServedIMSI());
				HashDetails hashDetails =new HashDetails(); 

				hashDetails.setImsi(csvData.getServedIMSI());	
				hashDetails.setMsisdn(csvData.getServedMSISDN());
				hashDetails.setCount(1);


				List<HashDetails> details  = hashTable.get(csvData.getServedIMEI());

				log.info("Data get from Hash Map="+details);
				if(details == null) {
					List<HashDetails> hashList =new ArrayList<HashDetails>();

					hashList.add(hashDetails);

					if(csvData.getServedIMEI().length() > 0) {

						log.info("Value Put in HashMap when imei lenth greater then 0 ="+"IMEI IS="+csvData.getServedIMEI()+"Value"+hashList);
						hashTable.put(csvData.getServedIMEI(), hashList);
					}else {
						log.info("Value Put in HashMap when imei lenth  not greater then 0 ="+"IMEI IS="+csvData.getServedIMEI()+"Value"+hashList);
						hashTable.put("000000000000000", hashList);
					}

				}
				else {
					List<HashDetails> hashdata =new ArrayList<HashDetails>();

					for(HashDetails eachhashData : details) {
				
						HashDetails coreDetails=new HashDetails();

						if(eachhashData.getImsi().equals(csvData.getServedIMSI())){

							if(eachhashData.getMsisdn().equals(csvData.getServedMSISDN())){

								coreDetails.setCount(eachhashData.getCount()+1);
								coreDetails.setImsi(eachhashData.getImsi());
								coreDetails.setMsisdn(eachhashData.getMsisdn());
								log.info("Msisdn Equal"+coreDetails);

								hashdata.add(coreDetails);
							}else {

								coreDetails.setCount(eachhashData.getCount());
								coreDetails.setImsi(eachhashData.getImsi());
								coreDetails.setMsisdn(eachhashData.getMsisdn());
								log.info("Msisdn not equal="+coreDetails);
								hashdata.add(coreDetails);
								hashdata.add(hashDetails);
							}
						}else {
							coreDetails.setCount(eachhashData.getCount());
							coreDetails.setImsi(eachhashData.getImsi());
							coreDetails.setMsisdn(eachhashData.getMsisdn());
							log.info("IMSI not equal="+coreDetails);
							hashdata.add(coreDetails);
							hashdata.add(hashDetails);
						}
					}


					if(hashdata != null && hashdata.size() > 0) {
						log.info("Value Put in HashMap when imei lenth 15 ans data already exist ="+"IMEIIS ="+csvData.getServedIMEI()+"Value"+hashdata);

						hashTable.replace(csvData.getServedIMEI(), hashdata);
						hashdata.clear();
					}
				}

			}

			for(String key : hashTable.keySet()) {
				log.info("Key Extracted from hah MAP="+key);

				List<HashDetails> hashdata =hashTable.get(key);		

				log.info("Value of Hasmap="+hashdata.toString());
				int indexValue=hashdata.size();

				for(HashDetails arraydetails :hashdata) {

					int occurenceCount=0;
					occurenceCount = occurenceCount + arraydetails.getCount();
					int alreadyCount = counterDetais[indexValue];
					counterDetais[indexValue] =alreadyCount+occurenceCount;
				}

			}

			for(int i=0 ; i < counterDetais.length; i++) {
				log.info("VAlue Of Array index="+counterDetais[i]);
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			log.info("Exception="+e);

		}





	}





}
