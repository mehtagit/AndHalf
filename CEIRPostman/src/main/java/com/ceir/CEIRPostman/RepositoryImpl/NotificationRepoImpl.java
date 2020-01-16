package com.ceir.CEIRPostman.RepositoryImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CEIRPostman.Repository.NotificationRepository;
import com.ceir.CEIRPostman.model.Notification;

@Service
public class NotificationRepoImpl {

	@Autowired
	NotificationRepository notificationRepository;
	
	public List<Notification> notitificationByStatus(int status) {
		try {
			List<Notification> notification=notificationRepository.findByStatus(status);
		    return notification;
		}
		catch(Exception e) {
            return null;
		}
	}
}
