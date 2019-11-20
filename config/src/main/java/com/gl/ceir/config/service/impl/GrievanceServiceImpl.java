package com.gl.ceir.config.service.impl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.GrievanceFilterRequest;
import com.gl.ceir.config.model.GrievanceHistory;
import com.gl.ceir.config.model.GrievanceMsg;
import com.gl.ceir.config.model.GrievanceReply;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.Grievance;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.GrievanceStatus;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.WebActionDbFeature;
import com.gl.ceir.config.specificationsbuilder.GrievanceHistorySpecificationBuilder;
import com.gl.ceir.config.specificationsbuilder.GrievanceSpecificationBuilder;
import com.gl.ceir.config.repository.GrievanceHistoryRepository;
import com.gl.ceir.config.repository.GrievanceMsgRepository;
import com.gl.ceir.config.repository.GrievanceRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;

@Service
public class GrievanceServiceImpl{

	private static final Logger logger = LogManager.getLogger(GrievanceServiceImpl.class);
	@Autowired
	GrievanceRepository grievanceRepository;
	@Autowired
	GrievanceMsgRepository grievanceMsgRepository;
	@Autowired
	GrievanceHistoryRepository grievanceHistoryRepository;
	@Autowired
	WebActionDbRepository webActionDbRepository;
	@Autowired
	PropertiesReader propertiesReader;
	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;
	
