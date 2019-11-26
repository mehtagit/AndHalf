package com.gl.ceir.config.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.MessageConfigurationDb;
import com.gl.ceir.config.model.RequestCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.ConsignmentStatus;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.WebActionDbFeature;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.MessageConfigurationDbRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.ConsignmentMgmtSpecificationBuilder;
import com.gl.ceir.config.util.Utility;


@Service
public class ConsignmentServiceImpl {

	private static final Logger logger = LogManager.getLogger(ConsignmentServiceImpl.class);

	//private final Path fileStorageLocation;

	@Autowired
	private ConsignmentRepository consignmentRepository;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired	
	StockDetailsOperationRepository stockDetailsOperationRepository;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired	
	EmailUtil emailUtil;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	MessageConfigurationDbRepository messageConfigurationDbRepository;

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;


	@Transactional
	public GenricResponse registerConsignment(ConsignmentMgmt consignmentFileRequest) {

		try {

			if(consignmentFileRequest.getConsignmentNumber() !=null || consignmentFileRequest.getConsignmentNumber() != " ") {

				ConsignmentMgmt consignmentMgmt	=consignmentRepository.getByConsignmentNumber(consignmentFileRequest.getConsignmentNumber());

				if(consignmentMgmt != null) {

					return new GenricResponse(3,"Consignment Already Exist",consignmentFileRequest.getTxnId());
				}
			}
			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.CONSIGNMENT_REGISTER.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(consignmentFileRequest.getTxnId());


			consignmentFileRequest.setConsignmentStatus(ConsignmentStatus.UPLOADING.getCode());
			webActionDbRepository.save(webActionDb);

			consignmentRepository.save(consignmentFileRequest);

			return new GenricResponse(0,"Register Successfully",consignmentFileRequest.getTxnId());

		}catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<ConsignmentMgmt> getAll(Long importerId) {
		try {
			logger.info("Going to get All Cosignment List ");
			return consignmentRepository.getByUserIdOrderByIdDesc(importerId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public List<ConsignmentMgmt> getFilterConsignments(FilterRequest consignmentMgmt, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			System.out.println("dialect : " + propertiesReader.dialect);
			ConsignmentMgmtSpecificationBuilder cmsb = new ConsignmentMgmtSpecificationBuilder(propertiesReader.dialect);

			if(Objects.nonNull(consignmentMgmt.getUserId()))
				cmsb.with(new SearchCriteria("userId", consignmentMgmt.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(consignmentMgmt.getStartDate()))
				cmsb.with(new SearchCriteria("createdOn", consignmentMgmt.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(consignmentMgmt.getEndDate()))
				cmsb.with(new SearchCriteria("createdOn",consignmentMgmt.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(consignmentMgmt.getConsignmentStatus()))
				cmsb.with(new SearchCriteria("consignmentStatus", consignmentMgmt.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(consignmentMgmt.getTaxPaidStatus()) && !" ".equals(consignmentMgmt.getTaxPaidStatus()) && !consignmentMgmt.getTaxPaidStatus().isEmpty())
				cmsb.with(new SearchCriteria("taxPaidStatus", consignmentMgmt.getTaxPaidStatus(), SearchOperation.EQUALITY, Datatype.STRING));

			List<ConsignmentMgmt> data =consignmentRepository.findByUser_id(consignmentMgmt.getUserId());
			logger.info("Data to be fetch in db using jioin ="+data);

			return consignmentRepository.findAll(cmsb.build(), pageable).getContent();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}





	public Page<ConsignmentMgmt> getFilterPaginationConsignments(FilterRequest consignmentMgmt, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			ConsignmentMgmtSpecificationBuilder cmsb = new ConsignmentMgmtSpecificationBuilder(propertiesReader.dialect);

			if(Objects.nonNull(consignmentMgmt.getUserId()))
				cmsb.with(new SearchCriteria("userId", consignmentMgmt.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(consignmentMgmt.getStartDate()))
				cmsb.with(new SearchCriteria("createdOn",consignmentMgmt.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(consignmentMgmt.getEndDate()))
				cmsb.with(new SearchCriteria("createdOn",consignmentMgmt.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));


			if(Objects.nonNull(consignmentMgmt.getTaxPaidStatus()) && !" ".equals(consignmentMgmt.getTaxPaidStatus()) && !consignmentMgmt.getTaxPaidStatus().isEmpty())
				cmsb.with(new SearchCriteria("taxPaidStatus", consignmentMgmt.getTaxPaidStatus(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(consignmentMgmt.getConsignmentStatus())) {
				cmsb.with(new SearchCriteria("consignmentStatus", consignmentMgmt.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			}else {

				if(consignmentMgmt.getFeatureId() != null && consignmentMgmt.getUserTypeId()!= null) {

					List<Integer> consignmentStatus=new ArrayList<Integer>();
					List<StateMgmtDb> featureList =	stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(consignmentMgmt.getFeatureId(), consignmentMgmt.getUserTypeId());
					if(featureList != null) {
						for(StateMgmtDb stateDb : featureList ) {
							consignmentStatus.add(stateDb.getState());
						}
						logger.info("Array list to add is ="+consignmentStatus);
						cmsb.addSpecification(cmsb.joinWithUserIN(new SearchCriteria("consignmentStatus", consignmentMgmt.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.INT),consignmentStatus));
					}
				}
			}
			return consignmentRepository.findAll(cmsb.build(), pageable);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}


	public ConsignmentMgmt getRecordInfo(String txnId) {
		try {
			logger.info("Going to get  Cosignment Record Info");
			return consignmentRepository.getByTxnId(txnId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}



	@Transactional
	public GenricResponse updateConsignment(ConsignmentMgmt consignmentFileRequest) {

		try {

			ConsignmentMgmt consignmentInfo = consignmentRepository.getByTxnId(consignmentFileRequest.getTxnId());

			logger.info("ConsignmentInfo="+consignmentInfo.toString());

			if(consignmentInfo == null) {

				return new GenricResponse(4,"Consignment Does Not exist.", consignmentFileRequest.getTxnId());

			}
			else {
				/*if(0 == consignmentInfo.getConsignmentStatus() ||2== consignmentInfo.getConsignmentStatus())
				{*/
				if(consignmentInfo.getConsignmentNumber() == consignmentFileRequest.getConsignmentNumber())
				{
					return new GenricResponse(3,"Consignment Already Exist",consignmentFileRequest.getTxnId());	

				}else {

					consignmentInfo.setConsignmentNumber(consignmentFileRequest.getConsignmentNumber());
					consignmentInfo.setExpectedArrivaldate(consignmentFileRequest.getExpectedArrivaldate());
					consignmentInfo.setExpectedArrivalPort(consignmentFileRequest.getExpectedArrivalPort());
					consignmentInfo.setExpectedDispatcheDate(consignmentFileRequest.getExpectedDispatcheDate());
					consignmentInfo.setOrganisationCountry(consignmentFileRequest.getOrganisationCountry());
					consignmentInfo.setQuantity(consignmentFileRequest.getQuantity());
					consignmentInfo.setSupplierId(consignmentFileRequest.getSupplierld());
					consignmentInfo.setSupplierName(consignmentFileRequest.getSupplierName());
					consignmentInfo.setTaxPaidStatus(consignmentFileRequest.getTaxPaidStatus());
					consignmentInfo.setTotalPrice(consignmentFileRequest.getTotalPrice());

					if(consignmentFileRequest.getFileName() != null && consignmentFileRequest.getFileName() != " ")
					{
						consignmentInfo.setConsignmentStatus(ConsignmentStatus.UPLOADING.getCode());	
						consignmentInfo.setFileName(consignmentFileRequest.getFileName());
					}

					consignmentRepository.save(consignmentInfo);

					WebActionDb webActionDb = new WebActionDb();
					webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
					webActionDb.setSubFeature(WebActionDbSubFeature.UPDATE.getName());
					webActionDb.setState(WebActionDbState.INIT.getCode());
					webActionDb.setTxnId(consignmentFileRequest.getTxnId());

					webActionDbRepository.save(webActionDb);

					return new GenricResponse(0,"Consignment Update in Processing.",consignmentFileRequest.getTxnId());
				}				
				/*
				}else {

					return new GenricResponse(200,"Operation NOt Allowed.",consignmentFileRequest.getTxnId());
				}*/
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	} 



	@Transactional
	public GenricResponse deleteConsigmentInfo(ConsignmentMgmt consignmentRequest) {
		try {
			ConsignmentMgmt consignment = consignmentRepository.getByTxnId(consignmentRequest.getTxnId());

			if(consignment == null) {
				return new GenricResponse(4, "Consignment Does not Exist",consignmentRequest.getTxnId());
			}
			/*if(0 == consignment.getConsignmentStatus()|| 2 == consignment.getConsignmentStatus())
			{
			 */

			consignment.setConsignmentStatus(ConsignmentStatus.PROCESSING.getCode());
			consignment.setRemarks(consignmentRequest.getRemarks());

			consignmentRepository.save(consignment);

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.DELETE.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(consignmentRequest.getTxnId());

			webActionDbRepository.save(webActionDb);

			return new GenricResponse(200, "Delete In Progress",consignmentRequest.getTxnId());
			/*}
			else {
				return new GenricResponse(200, "Operation Not Allowed",consignmentRequest.getTxnId());

			}*/

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	@Transactional
	public GenricResponse updateConsignmentStatus(ConsignmentUpdateRequest consignmentUpdateRequest) {
		try {

			ConsignmentMgmt  consignmentMgmt = consignmentRepository.getByTxnId(consignmentUpdateRequest.getTxnId());

			if(0 == consignmentUpdateRequest.getAction()) {

				if(consignmentMgmt == null) {

					return new GenricResponse(4, "TxnId Does not Exist", consignmentUpdateRequest.getTxnId());
				}else {


					UserProfile userProfile =	userProfileRepository.getByUserId(consignmentUpdateRequest.getUserId());

					if("CEIR".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
						MessageConfigurationDb messageDB= messageConfigurationDbRepository.getByTag("Consignment_Success_CEIRAuthority_Email_Message");

						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.PENDING_APPROVAL_FROM_CUSTOMS.getCode());
						consignmentRepository.save(consignmentMgmt);	
						//mail send to user and Custom 



						logger.info("Email sending to user to accept ceir");
						emailUtil.sendEmail("pardeepjangra695@gmail.com", "jangrapardeep695@gmail.com", "TEST", messageDB.getValue());

					}else if("CUSTOM".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {

						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.APPROVED.getCode());
						consignmentRepository.save(consignmentMgmt);

						MessageConfigurationDb messageDB= messageConfigurationDbRepository.getByTag("Consignment_Approved_CustomImporter_Email_Message");

						//send mail to user and CEIR
						logger.info("Email sending to user accpet custom");
						emailUtil.sendEmail("pardeepjangra695@gmail.com", "jangrapardeep695@gmail.com", "TEST", messageDB.getValue());

					}
				}
			}else {
				if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
					consignmentMgmt.setConsignmentStatus(ConsignmentStatus.REJECTED_BY_CEIR_AUTHORITY.getCode());
					consignmentMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
					consignmentRepository.save(consignmentMgmt);
					//mail send to user and custom
					MessageConfigurationDb messageDB= messageConfigurationDbRepository.getByTag("Consignment_Reject_CEIRAuthority_Email_Message");

					logger.info("Email sending to user reject ceir");
					emailUtil.sendEmail("pardeepjangra695@gmail.com", "jangrapardeep695@gmail.com", "TEST", messageDB.getValue());
				}else if("CUSTOM".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {

					consignmentMgmt.setConsignmentStatus(ConsignmentStatus.REJECTED_BY_CUSTOMS.getCode());
					consignmentMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
					MessageConfigurationDb messageDB= messageConfigurationDbRepository.getByTag("Consignment_Rejected_Custom_Email_Message");

					consignmentRepository.save(consignmentMgmt);
					//send mail to user and CEIR
					logger.info("Email sending to user reject custom");
					emailUtil.sendEmail("pardeepjangra695@gmail.com", "jangrapardeep695@gmail.com", "TEST", messageDB.getValue());

				}
			}
			return new GenricResponse(0, "Consignment Update SuccessFully",consignmentUpdateRequest.getTxnId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public RequestCountAndQuantity getConsignmentCountAndQuantity( Integer userId, Integer consignmentStatus) {
		try {
			logger.info("Going to get  Cosignment count and quantity.");
			return consignmentRepository.getConsignmentCountAndQuantity(userId, consignmentStatus);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
}
