package com.gl.CEIR.FileProcess.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gl.CEIR.FileProcess.ServiceImpl.ConsignmentDeleteServiceImpl;
import com.gl.CEIR.FileProcess.ServiceImpl.ConsignmentRegisterServiceImpl;
import com.gl.CEIR.FileProcess.ServiceImpl.ConsignmentUpdateServiceImpl;
import com.gl.CEIR.FileProcess.ServiceImpl.FileActionServiceImpl;
import com.gl.CEIR.FileProcess.ServiceImpl.StockDeleteServiceImpl;
import com.gl.CEIR.FileProcess.ServiceImpl.StockUpdateServiceImpl;
import com.gl.CEIR.FileProcess.ServiceImpl.StockUploadServiceImpl;
import com.gl.ceir.config.model.WebActionDb;


@Service
public class FileActionControlling  implements Runnable{

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	FileActionServiceImpl FileActionServiceImpl;

	@Autowired
	ConsignmentDeleteServiceImpl consignmentDeleteServiceImpl;

	@Autowired                                                        
	ConsignmentRegisterServiceImpl consignmentRegisterServiceImpl;

	@Autowired
	ConsignmentUpdateServiceImpl consignmentUpdateServiceImpl;

	@Autowired
	StockDeleteServiceImpl stockDeleteServiceImpl;

	@Autowired
	StockUpdateServiceImpl stockUpdateServiceImpl;

	@Autowired
	StockUploadServiceImpl stockUploadServiceImpl;



	@Override
	public void run() {

		while(true) {
			try {

				WebActionDb webActionDb	=	FileActionServiceImpl.getFileActionDetails();

				log.info("Web action Details Fetch ="+webActionDb);

				if(webActionDb != null) {

					if("Consignment".equalsIgnoreCase(webActionDb.getFeature())) {

						if("Register".equalsIgnoreCase(webActionDb.getSubFeature())) {
							consignmentRegisterServiceImpl.saveprocess(webActionDb);

						}else if("Update".equalsIgnoreCase(webActionDb.getSubFeature())) {
							consignmentUpdateServiceImpl.updateProcess(webActionDb);

						}else if("Delete".equalsIgnoreCase(webActionDb.getSubFeature())) {

							consignmentDeleteServiceImpl.deleteProcess(webActionDb);
						}

					}else if("Stock".equalsIgnoreCase(webActionDb.getFeature())) {

						if("Upload".equalsIgnoreCase(webActionDb.getSubFeature())) {
							// stockUploadServiceImpl.saveStockProcess(webActionDb);


						}else if("Update".equalsIgnoreCase(webActionDb.getSubFeature())) {

							stockUpdateServiceImpl.updateStockProcess(webActionDb);

						}else if("Delete".equalsIgnoreCase(webActionDb.getSubFeature())) {

							stockDeleteServiceImpl.deleteStockProcess(webActionDb);


						}

					}
				}
				Thread.sleep(6000);

			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception in Main");
			}

		}
	}

}
