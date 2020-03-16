package com.functionapps.parser;

import com.functionapps.db.MySQLConnection;
import com.functionapps.files.FileList;
import com.functionapps.log.LogWriter;

import java.sql.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import javax.print.attribute.standard.PresentationDirection;

import org.apache.log4j.Logger;

/*Parser will take two arguments
 * 1. First argument will be Test if we want to create report related table Or update if we want to insert data into table
 * 2. The second argument will be report name which we want to create or update*/

public class ParserMain {
	public static void main(String args[]){
		Logger logger = Logger.getLogger(ParserMain.class);
		String requestType = null;
		Connection conn    = null;
		ArrayList< String[] > fileList  = null;
		ArrayList< String[] > remainFileList  = null;
		if( args.length < 0 ){
			printHelp();
		}
		if(args.length>0){

			boolean result         = false;
	        HexFileReader hfr      = new HexFileReader();
	        LogWriter logWriter    = new LogWriter();
			String basePath = "";
			String filePath        = "";
			String startTime       = null;
			String endTime         = null;
			String fileName        = null;
			SimpleDateFormat sdf   = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String tableName       = args[0];
	        String[] rawDataResult = null;
	        String previousSequence = null;
	        String[] data = null;
	        String serial_no = null;
	        System.out.println("first stage ");
	        int raw_upload_set_no=1;
	        try {
	        	conn = new com.functionapps.db.MySQLConnection().getConnection();
	        	CEIRParserMain ceir_parser_main = new CEIRParserMain();

	        	ResultSet my_result_set= ceir_parser_main.operatorDetails(  conn,  tableName);
				if(my_result_set.next()){
					raw_upload_set_no = my_result_set.getInt("raw_upload_set_no");
				}

	        	
	        	
	        	basePath = hfr.getFilePath(conn,"mediation_ftp_path");
	        	if(!basePath.endsWith("/")){
	        		basePath+="/";
	        	}

	        	filePath = basePath+"/"+args[0]+"/";
//		        logger.info("File Path is "+filePath);

	        	System.out.println("File Path is "+basePath);
				logger.info("Base Path is ["+basePath+"]");

	        	//FileList fl = new FileList();
	        	//fileName  = new FileList().getFileName(tableName);
	        	//calendar  = Calendar.getInstance();
	        	//startTime = sdf.format( Calendar.getInstance().getTime() );
	        	//startRow = new Query().getStartIndexFromTable( tableName );

	        	remainFileList  = new FileList().remaingFileList( args[0],filePath );
	        	System.out.println("	Remain file size is "+remainFileList.size());
	        	for(int k=0;k<remainFileList.size();k++){
//	        	while( remainFileList.size() != 0 ){	
	        		
	        		if( remainFileList.size() > 0 ){
		    			for( int i = 0; i < remainFileList.size(); i++ ){
				        	startTime = sdf.format( Calendar.getInstance().getTime() );
				        	data = remainFileList.get(i)[0].split("_");				        	
//				    		serial_no = data[data.length-1].substring(0,data[data.length-1].length()-4);
//				    		serial_no = data[data.length-1];
//				        	previousSequence = hfr.getPreviousFileCount( args[1], conn );
//				        	if(Integer.parseInt(serial_no)== Integer.parseInt(previousSequence)+1){
				        	if(true){

				        		if( remainFileList.get(i)[1] != null ){
					            	System.out.println("first stage");
//							        logger.info("File Name is "+remainFileList.get(i)[0]);

					            	rawDataResult = hfr.readConvertedCSVFile(conn ,remainFileList.get(i)[0], remainFileList.get(i)[1], args[0],basePath,raw_upload_set_no);
//					            	hfr.updateNextCount( args[1], conn ,Integer.parseInt(serial_no));
					            	endTime = sdf.format( Calendar.getInstance().getTime() );
						            if( rawDataResult != null ){
						            	if( rawDataResult[1] != null && rawDataResult[2] != null ){
//						            		result  = logWriter.writeLog( remainFileList.get(i)[0], remainFileList.get(i)[2], rawDataResult[0], startTime, endTime, rawDataResult[1], rawDataResult[2], rawDataResult[5], rawDataResult[6],rawDataResult[3], rawDataResult[4]);
						            	}else{
						            		break;
						            	}
						            }else{
//						            	result = logWriter.writeLog( remainFileList.get(i)[0], remainFileList.get(i)[2],"0", startTime, endTime, "null", "null", "0", "0","0",rawDataResult[4]);
						            	break;
						            }
					            }				        		
				        	}
				        	else{
				        		System.out.println("sequence is not matching");
//				        		break;
				        	}
			        	}
	    			}
	        		remainFileList  = new FileList().remaingFileList( args[0] ,filePath);
	        	}
//				fileList               = new FileList().fileList( args[0] );
//	        	for( int i = 0; i < fileList.size(); i++ ){
//		        	startTime = sdf.format( Calendar.getInstance().getTime() );
//		            //System.out.println( "file name is ["+fileList.get(i)[1]+"]+");
//		            if( fileList.get(i)[1] != null ){
//			       //     hfr.readBinaryFileUsingDIS( fileList.get(i)[0], fileList.get(i)[1], tableName );
//		            }
//	        	}
	        	fileList = null;
//	        	hfr.sortAllFile( args[1], filePath );
            	//endTime       = sdf.format( Calendar.getInstance().getTime() );
//    			fileList      = new FileList().fileList( args[0] );
//    			if( fileList.size() > 0 ){
//	    			for( int i = 0; i < fileList.size(); i++ ){
//			        	startTime = sdf.format( Calendar.getInstance().getTime() );
//			            if( fileList.get(i)[1] != null ){
//			            	rawDataResult = hfr.readConvertedCSVFile( conn, fileList.get(i)[0], fileList.get(i)[1], args[0],basePath,raw_upload_set_no);
//			            	endTime = sdf.format( Calendar.getInstance().getTime() );
//				            if( rawDataResult != null ){
//				            	if( rawDataResult[1] != null && rawDataResult[2] != null ){
//				            		result  = logWriter.writeLog( fileList.get(i)[0], fileList.get(i)[2], rawDataResult[0], startTime, endTime, rawDataResult[1], rawDataResult[2], rawDataResult[5], rawDataResult[6],rawDataResult[3], rawDataResult[4]);
//				            	}else{
//				            		break;
//				            	}
//				            }else{
//				            	result = logWriter.writeLog( fileList.get(i)[0], fileList.get(i)[2],"0", startTime, endTime, "null", "null", "0", "0","0",rawDataResult[4]);
//				            	break;
//				            }
//			            }
//		        	}
//    			}else{
//    				System.out.println("No sorted file found");
//    				System.exit(0);
//    			}
	         }catch (Exception e){
	        	e.printStackTrace();
	            System.out.println("No record found from file ["+fileList.get(0)[1]+"]");
	        }finally{
	        	try{
	        		if( conn != null ){
	        			conn.close();
	        		}
	        	}catch( Exception ex ){}
	        }
	        
		
			
		}
//		if( requestType.equalsIgnoreCase("test")){
//			try{
//				new HexFileReader().getFields( );
//			}catch( Exception e ){
//				e.printStackTrace();
//			}	
//		}
//		else if(requestType.equalsIgnoreCase("update")){}else{
//			System.out.println("No request supported");
//		}
		    
	}
	
	public static void printHelp(){
        System.out.println(  "java " + ParserMain.class.getSimpleName() +
                " \"<Operator Name>\" ");
        System.exit(0);
	}
	
}

