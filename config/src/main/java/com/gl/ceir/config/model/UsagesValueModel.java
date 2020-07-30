package com.gl.ceir.config.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "device_usage_db")
@Entity
public class UsagesValueModel implements Serializable {

     @Id
     private String imei;
     private String msisdn;
     private String imsi;

     private int FOREGIN_RULE;
     private String MOBILE_OPERATOR;
     private String PERIOD;
     private String CREATE_FILENAME;
     private String UPDATE_FILENAME;
     private String SYSTEM_TYPE;
     private String FAILED_RULE_ID;
     private String FAILED_RULE_NAME;
     private String TAC;
     private String ACTION;
     private String FAILED_RULE_DATE;
     private String FEATURE_NAME;
     private String RECORD_TIME;
     private String RECORD_TYPE;

     public int getFOREGIN_RULE() {
          return FOREGIN_RULE;
     }

     public void setFOREGIN_RULE(int FOREGIN_RULE) {
          this.FOREGIN_RULE = FOREGIN_RULE;
     }

     public String getMOBILE_OPERATOR() {
          return MOBILE_OPERATOR;
     }

     public void setMOBILE_OPERATOR(String MOBILE_OPERATOR) {
          this.MOBILE_OPERATOR = MOBILE_OPERATOR;
     }

     public String getPERIOD() {
          return PERIOD;
     }

     public void setPERIOD(String PERIOD) {
          this.PERIOD = PERIOD;
     }

     public String getCREATE_FILENAME() {
          return CREATE_FILENAME;
     }

     public void setCREATE_FILENAME(String CREATE_FILENAME) {
          this.CREATE_FILENAME = CREATE_FILENAME;
     }

     public String getUPDATE_FILENAME() {
          return UPDATE_FILENAME;
     }

     public void setUPDATE_FILENAME(String UPDATE_FILENAME) {
          this.UPDATE_FILENAME = UPDATE_FILENAME;
     }

     public String getSYSTEM_TYPE() {
          return SYSTEM_TYPE;
     }

     public void setSYSTEM_TYPE(String SYSTEM_TYPE) {
          this.SYSTEM_TYPE = SYSTEM_TYPE;
     }

     public String getFAILED_RULE_ID() {
          return FAILED_RULE_ID;
     }

     public void setFAILED_RULE_ID(String FAILED_RULE_ID) {
          this.FAILED_RULE_ID = FAILED_RULE_ID;
     }

     public String getFAILED_RULE_NAME() {
          return FAILED_RULE_NAME;
     }

     public void setFAILED_RULE_NAME(String FAILED_RULE_NAME) {
          this.FAILED_RULE_NAME = FAILED_RULE_NAME;
     }

     public String getTAC() {
          return TAC;
     }

     public void setTAC(String TAC) {
          this.TAC = TAC;
     }

     public String getACTION() {
          return ACTION;
     }

     public void setACTION(String ACTION) {
          this.ACTION = ACTION;
     }

     public String getFAILED_RULE_DATE() {
          return FAILED_RULE_DATE;
     }

     public void setFAILED_RULE_DATE(String FAILED_RULE_DATE) {
          this.FAILED_RULE_DATE = FAILED_RULE_DATE;
     }

     public String getFEATURE_NAME() {
          return FEATURE_NAME;
     }

     public void setFEATURE_NAME(String FEATURE_NAME) {
          this.FEATURE_NAME = FEATURE_NAME;
     }

     public String getRECORD_TIME() {
          return RECORD_TIME;
     }

     public void setRECORD_TIME(String RECORD_TIME) {
          this.RECORD_TIME = RECORD_TIME;
     }

     public String getRECORD_TYPE() {
          return RECORD_TYPE;
     }

     public void setRECORD_TYPE(String RECORD_TYPE) {
          this.RECORD_TYPE = RECORD_TYPE;
     }

     public String getImei() {
          return imei;
     }

     public void setImei(String imei) {
          this.imei = imei;
     }

     public String getMsisdn() {
          return msisdn;
     }

     public void setMsisdn(String msisdn) {
          this.msisdn = msisdn;
     }

     public String getImsi() {
          return imsi;
     }

     public void setImsi(String imsi) {
          this.imsi = imsi;
     }

     public static long getSerialversionuid() {
          return serialVersionUID;
     }
     private static final long serialVersionUID = 1L;

     @Override
     public String toString() {
          StringBuilder sb = new StringBuilder();
          sb.append("UsagesValueModel{imei=").append(imei);
          sb.append(", msisdn=").append(msisdn);
          sb.append(", imsi=").append(imsi);
          sb.append(", FOREGIN_RULE=").append(FOREGIN_RULE);
          sb.append(", MOBILE_OPERATOR=").append(MOBILE_OPERATOR);
          sb.append(", PERIOD=").append(PERIOD);
          sb.append(", CREATE_FILENAME=").append(CREATE_FILENAME);
          sb.append(", UPDATE_FILENAME=").append(UPDATE_FILENAME);
          sb.append(", SYSTEM_TYPE=").append(SYSTEM_TYPE);
          sb.append(", FAILED_RULE_ID=").append(FAILED_RULE_ID);
          sb.append(", FAILED_RULE_NAME=").append(FAILED_RULE_NAME);
          sb.append(", TAC=").append(TAC);
          sb.append(", ACTION=").append(ACTION);
          sb.append(", FAILED_RULE_DATE=").append(FAILED_RULE_DATE);
          sb.append(", FEATURE_NAME=").append(FEATURE_NAME);
          sb.append(", RECORD_TIME=").append(RECORD_TIME);
          sb.append(", RECORD_TYPE=").append(RECORD_TYPE);
          sb.append('}');
          return sb.toString();
     }

    
     
     

}
