package com.ceir.SLAModule.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceir.SLAModule.App;
import com.ceir.SLAModule.model.constants.ConsignmentStatus;
import com.ceir.SLAModule.model.constants.GrievanceStatus;
import com.ceir.SLAModule.model.constants.StockStatus;

@Service
public class SLAService implements Runnable{

	@Autowired
	ConsignmentService consignmentService;
	
	@Autowired
	GrievanceService grievanceService;
	
	@Autowired
	StockService stockService;
	
	
	private final static Logger log =LoggerFactory.getLogger(App.class);
	
	@Override
	public void run() 
	{
		while(true) {
			consignmentService.consignmentProcess(ConsignmentStatus.PENDING_APPROVAL_FROM_CEIR_AUTHORITY.getCode());
			grievanceService.grievanceProcess(GrievanceStatus.PENDING_WITH_ADMIN.getCode());
			stockService.stockProcess(StockStatus.SUCCESS.getCode());			
			try {
				Thread.sleep(3600000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
