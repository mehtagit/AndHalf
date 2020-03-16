package com.functionapps.constants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import org.apache.log4j.Logger;

public class PropertyReader {


	private InputStream inputStream;
	private static Properties prop;
	private	final static Logger logger = Logger.getLogger(PropertyReader.class);
    private static PropertyReader reader = null;
    
	private PropertyReader(){
		
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
			String propFileName = "config.properties";
			
			logger.info("Porperty File is ["+propFileName+"]");
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				//Will be replaced by CustomException
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		}
		catch(IOException io)
		{
			logger.error(io.toString(),(Throwable)io);
			//System.exit(-1);
		}
	}


	public  String getPropValue(String Key) throws IOException  {
		
		if(prop == null){
			loadProperties();
		}
			
		if(prop!=null)
		{
			return prop.getProperty(Key);
		}  
		else
		{
			/*MyCustomException.getErrorMessge("Properties File Not Loaded");*/
			//Will be replaced by CustomException
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
