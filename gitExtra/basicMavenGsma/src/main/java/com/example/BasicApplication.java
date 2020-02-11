package com.example;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.security.KeyStore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class BasicApplication {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String KEYSTOREPATH = "/home/ubuntu/CEIR/GSMA_CLIENT/gsma_ks.jks"; // or .p12
    private static final String KEYSTOREPASS = "Hello1234";
    private static final String KEYPASS = "keypass";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BasicApplication.class, args);
        String fileOutputName = args[0];

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileOutputName));
//        String line = reader.readLine();
//        while (line != null) {
//            LOGGER.log(Level.INFO, " Line is" + line);
//            String[] arrOfFile = line.split(",", 13);
//            String imei_tac = arrOfFile[1].trim().substring(0, 8);
//            BasicApplication stt = new BasicApplication();
//            stt.gsmaApplication(imei_tac);
//            line = reader.readLine();
//        }
        BasicApplication stt = new BasicApplication();
        reader.readLine();
        String line = reader.readLine();
        Set<String> hash_Set = new HashSet<String>();
        while (line != null) {
            String data = line.substring(line.indexOf(",", 2) + 1, line.indexOf(",", 4)).trim();
            if (!data.equals("")) {
                hash_Set.add(data.substring(0, 8));
            }
            line = reader.readLine();
        }
        reader.close();
        Iterator itr = hash_Set.iterator();
        while (itr.hasNext()) {
            LOGGER.log(Level.INFO, " Initilase  for " + itr.next());
            stt.gsmaApplication(itr.next().toString());
        }

        
    }

    public KeyStore readStore() throws Exception {
        try (InputStream keyStoreStream = this.getClass().getResourceAsStream(KEYSTOREPATH)) {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // or "PKCS12"
            keyStore.load(keyStoreStream, KEYSTOREPASS.toCharArray());
            return keyStore;
        }
    }

    public String gsmaApplication(String imei_tac) throws InterruptedException, Exception {
        LOGGER.log(Level.INFO, "imei_tac is " + imei_tac);
        String msisdn = imei_tac;
        String status = "0";

        Connection conn = null;
        if (msisdn.length() != 8 || !msisdn.matches("[0-9]+")) {
            status = " DeviceId should be 1st 8 digits of an IMEI";
            LOGGER.log(Level.SEVERE, "DeviceId should be 1st 8 digits of an IMEI");
        } else {
            Thread.sleep(100);
            LOGGER.log(Level.FINEST, "Gsma Application Started");
            String url = System.getenv("ceir_db_url");
            String username = System.getenv("ceir_db_username");
            String password = System.getenv("ceir_db_password");
            String db_name = System.getenv("ceir_db_dbName");
            String db_type = System.getenv("ceir_db_dbType");
            String jdbcUrl = "";
            String className = "";
            int dbtypeNUM = 0;
            if (db_type.equals("oracle") || db_type.equals(null) || db_type.equals("null")) {
                jdbcUrl = "jdbc:oracle:thin:@" + url + "/" + db_name;
                className = "oracle.jdbc.driver.OracleDriver";
                dbtypeNUM = 1;
            } else {
                jdbcUrl = "jdbc:mysql://" + url + "/" + db_name;
                className = "com.mysql.cj.jdbc.Driver";
                dbtypeNUM = 2;
            }
            LOGGER.log(Level.INFO, "Database Initialised at database " + jdbcUrl);

            try {
                Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                LOGGER.log(Level.SEVERE, "Database ClassnotFound " + e);
            }

            conn = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt = conn.createStatement();
            String msidnid = " select id from gsma_tac_db where  device_id = " + msisdn + "  ";
            ResultSet resultmsdn = stmt.executeQuery(msidnid);
            String resultid = "0";
            try {
                while (resultmsdn.next()) {
                    resultid = resultmsdn.getString(1);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Database ClassnotFound " + e);

            }
            LOGGER.log(Level.INFO, "Found at resultid  " + resultid);

            if (resultid.equals("0") || resultid.equals("")) {
                String str = " select value from system_configuration_db where tag in ('gsma_tac_APIKey' , 'gsma_tac_Password','gsma_tac_Salt_String' , 'gsma_tac_Organization_Id' ,'gsma_tac_Secretkey', 'gsma_tac_httpPostUrl',  'gsma_tac_timewait')  ";

                ResultSet result = stmt.executeQuery(str);
                String APIKey = null, Password = null, Salt_String = null, Organization_Id = null, Secretkey = null, httpPostUrl = null, gsma_tac_timewait = null;
                String arr[] = new String[7];
                int count = 0;
                while (result.next()) {
                    System.out.println("count    " + count + ",,,,, " + result.getString(1));
                    arr[count] = result.getString(1);
                    count++;
                }

                if (dbtypeNUM == 2) {
                    APIKey = arr[0];
                    Password = arr[1];
                    Salt_String = arr[2];
                    Organization_Id = arr[3];
                    Secretkey = arr[4];
                    httpPostUrl = arr[5];
                    gsma_tac_timewait = arr[6];
                }
                if (dbtypeNUM == 1) {

                    APIKey = arr[6];
                    Password = arr[5];
                    Salt_String = arr[4];
                    Organization_Id = arr[3];
                    Secretkey = arr[2];
                    httpPostUrl = arr[1];
                    gsma_tac_timewait = arr[0];

                }

                LOGGER.log(Level.INFO, "System Configurations loaded");

                conn.close();

                BasicApplication obj = new BasicApplication();
                SSLContext sslContext = SSLContexts.custom()
                        .loadKeyMaterial(obj.readStore(), KEYPASS.toCharArray()) // use null as second param if you don't have a separate key password
                        .build();
                String testrequest = "{\"portName\":\"Phnom penh\",\"country\": \"Cambodia\", \"portType\": \"AIR\",\"deviceId\":\"" + msisdn + "\"}";
                StringEntity input = new StringEntity(testrequest);
                input.setContentType("application/json");

                HttpClient httpClient = HttpClients.custom().setSslcontext(sslContext).build();
//         HttpPost request = new HttpPost("https://devicecheck.gsma.com/imeirtl/leadclookup");
//        HttpPost request = new HttpPost("https://imeidb.gsma.com/services/rest/GetHandSetDetails");   // original
                HttpPost request = new HttpPost(httpPostUrl);
                int timewait = Integer.parseInt(gsma_tac_timewait);

                Thread.sleep(timewait);

                String auth = EncriptonService.getAuth(msisdn, APIKey, Password, Salt_String, Organization_Id, Secretkey);
//        String auth = "UskCX7vhGHdLUotAa+WHtzCwuNkqz9WBgnrBM5i2lpJcfGOEYpx1Bg6GV88MBxltjdm+2+bn946nqiwbo1WW0DeBh5k8AldLqRK1TaMXx5w=";
                request.addHeader("Authorisation", auth);
//	request.addHeader("Content-Type", "application/x-www-form-urlencoded");
                request.addHeader("Content-Type", "application/json");
                request.setEntity(input);
                HttpResponse response = httpClient.execute(request);

                HttpEntity entity = response.getEntity();

                String message = EntityUtils.toString(response.getEntity());

                EntityUtils.consume(entity);
                LOGGER.log(Level.INFO, "Response received from Api" + message);

                BasicApplication snt = new BasicApplication();
                status = snt.databaseMapper(message);
            } else {
                status = "IMEI already Exists in schema";

                LOGGER.log(Level.WARNING, "IMEI already Exists in schema");
            }
        }
        LOGGER.log(Level.INFO, "Final Status.." + status);
        return status;

    }

    public String databaseMapper(String message) throws SQLException {
        String status = "";
        Connection conn = null;
        try {
            Gson gson = new Gson();
            GsmaEntity product = gson.fromJson(message, GsmaEntity.class);
            String url = System.getenv("ceir_db_url");
            String username = System.getenv("ceir_db_username");
            String password = System.getenv("ceir_db_password");
            String db_name = System.getenv("ceir_db_dbName");
            String db_type = System.getenv("ceir_db_dbType");
            int id = 0;
            String jdbcUrl = "";
            String className = "";
            String limit_var = "";
            if (db_type.equals("oracle")) {
                jdbcUrl = "jdbc:oracle:thin:@" + url + "/" + db_name;
                className = "oracle.jdbc.driver.OracleDriver";
                limit_var = " fetch next 1 rows only";
            } else {
                jdbcUrl = "jdbc:mysql://" + url + "/" + db_name;
                className = "com.mysql.cj.jdbc.Driver";
                limit_var = " limit 1 ";
            }
            LOGGER.log(Level.INFO, "Database Initialised at database " + jdbcUrl);
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt = conn.createStatement();
            String str = "select id from gsma_tac_db order by id desc " + limit_var + "";
            System.out.println("Str is " + str);
            ResultSet result = stmt.executeQuery(str);
            try {
                while (result.next()) {
                    id = result.getInt(1);
                    System.out.println("......" + id);
                }
            } catch (Exception e) {
                System.out.println("EXception in id...(252)");
            }
            int idd = id + 1;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now1 = LocalDateTime.now();
            String now = dtf.format(now1);
            System.out.println("Local TTTTTTTTTTIMEEEEEEEEE" + now);
            System.out.println("gsma_tac_db Id ...." + idd);
            String sql = "insert into  gsma_tac_db (id,  status_message,  device_id, band_name ,model_name,internal_model_name ,marketing_name,equipment_type ,sim_support,"
                    + " nfc ,wlan,bluetooth  ,lpwan  ,manufacturer_or_applicant,tac_approved_date ,gsma_approved_tac,operating_system ,device_certify_body ,radio_interface , created_on  )"
                    + "Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ? )";    //created_on
            PreparedStatement statement = conn.prepareStatement(sql);

//            statement.setInt(1, product.getStatusCode());
            statement.setInt(1, idd);
            statement.setString(2, product.getStatusMessage());
            statement.setString(3, product.getDeviceId());
            statement.setString(4, product.getBrandName());
            statement.setString(5, product.getModelName());
            statement.setString(6, product.getInternalModelName());
            statement.setString(7, product.getMarketingName());
            statement.setString(8, product.getEquipmentType());
            statement.setString(9, product.getSimSupport());
            statement.setString(10, product.getNfcSupport());
            statement.setString(11, product.getWlanSupport());
            statement.setString(12, product.getBlueToothSupport());   //
            statement.setString(13, product.getLpwan());        //  
            statement.setString(14, product.getManufacturer());
            statement.setString(15, product.getTacApprovedDate());
            statement.setString(16, product.getGsmaApprovedTac());
            statement.setString(17, product.getOperatingSystem());
            statement.setString(18, product.getDeviceCertifybody());
            statement.setString(19, product.getRadioInterface());
            statement.setString(20, now);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
            }

            System.out.println("product.getBrandName()" + product.getBrandName());

            LOGGER.log(Level.INFO, "product.getBrandName()" + product.getBrandName());
            if (!product.getBrandName().equals("NA")) {
                LOGGER.log(Level.INFO, "Excecution start for brand and Model");
                Statement stmt2 = conn.createStatement();
                ResultSet result1 = stmt2.executeQuery("select id ,count(*)as count from brand_name  where brand_name =   '" + product.getBrandName() + "'  group by id ");
                int Rid = 0;
                int ridd = 0;
                int count = 0;
                int brand_id = 0;
                try {
                    while (result1.next()) {
                        brand_id = result1.getInt(1);
                        count = result1.getInt(2);
                        LOGGER.log(Level.INFO, "count,,,,,,,," + count + "brand_id" + brand_id);

                    }

                } catch (Exception e) {
                    LOGGER.log(Level.INFO, "It is exc. as count is 0" + e);

                }

                if (count != 0) {
                    LOGGER.log(Level.INFO, "Brand Already Exists in Schema");
                    Rid = brand_id;
                    ridd = Rid;
                } else {

                    ResultSet result2 = stmt.executeQuery("select id from brand_name order by id desc " + limit_var + "");
                    LOGGER.log(Level.INFO, "....................");

                    while (result2.next()) {
                        Rid = result2.getInt(1);
                    }

                    ridd = Rid + 1;
                    System.out.println("ridd.." + ridd);

                    String srt = "insert into brand_name(id , brand_name ) VAlues(" + ridd + ",   '" + product.getBrandName() + "'  ) ";
                    LOGGER.log(Level.INFO, "quert ast..." + srt);

                    PreparedStatement statementR = conn.prepareStatement(srt);
                    int rowsInsertedR = statementR.executeUpdate();
                    if (rowsInsertedR > 0) {
                        LOGGER.log(Level.INFO, "Data inserted in brand_name");
                    }
                }

                try {
                    int Bid = 0;
                    String ast = "select id from model_name order by id desc   " + limit_var + "  ";
                    LOGGER.log(Level.INFO, "Query at " + ast);
                    try {
                        ResultSet result6 = stmt.executeQuery(ast);
                        while (result6.next()) {
                            Bid = result6.getInt(1);
                        }
                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "Warning " + e);
                    }
                    int Bidd = Bid + 1;
                    String aasr = "insert into model_name( model_name,brand_name , brand_name_id , id ) VAlues( ' " + product.getModelName() + " ',   '   " + product.getBrandName() + " ' ," + ridd + ",  " + Bidd + "   )";
                    LOGGER.log(Level.INFO, "Query at " + aasr);
                    try {
                        PreparedStatement statementM = conn.prepareStatement(aasr);
                        int rowsInsertedM = statementM.executeUpdate();
                        if (rowsInsertedM > 0) {
                            LOGGER.log(Level.INFO, "Data inserted in Model_name");
                        }
                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "Data is already present in Model_name");
                    }

                } catch (Exception e) {
                    LOGGER.log(Level.INFO, "Data exists in Model_name " + e);
                }

            } else {
                LOGGER.log(Level.INFO, "Data will not Save  in Model_name  and Brand_name as IT shows NA ");
            }

            status = "Success";
        } catch (Exception e1) {
            status = e1.toString();
        } finally {
            conn.close();
        }

        return status;
    }

}

