package com.gl.ceirreportbuilder.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.gl.ceirreportbuilder.configuration.PropertiesReader;
import com.gl.ceirreportbuilder.model.ReportStatusDb;
import com.gl.ceirreportbuilder.model.Scheduler;
import com.gl.ceirreportbuilder.model.SystemConfigListDb;
import com.gl.ceirreportbuilder.repository.ReportStatusDbRepository;
import com.gl.ceirreportbuilder.repository.SchedulerRepository;
import com.gl.ceirreportbuilder.repository.SystemConfigListRepository;
import com.gl.ceirreportbuilder.thread.ThreadCreator;

@Component
public class ReportMainController {
	
	private final Logger logger = LogManager.getLogger(ReportMainController.class);

	@Autowired
	SchedulerRepository schedulerRepository;
	@Autowired
	SystemConfigListRepository systemConfigListRepository;
	@Autowired
	ReportStatusDbRepository reportStatusDbRepository;
	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	PropertiesReader propertiesReader;
	
	public void createScheduleReports( ApplicationContext context ) {
		List<Integer> reportStatus = new ArrayList<Integer>();
		List<SystemConfigListDb> scheduleFlags = null;
		List<ReportStatusDb> reportList = null;
		List<SystemConfigListDb> temp = null;
		LocalDateTime startTime = null;
		ThreadCreator thread = null;
		Scheduler scheduler = null;
		Integer enabled  = 1;
		Integer typeFlag = 1;
		int threadCount = 0;
		long sleepTime = propertiesReader.processSleepTime;
		int inprocess = 0;
		try {
			logger.info("Report Builder type ["+propertiesReader.schedulerName+"] and re-try count:["+propertiesReader.retryCount+"]");
//			Scheduler scheduler = schedulerRepository.findBySchedulerName(propertiesReader.schedulerName+"_report_builder");
			scheduleFlags = systemConfigListRepository.findByTag("Schedule_Flag", Sort.by( "id"));
			logger.info("Schedule_flag result length:["+scheduleFlags.size()+"]");
			for( SystemConfigListDb status : scheduleFlags ) {
				logger.info("Schedule_Flag is ["+status.getInterp()+"]");
				if( status.getInterp().equalsIgnoreCase("New") || status.getInterp().equalsIgnoreCase("Schedule") ) {
					reportStatus.add( status.getValue());
				}else if( status.getInterp().equalsIgnoreCase("inprocess")) {
					inprocess = status.getValue();
				}
			}
			temp = systemConfigListRepository.findByTag("Report_Status", Sort.by( "id"));
			logger.info("Report_Status result length:["+temp.size()+"]");
			for( SystemConfigListDb status : temp ) {
				if( status.getInterp().equalsIgnoreCase("Enabled") ) {
					enabled = status.getValue();
				}
			}
			temp = systemConfigListRepository.findByTag("Type_Flag", Sort.by( "id"));
			logger.info("Type_Flag result length:["+temp.size()+"]");
			for( SystemConfigListDb status : temp ) {
				if( status.getInterp().equalsIgnoreCase(propertiesReader.schedulerName) ) {
					typeFlag = status.getValue();
				}
			}
			logger.info("Allowed report stanatus in ["+reportStatus.toString()+"]");
			while( true ) {
				scheduler = schedulerRepository.findBySchedulerName(propertiesReader.schedulerName+"_report_builder");
				if( scheduler != null && (scheduler.getMaxAllowedThread() > scheduler.getMaxRunningThread()) ) {
					threadCount = 0;
					startTime = LocalDateTime.now();
					reportList = reportStatusDbRepository.findScheduleReports(reportStatus, enabled, typeFlag, propertiesReader.retryCount );
					logger.info("Number of reports to schedule is ["+reportList.size()+"]");
					for( ReportStatusDb statusDetails : reportList ){
						logger.info("Going to create thread for reportnameId:["+statusDetails.getReportnameId()+"]");
						statusDetails.setScheduleFlag(inprocess);
						statusDetails = reportStatusDbRepository.save(statusDetails);
						thread = (ThreadCreator)context.getBean("threadCreator");
						thread.setSchedulerName( propertiesReader.schedulerName+"_report_builder" );
						thread.setReportStatusDb( statusDetails );
						thread.setScheduleFlags(scheduleFlags);
						thread.setTypeFlag(typeFlag);
						scheduler.setMaxRunningThread( scheduler.getMaxRunningThread() +1);
						scheduler = schedulerRepository.save( scheduler );
						logger.info("After increment scheduler status:["+scheduler.toString()+"]");
						taskExecutor.execute(thread);
						threadCount++;
						if( scheduler.getMaxAllowedThread() <= scheduler.getMaxRunningThread() )
							break;
					}
					sleepTime = propertiesReader.processSleepTime - Duration.between( startTime, LocalDateTime.now()).getSeconds();
					logger.info("Configured sleep time is ["+propertiesReader.processSleepTime+"] and thread is going to sleep for ["+sleepTime+"]");
				}else {
					logger.info("No scheduler information found for ["+propertiesReader.schedulerName+"_report_builder]");
				}
				if( sleepTime > 0 )
					Thread.sleep( sleepTime*1000 );
				logger.info("Going to destroy task list.");
			}
//			taskExecutor.shutdown();
		}catch( Exception ex) {
			logger.error( ex.getMessage(), ex);
		}finally {
			if( Objects.nonNull(taskExecutor))
				taskExecutor.destroy();
		}
	}
	
}
