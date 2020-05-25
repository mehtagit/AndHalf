package com.functionapps.parser;

import java.util.ArrayList;

public class RuleTesting {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String a = "09";
        if (a.toUpperCase().matches("^[0-9]+$")) {
            System.out.println("yes");
        } else {
            System.out.println("No");
        }

    }

}
