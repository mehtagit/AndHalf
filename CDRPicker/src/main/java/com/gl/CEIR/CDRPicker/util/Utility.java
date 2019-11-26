package com.gl.CEIR.CDRPicker.util;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class Utility {


	public String parseMillisTodate(long dateFormet) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyyMMddHmss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateFormet);
		return  formatter.format(calendar.getTime());
	}



	public Date getDate(String date) throws ParseException {

		SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
		Date date1=formatter1.parse(date); 
		return date1;
	}


	public  long getMillis(String dateFormet) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHmss");
		java.util.Date date = sdf.parse(dateFormet);
		long millis = date.getTime();
		return millis;	


	}


	final static String[] EXTENSIONS = new String[]{
	"dat"};

	public  FilenameFilter IMAGE_FILTER = new FilenameFilter() {

		public boolean accept( File dir,  String name) {
			for ( String ext : EXTENSIONS) {
				if (name.endsWith("."+ext)) {
					return (true);
				}
			}
			return (false);
		}
	};



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

}
