package com.gl.CEIR.GreyListProcessing.Util;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Utility {

	Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}


	public	String getYesterdayDateString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(yesterday());
	}

	public	String getYesterDayMonth() {
		DateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
		return dateFormat.format(yesterday());
	}


	public	void writeInFile(String fileName, String header, String record) {
		try {

			File tmpDir = new File(fileName);
			boolean exists = tmpDir.exists();

			FileWriter fw = new FileWriter(fileName, true);

			if (!exists)
				fw.append(header);

			fw.append("\n");
			fw.append(record);
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	public String getTxnId() {

		DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmm");
		Date date = new Date();
		String transactionId = dateFormat.format(date);	
		return transactionId;

	}

}
