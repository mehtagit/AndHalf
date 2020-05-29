package com.functionapps.parser;

import com.functionapps.files.FileList;
import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;

public class ParserMain {

    static Logger logger = Logger.getLogger(ParserMain.class);

    public static void main(String args[]) {
        Connection conn = null;
        if (args.length < 0) {
            printHelp();
        }
        if (args.length > 0) {
            logger.info("ParserMain.class ");
            HexFileReader hfr = new HexFileReader();
            String basePath = "";
            String intermPath = "";
            String filePath = "";
            String fileName = null;
            File filder = null;
            String tableName = args[0];
            
            int raw_upload_set_no = 1;
            try {
                conn = new com.functionapps.db.MySQLConnection().getConnection();
                CEIRParserMain ceir_parser_main = new CEIRParserMain();
                ResultSet my_result_set = ceir_parser_main.operatorDetails(conn, tableName);
                if (my_result_set.next()) {
                    raw_upload_set_no = my_result_set.getInt("raw_upload_set_no");
                }
                basePath = hfr.getFilePath(conn, tableName.toLowerCase() + "_file_path");
                if (!basePath.endsWith("/")) {
                    basePath += "/";
                }
                intermPath = basePath + "/" + args[0].toLowerCase() + "/";
                if (LocalDateTime.now().getHour() % 2 == 0) {
                    filder = new File(intermPath + "sm_msc01/");
                    if (filder.listFiles().length == 0) {
                        filePath = intermPath + "sm_msc02/";
                    } else {
                        filePath = intermPath + "sm_msc01/";
                    }
                } else {
                    filder = new File(intermPath + "sm_msc02/");
                    if (filder.listFiles().length == 0) {
                        filePath = intermPath + "sm_msc01/";
                    } else {
                        filePath = intermPath + "sm_msc02/";
                    }
                }
                String source = filePath.replace(intermPath, "");
                fileName = new FileList().readOldestOneFile(filePath);
                logger.info("FilePath :" + filePath + ";fileName:" + fileName + " ;basePath :" + basePath + ";source : " + source);
                hfr.readConvertedCSVFile(conn, fileName, args[0], filePath, raw_upload_set_no, source);
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                // System.out.println("No record found from file ");
            } finally {
                try {
                    logger.info(" Process 2 Start " + args[0]);
                    CEIRParserMain.CDRPARSERmain(conn, args[0]);
                } catch (Exception ex) {
                    logger.info(" :" + ex);
                }
                System.exit(0);
            }
        }
    }

    public static void printHelp() {
        // System.out.println("java " + ParserMain.class.getSimpleName()                + " \"<Operator Name>\" ");
        System.exit(0);
    }

}

//FileList fl = new FileList();
//fileName  = new FileList().getFileName(tableName);
//calendar  = Calendar.getInstance();
//startTime = sdf.format( Calendar.getInstance().getTime() );
//startRow = new Query().getStartIndexFromTable( tableName );
//serial_no = data[data.length-1].substring(0,data[data.length-1].length()-4);
//serial_no = data[data.length-1];
//previousSequence = hfr.getPreviousFileCount( args[1], conn );
//if(Integer.parseInt(serial_no)== Integer.parseInt(previousSequence)+1){
//fileList               = new FileList().fileList( args[0] );
//for( int i = 0; i < fileList.size(); i++ ){
//	startTime = sdf.format( Calendar.getInstance().getTime() );
//    // // System.out.println( "file name is ["+fileList.get(i)[1]+"]+");
//    if( fileList.get(i)[1] != null ){
//   //     hfr.readBinaryFileUsingDIS( fileList.get(i)[0], fileList.get(i)[1], tableName );
//    }
//}
//hfr.sortAllFile( args[1], filePath );
//endTime       = sdf.format( Calendar.getInstance().getTime() );
//fileList      = new FileList().fileList( args[0] );
//if( fileList.size() > 0 ){
//	for( int i = 0; i < fileList.size(); i++ ){
//    	startTime = sdf.format( Calendar.getInstance().getTime() );
//        if( fileList.get(i)[1] != null ){
//        	rawDataResult = hfr.readConvertedCSVFile( conn, fileList.get(i)[0], fileList.get(i)[1], args[0],basePath,raw_upload_set_no);
//        	endTime = sdf.format( Calendar.getInstance().getTime() );
//            if( rawDataResult != null ){
//            	if( rawDataResult[1] != null && rawDataResult[2] != null ){
//            		result  = logWriter.writeLog( fileList.get(i)[0], fileList.get(i)[2], rawDataResult[0], startTime, endTime, rawDataResult[1], rawDataResult[2], rawDataResult[5], rawDataResult[6],rawDataResult[3], rawDataResult[4]);
//            	}else{
//            		break;
//            	}
//            }else{
//            	result = logWriter.writeLog( fileList.get(i)[0], fileList.get(i)[2],"0", startTime, endTime, "null", "null", "0", "0","0",rawDataResult[4]);
//            	break;
//            }
//        }
//	}
//}else{
//	 // System.out.println("No sorted file found");
//	System.exit(0);
//}
//if( requestType.equalsIgnoreCase("test")){
//try{
//	new HexFileReader().getFields( );
//}catch( Exception e ){
//	e.printStackTrace();
//}	
//}
//else if(requestType.equalsIgnoreCase("update")){}else{
// // System.out.println("No request supported");
//}
