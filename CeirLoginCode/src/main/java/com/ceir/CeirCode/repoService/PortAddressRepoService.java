package com.ceir.CeirCode.repoService;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.ModemInfoTable;
import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.repo.PortAddressRepo;
@Service
public class PortAddressRepoService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PortAddressRepo portAddRepo;

	public List<ModemInfoTable> getByPort(String portId){
		try {
			return portAddRepo.findByPortId(portId);
		}
		catch(Exception e) {
			log.info("error occurs when fetching data by port");
			log.info(e.toString());
			return null;
		}
	}
	
	public ModemInfoTable getById(long id){
		try {
			return portAddRepo.findById(id);
		}
		catch(Exception e) {
			log.info("error occurs when fetching data by id");
			log.info(e.toString());
			return null;
		}
	}
	
	public ModemInfoTable  save(ModemInfoTable portAddress) {
		
		try {
			log.info("going to save  port Address data");
			return portAddRepo.save(portAddress);
			
		}
		catch(Exception e) {
			log.info("error occurs while adding  port address data");
			log.info(e.toString());
			return  null;
		}
	}
	
	
	
	public int  deleteById(long id) {
		
		try {
			log.info("going to delete  port Address data by id="+id);
			portAddRepo.deleteById(id);
			return 1;
		}
		catch(Exception e) {
			log.info("error occurs while delete  port address data");
			log.info(e.toString());
			return  0;
		}
	}
	
	
	
	
}