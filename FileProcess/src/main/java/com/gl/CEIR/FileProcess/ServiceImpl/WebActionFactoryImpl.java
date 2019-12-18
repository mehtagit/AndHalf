package com.gl.CEIR.FileProcess.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.service.WebActionFactory;
import com.gl.CEIR.FileProcess.service.WebActionService;
import com.gl.ceir.config.model.WebActionDb;

@Component("WebActionFactoryImpl")
public class WebActionFactoryImpl implements WebActionFactory {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired                                                        
	ConsignmentRegisterServiceImpl consignmentRegisterServiceImpl;

	@Autowired
	ConsignmentUpdateServiceImpl consignmentUpdateServiceImpl;

	@Autowired
	ConsignmentDeleteServiceImpl consignmentDeleteServiceImpl;

	@Autowired
	StockDeleteServiceImpl stockDeleteServiceImpl;

	@Autowired
	StockUpdateServiceImpl stockUpdateServiceImpl;

	@Autowired
	StockRegisterServiceImpl stockUploadServiceImpl;
	
	@Override
	public WebActionService create(WebActionDb webActionDb) {
		
		if("Consignment".equalsIgnoreCase(webActionDb.getFeature())) {
			if("Register".equalsIgnoreCase(webActionDb.getSubFeature())) {
				return consignmentRegisterServiceImpl;

			}else if("Update".equalsIgnoreCase(webActionDb.getSubFeature())) {
				return consignmentUpdateServiceImpl;
			
			}else if("Delete".equalsIgnoreCase(webActionDb.getSubFeature())) {
				return consignmentDeleteServiceImpl;
			
			}
		}else if("Stock".equalsIgnoreCase(webActionDb.getFeature())) {
			if("Upload".equalsIgnoreCase(webActionDb.getSubFeature())) {
				return stockUploadServiceImpl;
			
			}else if("Update".equalsIgnoreCase(webActionDb.getSubFeature())) {
				return stockUpdateServiceImpl;
			
			}else if("Delete".equalsIgnoreCase(webActionDb.getSubFeature())) {
				return stockDeleteServiceImpl;
			}
		}
		
		log.info("WebAction service should not be null.");
		return null;
	}
}