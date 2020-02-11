package com.outputFileExchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@SpringBootApplication
public class OutputFileExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutputFileExchangeApplication.class, args);
        OutputFileExchangeApplication atr = new OutputFileExchangeApplication();

        String forDate = "";
        try {
            if (args.length == 0) {
                forDate = "2058";
            } else {
                forDate = args[0];
            }
            atr.outputfilemethod(forDate);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    CHECK DT SQL QUERY
    public void outputfilemethod(String args) throws SQLException {
        Connection conn = null;
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            Properties prop = new Properties();
            InputStream input = new FileInputStream("application.properties");
            prop.load(input);
            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");
            String db_name = prop.getProperty("db.db_name");
            String vas_provider = prop.getProperty("db.vas_provider");
//        String fileNameInputpath = prop.getProperty("db.store_path");
            String fileNameInputpath = "";
            String jdbcUrl = "jdbc:mysql://" + url + "/" + db_name;
            System.out.println("jdbcUrl>>>>>> " + jdbcUrl);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
            Date date1 = new Date();
            String batchdate = null;

            String batchno = "00001";

            if (args.equals(null) || args.equals("2058")) {
                batchdate = dateFormat1.format(date1).replace("/", "");
            } else {
                batchdate = args.replace("/", "");
            }

            String fileOutputName = fileNameInputpath + vas_provider + "_" + batchno + "_" + batchdate + ".renew.done";
            System.out.println("fileOutputName >>>>>>>>>>>"+ fileOutputName);
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(fileOutputName));
            String line = reader.readLine();
        System.out.println("TO be updated created " + line.toString());
            while (line != null) {
//            try{
                String[] arrOfFile = line.trim().split(",", 13);
                String msisdn = arrOfFile[0];
                String serviceId = arrOfFile[1];
                String serviceExp = arrOfFile[4];
                String processResultCode = arrOfFile[8];

                conn = DriverManager.getConnection(jdbcUrl, username, password);
                String sqlnew = null;
                System.out.println(" msisdn ..."+ msisdn +"...processResultCode..."+ processResultCode);
                if (processResultCode.equals("1")) {

                    sqlnew = "update vms_users set pack_id= (select pack_id from vms_packs where service_id = '" + serviceId + "') , next_renewal_date = '" + serviceExp + "' where  msisdn =  " + msisdn + "";
                    System.out.println("update equery sqlnew" + sqlnew);
                    PreparedStatement statement6 = conn.prepareStatement(sqlnew);
                    int rowsInserted6 = statement6.executeUpdate();
                    if (rowsInserted6 > 0) {
                        System.out.println("Action Taken :" + sqlnew);
                    }
                }
                if (processResultCode.equals("3") || processResultCode.equals("4") || processResultCode.equals("5") || processResultCode.equals("6")) {

//                INSERT INTO new_table SELECT * FROM original_table;
                    String newQuy = "INSERT INTO vms_users_history SELECT * FROM vms_users where msisdn =   " + msisdn + " ";
                    PreparedStatement statement4 = conn.prepareStatement(newQuy);
                    int rowsInserted4 = statement4.executeUpdate();
                    if (rowsInserted4 > 0) {
                        System.out.println("Action Taken  for insert :" + newQuy);
                    }

                    sqlnew = "delete from vms_users where  msisdn = " + msisdn + "";
                    PreparedStatement statement = conn.prepareStatement(sqlnew);
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Action Taken :" + sqlnew);
                    }
                }

                line = reader.readLine();
//        }//         catch(Exception e){System.out.println("......."+ e);}   
                conn.close();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();

        }

    }

}



















//    	Class.forName("com.mysql.jdbc.Driver");  
//    	Connection con=DriverManager.getConnection("jdbc:mysql://172.31.22.35:3306/testFileUploadDb","root","root");  
//    	
//    	Statement stmt=con.createStatement();  
//    	ResultSet result=stmt.executeQuery("select * from VasPro");  
//    	StringBuffer s = new StringBuffer(""); 
//    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//    	Date date1 = new Date();
//    	String dateNow = dateFormat.format(date1);      	
//    	
//  String sql = "UPDATE vms_users SET serviceId=?, userName=?, renewalCost=?  , serviceExp= ?,"
//                        + "  serviceDur=?  , uniqueId=? ,   processTime =? , processResultCode = ?,"
//                        + "   cbsResponse = ? , amount = ?  ,lastChargedTime = ? ,  lastChargedAmt = ?   WHERE msisdn =?";
//729366851,VMS30,SMARTEYES,35,2020-01-22 10:35:25,30,8ca2ebc1-3bee-4d09-95f4-08137c37df2b,2020-01-27 15:46:13,0,405914555:There is not enough money in the account.,0,,0
//                statement.setString(1, serviceId);
//                statement.setString(2, userName);
//                statement.setString(3, renewalCost);
//                statement.setString(4, serviceExp);
//                statement.setString(5, serviceDur);
//                statement.setString(6, uniqueId);
//                statement.setString(7, processTime);
//                statement.setString(8, processResultCode);
//                statement.setString(9, cbsResponse);
//                statement.setString(10, amount);
//                statement.setString(11, lastChargedTime);
//                statement.setString(12, lastChargedAmt);
//                statement.setString(13, msisdn);
//String userName = arrOfFile[2];
//            String renewalCost = arrOfFile[3];
//String serviceDur = arrOfFile[5];
//            String uniqueId = arrOfFile[6];
//
//            String processTime = arrOfFile[7];
//String cbsResponse = arrOfFile[9];
//            String amount = arrOfFile[10];
//            String lastChargedTime = arrOfFile[11];
//
//            String lastChargedAmt = arrOfFile[12];
//        String zipFilePath = fileOutputName;
//        String destDir = fileNameInputpath;
//        File dir = new File(destDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//            System.out.println("Directory Made");
//        }
//        FileInputStream fis;
//        byte[] buffer = new byte[1024];
//        fis = new FileInputStream(zipFilePath);
//        ZipInputStream zis = new ZipInputStream(fis);
//        ZipEntry ze = zis.getNextEntry();
//        while (ze != null) {
//            String fileName = ze.getName();
//            File newFile = new File(destDir + File.separator + fileName);
//            System.out.println("Unzipping to " + newFile.getAbsolutePath());
//            new File(newFile.getParent()).mkdirs();
//            FileOutputStream fos = new FileOutputStream(newFile);
//            int len;
//            System.out.println("length is "+ zis.read(buffer)  );
//            while ((len = zis.read(buffer)) > 0) {
//                fos.write(buffer, 0, len);
//            }
//            fos.close();
//        }
//            zis.closeEntry();
//            ze = zis.getNextEntry();
//            zis.closeEntry();
//            
//            zis.close();
//            fis.close();
//        File fout = new File(fileOutputName);
//        int count = 1;
