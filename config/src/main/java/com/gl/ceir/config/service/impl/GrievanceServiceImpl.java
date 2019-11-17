package com.gl.ceir.config.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.GrievanceFilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.Grievance;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.GrievanceStatus;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.WebActionDbFeature;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;
import com.gl.ceir.config.service.GrievanceService;
import com.gl.ceir.config.specificationsbuilder.GrievanceSpecificationBuilder;
import com.gl.ceir.config.repository.GrievanceRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;

@Service
public class GrievanceServiceImpl{

	private static final Logger logger = LogManager.getLogger(ConsignmentServiceImpl.class);
	@Autowired
	GrievanceRepository grievanceRepository;
	
	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	PropertiesReader propertiesReader;
	
	@Transactional
	public GenricResponse save(Grievance grievance) {
		try {

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.GRIEVANCE.getName());
			webActionDb.setState(GrievanceStatus.NEW.getCode());
			webActionDb.setTxnId(grievance.getTxnId());


			grievance.setGrievanceStatus( GrievanceStatus.NEW.getCode() );
			webActionDbRepository.save(webActionDb);
			grievanceRepository.save(grievance);
			return new GenricResponse(0,"Consignment Update in Processing.",grievance.getTxnId());

		}catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<Grievance> getGrievanceByUserId(Integer userId) {
		try {
			logger.info("Going to get All Cosignment List ");
			return grievanceRepository.getGrievanceByUserId(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public Grievance getByGrievanceTxnId(String txnId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Grievance> getFilterGrievances(GrievanceFilterRequest grievance, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			System.out.println("dialect : " + propertiesReader.dialect);
			GrievanceSpecificationBuilder gsb = new GrievanceSpecificationBuilder(propertiesReader.dialect);
			if(Objects.nonNull(grievance.getUserId()))
				gsb.with(new SearchCriteria("userId", grievance.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));
			if(Objects.nonNull(grievance.getStartDate()))
				gsb.with(new SearchCriteria("createdOn", grievance.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getEndDate()))
				gsb.with(new SearchCriteria("createdOn",grievance.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getGrievanceStatus()))
				gsb.with(new SearchCriteria("grievanceStatus", grievance.getGrievanceStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			if(Objects.nonNull(grievance.getGrievanceId()))
				gsb.with(new SearchCriteria("grievanceId", grievance.getGrievanceStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			if(Objects.nonNull(grievance.getTxnId()))
				gsb.with(new SearchCriteria("txnId", grievance.getGrievanceId(), SearchOperation.EQUALITY, Datatype.STRING));
			//List<Grievance> data = grievanceRepository.getGrievanceByUserId(grievance.getUserId());
			//logger.info("Data to be fetch in db using jioin ="+data);
			return grievanceRepository.findAll(gsb.build(), pageable).getContent();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}
	
	public Page<Grievance> getFilterPaginationGrievances(GrievanceFilterRequest grievance, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			GrievanceSpecificationBuilder gsb = new GrievanceSpecificationBuilder(propertiesReader.dialect);
			if(Objects.nonNull(grievance.getUserId()))
				gsb.with(new SearchCriteria("userId", grievance.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));
			if(Objects.nonNull(grievance.getStartDate()))
				gsb.with(new SearchCriteria("createdOn", grievance.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getEndDate()))
				gsb.with(new SearchCriteria("createdOn",grievance.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getGrievanceStatus()))
				gsb.with(new SearchCriteria("grievanceStatus", grievance.getGrievanceStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			if(Objects.nonNull(grievance.getGrievanceId()))
				gsb.with(new SearchCriteria("grievanceId", grievance.getGrievanceStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			if(Objects.nonNull(grievance.getTxnId()))
				gsb.with(new SearchCriteria("txnId", grievance.getGrievanceId(), SearchOperation.EQUALITY, Datatype.STRING));
			return grievanceRepository.findAll(gsb.build(), pageable);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

}
