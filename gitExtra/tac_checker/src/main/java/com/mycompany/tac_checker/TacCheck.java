/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tac_checker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class TacCheck {

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {

        String imei_tac = args[0];
        TacCheck stt = new TacCheck();
       String val =  stt.tacApplication(imei_tac);
        System.out.println( val);
    }

    public String tacApplication(String imei_tac) throws SQLException {

        String url = System.getenv("ceir_db_url");
        String username = System.getenv("ceir_db_username");
        String password = System.getenv("ceir_db_password");
        String db_name = System.getenv("ceir_db_dbName");
        Connection conn = null;
        String res = "";
        try {
            String jdbcUrl = "jdbc:oracle:thin:@" + url + "/" + db_name;
            String className = "oracle.jdbc.driver.OracleDriver";
            Class.forName(className);
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt2 = conn.createStatement();
            ResultSet result1 = stmt2.executeQuery("select count(id)as count from gsma_tac_db  where device_id =   '" + imei_tac + "'  group by id ");
            int count = 0;

            try {
                while (result1.next()) {
                    count = result1.getInt(1);
                }
            } catch (Exception e) {
                System.out.println("");
            }
            if (count == 0) {
                res = "Not Present";
            } else {
                res = "Present";
            }
        } catch (Exception e) {
        } finally {
            conn.close();
        }
        return res;
    }

}
