package com.StolenApp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StolenAppApplication {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
            try {
                SpringApplication.run(StolenAppApplication.class, args);
                StolenRecoveryStart clss = new  StolenRecoveryStart();
                clss.stolenVsRecover(args[0]);
            } catch (SQLException ex) {
                Logger.getLogger(StolenAppApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	}

}
