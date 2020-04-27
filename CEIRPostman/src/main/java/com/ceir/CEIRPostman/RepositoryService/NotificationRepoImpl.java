package com.ceir.CEIRPostman.RepositoryService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CEIRPostman.Repository.NotificationRepository;
import com.ceir.CEIRPostman.model.Notification;

@Service
public class NotificationRepoImpl {

	@Autowired
	NotificationRepository notificationRepository;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	public List<Notification> notitificationByStatus(int status,String channelType) {
		try {
			List<Notification> notification=notificationRepository.findByStatusAndChannelType(status,channelType);
		    return notification;
		}
		catch(Exception e) {
			log.info(e.toString());
            return new ArrayList<Notification>();
		}
	}
}
