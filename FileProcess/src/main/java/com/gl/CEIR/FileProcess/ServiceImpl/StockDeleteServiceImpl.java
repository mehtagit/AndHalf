package com.gl.CEIR.FileProcess.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gl.CEIR.FileProcess.service.WebActionService;
import com.gl.ceir.config.model.WebActionDb;

@Service
public class StockDeleteServiceImpl implements WebActionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean process(WebActionDb webActionDb) {

		try {

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;

		}

		return Boolean.TRUE;

	}
}
