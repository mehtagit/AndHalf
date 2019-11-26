package com.gl.CEIR.CDRPicker.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.CEIR.CDRPicker.beans.CDRFile;
import com.gl.CEIR.CDRPicker.util.Utility;

@Service
public class CSVService {

	@Autowired
	static
	Utility utility;

	private static Logger log = LoggerFactory.getLogger(CSVService.class);


	public  List<CDRFile> readFileFromCSV(String fileName) throws ParseException, IOException {
		List<CDRFile> cdrFiles = new ArrayList<>();
		log.info("Finding the file"+fileName);
		Path pathToFile = Paths.get(fileName);
		BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII);
		try
		{
			String line = br.readLine();
			for(int i=1; (line = br.readLine()) != null; i++) {

				String[] attributes = line.split(",");
				CDRFile cdr = createFile(attributes);
				cdrFiles.add(cdr);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			log.info("Exception Occure="+ioe);
		}catch (Exception e) {
			e.printStackTrace();
			log.info("Exception Occure="+e.getMessage());
			log.info("Exception Occure="+e);
		}
		return cdrFiles;
	}


	private static CDRFile createFile(String[] metadata) throws ParseException {

		String serialNumber=metadata[0];
		String recordType=metadata[1];
		String servedIMSI=metadata[2];
		String servedIMEI=metadata[3];
		String servedMSISDN=metadata[4];
		String calledNumber=metadata[5];
		String connectedNumber=metadata[6];
		String roamingNumber=metadata[7];
		String recordingEntity=metadata[8];
		String mscOutgoingTKGP=metadata[9];
		String locationArea=metadata[10];
		String plmnId=metadata[11];
		String rncId_value=metadata[12];
		String locationArea1=metadata[13];
		String changeTime=metadata[14];
		String mSCID=metadata[15];
		String teleservice=metadata[16];
		String seizureTime=metadata[17];
		String answerTime=metadata[18];
		String releaseTime=metadata[19];
		String callDuration=metadata[20];
		String causeForTerm=metadata[21];
		String callReference=metadata[22];
		String chargeIndicator=metadata[23];
		String chargedParty=metadata[24];
		String serviceKey=metadata[25];
		String networkCallReference=metadata[26];
		String mSCAddress=metadata[27];
		String exchangeIdentity=metadata[28];
		String dialledNumber=metadata[29];
		String subscriberCategory=metadata[30];
		String recordSequenceNumber=metadata[31];
		String hotBillingTag2=metadata[32];
		String bSCIdentification=metadata[33];
		String serviceCategory =metadata[34];
		String transactionIdentification=metadata[35];
		String outgoingTKGPName=metadata[36];
		String calledIMSI=metadata[37];
		String millisecDuration=metadata[38];
		String callingNumber=metadata[39];
		String mscIncomingTKGP=metadata[40];
		String callType=metadata[41];
		String userType=metadata[42];
		String dataRate=metadata[43];
		String incomingTKGPName=metadata[44];
		String routingCategory=metadata[45];
		String ci_answerTime=metadata[46];
		String ci_releaseTime=metadata[47];
		String ci_roamingNumber=metadata[48]; 
		String originationTime=metadata[49];
		String destinationNumber=metadata[50];
		String sourceUnitName=metadata[51];
		String logicalFileName=metadata[52];
		String statusMessage=metadata[53];
	//	String autoCorrectedCDR=metadata[54];

		return new CDRFile( serialNumber, recordType,  servedIMSI,  servedIMEI,  servedMSISDN,
				calledNumber,  connectedNumber, roamingNumber,  recordingEntity,
				mscOutgoingTKGP,  locationArea,  plmnId,  rncId_value,  locationArea1,
				changeTime, mSCID,  teleservice,  seizureTime,  answerTime,
				releaseTime,  callDuration,  causeForTerm,  callReference,  chargeIndicator,
				chargedParty,  serviceKey,  networkCallReference,  mSCAddress,
				exchangeIdentity,  dialledNumber,  subscriberCategory,  recordSequenceNumber,
				hotBillingTag2,  bSCIdentification,  serviceCategory,  transactionIdentification,
				outgoingTKGPName,  calledIMSI, millisecDuration,  callingNumber,
				mscIncomingTKGP,  callType,  userType,  dataRate,  incomingTKGPName,
				routingCategory,  ci_answerTime,  ci_releaseTime, ci_roamingNumber,
				originationTime,  destinationNumber,  sourceUnitName, logicalFileName,
				statusMessage); 
	}




	public void csvMaker(String jsonData,String filepath) throws IOException
	{
		FileOutputStream fos = null;

		Calendar cal = Calendar.getInstance();
		String strDate= new SimpleDateFormat("yyyy-MM-dd-HH").format(cal.getTime());


		File file = new File(filepath);

		String lineSeparator = System.getProperty("line.separator");

		try {
			fos = new FileOutputStream(file,  true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fos.write(lineSeparator.getBytes());
		fos.write(jsonData.getBytes());	
		fos.flush();	


	}



	public String getActualFileName(String  orignalDate) throws IOException {

		log.info("Date Passing ="+orignalDate);
		//final File dir = new File("/home/ubuntu/cdrPicker/");

		File dir = new File("/home/ubuntu/cdrPicker/");
		String[] extensions = new String[] { "csv" };

		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		for (File file : files) {
			if(file.getName().contains(orignalDate)) {

				log.info("file: " + file.getName());

				return file.getName();
			}
		}
		return null;
	}




	public String fileMove(String filePath ,String fileMovePath) {

		try {
			Path temp =Files.move(Paths.get(filePath), Paths.get(fileMovePath),StandardCopyOption.REPLACE_EXISTING);

			if(temp != null) {
				return "success";	

			}else {
				return "fail";
			}

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Exception found="+e);
			return null;
		}


	}


}
