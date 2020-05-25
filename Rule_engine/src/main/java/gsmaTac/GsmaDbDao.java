package gsmaTac;

import com.gl.utils.Util;
import com.google.gson.Gson;
import static gsmaTac.BasicApplication.logger;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

class GsmaDbDao {

    public String databaseMapper(String message, Connection conn) throws SQLException {

        try {
            Gson gson = new Gson();
            GsmaEntity product = gson.fromJson(message, GsmaEntity.class);
            int id = 0;

            String limit_var = "";
            if (conn.toString().contains("oracle")) {
                limit_var = " fetch next 1 rows only";
            } else {
                limit_var = " limit 1 ";
            }
            Statement stmt = conn.createStatement();
            boolean isOracle = conn.toString().contains("oracle");
            String dateFunction = Util.defaultDate(isOracle);
            String str = "select id from gsma_tac_db order by id desc " + limit_var + "";
            logger.info("Str is " + str);
            ResultSet result = stmt.executeQuery(str);
            try {
                while (result.next()) {
                    id = result.getInt(1);
                    logger.info("......" + id);
                }
            } catch (Exception e) {
                logger.info("EXception in id..");
            }
            result.close();

            int idd = id + 1;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
            LocalDateTime now1 = LocalDateTime.now();
            String now = dtf.format(now1);
            String getDeviceCertifybody = product.getDeviceCertifybody().contains("[") ? product.getDeviceCertifybody().replace("[", " ") : product.getDeviceCertifybody();
            logger.info(" ....  ");
            getDeviceCertifybody = getDeviceCertifybody.contains("]") ? getDeviceCertifybody.replace("]", " ") : getDeviceCertifybody;
            logger.info("gsma_tac_db getRadioInterface ...." + getDeviceCertifybody);
            String getOperatingSystem = product.getOperatingSystem().contains("[") ? product.getOperatingSystem().replace("[", " ") : product.getOperatingSystem();
            logger.info(" ....  ");
            getOperatingSystem = getOperatingSystem.contains("]") ? getOperatingSystem.replace("]", " ") : getOperatingSystem;
            logger.info("gsma_tac_db getRadioInterface ...." + getOperatingSystem);
            String getRadioInterface = product.getRadioInterface().contains("[") ? product.getRadioInterface().replace("[", " ") : product.getRadioInterface();
            logger.info(" ....  ");
            getRadioInterface = getRadioInterface.contains("]") ? getRadioInterface.replace("]", " ") : getRadioInterface;
            logger.info("gsma_tac_db getRadioInterface ...." + getRadioInterface);
            String sqlqry = "insert into  gsma_tac_db ( created_on, status_message,  device_id, band_name ,model_name,internal_model_name ,marketing_name,equipment_type ,sim_support,"
                    + " nfc ,wlan,bluetooth  ,lpwan  ,manufacturer_or_applicant,tac_approved_date ,gsma_approved_tac,operating_system ,device_certify_body ,radio_interface ,id, status_code , brand_name)"
                    + "Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? , ? ,? ,?)";    //created_on
            PreparedStatement statement = conn.prepareStatement(sqlqry);
            statement.setString(1, now);
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
            statement.setString(17, getOperatingSystem);
            statement.setString(18, getDeviceCertifybody);
            statement.setString(19, getRadioInterface);
            statement.setInt(20, idd);
            statement.setString(21, product.getStatusCode());
            statement.setString(22, product.getBrandName());

//            statement.setString(20, now);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Inserted in gsmaTacDb" + sqlqry);
            }
            statement.closeOnCompletion();
            logger.info("product.getBrandName()" + product.getBrandName());

            if (!product.getBrandName().equals("NA")) {
                logger.info("Excecution start for brand and Model");
//                Statement stmt2 = conn.createStatement();
                ResultSet result1 = stmt.executeQuery("select id ,count(*)as count from brand_name  where brand_name =   '" + product.getBrandName() + "'  group by id ");
                logger.info("select id ,count(*)as count from brand_name  where brand_name =   '" + product.getBrandName() + "'  group by id ");
                int Rid = 0;
                int ridd = 0;
                int count = 0;
                int brand_id = 0;
                try {
                    while (result1.next()) {
                        brand_id = result1.getInt("id");
                        count = result1.getInt("count");
                        logger.info("count,,,,,,,," + count + "brand_id" + brand_id);
                    }
                    result1.close();

                } catch (Exception e) {
                    logger.info("It is exc. as count is 0" + e);
                }

                if (count != 0) {
                    logger.info("Brand Already Exists in Schema");
                    Rid = brand_id;
                    ridd = Rid;
                } else {
                    ResultSet result2 = stmt.executeQuery("select id from brand_name order by id desc " + limit_var + "");
                    logger.info("select id from brand_name order by id desc " + limit_var + "");
                    while (result2.next()) {
                        Rid = result2.getInt(1);
                    }
                    result2.close();

                    ridd = Rid + 1;
                    logger.info("ridd.." + ridd);

                    String srt = "insert into brand_name(id , brand_name  ,created_on) VAlues(" + ridd + ",   '" + product.getBrandName() + "'  , '" + now + "' ) ";
                    logger.info("insert ....." + srt);

                    PreparedStatement statementR = conn.prepareStatement(srt);
                    int rowsInsertedR = statementR.executeUpdate();
                    if (rowsInsertedR > 0) {
                        logger.info("Data inserted in brand_name");
                    }
                    statementR.closeOnCompletion();
                }

                try {
                    int Bid = 0;
                    String ast = "select id from model_name order by id desc   " + limit_var + "  ";
                    logger.info("Query at " + ast);
                    try {
                        ResultSet result6 = stmt.executeQuery(ast);
                        while (result6.next()) {
                            Bid = result6.getInt(1);
                        }
                        result6.close();
                    } catch (Exception e) {
                        logger.info("Warning " + e);
                    }

                    int Bidd = Bid + 1;
                    String aasr = "insert into model_name( model_name,brand_name , brand_name_id , id , created_on) VAlues( ' " + product.getModelName() + " ',   '   " + product.getBrandName() + " ' ," + ridd + ",  " + Bidd + " , '" + now + "'  )";
                    logger.info("Query  insert model_name.. " + aasr);
                    try {
                        PreparedStatement statementM = conn.prepareStatement(aasr);
                        int rowsInsertedM = statementM.executeUpdate();
                        if (rowsInsertedM > 0) {
                            logger.info("Data inserted in Model_name");
                        }
                        statementM.closeOnCompletion();
                    } catch (Exception e) {
                        logger.info("Data is already present in Model_name");
                    }

                } catch (Exception e) {
                    logger.info("Data exists in Model_name " + e);
                }

            } else {
                logger.info("Data will not Save  in Model_name  and Brand_name as IT shows NA ");

            }

        } catch (Exception e1) {
            logger.error("" + e1);
        } finally {

        }
        return "";
    }

    public Map<String, String> getExistingGsmaDetails(String imeiTac, Connection conn) {
        String resultid = "0";
        Map<String, String> map = new HashMap<String, String>();
        try {

            Statement stmt = conn.createStatement();
            String msidnid = " select id from gsma_tac_db where  device_id = " + imeiTac + "  ";
            ResultSet resultmsdn = stmt.executeQuery(msidnid);
            try {
                while (resultmsdn.next()) {
                    resultid = resultmsdn.getString(1);
                }
                resultmsdn.close();
            } catch (Exception e) {
                logger.info("Database ClassnotFound " + e);
            }
            map.put("resultid", resultid);
            logger.info("Found at resultid  " + resultid);
            String str = " select tag , value from system_configuration_db where tag in ('gsma_tac_APIKey' , 'gsma_tac_Password','gsma_tac_Salt_String' , 'gsma_tac_Organization_Id' ,'gsma_tac_Secretkey', 'gsma_tac_httpPostUrl',  'gsma_tac_timewait')  ";
            ResultSet result = stmt.executeQuery(str);
            logger.info("  " + str);
            while (result.next()) {
                map.put(result.getString("tag"), result.getString("value"));
            }

            result.close();
            stmt.close();

        } catch (Exception e) {
            logger.info("Error." + e);
        }

        return map;

    }

    void invalidGsmaDb(String deviceId, Connection conn) {

        try {
            boolean isOracle = conn.toString().contains("oracle");
            String dateFunction = Util.defaultDate(isOracle);
            PreparedStatement statementR = conn.prepareStatement("insert into gsma_invalid_tac_db (created_on , tac ) values ( " + dateFunction + "  , '" + deviceId + "'   )    ");
            logger.info("IMEI is not GsmaApprovedTac...  " + "insert into gsma_invalid_tac_db (created_on , tac ) values ( " + dateFunction + "  , '" + deviceId + "'   )    ");
            statementR.executeUpdate();
            statementR.closeOnCompletion();

        } catch (Exception e) {
            logger.info("Error + " + e);
        }

    }

}
