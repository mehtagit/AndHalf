package com.gl.ceir.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.constant.Alerts;
import com.gl.ceir.constant.ConfigTags;
import com.gl.ceir.constant.Datatype;
import com.gl.ceir.constant.ReferTable;
import com.gl.ceir.constant.SearchOperation;
import com.gl.ceir.entity.Grievance;
import com.gl.ceir.entity.SystemConfigurationDb;
import com.gl.ceir.entity.User;
import com.gl.ceir.pojo.RawMail;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.repo.GrievanceRepository;
import com.gl.ceir.repo.UserRepository;
import com.gl.ceir.service.BaseService;
import com.gl.ceir.specification.GenericSpecificationBuilder;
import com.gl.ceir.util.DateUtil;

@Component
public class CloseGrievance extends BaseService{

	private static final Logger logger = LogManager.getLogger(CloseGrievance.class);

	List<Grievance> processedGrievances = new ArrayList<>();
	List<RawMail> rawMails = new ArrayList<>();

	@Autowired
	GrievanceRepository grievanceRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public void fetch() {
		User user = null;
		
		try {
			SystemConfigurationDb defaultPerioToCloseGrievance = systemConfigurationDbRepository.getByTag(ConfigTags.DEFAULT_PERIOD_TO_CLOSE_GRIEVANCE);

			if(Objects.isNull(defaultPerioToCloseGrievance)) {
				alertServiceImpl.raiseAnAlert(Alerts.ALERT_004, 0);
				logger.info("Alert [ALERT_004] is raised. So, doing nothing.");
				return;
			}

			String date = DateUtil.nextDate( (Integer.parseInt(defaultPerioToCloseGrievance.getValue())) * -1);
			logger.info("Close grievance raised on date [" + date + "] because of inactivity.");

			List<Grievance> grievances = grievanceRepository.findAll(buildSpecification(date).build());

			Map<String, String> placeholderMap = new HashMap<>();
			placeholderMap.put("<days>", defaultPerioToCloseGrievance.getValue());
			
			for(Grievance grievance : grievances) {
				grievance.setGrievanceStatus(4); // Closed by System.
				processedGrievances.add(grievance);

				// Save in notification.
				user = userRepository.getById(grievance.getUserId());
				if(Objects.nonNull(user)) {
					placeholderMap.put("<User>", user.getUserProfile().getFirstName());
					placeholderMap.put("<txn_id>", grievance.getTxnId());

					rawMails.add(new RawMail("Email", 
							"MAIL_TO_USER_ON_GRIEVANCE_CLOSURE", 
							grievance.getUserId(), 
							6L, // Feature Id 
							"Process",
							"Closure",
							grievance.getTxnId(),
							"Closure Of Grievance With Transaction Number " + grievance.getTxnId(), // Subject 
							placeholderMap, 
							ReferTable.USERS, 
							grievance.getUserType(),
							grievance.getUserType()));
				}else {
					logger.info("ALERT : No user is found for Grievance [" + grievance.getTxnId() + "]");
					
					Map<String, String> bodyPlaceHolderMap = new HashMap<>();
					bodyPlaceHolderMap.put("<id>", Long.toString(grievance.getUserId()));
					
					alertServiceImpl.raiseAnAlert(Alerts.ALERT_005, 0, bodyPlaceHolderMap);
				}
			}

			if(processedGrievances.isEmpty()) {
				logger.info("No grievance to close today. ["+ DateUtil.nextDate(0) +"]");
			}else {
				process(processedGrievances);
			}

		}catch (NumberFormatException e) {
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_004, 0);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public void process(Object o) {
		@SuppressWarnings("unchecked")
		List<Grievance> grievances = (List<Grievance>) o;

		grievanceRepository.saveAll(grievances);
		// TODO update in history

		notifierWrapper.saveNotification(rawMails);

	}

	private GenericSpecificationBuilder<Grievance> buildSpecification(String date){
		GenericSpecificationBuilder<Grievance> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		// Grievance status - Pending With User
		cmsb.with(new SearchCriteria("grievanceStatus", 2, SearchOperation.EQUALITY, Datatype.STRING));
		cmsb.with(new SearchCriteria("modifiedOn", date, SearchOperation.GREATER_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("modifiedOn", date, SearchOperation.LESS_THAN, Datatype.DATE));

		return cmsb;
	}	
}
