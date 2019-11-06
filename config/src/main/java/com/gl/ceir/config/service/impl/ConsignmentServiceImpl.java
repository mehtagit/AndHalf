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
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.ConsignmentMgmtSpecificationBuilder;

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
			webActionDb.setFeature("Consignment");
			webActionDb.setSubFeature("Register");
			webActionDb.setState(0);
			webActionDb.setTxnId(consignmentFileRequest.getTxnId());


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

	public Page<ConsignmentMgmt> getFilterConsignments(ConsignmentMgmt consignmentMgmt, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			
			ConsignmentMgmtSpecificationBuilder cmsb = new ConsignmentMgmtSpecificationBuilder();
			if(Objects.nonNull(consignmentMgmt.getConsignmentNumber()))
				cmsb.with(new SearchCriteria("consignmentNumber", consignmentMgmt.getConsignmentNumber(), Datatype.STRING, SearchOperation.EQUALITY));
			if(Objects.nonNull(consignmentMgmt.getSupplierId()))
				cmsb.with(new SearchCriteria("supplierId", consignmentMgmt.getSupplierId(), Datatype.STRING, SearchOperation.EQUALITY));
			if(Objects.nonNull(consignmentMgmt.getCreatedOn()))
				cmsb.with(new SearchCriteria("createdOn", consignmentMgmt.getCreatedOn(), Datatype.DATE, SearchOperation.GREATER_THAN));
			
			Page<ConsignmentMgmt> page = consignmentRepository.findAll(cmsb.build(), pageable);
			
			return page;

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
				if(3 == consignmentInfo.getConsignmentStatus() || 5 == consignmentInfo.getConsignmentStatus() || 8 == consignmentInfo.getConsignmentStatus())
				{
					if(consignmentInfo.getConsignmentNumber() == consignmentFileRequest.getConsignmentNumber())
					{
						return new GenricResponse(3,"Consignment Already Exist",consignmentFileRequest.getTxnId());	

					}else {

						consignmentInfo.setConsignmentNumber(consignmentFileRequest.getConsignmentNumber());
						consignmentInfo.setExpectedArrivaldate(consignmentFileRequest.getExpectedArrivaldate());
						consignmentInfo.setExpectedArrivalPort(consignmentFileRequest.getExpectedArrivaldate());
						consignmentInfo.setExpectedDispatcheDate(consignmentFileRequest.getExpectedDispatcheDate());
						consignmentInfo.setOrganisationCountry(consignmentFileRequest.getOrganisationCountry());
						consignmentInfo.setQuantity(consignmentFileRequest.getQuantity());
						consignmentInfo.setSupplierId(consignmentFileRequest.getSupplierId());
						consignmentInfo.setSupplierName(consignmentFileRequest.getSupplierName());
						consignmentInfo.setTaxPaidStatus(consignmentFileRequest.getTaxPaidStatus());

						if(consignmentFileRequest.getFileName() != null && consignmentFileRequest.getFileName() != " ")
						{
							consignmentInfo.setConsignmentStatus(1);	
							consignmentInfo.setFileName(consignmentFileRequest.getFileName());
						}

						consignmentRepository.save(consignmentInfo);

						WebActionDb webActionDb = new WebActionDb();
						webActionDb.setFeature("Consignment");
						webActionDb.setSubFeature("Update");
						webActionDb.setState(0);
						webActionDb.setTxnId(consignmentFileRequest.getTxnId());

						webActionDbRepository.save(webActionDb);

						return new GenricResponse(0,"Consignment Update in Processing.",consignmentFileRequest.getTxnId());
					}				

				}else {

					return new GenricResponse(200,"Operation NOt Allowed.",consignmentFileRequest.getTxnId());
				}
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	} 



	@Transactional
	public GenricResponse deleteConsigmentInfo(String txnId) {
		try {
			ConsignmentMgmt consignment = consignmentRepository.getByTxnId(txnId);

			if(consignment == null) {
				return new GenricResponse(4, "Consignment Does not Exist",txnId);
			}
			if(1 == consignment.getConsignmentStatus()||3 == consignment.getConsignmentStatus() || 5 == consignment.getConsignmentStatus() || 8 == consignment.getConsignmentStatus())
			{

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature("Consignment");
				webActionDb.setSubFeature("Delete");
				webActionDb.setState(0);
				webActionDb.setTxnId(txnId);

				webActionDbRepository.save(webActionDb);

				return new GenricResponse(200, "Delete In Progress",txnId);
			}
			else {
				return new GenricResponse(200, "Operation Not Allowed",txnId);

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}












}
