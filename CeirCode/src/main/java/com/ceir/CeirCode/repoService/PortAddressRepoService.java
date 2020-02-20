package com.ceir.CeirCode.repoService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.repo.PortAddressRepo;
@Service
public class PortAddressRepoService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PortAddressRepo portAddRepo;

	public List<PortAddress> getByPort(Integer port){
		try {
			return portAddRepo.findByPort(port);
		}
		catch(Exception e) {
			log.info("error occurs when fetching data by port");
			log.info(e.toString());
			return null;
		}
	}
	
	public PortAddress getById(long id){
		try {
			return portAddRepo.findById(id);
		}
		catch(Exception e) {
			log.info("error occurs when fetching data by id");
			log.info(e.toString());
			return null;
		}
	}
}
