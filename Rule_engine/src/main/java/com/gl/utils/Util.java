package com.gl.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String defaultDate(boolean isOracle) {
        if (isOracle) {
            return "sysdate";
        } else {
            return "now()";
        }
    }

//         String dateNow = "";
//                    if (conn.toString().contains("oracle")) {
//                        dateNow = new SimpleDateFormat("dd-MMM-YY").format(new Date());
//                    } else {
//                        dateNow = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
//                    }
  

}
