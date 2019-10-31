package com.gl.ceir.config.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.exceptions.FileStorageException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.StolenandRecoveryMgmt;
import com.gl.ceir.config.repository.StolenAndRecoveryRepository;

@Service
public class StolenAndRecoveryServiceImpl {

	private static final Logger logger = LogManager.getLogger(StolenAndRecoveryServiceImpl.class);



	private final static String NUMERIC_STRING = "0123456789";

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	StolenAndRecoveryRepository stolenAndRecoveryRepository;


	@Transactional
	public GenricResponse storeFile(MultipartFile file, StolenandRecoveryMgmt stolenandRecoveryDetails) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			String txnId=null;

			String serverPath=fileStorageProperties.getActionUploadDir();
			serverPath = serverPath.replace("txnId", txnId);

			File dir = new File(serverPath);
			if (!dir.exists()) {
				boolean status=	dir.mkdirs();
			}

			File serverFile = new File(serverPath+file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(file.getBytes());
			stream.flush();

			stolenandRecoveryDetails.setTxnId(txnId);

			stolenAndRecoveryRepository.save(stolenandRecoveryDetails);

			return new GenricResponse(200,"Upload Successfully.",stolenandRecoveryDetails.getTxnId());

		}catch (Exception e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);

		}


	}
	


	public List<StolenandRecoveryMgmt> getAllInfo(Long userId,String sourceType){
		try {

			return 	null;
					//stolenAndRecoveryRepository.findByUserIdAndSourceType(userId, sourceType);

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	

	}




}
