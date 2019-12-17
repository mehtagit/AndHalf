package com.gl.CEIR.FileProcess.ServiceImpl;

import org.springframework.stereotype.Service;

import com.gl.CEIR.FileProcess.service.WebActionService;
import com.gl.ceir.config.model.WebActionDb;

@Service
public class StockUploadServiceImpl implements WebActionService{

	@Override
	public boolean process(WebActionDb webActionDb) {
		try {


		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}


}
