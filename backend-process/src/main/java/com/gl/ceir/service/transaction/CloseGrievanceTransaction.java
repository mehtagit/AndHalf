package com.gl.ceir.service.transaction;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.entity.Grievance;
import com.gl.ceir.notifier.NotifierWrapper;
import com.gl.ceir.pojo.RawMail;
import com.gl.ceir.repo.GrievanceRepository;

@Component
@Transactional(rollbackOn = Exception.class)
public class CloseGrievanceTransaction {
	
	@Autowired
	GrievanceRepository grievanceRepository;
	
	@Autowired
	NotifierWrapper notifierWrapper;
	
	public void performTransaction(List<Grievance> grievances, List<RawMail> rawMails) {

		grievanceRepository.saveAll(grievances);
		// TODO update in history

		notifierWrapper.saveNotification(rawMails);
	}

}
