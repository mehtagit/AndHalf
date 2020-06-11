/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine.BlackList;

import com.gl.utils.Util;
import com.google.gson.Gson;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class BlacklistServiceImpl {

    static final Logger logger = Logger.getLogger(BlacklistServiceImpl.class);

    public String databaseMapper(String message ,Connection conn ) {
         
        String rslt = null;
        try {
            Gson gson = new Gson();
            BlacklistTacDb blacklistTacDb = (gson.fromJson(message, BlacklistTacDb.class));
            BlacklistTacDeviceDetailsDb blacklistTacDeviceDetailsDb = blacklistTacDb.getDeviceDetails();
            rslt = blacklistTacDb.getBlacklistStatus().toString();
            int id = insertBlacklistTacDbAndGetId(conn, blacklistTacDb);
            insertBlckListTacDeviceDetail(conn, blacklistTacDeviceDetailsDb, id);
            List<BlacklistTacDeviceHistoryDb> blacklistTacDeviceHistoryDb = blacklistTacDb.getDeviceHistory();
            insertBlacklistTacDeviceHistory(conn, id, blacklistTacDeviceHistoryDb);
        } catch (Exception ex) {
            logger.debug(" .." + ex);
        } finally {
            try {
               
            } catch (Exception ex) {
                logger.debug(" .." + ex);
            }

        }
        return rslt;
    }

    private int insertBlacklistTacDbAndGetId(Connection conn, BlacklistTacDb blacklistTacDb) {
        int iid = 0;
        String limiter = " limit 1 ";
        if (conn.toString().contains("oracle")) {
            limiter = " fetch next 1 rows only  ";
        }
        boolean isOracle = conn.toString().contains("oracle");
        String dateFunction = Util.defaultDate(isOracle);
        Statement stmt = null;
        String query = " insert into blacklist_imei_db (blacklist_status , ref_code , deviceid, created_on)"
                + "values ('" + blacklistTacDb.getBlacklistStatus() + "', '" + blacklistTacDb.getRefCode() + "' , '" + blacklistTacDb.getDeviceid() + "' ," + dateFunction + " )";
        logger.debug("query .." + query);
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            logger.debug("Inserted in blck imei Db");
            ResultSet resultmsdn = stmt.executeQuery("select id from blacklist_imei_db order by id desc  " + limiter + "  ");
            try {
                while (resultmsdn.next()) {
                    iid = resultmsdn.getInt(1);
                }
            } catch (Exception e) {
                logger.debug(" inside Erorr" + e);
            }
            resultmsdn.close();
            stmt.closeOnCompletion();
        } catch (Exception e) {
            logger.debug(" ERrorr" + e);
        }
        return iid;

    }

    private void insertBlckListTacDeviceDetail(Connection conn, BlacklistTacDeviceDetailsDb btdd, int id) {
        Statement stmt = null;
        boolean isOracle = conn.toString().contains("oracle");
        String dateFunction = Util.defaultDate(isOracle);
        String query = " insert into  blacklist_imei_device_details_db ( bluetooth,brand_name, device_type, manufacturer,marketing_name, model_name , nfc ,operating_system , wlan , blacklist_tac_db_id ,created_on) "
                + " values( '" + btdd.getBluetooth() + "','" + btdd.getBrandName() + "', '" + btdd.getDeviceType() + "', '" + btdd.getManufacturer() + "','" + btdd.getMarketingName() + "','" + btdd.getModelName() + "','" + btdd.getNFC() + "',  '" + btdd.getOperatingSystem() + "', '" + btdd.getWLAN() + "', '" + id + "' ," + dateFunction + "     ) ";
        logger.debug("uqry " + query);
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            logger.debug("Inserted in blck Tac Details Db");
            
            stmt.closeOnCompletion();
        } catch (Exception e) {
            logger.debug(" ERrorr" + e);
        }

    }

    private void insertBlacklistTacDeviceHistory(Connection conn, int id, List<BlacklistTacDeviceHistoryDb> blacklistTacDeviceHistoryDb) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            logger.debug("Error " + ex);
        }
        String query = null;
        boolean isOracle = conn.toString().contains("oracle");
        String dateFunction = Util.defaultDate(isOracle);
        for (BlacklistTacDeviceHistoryDb btr : blacklistTacDeviceHistoryDb) {
            logger.debug("..." + btr.getAction() + "..." + btr.getCountry() + "..." + btr.getDateReported());
            query = " insert  into blacklist_imei_device_history_db ( action,country, date_reported,organisation,organisation_type,reason ,blacklist_tac_db_id , created_on) "
                    + "values( '" + btr.getAction() + "', '" + btr.getCountry() + "',  '" + btr.getDateReported() + "',  '" + btr.getOrganisation() + "',  '" + btr.getOrganisationType() + "', '" + btr.getReason() + "', '" + id + "'  , " + dateFunction + "  )";
            try {
                stmt.executeUpdate(query);
//stmt.closeOnCompletion();
            } 
            catch (Exception e) {
                logger.debug(" ERror" + e);
            }
        }

        logger.debug("Inserted in blck Tac history Db");
        try {
            stmt.close();
        } catch (SQLException ex) {
            logger.debug(" ERrorr" + ex);
        }
    }

    void insertInvalidTac(Connection conn, BlacklistTacDb blacklistTacDb) {
 boolean isOracle = conn.toString().contains("oracle");
        String dateFunction = Util.defaultDate(isOracle);
        Statement stmt = null;
        String query = " insert into blacklist_imei_invalid_db ( tac ,created_on )" // gsma_blacklist_tac_invalid_db earlier
                + "values ( '" + blacklistTacDb.getDeviceid() + "' , "+ dateFunction+"  )";
        logger.debug("query .." + query);
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            logger.debug("Inserted in blck Tac Db");
        } catch (Exception e) {
            logger.debug("Error " + e);
        }

    }

    String getBlacklistStatus(Connection conn,String Imei) {
        String stats = "NA";
        Statement stmt = null;
        ResultSet rs0 = null;
        ResultSet rs1 = null;
        String query = null;
        String limiter = " limit 1 ";
        if (conn.toString().contains("oracle")) {
            limiter = " fetch next 1 rows only ";
        }

        try {
            query = "select count(deviceid) as counted from blacklist_imei_db  where deviceid = '" + Imei + "' ";
            logger.debug("Query to get getBlacklistCount [" + query + "]");
            stmt = conn.createStatement();
            rs0 = stmt.executeQuery(query);
            int cnnt = 0;
            while (rs0.next()) {
                cnnt = rs0.getInt("counted");
            }
            if (cnnt == 0) {
                stats = "NA";
            } else {
                query = " select blacklist_status  from  blacklist_imei_db  where deviceid = '" + Imei + "'  " + limiter + "  ";
                logger.debug("Query to get getBlacklist Status [" + query + "]");
                rs1 = stmt.executeQuery(query);
                while (rs1.next()) {
                    stats = rs1.getString("blacklist_status");
                }
            }
            rs0.close();
            rs1.close();
            stmt.close();
            logger.debug("  BlacklistSTatuss " + stats);
        } catch (Exception e) {
            logger.debug("Error at   getBlack listStatus" + e);
        }

        return stats;
    }

}