	@Transactional
	public GenricResponse save(Grievance grievance) {
		try {

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.GRIEVANCE.getName());
			webActionDb.setState(GrievanceStatus.NEW.getCode());
			webActionDb.setTxnId(grievance.getTxnId());
			grievance.setGrievanceStatus( GrievanceStatus.NEW.getCode() );
			grievanceRepository.save(grievance);
			webActionDbRepository.save(webActionDb);
			return new GenricResponse(0,"Grievance registered successfuly",grievance.getTxnId());

		}catch (Exception e) {
			logger.error("Grievance Registration failed="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<Grievance> getGrievanceByUserId(Integer userId) {
		try {
			logger.info("Going to get All grievances List ");
			return grievanceRepository.getGrievanceByUserId(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public List<Grievance> getAllGrievanceStatusNotClosed(Integer userId) {
		try {
			logger.info("Going to get All grievances List ");
			return grievanceRepository.getAllGrievanceStatusNotClosed(userId, GrievanceStatus.CLOSED.getCode());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public List<Grievance> getAllGrievanceStatusNotClosedForAdmin() {
		try {
			logger.info("Going to get All grievances List ");
			return grievanceRepository.getAllGrievanceStatusNotClosedForAdmin(GrievanceStatus.CLOSED.getCode());
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
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "grievanceId"));
			System.out.println("dialect : " + propertiesReader.dialect);
			GrievanceSpecificationBuilder gsb = new GrievanceSpecificationBuilder(propertiesReader.dialect);
			if(Objects.nonNull(grievance.getUserId()) && (grievance.getUserId() != -1 && grievance.getUserId() != 0))
				gsb.with(new SearchCriteria("userId", grievance.getUserId(), SearchOperation.EQUALITY, Datatype.INT));
			if(Objects.nonNull(grievance.getStartDate()))
				gsb.with(new SearchCriteria("createdOn", grievance.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getEndDate()))
				gsb.with(new SearchCriteria("createdOn",grievance.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getGrievanceStatus()) && grievance.getGrievanceStatus() != -1)
				gsb.with(new SearchCriteria("grievanceStatus", grievance.getGrievanceStatus(), SearchOperation.EQUALITY, Datatype.INT));
			else
				gsb.with(new SearchCriteria("grievanceStatus", GrievanceStatus.CLOSED.getCode(), SearchOperation.NEGATION, Datatype.INT));
			if(Objects.nonNull(grievance.getGrievanceId()) && (grievance.getGrievanceId() != -1 && grievance.getGrievanceId() != 0))
				gsb.with(new SearchCriteria("grievanceId", grievance.getGrievanceId(), SearchOperation.EQUALITY, Datatype.LONG));
			if(Objects.nonNull(grievance.getTxnId()))
				gsb.with(new SearchCriteria("txnId", grievance.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));
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
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "grievanceId"));
			GrievanceSpecificationBuilder gsb = new GrievanceSpecificationBuilder(propertiesReader.dialect);
			if(Objects.nonNull(grievance.getUserId()) && (grievance.getUserId() != -1 && grievance.getUserId() != 0))
				gsb.with(new SearchCriteria("userId", grievance.getUserId(), SearchOperation.EQUALITY, Datatype.INT));
			if(Objects.nonNull(grievance.getStartDate()))
				gsb.with(new SearchCriteria("createdOn", grievance.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getEndDate()))
				gsb.with(new SearchCriteria("createdOn",grievance.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getGrievanceStatus()) && grievance.getGrievanceStatus() != -1)
				gsb.with(new SearchCriteria("grievanceStatus", grievance.getGrievanceStatus(), SearchOperation.EQUALITY, Datatype.INT));
			else
				gsb.with(new SearchCriteria("grievanceStatus", GrievanceStatus.CLOSED.getCode(), SearchOperation.NEGATION, Datatype.INT));
			if(Objects.nonNull(grievance.getGrievanceId()) && (grievance.getGrievanceId() != -1 && grievance.getGrievanceId() != 0))
				gsb.with(new SearchCriteria("grievanceId", grievance.getGrievanceId(), SearchOperation.EQUALITY, Datatype.LONG));
			if(Objects.nonNull(grievance.getTxnId()))
				gsb.with(new SearchCriteria("txnId", grievance.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));
			return grievanceRepository.findAll(gsb.build(), pageable);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}
	
	@Transactional
	public GenricResponse saveGrievanceMsg(GrievanceReply grievanceReply) {
		GrievanceMsg grievanceMsg = null;
		GrievanceHistory grievanceHistory = null;
		try {
			Grievance grievance = grievanceRepository.getBygrievanceId( grievanceReply.getGrievanceId() );
			/*****Grievance Message new object*****/
			grievanceMsg = new GrievanceMsg();
			grievanceMsg.setGrievanceId( grievance.getGrievanceId() );
			grievanceMsg.setUserId( grievanceReply.getUserId() );
			grievanceMsg.setUserType( grievanceReply.getUserType());
			grievanceMsg.setFileName( grievanceReply.getFileName());
			grievanceMsg.setReply( grievanceReply.getReply());
			/*****Grievance Message new object all parameters set*****/
			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.GRIEVANCE.getName());
			/**Grievance status update**/
			if( grievanceReply.getGrievanceStatus() != GrievanceStatus.CLOSED.getCode() ) {
				if( grievance.getUserId() != grievanceReply.getUserId() ) { 
					webActionDb.setState(GrievanceStatus.PENDING_WITH_USER.getCode());
					grievance.setGrievanceStatus( GrievanceStatus.PENDING_WITH_USER.getCode() );
				}else{
					webActionDb.setState(GrievanceStatus.PENDING_WITH_ADMIN.getCode());
					grievance.setGrievanceStatus( GrievanceStatus.PENDING_WITH_ADMIN.getCode() );
				}
			}else{
				webActionDb.setState(GrievanceStatus.CLOSED.getCode());
				grievance.setGrievanceStatus( GrievanceStatus.CLOSED.getCode() );
				/**Grievance History object**/
				grievanceHistory = new GrievanceHistory();
				grievanceHistory.setGrievanceId( grievance.getGrievanceId());
				grievanceHistory.setCategoryId( grievance.getCategoryId());
				grievanceHistory.setUserId( grievance.getUserId());
				grievanceHistory.setUserType( grievance.getUserType() );
				grievanceHistory.setCategoryId( grievance.getCategoryId());
				grievanceHistory.setFileName( grievance.getFileName() );
				grievanceHistory.setGrievanceStatus( GrievanceStatus.CLOSED.getCode() );
				grievanceHistory.setCreatedOn(grievance.getCreatedOn());
				grievanceHistory.setTxnId( grievance.getTxnId() );
				grievanceHistory.setRemarks( grievance.getRemarks() );
				/**Grievance History object all parameters set**/
			}
			/**Grievance status update end**/
			webActionDb.setTxnId(grievanceReply.getTxnId());
			webActionDbRepository.save(webActionDb);
			grievanceRepository.save(grievance);
			grievanceMsgRepository.save(grievanceMsg);
			if( grievanceHistory != null )
				grievanceHistoryRepository.save(grievanceHistory);
			return new GenricResponse(0,"Grievance Message saved successfuly.",grievance.getTxnId());

		}catch (Exception e) {
			logger.error("Grievance Message update failed"+e.getMessage());

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public List<GrievanceMsg> getAllGrievanceMessagesByGrievanceId( Long grievanceId ){
		try {
			logger.info("Going to get All grievance Messages List ");
			return grievanceMsgRepository.getGrievanceMsgByGrievanceId(grievanceId );
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public Page<GrievanceHistory> getFilterPaginationGrievanceHistory(GrievanceFilterRequest grievance, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "id"));
			GrievanceHistorySpecificationBuilder gsb = new GrievanceHistorySpecificationBuilder(propertiesReader.dialect);
			if(Objects.nonNull(grievance.getUserId()) && (grievance.getUserId() != -1 && grievance.getUserId() != 0))
				gsb.with(new SearchCriteria("userId", grievance.getUserId(), SearchOperation.EQUALITY, Datatype.INT));
			if(Objects.nonNull(grievance.getStartDate()))
				gsb.with(new SearchCriteria("createdOn", grievance.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getEndDate()))
				gsb.with(new SearchCriteria("createdOn",grievance.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));
			if(Objects.nonNull(grievance.getGrievanceId()) && (grievance.getGrievanceId() != -1 && grievance.getGrievanceId() != 0))
				gsb.with(new SearchCriteria("grievanceId", grievance.getGrievanceId(), SearchOperation.EQUALITY, Datatype.LONG));
			if(Objects.nonNull(grievance.getTxnId()))
				gsb.with(new SearchCriteria("txnId", grievance.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));
			return grievanceHistoryRepository.findAll(gsb.build(), pageable);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

}
