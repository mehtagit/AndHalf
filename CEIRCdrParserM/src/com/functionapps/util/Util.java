package com.functionapps.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String defaultDate(boolean isOracle) {
        if (isOracle) {
            return "sysdate";
        } else {
            return "now()";
        }
    }

//    public static void main(String[] args) {
    public static long timeDiff(String created_on, String modified_on) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy HH.mm.ss");
        Date d1 = null;
        Date d2 = null;
        long diff = 0L;
        try {
            d1 = format.parse(created_on);
            d2 = format.parse(modified_on);
            diff = d2.getTime() - d1.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("diff// " + diff);
        return diff;
    }

//    public static void main(String[] args) {
//
//        String aa = "13244dfa^&sfasdf23113";
//        if (aa.matches("^[-\\w.]+")) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
//
//    }
}
