/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.FileScpProcess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 *
 * @author maverick
 */
public class CleanUpDb {

     static Logger logger = Logger.getLogger(CleanUpDb.class);
     static StackTraceElement l = new Exception().getStackTrace()[0];
     public static PropertyReader propertyReader;

     public static void main(String[] args) {
          propertyReader = new PropertyReader();
          Connection conn = null;
          conn = (Connection) new com.gl.FileScpProcess.MySQLConnection().getConnection();
          String query = null;
          ResultSet rs2 = null;
          Statement stmt2 = null;

          try {
               query = " select * from cdr_process_status where  process_name = 'scriptV2'     order by id desc fetch next 1 rows only  ";
               logger.info("   Query  " + query);
               stmt2 = conn.createStatement();
               rs2 = stmt2.executeQuery(query);
               String CREATED_ON = "";
               String OPERATOR = "";
               String status = "";
               while (rs2.next()) {
                    CREATED_ON = rs2.getString("CREATED_ON");
                    OPERATOR = rs2.getString("OPERATOR");
                    status = rs2.getString("status");
               }

               if (status.equalsIgnoreCase("Start")) {
                    logger.info(CREATED_ON + " **  " + OPERATOR + " ** " + status);
                    query = " delete from cdr_pre_processing_report  where created_on > '" + CREATED_ON + "'";
                    logger.info(" cdr_pre_processing_report   delete  " + query);
                    stmt2.executeQuery(query);

                    query = " delete from cdr_file_details_db  where created_on > '" + CREATED_ON + "'";
                    logger.info(" cdr_file_details_db   delete  " + query);
                    stmt2.executeQuery(query);

               }

          } catch (Exception e) {
               logger.error("" + e);
          } finally {
               try {
                    rs2.close();
                    stmt2.close();
               } catch (Exception e) {
                    logger.error("" + l.getClassName() + "/" + l.getMethodName() + ":" + l.getLineNumber() + e);
               }
          }

     }

}
