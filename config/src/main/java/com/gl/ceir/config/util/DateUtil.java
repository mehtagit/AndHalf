package com.gl.ceir.config.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	private final static String NUMERIC_STRING = "0123456789";

	public static String nextDate(int nextdateDay, String format) {
		if(Objects.isNull(format)) {
			format = "yyyy-MM-dd";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_MONTH, nextdateDay);  
		String newDate = sdf.format(cal.getTime());  

		return newDate;

	}

	public Date formatChanger(LocalDateTime localDateTime ) throws ParseException {
		String dmyFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localDateTime);
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dmyFormat);

		return date;
	}
	
	public LocalDateTime formatChangerLocaldateTime(LocalDateTime localDateTime, String outFormat){
		LocalDateTime date = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outFormat);
		try {
			date = LocalDateTime.parse(localDateTime.format(formatter));
		}catch( Exception ex) {
			date = localDateTime;
		}
		return date;
	}
	
	public LocalDate parseDate(String date) {
		return LocalDate.parse(date);
	}
	
	public void compareDateWithToday() {
		
	}

	public static void main(String[] args) {
		String testdate = "04-01";
		
		System.out.println("DateUtil.nextDate(27, \"yyyy-MM-dd\") " + DateUtil.nextDate(-27, "yyyy-MM-dd"));
		String currentYear = DateUtil.nextDate(0, "yyyy-MM-dd").split("-")[0];
		String finalDate = currentYear + "-" + testdate;
		System.out.println("finalDate :" + finalDate);
		
		// Compare date
		// if final date is greater then today -> stringToDate() -> Compare date()
		// 	year = currentyear - 1;
		// 	check date = year + "-" + testdate
		// else
		//    
		
		// if( )
	} 

	public static Date addDaysInDate(Integer days,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date); 
		c.add(Calendar.DATE, days); // Adding days
		String output = sdf.format(c.getTime());
		return stringToDate(output);
	}
	public static Date stringToDate(String date) {
		    Date date1;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				return date1;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}  
	}

	public static String dateToString(Date date) {
		try {
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=dateFormat.format(date);
		return currentDate;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		} 
	}
}
