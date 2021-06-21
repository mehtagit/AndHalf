package com.gl.ceirreportbuilder.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Scheduler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String schedulerName;
	
	private int maxAllowedThread;
	
	private int maxRunningThread;
	
	@CreationTimestamp
	private Date createdOn;
	
	@UpdateTimestamp
	private Date modifiedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSchedulerName() {
		return schedulerName;
	}

	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}

	public int getMaxRunningThread() {
		return maxRunningThread;
	}

	public void setMaxRunningThread(int maxRunningThread) {
		this.maxRunningThread = maxRunningThread;
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

	public int getMaxAllowedThread() {
		return maxAllowedThread;
	}

	public void setMaxAllowedThread(int maxAllowedThread) {
		this.maxAllowedThread = maxAllowedThread;
	}

	@Override
	public String toString() {
		return "Scheduler [id=" + id + ", schedulerName=" + schedulerName + ", maxAllowedThread=" + maxAllowedThread
				+ ", maxRunningThread=" + maxRunningThread + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ "]";
	}
	
}
