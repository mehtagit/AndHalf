/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.ceir.config.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author maverick
 */
@ApiModel
@Entity
public class ReportDb implements Serializable {     //extends BaseEntity

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reportnameId;

    String reportName;
    
    
    
    @Column(nullable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdOn;

    @Column(nullable = false)
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date modifiedOn;

    
    @OneToOne(mappedBy = "reportDb", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	ScheduleReportDb scheduleReportDb;
    
    public int getReportnameId() {
        return reportnameId;
    }

    public void setReportnameId(int reportnameId) {
        this.reportnameId = reportnameId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

//    

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public ScheduleReportDb getScheduleReportDb() {
        return scheduleReportDb;
    }

    public void setScheduleReportDb(ScheduleReportDb scheduleReportDb) {
        this.scheduleReportDb = scheduleReportDb;
    }

    @Override
    public String toString() {
        return "ReportDb{" + "reportnameId=" + reportnameId + ", reportName=" + reportName + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", scheduleReportDb=" + scheduleReportDb + '}';
    }
    
    
    
}
