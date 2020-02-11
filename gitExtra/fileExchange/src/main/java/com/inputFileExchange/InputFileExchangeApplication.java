package com.inputFileExchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@SpringBootApplication
public class InputFileExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(InputFileExchangeApplication.class, args);
        InputFileExchangeApplication atr = new InputFileExchangeApplication();
        String forDate = "";
        try {
            if (args.length == 0) {
                forDate = "2058";
            } else {
                forDate = args[0];
            }
            atr.inputfilemethod(forDate);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
//            try {
//                
//                atr.inputfilemethod("2058");
//            } catch (Exception e1) {
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inputfilemethod(String args) throws Exception {

        Properties prop = new Properties();
//    	 InputFileExchangeApplication.class.getClassLoader().getResourceAsStream("application.properties");
//        InputStream input = InputFileExchangeApplication.class.getClassLoader().getResourceAsStream("/application.properties");
        InputStream input = new FileInputStream("application.properties");
        prop.load(input);
        String url = prop.getProperty("db.url");
        String password = prop.getProperty("db.password");
        String username = prop.getProperty("db.username");
        String db_name = prop.getProperty("db.db_name");
        String vas_provider = prop.getProperty("db.vas_provider");
//        String fileNameInputpath = prop.getProperty("db.store_path");
        String fileNameInputpath = "";
        String jdbcUrl = "jdbc:mysql://" + url + "/" + db_name;
        System.out.println("DBSTRING....." + jdbcUrl);
        System.out.println("DBusername....." + username + ",..Paswword..." + password);
        System.out.println("vas_provider is :" + vas_provider);

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        String batchdate = "";
        String yesterDate = null;
        if (args.equals(null) || args.equals("2058")) {
            batchdate = dateFormat1.format(date1).replace("-", "");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            yesterDate = dateFormat1.format(cal.getTime());
        } else {
            LocalDate date = LocalDate.parse(args);
            batchdate = date.toString().replace("-", "");
            LocalDate returnvalue = date.minusDays(1);
            yesterDate = returnvalue.toString();
        }

        System.out.println("YESTERDAY RECENT Is" + args);
//        if (args.equals(null) || args.equals("2058")) {
//            yesterDate = dateFormat1.format(cal.getTime());
//        } else {
//            yesterDate = args;
//        }
        System.out.println("UPDATES YESTERDAY ::" + yesterDate);
//		Class.forName("com.mysql.jdbc.Driver"); 
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        String batchno = "00001";
        String fileNameInput = fileNameInputpath + vas_provider + "_" + batchno + "_" + batchdate + ".renew";
        File fout = new File(fileNameInput);

        Statement stmt = con.createStatement();

        String sql = "Select msisdn, service_id,  pack_price, next_renewal_date  ,pack_validity from vms_users inner join  vms_packs on vms_packs.pack_id = vms_users.pack_id where renewal_flag = 1 and next_renewal_date like '" + yesterDate + "%'";

        ResultSet result = stmt.executeQuery(sql);
        System.out.println("Statement Query ...--" + sql);
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        while (result.next()) {
            StringBuffer s = new StringBuffer("");
//        		String msisdn = result.getString(1);
            String userName = result.getString(3);
            System.out.println("userName is :" + userName);
            s.append(result.getString(1) + "," + result.getString(2) + "," + vas_provider + "," + result.getString(3)
                    + "," + result.getString(4) + "," + result.getString(5));
            bw.write(s.toString());
            bw.newLine();
        }
        bw.close();

        File file = new File(fileNameInput);
        String zipFileName = fileNameInputpath + file.getName().concat(".zip");
        System.out.println("zipFileName:" + zipFileName);
        FileOutputStream fosz = new FileOutputStream(zipFileName);
        ZipOutputStream zos = new ZipOutputStream(fosz);
        System.out.println("zos :" + zos);
        zos.putNextEntry(new ZipEntry(file.getName()));
        byte[] bytes = Files.readAllBytes(Paths.get(fileNameInput));
        System.out.println(">>>>" + bytes.length);
        zos.write(bytes, 0, bytes.length);
        zos.closeEntry();
        zos.close();

        File oldfile = new File(zipFileName);
        File newfile = new File(zipFileName + ".upload");
        boolean status = oldfile.renameTo(newfile);
        System.out.println("status is " + status);

    }
}

//}
//		String zipFileName = fout.getName().concat(".zip");
//		FileOutputStream fosz = new FileOutputStream(zipFileName);
//		ZipOutputStream zos = new ZipOutputStream(fosz);
//	    zos.putNextEntry(new ZipEntry(fout.getName()));
//		byte[] bytes = Files.readAllBytes(Paths.get(fileNameInput));
//		zos.write(bytes, 0, bytes.length);
//		zos.closeEntry();
//		zos.close();
//		String url = "10.150.100.36:3306";
//		String username = "root";
//		String password = "root";
//		String db_name = "vms_se";
//		String fileNameInputpath = "D:/javaFiles/";
////    			#/root/File_Exchange_Path    #D:/javaFiles/
//		String vas_provider = "SMARTEYES";
