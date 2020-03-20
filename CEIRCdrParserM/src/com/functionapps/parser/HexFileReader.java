package com.functionapps.parser;

import com.functionapps.db.Query;
import com.functionapps.zte.ZTEFields;

import java.util.HashMap;
import java.util.List;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.security.auth.login.FailedLoginException;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import java.text.SimpleDateFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.InputStreamReader;
//import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class HexFileReader {

    Logger logger = Logger.getLogger(HexFileReader.class);
    private static final boolean String = false;
    ZTEFields zte = new ZTEFields();
    String[] fields = zte.zteCDRFields;

    public String getFields() {
        String retVal = "{\"ColumnNames\":[";
        String columnNames = "";
        try {
            for (String column : fields) {
                columnNames = columnNames + "{\"colName\":\"" + column + "\"},";
            }
            retVal = retVal + columnNames.substring(0, columnNames.length() - 1) + "]}";
        } catch (Exception e) {
            retVal = "{\"ColumnNames\":[{\"colName\":\"400\"},{\"colName\":\"No file found.\"}]}";
            e.printStackTrace();
        }
        System.out.println(retVal);
        return retVal;
    }

    public boolean insertDataIntoRaw(String repName, HashMap< String, ArrayList< String>> fileData) throws SQLException {
        int limit = 10000;
        boolean result = false;
        String query = null;
        String values = "values(";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            query = "insert into " + repName + "(";
            for (String field : fields) {
                query = query + field + ",";
                values = values + "?,";
            }
            query = query.substring(0, query.length() - 1) + ") " + values.substring(0, values.length() - 1) + ")";
            conn = new com.functionapps.db.MySQLConnection().getConnection();
            ps = conn.prepareStatement(query);
            for (int j = 0; j < fileData.get(fields[0]).size(); j++) {
                for (int i = 0; i < fields.length; i++) {
                    ps.setString(i + 1, fileData.get(fields[i]).get(j));
                }
                ps.addBatch();
                if ((j % limit) == 0 && j != 0) {
                    ps.executeBatch();
                    System.out.println("Data insert is [" + j + "]");
                } else if (j == (fileData.get(fields[0]).size() - 1)) {
                    ps.executeBatch();
                    System.out.println("Remaining data inserted is [" + j + "]");
                }
            }
            conn.commit();
            result = true;
        } catch (Exception e) {
            System.out.println("Failed to insert data.");
            conn.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    conn.close();
                }
            } catch (Exception ex) {
            }
        }
        return result;
    }

    public String[] readBinaryFileUsingDIS(String fileName, String filePath, String repName) {
        String csvFilePath = "/home/ildidea/upload/" + repName + "/";
        int i = 0;
        int offset = 0;
        int numRead = 0;
        int sCalls = 0;
        int fCalls = 0;
        String cdrSeqNo = null;
        String callDuraExt = null;
        String cdrStartTime = null;
        String cdrEndTime = null;
        String endTime = null;
        String data = null;
        String fieldName = null;
        String answerId = null;
        String callDuration = null;
        String partId = null;
        File file = null;
        FileInputStream fis = null;
        DataInputStream dis = null;
        byte[] buffer = null;
        //String line          = null;
        int[] fieldOffset = null;
        String[] result = null;
        StringBuilder sbCsv = null;
        FileWriter csvFileWriter = null;
        DecimalConverter dc = new DecimalConverter();
        HashMap< String, int[]> hm = new HashMap< String, int[]>();

        try {
            /**
             * **************Insert Query and prepared statement
             * start**************
             */
            //StringBuilder csvHeader=new StringBuilder();
            /*for( String field : fields ){
				csvHeader.append(field).append(",");
			}*/
            /**
             * **************Insert Query and prepared statement
             * ends**************
             */
            buffer = new byte[392];
            file = new File(filePath);
            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
            hm = zte.getfieldSet();
            sbCsv = new StringBuilder();
            csvFileWriter = new FileWriter(csvFilePath + fileName + ".csv");
            //csvHeader.setLength(Math.max(csvHeader.length() - 1, 0));
            //csvFileWriter.write(csvHeader.toString());
            //csvFileWriter.write(System.getProperty( "line.separator" ));
            while ((offset < buffer.length) && (numRead = dis.read(buffer, offset, buffer.length - offset)) >= 0) {
                for (int j = 0; j < fields.length; j++) {
                    data = null;
                    fieldOffset = new int[3];
                    fieldName = fields[j];
                    if (!fieldName.equals("cdr_condsider") && !fieldName.equals("CallDurationInSec") && !fieldName.equals("Changed_date") && !fieldName.equals("cdr_date")) {
                        fieldOffset = hm.get(fieldName);
                        //System.out.println("Field name ["+fieldName+"]");
                        byte[] byteData = null;
                        switch (fieldOffset[2]) {
                            case 0:
//									byteData = Arrays.copyOfRange( buffer, fieldOffset[0], fieldOffset[0]+fieldOffset[1]);
                                data = dc.hex2Decimal(dc.bytesToHex(byteData));
                                break;
                            case 1:
//									byteData = Arrays.copyOfRange( buffer, fieldOffset[0], fieldOffset[0]+fieldOffset[1]);
                                if (fieldName.equalsIgnoreCase("Call_Duration")) {
                                    data = dc.bytesToHex(byteData);
                                    callDuration = data;
                                    //System.out.println("Call_Duration hex value ["+dc.bytesToHex(byteData)+"]");
                                } else {
                                    data = dc.getNumberFromBCD(dc.bytesToHex(byteData));
                                }
                                break;
                            case 2:
//									byteData = Arrays.copyOfRange( buffer, fieldOffset[0], fieldOffset[0]+fieldOffset[1]);
                                data = dc.getNumberFromRightBCD(dc.bytesToHex(byteData));
                                break;
                            case 3:
                                data = dc.getBitFromByte(buffer[fieldOffset[0]], fieldOffset[1]);
                                break;
                            case 4:
//									byteData = Arrays.copyOfRange( buffer, fieldOffset[0], fieldOffset[0]+fieldOffset[1]);
                                data = dc.getStringFromByte(byteData);
                                break;
                            default:
                                break;
                        }
                        if (fieldName.equalsIgnoreCase("AnswerID")) {
                            answerId = data;
                        } else if (fieldName.equalsIgnoreCase("EndTime")) {
                            endTime = data;
                        } else if (fieldName.equalsIgnoreCase("SeqNum")) {
                            cdrSeqNo = data;
                        } else if (fieldName.equalsIgnoreCase("Call_Duration_Extended")) {
                            callDuraExt = data;
                        } else if (fieldName.equalsIgnoreCase("PartRecID")) {
                            partId = data;
                        }

                        //ps.setString( j+1, data);
                    } else if (fieldName.equals("CallDurationInSec")) {
                        if (answerId.equals("1")) {
                            //System.out.println("Call duration ["+callDuration+"]");
                            data = this.getCallDurationInSecond(callDuration, partId, cdrSeqNo, callDuraExt);
                        } else {
                            data = "0";
                        }
                    } else if (fieldName.equals("Changed_date") || fieldName.equals("cdr_date")) {
                        data = "0";
                    } else {
                        data = "N";
                    }
                    sbCsv.append(data).append(",");
                }
                sbCsv.setLength(Math.max(sbCsv.length() - 1, 0));
                csvFileWriter.write(sbCsv.toString());
                csvFileWriter.write(System.getProperty("line.separator"));
                sbCsv.setLength(0);
                i++;
                //break;
            }
            csvFileWriter.flush();
            new com.functionapps.files.FileList().moveFile(fileName, repName, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (Exception ex) {
            }
            data = null;
            fieldName = null;
            file = null;
            buffer = null;
            fieldOffset = null;
            dc = null;
            hm = null;
        }
        return result;
    }

    public boolean insertIntoFileDetailTable(String fileName, String fileSize, String file, int cdrCount) {
        boolean result = false;
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String getCallDurationInSecond(String callDuration, String partId, String cdrSeqNo, String callDurationExt) {
        String duration = null;
        int seqNo = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;
        int total = 0;
        try {
            if (cdrSeqNo != null && !cdrSeqNo.equals("")) {
                seqNo = Integer.valueOf(cdrSeqNo);
            }
            if (callDuration.length() == 8) {
                //System.out.println("Hour part ["+callDuration.substring( 2, 4)+"],minute part ["+callDuration.substring( 4, 6)+"] and second part["+callDuration.substring( 6, 8)+"]");
                hour = Integer.valueOf(callDuration.substring(2, 4)) * 3600;
                minute = Integer.valueOf(callDuration.substring(4, 6)) * 60;
                second = Integer.valueOf(callDuration.substring(6, 8));
                //System.out.println("Hour part ["+hour+"],minute part ["+minute+"] and second part["+second+"]");
                if (partId.equals("3") && seqNo != 0) {
                    total = hour + minute + second + ((seqNo - 1) * 1800);
                } else {
                    total = hour + minute + second;
                }
                if (callDurationExt != null && !callDurationExt.equals("") && !callDurationExt.equals("0")) {
//					duration = String.valueOf((total + 1 ));
                } else {
//					duration = String.valueOf(total);
                }
            } else {
                duration = "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duration;
    }

    public boolean sortAllFile(String repName, String path) {
        boolean result = false;
        Process newProcess = null;
        String command = null;
        try {
            if (path != null) {
                command = "sh " + path + "script/sort_file.sh " + path;
                System.out.println("Command to execute script is [" + command + "]");
                newProcess = Runtime.getRuntime().exec(command);
                newProcess.waitFor();
                System.out.println("File sorting completed.");
                result = true;
            } else {
                result = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String[] readConvertedCSVFile(Connection conn, String fileName, String filePath, String repName, String basePath, int raw_upload_set_no) {
//		String errorFilePath = "D:\\lokesh\\CEIR\\"+repName+"/old/"+fileName+".error";
//		String errorFilePath = filePath+fileName+".error";
        String cdrCount = null;
//		String answerTime = null;
        String endTime = null;
        String inTrkName = null;
        String outTrkName = null;
        String inTrkNo = null;
        String outTrkNo = null;
        String partId = null;
        String billId = null;
        int sCount = 0;
        int fCount = 0;
        int startId = 0;
        int endId = 0;
        int endRow = 0;
        int i = 0;
        int k = 0;
        int limit = raw_upload_set_no;
        int startRow = 0;
        int rowInserted = 0;
        String query = null;
        String failquery = null;
        String values = "values(";
        String failvalues = "values(";
        //Connection conn       = null;
        PreparedStatement ps = null;
        PreparedStatement failed_ps = null;
        PreparedStatement temPS = null;
        ResultSet rs = null;
        String cdrStartTime = null;
        String cdrEndTime = null;
        String cdrTime = null;
        String changeCDRTime = null;
        String fieldName = null;
        File file = null;
        String line = null;
        String str = null;
        String answerId = null;
        String startTime = null;
        String preCDRTime = null;
        String[] data = null;
        BufferedReader br = null;
        FileWriter fw = null;
        FileReader fr = null;
        String[] result = null;
        Date date = null;
        SimpleDateFormat actF = null;
        SimpleDateFormat sdf = null;
        DataInputStream dis = null;
        FileInputStream fis = null;
        ArrayList< String> billIds = null;
        List< String> fieldList = null;
        HashMap< String, int[]> hm = new HashMap< String, int[]>();
        int fieldValue = 0;
        int failed_flag = 1;
        String recordtype = null;
        String imei = null;
        String imsi = null;
        String msisdn = null;
        String systemtype = null;
        String updatetime = null;
        System.out.println("in file reader");

        try {
            fieldList = new ArrayList<String>();
            ArrayList<CDRColumn> myfilelist = getCDRFields(conn, "CDR");
            System.out.println("file list size is " + myfilelist.size());
//			fieldList = Arrays.asList(fields);
            logger.info("File name is [" + fileName + "]");

            System.out.println("File Name is " + fileName);
            date = new Date();
            actF = new SimpleDateFormat("yyyyMMddHHmmss");     //	actF  = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			sdf   = new SimpleDateFormat("yyyy/mm/dd hh24:mi:ss");
            String[] fileArray = fileName.split("_");
            updatetime = sdf.format(actF.parse(fileArray[fileArray.length - 2]));

//			fw    = new FileWriter(errorFilePath);
            file = new File(filePath);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
//			startTime = sdf.format(Calendar.getInstance().getTime());
//			startRow  = new com.functionapps.db.Query().getStartIndexFromTable(conn, repName);
            /**
             * **************Insert Query and prepared statement
             * start**************
             */
            query = "insert into " + repName + "_raw" + "(";
            failquery = "insert into " + repName + "_error" + "(";
//			for( String field : fields ){
//				query  = query + field + ",";
//				values = values + "?,";
//			}
//			query  = query.substring( 0, query.length() - 1 )+") "+values.substring( 0, values.length() - 1 )+")";
//			System.out.println("query is "+query);

//			conn   = new com.functionapps.db.MySQLConnection().getConnection();
//			ps     = conn.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );
//			temPS = conn.prepareStatement( "insert into zte_billid_temp(partId,billId,answerTime) values(?,?,?)", Statement.RETURN_GENERATED_KEYS );
            /**
             * **************Insert Query and prepared statement
             * ends**************
             */
            hm = zte.getfieldSet();
            billIds = new ArrayList< String>();

//			preCDRTime = this.getPreviousTimeFromRaw(repName, conn);
            int fail_my_batch = 0;
            int pass_my_batch = 0;
            int my_batch_count = raw_upload_set_no;
            while ((line = br.readLine()) != null) {
                data = line.split(",", -1);
                if (k == 0) {
                    System.out.println(" data length is " + data.length + " fileld List size is " + myfilelist.size());
                    if (data.length == myfilelist.size()) {
                        System.out.println("Configured Column name and File Headers are matched");
                        int my_column_count = 0;
                        for (CDRColumn cdrColumn : myfilelist) {
                            if ((cdrColumn.columString).trim().equals(data[my_column_count].trim())) {
                                System.out.println("column name matched");
                                my_column_count++;
                                query = query + cdrColumn.columString + ",";
                                values = values + "?,";

                            } else {
                                System.out.println("Column name not matched");

                            }
                        }
                        if (my_column_count == myfilelist.size()) {

                            query = query + "operator" + "," + "file_name" + "," + "record_time" + ",";
                            values = values + "?,?,TO_DATE(?,'yyyy/mm/dd hh24:mi:ss'),";
                            query = query.substring(0, query.length() - 1) + ") " + values.substring(0, values.length() - 1) + ")";

                            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                            failquery = failquery + "operator" + "," + "file_name" + "," + "record_time" + ",";
                            failvalues = failvalues + "?,?,TO_DATE(?,'yyyy/mm/dd hh24:mi:ss'),";
                            failquery = failquery.substring(0, failquery.length() - 1) + ") " + failvalues.substring(0, failvalues.length() - 1) + ")";

                            failed_ps = conn.prepareStatement(failquery, Statement.RETURN_GENERATED_KEYS);
                            System.out.println("complete query is [" + query + "]");
                            System.out.println("complete header matched");
                        } else {
//							logger.info("getting error in file so moving the file ");
                            fr.close();
                            new com.functionapps.files.FileList().moveFile(fileName, repName, basePath, "error");
                            System.out.println("Total column are not matched" + my_column_count);
                            break;
                        }
                    } else {
//						logger.info("getting error in file so moving the file ");
                        fr.close();
                        System.out.println("Configured Comumn nad File headers are not matched");
                        new com.functionapps.files.FileList().moveFile(fileName, repName, basePath, "error");
                    }
                } else {
                    int j = 1;
                    for (CDRColumn cdrColumn : myfilelist) {

                        if (cdrColumn.graceType.equalsIgnoreCase("Mandatory")) {
                            if (data[j - 1] == "" || data[j - 1] == null) {
                                failed_flag = 0;
                            }
                            j++;
                        }
                    }
                    j = 1;
                    if (failed_flag == 1) {
//						System.out.println("my data length"+data.length+" "+j);
                        for (; j <= data.length; j++) {
//							System.out.println("j"+j+" data"+data[j-1]);
                            ps.setString(j, data[j - 1].trim());
                        }
                        ps.setString(j, repName);
                        ps.setString(j + 1, fileName);
                        ps.setString(j + 2, updatetime);
//						ps.setString(j+2,"TO_DATE('"+updatetime+"','yyyy/mm/dd hh24:mi:ss')");
//						System.out.println("PS is "+ps);
                        ps.addBatch();
                        pass_my_batch++;

                    } else {
                        for (; j <= data.length; j++) {
                            failed_ps.setString(j, data[j - 1].trim());
                        }
                        failed_ps.setString(j, repName);
                        failed_ps.setString(j + 1, fileName);
                        failed_ps.setString(j + 2, updatetime);
//						failed_ps.setString(j+2,"TO_DATE('"+updatetime+"','yyyy/mm/dd hh24:mi:ss')");
                        failed_ps.addBatch();
                        fail_my_batch++;

                    }
                }
                k++;
                System.out.println(k);
                if (pass_my_batch == my_batch_count) {
                    logger.info("Executing Pass batch");
                    System.out.println("Executing Pass Batch File");
                    ps.executeBatch();
                    conn.commit();
                    pass_my_batch = 0;
                }
                if (fail_my_batch == my_batch_count) {
                    logger.info("Executing Fail batch");
                    System.out.println("Executing Fail Batch File");
                    failed_ps.executeBatch();
                    conn.commit();
                    pass_my_batch = 0;
                }
            }
//			System.out.println("cdrStartTIme ["+cdrStartTime+"], cdrEndTime ["+cdrEndTime+"]");	
//			System.out.println(ps);
            ps.executeBatch();
            failed_ps.executeBatch();
            if (fr != null) {
                fr.close();
            }
            rowInserted = ps.getUpdateCount();
            rs = ps.getGeneratedKeys();
//			while( rs.next() ){
//				endRow      = (int) rs.getLong(1);
//				rowInserted = endRow - startRow;
//			}

            if (rs != null) {
                rs.close();
            }
            //System.out.println("cdr start row is ["+startRow+"] and end row is ["+endRow+"]");
//			this.updateCDRWithPartIdThree( conn, billIds, repName, cdrStartTime, startRow, endRow );
//			startId = new Query().getStartIndexFromTable(conn,"zte_billid_temp");
//			temPS.executeBatch();
//			rs = temPS.getGeneratedKeys();
//			while( rs.next() ){
//				endId = (int)rs.getLong(1);
//			}
//			if( endId != 0 ){
//				this.insertSlotStartAndEndSnoId( conn, cdrStartTime, startId, endId);
//			}
//			if( temPS != null ){
//				temPS.clearParameters();
//			}

            new com.functionapps.files.FileList().moveFile(fileName, repName, basePath, "file");

//			cdrCount = String.valueOf(i+1);
            if (cdrCount != null && cdrStartTime != null && cdrEndTime != null) {
                //System.out.println("CDR start time ["+cdrStartTime+"] and CDR end time ["+cdrEndTime+"]");
//				result = new String[]{ cdrCount, cdrStartTime, cdrEndTime, String.valueOf(rowInserted), this.getTableSize( conn, repName),String.valueOf(sCount),String.valueOf(fCount) };
            } else {
//				result = new String[]{ "0", "null", "null", "0", this.getTableSize( conn, repName),"0","0" };
            }
            conn.commit();
        } catch (Exception e) {
            logger.info("Exception [" + e + "]");
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception ex) {
            }
            result = null;
            //result = new String[]{ "0", "null", "null", "0", this.getTableSize( conn, repName),"0","0" };
        } finally {
            try {
                if (conn != null) {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.clearParameters();
                        ps.close();
                    }
//					if( temPS != null ){
//						temPS.clearParameters();
//						temPS.close();
//					}
                    //conn.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception ex) {
            }
//			errorFilePath = null;
            query = null;
            cdrCount = null;
//			answerTime   = null;
            endTime = null;
            inTrkName = null;
            outTrkName = null;
            inTrkNo = null;
            outTrkNo = null;
            partId = null;
            billId = null;
            values = null;
            cdrStartTime = null;
            cdrEndTime = null;
            data = null;
            fieldName = null;
            file = null;
            billIds = null;
            hm = null;
        }
        return result;
    }

    public String[] readConvertedFeatureFile(Connection conn, String fileName, String filePath, String repName, String basePath, int raw_upload_set_no, String txn_id, String subfeature, String management_table) {
        int i = 0;
        int k = 0;
        int limit = raw_upload_set_no;
        String query = null;
//		String failquery = null;
        String values = "values(";
//		String failvalues    = "values(";
        //Connection conn       = null;
        PreparedStatement ps = null;
//		PreparedStatement failed_ps = null;
        ResultSet rs = null;
        File file = null;
        String line = null;
        String[] data = null;
        BufferedReader br = null;
        FileWriter fw = null;
        FileReader fr = null;
        String[] result = null;
        SimpleDateFormat actF = null;
        SimpleDateFormat sdf = null;
        DataInputStream dis = null;
        FileInputStream fis = null;
        Date date = null;
        int fieldValue = 0;
        int failed_flag = 1;
        String my_column_name = "";
        System.out.println("in file reader");
        ErrorFileGenrator errFile = new ErrorFileGenrator();
        try {
            ArrayList<CDRColumn> myfilelist = getCDRFields(conn, repName);
            System.out.println("file list size is " + myfilelist.size());
            logger.info("File name is [" + fileName + "]");

            System.out.println("File Name is " + fileName);
            date = new Date();
            actF = new SimpleDateFormat("yyyyMddHHmmssSS");                         //	actF  = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			sdf   = new SimpleDateFormat("yyyy/mm/dd hh24:mi:ss");
            String[] fileArray = fileName.split("_");

//			fw    = new FileWriter(errorFilePath);
            file = new File(filePath);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
//			startTime = sdf.format(Calendar.getInstance().getTime());
//			startRow  = new com.functionapps.db.Query().getStartIndexFromTable(conn, repName);
            /**
             * **************Insert Query and prepared statement
             * start**************
             */
            query = "insert into " + repName + "_raw" + "(";
            /**
             * **************Insert Query and prepared statement
             * ends**************
             */

            int pass_my_batch = 0;
            int cnt = 0;
            int my_batch_count = raw_upload_set_no;

            System.out.println(" WE CAN  START HERE");
            while ((line = br.readLine()) != null) {      // checking data for errors 
//            	   if(line.isEmpty() )  { System.out.println(" LINE is empty ,, WE CAN PUT ERROR FILE HERE");}
                System.out.println("Line No " + cnt);
                data = line.split(",", -1);

                if (k == 0) {
                    System.out.println(" data length from File is " + data.length + "  List size is in db  " + myfilelist.size());

                    if (data.length == 1) {
                        System.out.println(" File is corrupted " + data.toString() + "  lllll..." + line);
                        errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0010, Error Message: The file is corrupt.    ");                                   /////////
                    }

                    for (int fldCount = 0; fldCount < data.length; fldCount++) {
                        if (data[fldCount].equals("") || data[fldCount] == "") {
                            System.out.println(" File Column is Empty ");
                            errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0009, Error Message: The header is not found in the file.    ");                                   /////////

                        }
                    }

                    if (data.length == myfilelist.size()) {
                        System.out.println("Configured Column name and File Headers are matched");
                        int my_column_count = 0;
                        for (CDRColumn cdrColumn : myfilelist) {
                            my_column_name = data[my_column_count].trim();
                            my_column_name = my_column_name.replaceAll(" ", "");
                            my_column_name = my_column_name.replaceAll("_", "");
                            my_column_name = my_column_name.replaceAll("/", "");
                            System.out.println(cdrColumn.columString + " file column " + data[my_column_count].trim());
                            if ((cdrColumn.columString).trim().equalsIgnoreCase(my_column_name)) {
                                System.out.println("column name matched");
                                my_column_count++;
                            } else {
                                failed_flag = 0;
                                System.out.println("Column name not matched");
                                errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0001, Error Message:    File Header   number is " + data.length + "  , Required size is(defined in db ) " + myfilelist.size() + "  is not macthed ");                                   /////////

                                break;
                            }
                        }
                        if (my_column_count == myfilelist.size()) {

                        } else {
                            failed_flag = 0;
                            fr.close();
                            errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0002, Error Message: The header in the file is not in correct order     ");                                   /////////
//							new com.functionapps.files.FileList().moveFileFeature(fileName, repName,basePath,"error");
                            System.out.println("Total column are not matched" + my_column_count);

                            break;
                        }
                    } else {
                        failed_flag = 0;
                        fr.close();
                        System.out.println("Configured Comumn and File headers Size are not matched");
                        errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0001, Error Message:    File Header   number is " + data.length + "  , Required size is(defined in db ) " + myfilelist.size() + "  is not macthed ");                                   /////////

                        break;
//						new com.functionapps.files.FileList().moveFileFeature(fileName, repName,basePath,"error");
                    }
                } else {
                    System.out.println("FILE AFTER HEADER");
                    System.out.println("Data as per record " + data[0] + " <><><><> " + data[1] + " <><><><> " + data[2] + " <><><><> " + data[3] + " <><><> " + data[4] + " <><><> " + data[5]);

                    int j = 1;
                    System.out.println(" DATA " + data[j - 1]);
                    Set<String> set = new HashSet<String>();
                   
                    String[] arrOfFile = line.trim().split(",", 8);
                    String imeiV = arrOfFile[4];
                    if (set.add(imeiV) == false) {
                        System.out.println("err.." + imeiV);
                        errFile.gotoErrorFile(txn_id, " Detail: " + line.toString() + "   Error Code :CON_FILE_0008, Error Message:   The record is duplicate in the file");
                        failed_flag = 0;  ///  added after 
                    }

                    for (int v = 0; v < data.length; v++) {
                        if (data[v].length() > 25) {
                            errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0004, Error Message:   File Contain a Long Field  Record   ");                                   /////////
                            failed_flag = 0;
                            fr.close();
                            break;
                        }
                    }     // harrd Coed

                    Statement stmt2 = conn.createStatement();
                    ResultSet result1 = stmt2.executeQuery(" select interp from system_config_list_db where tag = 'DEVICE_TYPE'");
                    Set<String> deviceType = new HashSet<String>();
                    try {
                        while (result1.next()) {
                            deviceType.add(result1.getString("interp"));
                                                  }
                    } catch (Exception e) {
                        System.out.println("Error aat device_type " + e);
                    }
                     stmt2.close();
                    System.out.println("size of deviceType" + deviceType.size());

                    if (!(deviceType.contains(data[0].trim()))) {
                        errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0006, Error Message:  The field value(Device Type) is not as per the specifications");                                   /////////
                        failed_flag = 0;
                        fr.close();
                        break;
                    }

                    if (!data[1].equalsIgnoreCase("IMEI") && !data[1].equalsIgnoreCase("ESN") && !data[1].equalsIgnoreCase("MEID")) {

                        errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0006, Error Message:  The field value(Device ID Type) is not as per the specifications");                                   /////////
                        failed_flag = 0;
                        fr.close();
                        break;
                    }
                    if (!data[2].equalsIgnoreCase("Y") && !data[2].equalsIgnoreCase("N")) {

                        errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0006, Error Message:  The field value(Multiple Sim Status) is not as per the specifications");                                   /////////
                        failed_flag = 0;
                        fr.close();
                        break;
                    }

                    boolean val = validateJavaDate(data[5]);
                    System.out.println("resss " + val);

                    if (!val) {
                        errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0006, Error Message:  The field value(Device Launch Date) is not as per the specifications");                                   /////////
                        failed_flag = 0;
                        fr.close();
                        break;
                    }

                    if (!data[6].equalsIgnoreCase("New") && !data[6].equalsIgnoreCase("Used")) {

                        errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_006, Error Message:  The field value(Device Status ) is not as per the specifications");                                   /////////
                        failed_flag = 0;
                        fr.close();
                        break;
                    }

                    for (CDRColumn cdrColumn : myfilelist) {
                        if (cdrColumn.graceType.equalsIgnoreCase("Mandatory")) {
                            System.out.println("DATA in field ... " + data[j - 1]);
                            if (data[j - 1] == null || data[j - 1] == "") {
                                errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0007, Error Message:   The mandatory parameter does not contain the value. ");                                   /////////
                                failed_flag = 0;
                                fr.close();
                                break;
                            }
                            j++;
                        }

                    }
                }
                k++;
            }
            br.close();

            k = 0;
            if (failed_flag == 1) {

                System.out.println("uploading file");       // uploading data to db ..
                file = new File(filePath);
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {

                    data = line.split(",", -1);
                    System.out.println("uploading file  with data.... " + data);
                    if (k == 0) {

                        System.out.println(" data length is " + data.length + " fileld List size is " + myfilelist.size());
                        if (data.length == myfilelist.size()) {
                            System.out.println("Configured Column name and File Headers are matched");
                            int my_column_count = 0;

                            for (CDRColumn cdrColumn : myfilelist) {
                                query = query + cdrColumn.columString + ",";
                                values = values + "?,";
                                my_column_count++;
                            }
                            if (my_column_count == myfilelist.size()) {
                                query = query + "txn_id" + "," + "file_name" + "," + "feature_type" + ",";
                                values = values + "?,?,?,";
                                query = query.substring(0, query.length() - 1) + ") " + values.substring(0, values.length() - 1) + ")";

                                ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                                System.out.println("complete query is [" + query + "]");
                                System.out.println("complete header matched");
                            } else {
//							logger.info("getting error in file so moving the file ");
                                fr.close();
//							new com.functionapps.files.FileList().moveFileFeature(fileName, repName,basePath,"error");
                                System.out.println("Total column are not matched" + my_column_count);
                                break;
                            }
                        } else {
//						logger.info("getting error in file so moving the file ");
                            fr.close();
                            System.out.println("Configured Comumn nad File headers are not matched ,,File Header   number is " + data.length + "  , Required size is(defined in db ) " + myfilelist.size() + "  is not macthed for 2nd");
                            errFile.gotoErrorFile(txn_id, "    Error Code :CON_FILE_0001, Error Message:    File Header   number is " + data.length + "  , Required size is(defined in db ) " + myfilelist.size() + "  is not macthed ");                                   /////////

//						new com.functionapps.files.FileList().moveFileFeature(fileName, repName,basePath,"error");
                        }
                    } else {
                        int mdntryCols[] = new int[myfilelist.size()];
                        int j = 1;
                        for (CDRColumn cdrColumn1 : myfilelist) {
                            if (cdrColumn1.graceType.equalsIgnoreCase("Mandatory")) {
                                if (data[j - 1] == "" || data[j - 1] == null) {
                                    System.out.println("DATA in MANULL AMNDORY AT  FILED ... " + data[j - 1]);
//                                                               errFile.gotoErrorFile(txn_id,  "    Error Code :CON_FILE_0007, Error Message:   The mandatory parameter does not contain the value. ");                                   /////////

                                    failed_flag = 0;
                                }
                                j++;
                            }
                        }
                        j = 1;
                        if (failed_flag == 1) {

                            List aList = new ArrayList();

                            Set<String> set = new HashSet<String>();

                            String imeiV = data[4].trim();

                            System.out.println("imeiV;;" + imeiV);
                            if (aList.contains(imeiV)) {
                                System.out.println("err.." + imeiV);    //		The record is duplicate in the file.
                                errFile.gotoErrorFile(txn_id, " Detail as..: " + line.toString() + "   Error Code :CON_FILE_0008, Error Message:   The record is duplicate in the file");
                                failed_flag = 0;  ///  added after 
                            } else {
                                aList.add(imeiV);
//                    if (set.add(imeiV) == false) {
//                        System.out.println("err.." + imeiV);    //		The record is duplicate in the file.
//                        errFile.gotoErrorFile(txn_id, " Detail as..: " + line.toString() + "   Error Code :CON_FILE_0008, Error Message:   The record is duplicate in the file");
//                    failed_flag = 0;  ///  added after 
//                    }

                                for (; j <= data.length; j++) {
                                    System.out.println("DATA at setString " + data[j - 1].trim());
                                    ps.setString(j, data[j - 1].trim());
                                }
                                ps.setString(j, txn_id);
                                ps.setString(j + 1, fileName);
                                ps.setString(j + 2, repName);
                                ps.addBatch();
                                pass_my_batch++;
                            }
                        }
                    }
                    k++;
                    System.out.println(k);
                    if (pass_my_batch == my_batch_count) {
                        logger.info("Executing Pass batch");
                        System.out.println("Executing Pass Batch File");
                        ps.executeBatch();
                        conn.commit();
                        pass_my_batch = 0;
                    }
                }
                br.close();
                ps.executeBatch();
                if (fr != null) {
                    fr.close();
                }
//		    rowInserted = ps.getUpdateCount();
                rs = ps.getGeneratedKeys();
                if (rs != null) {
                    rs.close();
                }
//			new com.functionapps.files.FileList().moveFile(fileName, repName,basePath,"file");
                conn.commit();

            } else {
                CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
                ceirfunction.addFeatureFileConfigDetails(conn, "update", repName, subfeature, txn_id, fileName, "PARAM_NOT_VALID", "");
                ceirfunction.updateFeatureFileStatus(conn, txn_id, 3, repName, subfeature);
                ceirfunction.updateFeatureManagementStatus(conn, txn_id, 1, management_table);

            }

        } catch (Exception e) {
            logger.info("Exception [" + e + "]");
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception ex) {
            }
            result = null;
        } finally {
            try {
                if (conn != null) {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.clearParameters();
                        ps.close();
                    }
                }
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception ex) {
            }
            query = null;
//			cdrCount     = null;
            values = null;
//			cdrStartTime = null;
//			cdrEndTime   = null;
            data = null;
            file = null;
//			billIds      = null;
//			hm           = null;
        }
        return result;
    }

    public String[] readConvertedCSVFileBack(Connection conn, String fileName, String filePath, String repName) {
//		String errorFilePath = "D:\\lokesh\\CEIR\\"+repName+"/old/"+fileName+".error";
        String errorFilePath = filePath + fileName + ".error";
        Boolean inTrkStatus = false;
        String cdrCount = null;
        String answerTime = null;
        String endTime = null;
        String inTrkName = null;
        String outTrkName = null;
        String inTrkNo = null;
        String outTrkNo = null;
        String partId = null;
        String billId = null;
        int sCount = 0;
        int fCount = 0;
        int startId = 0;
        int endId = 0;
        int endRow = 0;
        int i = 0;
        int limit = 10000;
        int startRow = 0;
        int rowInserted = 0;
        String query = null;
        String values = "values(";
        //Connection conn       = null;
        PreparedStatement ps = null;
        PreparedStatement temPS = null;
        ResultSet rs = null;
        String cdrStartTime = null;
        String cdrEndTime = null;
        String cdrTime = null;
        String changeCDRTime = null;
        String fieldName = null;
        File file = null;
        String line = null;
        String str = null;
        String answerId = null;
        String startTime = null;
        String preCDRTime = null;
        String[] data = null;
        BufferedReader br = null;
        FileWriter fw = null;
        FileReader fr = null;
        String[] result = null;
        Date date = null;
        SimpleDateFormat actF = null;
        SimpleDateFormat sdf = null;
        DataInputStream dis = null;
        FileInputStream fis = null;
        ArrayList< String> billIds = null;
        List< String> fieldList = null;
        HashMap< String, int[]> hm = new HashMap< String, int[]>();
        int fieldValue = 0;
        String recordtype = null;
        String imei = null;
        String imsi = null;
        String msisdn = null;
        String systemtype = null;
        System.out.println("in file reader");
        try {
            fieldList = new ArrayList<String>();
            ArrayList filelist = getCDRFields(conn, "CDR");
            fieldList = Arrays.asList(fields);

            date = new Date();
            actF = new SimpleDateFormat("yyyyMMddHHmmssSS");         //	actF  = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			fw    = new FileWriter(errorFilePath);
            file = new File(filePath);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
//			startTime = sdf.format(Calendar.getInstance().getTime());
//			startRow  = new com.functionapps.db.Query().getStartIndexFromTable(conn, repName);
            /**
             * **************Insert Query and prepared statement
             * start**************
             */
            query = "insert into " + repName + "(";
            for (String field : fields) {
                query = query + field + ",";
                values = values + "?,";
            }
            query = query.substring(0, query.length() - 1) + ") " + values.substring(0, values.length() - 1) + ")";
            System.out.println("query is " + query);

//			conn   = new com.functionapps.db.MySQLConnection().getConnection();
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//			temPS = conn.prepareStatement( "insert into zte_billid_temp(partId,billId,answerTime) values(?,?,?)", Statement.RETURN_GENERATED_KEYS );
            /**
             * **************Insert Query and prepared statement
             * ends**************
             */
            hm = zte.getfieldSet();
            billIds = new ArrayList< String>();

//			preCDRTime = this.getPreviousTimeFromRaw(repName, conn);
            while ((line = br.readLine()) != null) {
                data = line.split(",", -1);
                recordtype = data[0];
                imei = data[1];
                imsi = data[2];
                msisdn = data[3];
                systemtype = data[4];

                if (i == 0) {
//					query = "insert into "+repName+"(";
//					for( String field : fields ){
//						query  = query + field + ",";
//						values = values + "?,";
//						
//					}
//					query  = query.substring( 0, query.length() - 1 )+") "+values.substring( 0, values.length() - 1 )+")";					
                } else {
                    ps.setString(1, recordtype);
                    ps.setString(2, imei);
                    ps.setString(3, imsi);
                    ps.setString(4, msisdn);
                    ps.setString(5, systemtype);
                    ps.addBatch();
                }
                i++;
            }
            System.out.println("cdrStartTIme [" + cdrStartTime + "], cdrEndTime [" + cdrEndTime + "]");
            ps.executeBatch();
            if (fr != null) {
                fr.close();
            }
            rowInserted = ps.getUpdateCount();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                endRow = (int) rs.getLong(1);
                rowInserted = endRow - startRow;
            }

            if (rs != null) {
                rs.close();
            }
            //System.out.println("cdr start row is ["+startRow+"] and end row is ["+endRow+"]");
//			this.updateCDRWithPartIdThree( conn, billIds, repName, cdrStartTime, startRow, endRow );
//			startId = new Query().getStartIndexFromTable(conn,"zte_billid_temp");
//			temPS.executeBatch();
//			rs = temPS.getGeneratedKeys();
//			while( rs.next() ){
//				endId = (int)rs.getLong(1);
//			}
//			if( endId != 0 ){
//				this.insertSlotStartAndEndSnoId( conn, cdrStartTime, startId, endId);
//			}
//			if( temPS != null ){
//				temPS.clearParameters();
//			}

            new com.functionapps.files.FileList().moveFile(fileName, repName, "", "");

//			cdrCount = String.valueOf(i+1);
            if (cdrCount != null && cdrStartTime != null && cdrEndTime != null) {
                //System.out.println("CDR start time ["+cdrStartTime+"] and CDR end time ["+cdrEndTime+"]");
//				result = new String[]{ cdrCount, cdrStartTime, cdrEndTime, String.valueOf(rowInserted), this.getTableSize( conn, repName),String.valueOf(sCount),String.valueOf(fCount) };
            } else {
                result = new String[]{"0", "null", "null", "0", this.getTableSize(conn, repName), "0", "0"};
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception ex) {
            }
            result = null;
            //result = new String[]{ "0", "null", "null", "0", this.getTableSize( conn, repName),"0","0" };
        } finally {
            try {
                if (conn != null) {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.clearParameters();
                        ps.close();
                    }
                    if (temPS != null) {
                        temPS.clearParameters();
                        temPS.close();
                    }
                    //conn.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception ex) {
            }
//			errorFilePath = null;
            query = null;
            cdrCount = null;
            answerTime = null;
            endTime = null;
            inTrkName = null;
            outTrkName = null;
            inTrkNo = null;
            outTrkNo = null;
            partId = null;
            billId = null;
            values = null;
            cdrStartTime = null;
            cdrEndTime = null;
            data = null;
            fieldName = null;
            file = null;
            billIds = null;
            hm = null;
        }
        return result;
    }

    ArrayList getCDRFields(Connection conn, String feature) {
        String query = null;
        ResultSet rs = null;
        Statement stmt = null;
        ArrayList columnDetails = new ArrayList<CDRColumn>();
//		CEIRParserMain ceirparser = new CEIRParserMain();
        String period = checkGraceStatus(conn);

        try {
            query = "select * from static_rule_engine_mapping where feature='" + feature + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("Query is " + query);
            while (rs.next()) {
                CDRColumn cdrColumn = new CDRColumn(rs.getString("name"), rs.getString(period + "_type"), rs.getString("post_grace_type"));
                columnDetails.add(cdrColumn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                }
            }
        }

        return columnDetails;
    }

    private static String checkGraceStatus(Connection conn) {
        String period = "";
        String query = null;
        ResultSet rs1 = null;
        Statement stmt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Date graceDate = null;
        try {
            query = "select value from system_configuration_db where tag='grace_period_end_date'";
            System.out.println("Period is " + period);

            System.out.println("Query is " + query);

            stmt = conn.createStatement();
            rs1 = stmt.executeQuery(query);
            System.out.println("Period is " + period);
            while (rs1.next()) {
                graceDate = sdf.parse(rs1.getString("value"));
                if (currentDate.compareTo(graceDate) > 0) {
                    period = "post_grace";
                } else {
                    period = "grace";
                }
            }
            System.out.println("Period is " + period);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs1.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return period;
    }

    public String getPreviousTimeFromRaw(String repName, Connection conn) {
        String lastDate = null;
        String query = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            query = "select Changed_date from " + repName + " where sno=(select MAX(sno) from " + repName + ")";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                lastDate = rs.getString("Changed_date");
            }
        } catch (Exception ex) {
            lastDate = null;
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                }
            }
        }
        return lastDate;
    }

    public String getPreviousFileCount(String repName, Connection conn) {
        String seq_no = null;
        String query = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            query = "select seq_no from rep_schedule where rep_name = '" + repName + "'";
            System.out.println("Query is " + query);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                seq_no = rs.getString("seq_no");
            }
        } catch (Exception ex) {
            seq_no = null;
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                }
            }
        }
        return seq_no;
    }

    public String getFilePath(Connection conn, String tag_type) {
        String file_path = "";
        String query = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            query = "select value from system_configuration_db where tag='" + tag_type + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("to get configuration" + query);
            while (rs.next()) {
                file_path = rs.getString("value");
                System.out.println("in function file path " + file_path);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return file_path;

    }

    public String updateNextCount(String repName, Connection conn, int seq_no) {
        String seq_no1 = null;
        String query = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            query = "update rep_schedule set seq_no='" + seq_no + "' where rep_name = '" + repName + "';";
            System.out.println("Query is " + query);
//			PreparedStatement pstmt = conn.prepareStatement(query);
//			pstmt.executeUpdate();

            stmt = conn.createStatement();
            System.out.println("update count are " + stmt.executeUpdate(query));
            conn.commit();

            //			rs    = stmt.executeQuery(query);
//			while( rs.next() ){
//				seq_no = rs.getString("seq_no");
//			}
        } catch (Exception ex) {
            seq_no1 = null;
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    //conn.close();
                } catch (Exception e) {
                }
            }
        }
        return seq_no1;
    }

    public String getTableSize(Connection conn, String tableName) {
        String tableSize = null;
        String query = null;
        //Connection conn  = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            query = "SELECT round(sum((data_length + index_length) / 1024 / 1024 ), 4) `Size`  FROM information_schema.TABLES where table_name = '" + tableName + "'";
            //conn  = conn.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                tableSize = rs.getString("Size");
            }
        } catch (Exception e) {
            e.printStackTrace();
            tableSize = "0";
        } finally {
            if (conn != null) {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    //conn.close();
                } catch (Exception e) {
                }
            }
        }
        return tableSize;
    }

    public boolean updateCDRWithPartIdThree(Connection conn, ArrayList< String> billIds, String repName, String startTime, int startSno, int endSno) {
        int i = 0;
        boolean result = false;
        int eStartId = 0;
        int eEndId = 0;
        String query = null;
        String updateQuery = null;
        String tempBillID = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList< Integer[]> idDetailsOfCdr = null;
        //HashMap< String, String > tempBillIds = null;
        try {
            idDetailsOfCdr = new ArrayList< Integer[]>();
            idDetailsOfCdr = this.getBillIdDetailByCDRStartTime(conn, startTime);
            if (idDetailsOfCdr != null) {
                for (Object[] idDetail : idDetailsOfCdr) {
                    if (i == 0) {
                        eStartId = (int) idDetail[0];
                        eEndId = (int) idDetail[1];
                    } else {
                        eEndId = (int) idDetail[1];
                    }
                    i++;
                }
                query = "select billID,answerTime from zte_billid_temp where sno >=" + eStartId + " and sno <=" + eEndId;
                System.out.println("Query for getting all is :[" + query + "]");
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                updateQuery = "update " + repName + " set AnswerTime=?,cdr_date=? where PartRecID='3' and BillID=? and sno >=" + startSno + " and sno <=" + endSno;
                //updateQuery = "update "+repName+" set AnswerTime=IF((select answerTime from zte_billid_temp where partID='1' and billID=?)IS NULL, AnswerTime, answerTime) where PartRecID='3' and BillID=?";
                ps = conn.prepareStatement(updateQuery);
                if (rs != null) {
                    //tempBillIds = new HashMap< String, String >();
                    while (rs.next()) {
                        tempBillID = rs.getString("billID");//rs.getString("answerTime")
                        if (billIds.contains(tempBillID)) {
                            ps.setString(1, rs.getString("answerTime"));
                            ps.setString(2, rs.getString("answerTime"));
                            ps.setString(3, tempBillID);
                            //System.out.println("Update query for BillId ["+tempBillID+"] and AnswerTime ["+rs.getString("answerTime")+"] is :["+updateQuery.toString()+"]");
                            ps.addBatch();
                        }
                    }
                }
                ps.executeBatch();
            }
            if (conn != null) {
                conn.commit();
            }
            //stmt = conn.createStatement();
            //stmt.executeQuery("delete from zte_billid_temp where answerTime<=DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 3 HOUR)")

        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception ex) {
            }
            result = false;
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.clearParameters();
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception ex) {
            }
        }
        return result;
    }

    public boolean insertSlotStartAndEndSnoId(Connection conn, String cdrStartTime, int eStartId, int eEndId) {
        boolean result = false;
        try {
            result = new com.functionapps.db.Query().insert(conn, "insert into zte_id_details(cdr_start_time,e_start_id,e_end_id) values('" + cdrStartTime + "'," + eStartId + "," + eEndId + ")");
        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        }
        return result;
    }

    public ArrayList< Integer[]> getBillIdDetailByCDRStartTime(Connection conn, String cdrStartTime) {
        ArrayList< Integer[]> result = null;
        String query = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            query = "select e_start_id,e_end_id from zte_id_details where DATE_FORMAT(cdr_start_time,'%Y-%m-%d %H:%i:%s') <= DATE_SUB(FROM_UNIXTIME(FLOOR( UNIX_TIMESTAMP('" + cdrStartTime + "')/300 ) * 300),INTERVAL 15 MINUTE) AND DATE_FORMAT(cdr_start_time,'%Y-%m-%d %H:%i:%s') >= DATE_SUB(FROM_UNIXTIME(FLOOR( UNIX_TIMESTAMP('" + cdrStartTime + "')/300 ) * 300),INTERVAL 2 HOUR)";
            System.out.println("Query for getting id range is [" + query + "]");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if (rs != null) {
                result = new ArrayList< Integer[]>();
                while (rs.next()) {
                    result.add(new Integer[]{rs.getInt("e_start_id"), rs.getInt("e_end_id")});
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception ex) {
            }
        }
        return result;
    }

    public String getDatabaseSize(Connection conn, String dbName) {
        String result = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT  sum(round(((data_length + index_length) / 1024 / 1024 ), 4))  as size FROM information_schema.TABLES  WHERE table_schema ='" + dbName + "'");
            if (rs != null) {
                while (rs.next()) {
                    result = rs.getString("size");
                }
            }
        } catch (Exception e) {
            result = "0";
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception ex) {
            }
        }
        return result;
    }

    /*
	public boolean readHexdumpFile( String path, String fileName ){
		int i = 0;
		int lineLimit = 300;
		char[] buffSize  = new char[392];
		byte[] buffSize1 = new byte[392]; 
		boolean result    = false;
		//File file      = null;
		BufferedReader br = null;
		StringBuffer sb  = new StringBuffer();
		String line = null;
		Process newProcess = null;
		DecimalConverter dc = new DecimalConverter();
		try{
			//br = new BufferedReader( new FileReader(path+fileName));
			newProcess = Runtime.getRuntime().exec( "hexdump "+path+fileName );
			br         = new BufferedReader( new InputStreamReader( newProcess.getInputStream() ));
			//br = new BufferedReader( new FileInputStream(path+fileName));
			br.read(buffSize);
			sb.append(buffSize,2,0);
			System.out.println(dc.hex2Decimal(sb.toString()));
			br.close();
		}catch( Exception e ){
			e.printStackTrace();
			result = false;
		}
		return result;
	}
     */
 /*public HashMap< String, ArrayList< String > > readBinaryFileUsingDIS( String fileName, String filePath, String repName ){
		int i = 0;
		int offset  = 0;
		int numRead = 0;
		//boolean result       = false;
		int limit       = 10000;
		//boolean result  = false;
		String query    = null;
		String values   = "values(";
		Connection conn      = null;
		PreparedStatement ps = null;
		ResultSet rs         = null;
		String cdrConsider   = null;
		String cdrStartTime  = null;
		String drEndTime     = null;
		String data          = null;
		String fieldName     = null;
		File file            = null;
		FileInputStream fis  = null;
		DataInputStream dis  = null;
		byte[] buffer        = null;
		//String line          = null;
		int[] fieldOffset    = null;
		DecimalConverter dc  = new DecimalConverter();
		ArrayList< String > temp    = null; 
		HashMap< String, int[] > hm = new HashMap< String, int[] >();
		//HashMap< String, String > fieldValues = new HashMap< String, String >();
		HashMap< String, ArrayList< String > > result      = new HashMap< String, ArrayList< String > >();
		HashMap< String, ArrayList< String > > fieldValues = new HashMap< String, ArrayList< String > >();
		try{
			query = "insert into "+repName+"(";
			for( String field : fields ){
				query  = query + field + ",";
				values = values + "?,";
			}
			query = query.substring( 0, query.length() - 1 )+") "+values.substring( 0, values.length() - 1 )+")";
			conn  = new com.functionapps.db.MySQLConnection().getConnection();
			ps    = conn.prepareStatement( query );
			buffer = new byte[392];
			file = new File(filePath);
			fis  = new FileInputStream(file);
			dis  = new DataInputStream( fis );
			hm   = zte.getfieldSet();
			//i = dis.read( buffer );
			while( ( offset < buffer.length ) && (numRead=dis.read(buffer, offset, buffer.length-offset)) >= 0 ){
				for( int j = 0; j < fields.length; j++ ){
					data = null;
					temp = new ArrayList< String >();
					fieldOffset = new int[3];
					fieldName   = fields[j];
					if( !fieldName.equals("cdr_condsider") ){
						fieldOffset = hm.get(fieldName);
						byte[] byteData = null;
						if( i != 0 ){
							temp = fieldValues.get( fieldName );
						}
						switch( fieldOffset[2] ){
							case 0 : 
								byteData = Arrays.copyOfRange( buffer, fieldOffset[0], fieldOffset[0]+fieldOffset[1]);
								data     = dc.hex2Decimal(dc.bytesToHex(byteData)); 
								temp.add( data );
								break;
							case 1 :
								
								byteData = Arrays.copyOfRange( buffer, fieldOffset[0], fieldOffset[0]+fieldOffset[1]);
								//System.out.println("FieldName:["+fieldName+"],Offset["+fieldOffset[0]+"],EndIndex["+fieldOffset[0]+fieldOffset[1]+"] and Hex ["+dc.bytesToHex(byteData)+"]");
								data     = dc.getNumberFromBCD(dc.bytesToHex(byteData));
								temp.add( data );
								break;
							case 2 : 
								byteData = Arrays.copyOfRange( buffer, fieldOffset[0], fieldOffset[0]+fieldOffset[1]);
								data     = dc.getNumberFromRightBCD(dc.bytesToHex(byteData));
								temp.add( data );
								break;
							case 3:
								data = dc.getBitFromByte( buffer[fieldOffset[0]], fieldOffset[1]);
								temp.add( data );
								//System.out.println("Original data:"+fieldOffset[1]+", Type 3 data offset:"+(int)fieldOffset[1]);
								//System.out.println("byte:"+buffer[131]+",bits:"+Integer.toBinaryString( (int) buffer[131]));
								break;
							case 4:
								byteData = Arrays.copyOfRange( buffer, fieldOffset[0], fieldOffset[0]+fieldOffset[1]);
								data     = dc.getStringFromByte( byteData );
								temp.add( data );
								//System.out.println("Original data:"+fieldOffset[1]+", Type 3 data offset:"+(int)fieldOffset[1]);
								//System.out.println("byte:"+buffer[131]+",bits:"+Integer.toBinaryString( (int) buffer[131]));
								break;
							default : 
								break;
						}
						if( fieldName.equals("EndTime") && data != null || !data.equals("NULL") || !data.equals("0") || !data.equals("") ){
							cdrConsider = "Y";
							if( i == 0 ){
								cdrStartTime = data;
							}
						}else{
							cdrConsider = "N";
						}
						ps.setString( j+1, data);
					}else{
						ps.setString( j+1, data);
						temp.add(cdrConsider);
					}
					if( i == 0 ){
						fieldValues.put( fieldName, temp );
					}
				}
				i++;
			}
			result = fieldValues;
			new com.functionapps.files.FileList().moveFile(fileName, repName);
			//System.out.println(fieldValues.toString());
		}catch( Exception e ){
			e.printStackTrace();
			result = null;
		}finally{
			try{
				if(conn != null){
					if(rs != null)
						rs.close();
					if( ps != null )
						ps.close();
					conn.close();
				}
				if( fis != null )
					fis.close();
				if( dis != null )
					dis.close();
			}catch( Exception ex ){}
		}
		return result;
	}*/
    public int getFieldIndex(String fieldName) {
        int result = 0;
        List< String> fieldList = null;
        try {
            fieldList = new ArrayList<String>();
            fieldList = Arrays.asList(fields);
            if (fieldList.contains(fieldName)) {
                result = fieldList.indexOf(fieldName);
            }

        } catch (Exception ex) {
            result = 0;
            ex.printStackTrace();
        }
        return result;
    }

    public static boolean validateJavaDate(String strDate) {
        System.out.println("date,,," + strDate);
        strDate = strDate.replaceAll("/", "-");
        System.out.println("date....." + strDate);
        if (strDate.trim().equals("")) {
            return true;
        } else {
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
            sdfrmt.setLenient(false);

            try {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate + " is valid date format");
            } catch (Exception e) {
                System.out.println(strDate + " is Invalid Date format");
                return false;
            }
            return true;
        }
    }

}
