package com.gl.ceir.config.transaction;

import java.util.List;
import java.util.Objects;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.Userrole;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;

@Component
@Transactional(rollbackOn = Exception.class)
public class ConsignmentTransaction {

	private static final Logger logger = LogManager.getLogger(ConsignmentTransaction.class);

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	private ConsignmentRepository consignmentRepository;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	public boolean executeRegisterConsignment(ConsignmentMgmt consignmentMgmt, WebActionDb webActionDb) {
		boolean queryStatus = Boolean.FALSE;
		webActionDbRepository.save(webActionDb);
		logger.info(String.format("Consignment [{0}] saved in web_action_db.", consignmentMgmt.getTxnId()));

		consignmentRepository.save(consignmentMgmt);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in consigment_mgmt_db.");

		auditTrailRepository.save(new AuditTrail(consignmentMgmt.getUser().getId(), consignmentMgmt.getUserName(), 
				Long.valueOf(consignmentMgmt.getUserTypeId()), consignmentMgmt.getUserType(), Long.valueOf(consignmentMgmt.getFeatureId())
				, Features.CONSIGNMENT, 
				SubFeatures.REGISTER, "", consignmentMgmt.getTxnId(),consignmentMgmt.getRoleType()));
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}

	public boolean executeUpdateConsignment(ConsignmentMgmt consignmentMgmt, WebActionDb webActionDb) {
		boolean queryStatus = Boolean.FALSE;
		webActionDbRepository.save(webActionDb);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in web_action_db.");

		consignmentRepository.save(consignmentMgmt);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] updated in consigment_mgmt_db.");
		logger.info("request:"+consignmentMgmt +":::::usertypeid::::::::"+consignmentMgmt.getUserTypeId());
		auditTrailRepository.save(new AuditTrail(consignmentMgmt.getUser().getId(),consignmentMgmt.getUserName(),
				Long.valueOf(consignmentMgmt.getUserTypeId()), 
				consignmentMgmt.getUserType(),
				Long.valueOf(consignmentMgmt.getFeatureId()),
				Features.CONSIGNMENT, SubFeatures.UPDATE, "", consignmentMgmt.getTxnId(),consignmentMgmt.getRoleType()));
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}

	public boolean executeDeleteConsignment(ConsignmentMgmt consignmentMgmt, WebActionDb webActionDb) {
		boolean queryStatus = Boolean.FALSE;
		webActionDbRepository.save(webActionDb);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in web_action_db.");

		consignmentRepository.save(consignmentMgmt);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] updated in consigment_mgmt_db.");

		auditTrailRepository.save(new AuditTrail(consignmentMgmt.getUser().getId(), consignmentMgmt.getUserName(), Long.valueOf(consignmentMgmt.getUserTypeId()), consignmentMgmt.getUserType(), Long.valueOf(consignmentMgmt.getFeatureId()), 
				Features.CONSIGNMENT, SubFeatures.DELETE, "", consignmentMgmt.getTxnId(),consignmentMgmt.getRoleType()));
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}

	public boolean executeUpdateStatusConsignment(ConsignmentMgmt consignmentMgmt, WebActionDb webActionDb) {
		boolean queryStatus = Boolean.FALSE;
		logger.info("consignmentMgmt:::::"+consignmentMgmt);
		if(Objects.nonNull(webActionDb)) {
			webActionDbRepository.save(webActionDb);
			logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in webaction_db.");	
		}

		consignmentRepository.save(consignmentMgmt);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in consigment_mgmt_db.");

		
//Record insertion in  Audit trail  table
		
		if("CEIRSYSTEM".equalsIgnoreCase(consignmentMgmt.getRoleType())) {
			logger.info("No record insertion in audit trail [CEIRSYSTEM]");
		}
		else {
					auditTrailRepository.save(new AuditTrail(
					consignmentMgmt.getUser().getId(),
					consignmentMgmt.getUser().getUsername(),
					Long.valueOf(Long.valueOf(consignmentMgmt.getUser().getUsertype().getId())),
					consignmentMgmt.getUser().getUsertype().getUsertypeName(),
					Long.valueOf(consignmentMgmt.getFeatureId()),
					Features.CONSIGNMENT, SubFeatures.UPDATE, "",
					consignmentMgmt.getTxnId(),consignmentMgmt.getRoleType()));
			//auditTrailRepository.save(new AuditTrail(consignmentMgmt.getUser().getId(), "", 0L, "", 0L,Features.CONSIGNMENT, SubFeatures.UPDATE, "", consignmentMgmt.getTxnId()));
			
		}
				
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}
}
