package com.functionapps.constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;


import org.apache.log4j.Logger;

public class PropertyReader {

	private InputStream inputStream;
	private static Properties prop;
	 static Logger logger = Logger.getLogger(PropertyReader.class);
    private static PropertyReader reader = null;
    
	public PropertyReader(){
		loadProperties();
	}
	
	public static  PropertyReader getInstance(){
		
		if(reader == null){
			reader = new PropertyReader();
		}
		return reader;
	}
	
	private void loadProperties() {

		try {
			prop = new Properties();
			String propFileName = "/home/ceirapp/ceir/ceir_parser/webAction/conf/config.properties";
			
			logger.info("Porperty File is ["+propFileName+"]");
			
			inputStream = new FileInputStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				//Will be replaced by CustomException
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		}
		catch(IOException io){
			logger.error(io.toString(),(Throwable)io);
			//System.exit(-1);
		}
	}


	public  String getPropValue(String key) throws IOException  {
			
		if(Objects.nonNull(prop)){
			String value = prop.getProperty(key);
			 // System.out.println("Value of property [" + key + "] is [" + value +"]");			
			return value;
		}  
		else{
			return null;
		}
	}
	
	public void closePropStream()
	{
		
			try {
				inputStream.close();
			} catch (IOException io) {
				logger.error(io.toString(),(Throwable)io);
				//System.exit(-1);
				
			}
		
	}
}  