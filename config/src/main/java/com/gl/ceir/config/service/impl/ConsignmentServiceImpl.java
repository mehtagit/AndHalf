package com.gl.ceir.config.service.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;


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

	public List<ConsignmentMgmt> getFilteredConsignment(ConsignmentMgmt consignmentMgmt) {
		try {

			CaseSearchSpecificaton filter = new CaseSearchSpecificaton(consignmentMgmt);
			logger.info("Going to get All Cosignment List ");
			return consignmentRepository.findAll(filter);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}


	public  class CaseSearchSpecificaton implements Specification<ConsignmentMgmt> {

		public CaseSearchSpecificaton(ConsignmentMgmt filter) {
			super();
			this.filter = filter;
		}

		private ConsignmentMgmt  filter;

		public Predicate toPredicate(Root<ConsignmentMgmt> root, CriteriaQuery<?> cq,
				CriteriaBuilder cb) {
			Predicate p = cb.disjunction();

			if (filter.getTaxPaidStatus() != null) {
				p.getExpressions()
				.add(cb.equal(root.get("taxPaidStatus"), filter.getTaxPaidStatus()));
			}

			/*if (filter.getSurname() != null && filter.getAge() != null) {
				p.getExpressions().add(
						cb.and(cb.equal(root.get("surname"), filter.getSurname()),
								cb.equal(root.get("age"), filter.getAge())));
			}*/

			return p;
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
			if(3 == consignment.getConsignmentStatus() || 5 == consignment.getConsignmentStatus() || 8 == consignment.getConsignmentStatus())
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