//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.mavenproject1;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Map;
///**
// *
// * @author user
// */
//
//public class mvnParent {
//    
//      public static  void main(String[] args) throws FileNotFoundException, IOException {
//
//      //getting username using System.getProperty in Java
////       String user = System.getProperty("user.name") ;
//       String user = System.getProperty("testData") ;
//       System.out.println("Username using system property: "  + user);
//   
//     //getting username as environment variable in java, only works in windows
//          
//     //name and value of all environment variable in Java  program
//      Map<String, String> env = System.getenv();
//        for (String envName : env.keySet()) {
//            System.out.format("%s=%s%n", envName, env.get(envName));
//        }
////   System.setProperty("TEST11111111111111", "SSSSSSSSSSSSSSSS");
//System.out.println("<<<<<<<<<<<<<<"+   System.getProperty("TEST11111111111111")) ;
//          System.out.println("ended");
////           
//// Pro<String, String> env1 = System.getProperties();
////        for (String envName : env1.keySet()) {
////            System.out.format("%s=%s%n", envName, env1.get(envName));
////        }
//            
//            
//            
////    ProcessBuilder pb = new ProcessBuilder("/bin/sh"); 
////
////    Map<String, String> envMap = pb.environment();
////
//////    envMap.put("MY_ENV_VAR", "TTTTTTTTTTTTTTT");
////          System.out.println("$$$$$$$$$$$$$$+"+ envMap.get("MY_ENV_VAR"));
////    Set<String> keys = envMap.keySet();
////    for(String key:keys){
////        System.out.println(key+" ==> "+envMap.get(key));
////    }
////          System.out.println("esdddSTTTTTTTTTTTTTTTTTTTTTart");
////    FileReader fr =      new FileReader("/bin/sh"); 
////  
////    int i; 
////    while ((i=fr.read()) != -1) 
////      System.out.print((char) i); 
////    
////    
//    
//    
//            
//          
//            
//        }
//          
//        
//    }
//    
//  
