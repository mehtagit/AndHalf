package com.gl.ceirreportbuilder.thread;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gl.ceirreportbuilder.model.ReportStatusDb;
import com.gl.ceirreportbuilder.model.Scheduler;
import com.gl.ceirreportbuilder.model.SystemConfigListDb;
import com.gl.ceirreportbuilder.repository.ReportStatusDbRepository;
import com.gl.ceirreportbuilder.repository.SchedulerRepository;
import com.gl.ceirreportbuilder.service.impl.ReportBuilderServiceImpl;

@Component
@Scope("prototype")
public class ThreadCreator implements Runnable{
	private static final Logger logger = LogManager.getLogger(ThreadCreator.class);
	@Autowired
	ReportBuilderServiceImpl reportBuilderServiceImpl;
	@Autowired
	SchedulerRepository schedulerRepository;
	@Autowired
	ReportStatusDbRepository reportStatusDbRepository;
	
	ReportStatusDb reportStatusDb;
	
	List<SystemConfigListDb> scheduleFlags;

	String schedulerName;
	
	Integer typeFlag;
	
	public void setScheduleFlags(List<SystemConfigListDb> scheduleFlags) {
		this.scheduleFlags = scheduleFlags;
	}

	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}

	public void setReportStatusDb(ReportStatusDb reportStatusDb) {
		this.reportStatusDb = reportStatusDb;
	}

	public void setTypeFlag(Integer typeFlag) {
		this.typeFlag = typeFlag;
	}

	@Override
	public void run() {
		int schedule = 0;
		int inprocess = 0;
		List<ReportStatusDb> reportList = null;
		Scheduler scheduler = schedulerRepository.findBySchedulerName(schedulerName);
		logger.info("Before Process Report["+reportStatusDb.getReportnameId()+"]"
				+ " [Max allowed report threads:"+scheduler.getMaxAllowedThread()+"],[Running report threads:"+scheduler.getMaxRunningThread()+"]");
		for( SystemConfigListDb statusDetais : scheduleFlags ) {
			if( statusDetais.getInterp().equalsIgnoreCase("inprocess")) {
				inprocess = statusDetais.getValue();
			}else if( statusDetais.getInterp().equalsIgnoreCase("schedule") ) {
				schedule = statusDetais.getValue();
			}
		}
		if( reportBuilderServiceImpl.processReport( reportStatusDb )) {
			reportStatusDb.setRundate( reportStatusDb.getRundate().plusDays(1) );//Add last date + 1
			reportStatusDb.setScheduleFlag(schedule);
			reportStatusDb.setTryCount( 0 );
		}else {
			reportStatusDb.setScheduleFlag(schedule);
			reportStatusDb.setTryCount( reportStatusDb.getTryCount() + 1 );
		}
		scheduler = schedulerRepository.findBySchedulerName(schedulerName);
		reportList = reportStatusDbRepository.findByScheduleFlagAndTypeFlag(inprocess, typeFlag);
		if( reportList.size() > 0 ) {
			scheduler.setMaxRunningThread( reportList.size() - 1);
		}
		reportStatusDb = reportStatusDbRepository.save(reportStatusDb);
		scheduler = schedulerRepository.save( scheduler );
		logger.info("After Process Report["+reportStatusDb.getReportnameId()+"]"
				+ " [Max allowed report threads:"+scheduler.getMaxAllowedThread()+"],[Running report threads:"+scheduler.getMaxRunningThread()+"]");
	}

}
