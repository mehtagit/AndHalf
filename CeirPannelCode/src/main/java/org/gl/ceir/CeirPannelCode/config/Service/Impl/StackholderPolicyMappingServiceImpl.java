package org.gl.ceir.CeirPannelCode.config.Service.Impl;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.StackholderPolicyMapping;
import org.gl.ceir.CeirPannelCode.config.Repository.StackholderPolicyMappingRepository;

@Service
public class StackholderPolicyMappingServiceImpl {

	private static final Logger logger = LogManager.getLogger(StackholderPolicyMappingServiceImpl.class);



	@Autowired
	StackholderPolicyMappingRepository stackholderPolicyMappingRepository;





	public StackholderPolicyMapping getBlackListConfigDetails() {
		try {
			return stackholderPolicyMappingRepository.getByListType("BlackList");

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}



	public List<StackholderPolicyMapping> getFileControllingDetails(){

		return 	stackholderPolicyMappingRepository.findByListTypeOrListType("BlackList", "GreyList");


	}	


}
