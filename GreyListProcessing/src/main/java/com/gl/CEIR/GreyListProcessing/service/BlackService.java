package com.gl.CEIR.GreyListProcessing.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.CEIR.GreyListProcessing.Beans.ConfigurationDetails;
import com.gl.CEIR.GreyListProcessing.Beans.DumpDetails;
import com.gl.CEIR.GreyListProcessing.Repository.BlackListRepository;
import com.gl.CEIR.GreyListProcessing.Util.Utility;

@Service
public class BlackService implements  Runnable{

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	Utility utility;

	@Autowired
	BlackListRepository blackListRepo;

	@Override
	public void run() {
		while(true) {

			long millis=0;

			try {

				ConfigurationDetails configurationDetails	=blackListRepo.getConfigurationDetails();

				log.info("Configuration Details="+configurationDetails);

				if(configurationDetails.getUnit().equalsIgnoreCase("DAY")) {

					millis =86400000 * configurationDetails.getTime();
				}
				else if(configurationDetails.getUnit().equalsIgnoreCase("HOUR")) {

					millis =3600000 * configurationDetails.getTime();
				}
				else if(configurationDetails.getUnit().equalsIgnoreCase("MINUTE")) {

					millis =60000 * configurationDetails.getTime();
				}
				else {

					log.info("UNIT does not match");
					break;
				}

				String currentTime =utility.getTxnId();

				if(configurationDetails.getDumptype().equals("FULL")) {

					List<String> greListDump =	blackListRepo.getImeidetails();
					if(greListDump !=null) {
						for(String listDump :greListDump) {

							String header="IMEI";
							String record=listDump;
							String fileName="/home/ubuntu/ceir_api/files/blackListFile/BlackList_"+currentTime+".csv";
							utility.writeInFile(fileName, header, record);
						}
					}else {

						String header="Message";
						String record="No data available in BlackList.";
						String fileName="/home/ubuntu/ceir_api/files/blackListFile/BlackList_"+currentTime+".csv";
						utility.writeInFile(fileName, header, record);

					}
					blackListRepo.insertGreyListDetails("BlackList", "BlackList_"+currentTime+".csv","Full");

				}else if(configurationDetails.getDumptype().equals("Incremental")) {


					List<DumpDetails> dumpDetails=	blackListRepo.getpartiallyDumpDetails(configurationDetails.getUnit(),configurationDetails.getTime());
					log.info("Dump Details ="+dumpDetails);

					if(dumpDetails != null) {
						for(DumpDetails details :dumpDetails) {

							String header="IMEI,Operation";
							String record=details.getImei()+","+details.getOperation();
							String fileName="/home/ubuntu/ceir_api/files/blackListFile/BlackList_"+currentTime+".csv";
							utility.writeInFile(fileName, header, record);

						}
					}else {

						String header="Message";
						String record="No data available in BlackList.";
						String fileName="/home/ubuntu/ceir_api/files/blackListFile/BlackList_"+currentTime+".csv";
						utility.writeInFile(fileName, header, record);


					}

					blackListRepo.insertGreyListDetails("BlackList", "BlackList_"+currentTime+".csv","Incremental");
				}
				else {

					log.info("Dump type not Correct");
					Thread.sleep(300000);
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception found ="+e);
			}

			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
