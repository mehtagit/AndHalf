package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.FileStorageException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.BlackListImeiDetails;
import org.gl.ceir.CeirPannelCode.config.Model.BlackListTrackDetails;
import org.gl.ceir.CeirPannelCode.config.Model.ForeignerDetails;
import org.gl.ceir.CeirPannelCode.config.Model.ForeignerImeiDetails;
import org.gl.ceir.CeirPannelCode.config.Model.ForeignerRequest;
import org.gl.ceir.CeirPannelCode.config.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiInfo;
import org.gl.ceir.CeirPannelCode.config.Model.ImmegreationImeiDetails;
import org.gl.ceir.CeirPannelCode.config.Model.StackholderPolicyMapping;
import org.gl.ceir.CeirPannelCode.config.Repository.BlackListImeiDetailsRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.BlackListTrackDetailsRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.ForeignerDetailsRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.ForeignerImeiDetailsRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.ImmegreationImeiDetailsRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.StackholderPolicyMappingRepository;
import org.gl.ceir.CeirPannelCode.config.util.Utility;

@Service
public class ForeignerServiceImpl {

	@Autowired
	ForeignerDetailsRepository foreignerDetailsRepository ;

	@Autowired
	ForeignerImeiDetailsRepository foreignerImeiDetailsRepository;


	@Autowired
	ImmegreationImeiDetailsRepository immegreationImeiDetailsRepository;

	@Autowired
	StackholderPolicyMappingRepository stackholderPolicyMappingRepository;

	@Autowired
	Utility utility;

	@Autowired
	BlackListImeiDetailsRepository blackListImeiDetailsRepository;

	@Autowired
	BlackListTrackDetailsRepository blackListTrackDetailsRepository;


	@Transactional	
	public GenricResponse addForeignerInfo(ForeignerRequest foreignerDetails) {
		try {

			ForeignerDetails foreignerDetailsData = new ForeignerDetails();
			foreignerDetailsData.setCountry(foreignerDetails.getCountry());
			foreignerDetailsData.setCreatedOn(new Date());
			foreignerDetailsData.setEmailId(foreignerDetails.getEmailId());
			foreignerDetailsData.setForeignerId(foreignerDetails.getForeignerId());
			foreignerDetailsData.setName(foreignerDetails.getName());
			foreignerDetailsData.setPassportNumber(foreignerDetails.getPassportNumber());
			foreignerDetailsData.setUpdatedOn(new Date());
			foreignerDetailsData.setVisaExpireDate(foreignerDetails.getVisaExpireDate());
			foreignerDetailsData.setVisaNumber(foreignerDetails.getVisaNumber());

			ForeignerDetails passportNumberInfo = foreignerDetailsRepository.findByPassportNumberOrVisaNumber(foreignerDetails.getPassportNumber(), foreignerDetails.getVisaNumber());

			if(passportNumberInfo == null) {

				foreignerDetailsRepository.save(foreignerDetailsData);

				for(ImeiInfo details : foreignerDetails.getImeiInfo() )
				{
					ForeignerImeiDetails foreignerImeiDetails = new ForeignerImeiDetails();
					foreignerImeiDetails.setCreatedOn(new Date());
					foreignerImeiDetails.setUpdatedOn(new Date());
					foreignerImeiDetails.setFirstImei(details.getFirstImei());
					foreignerImeiDetails.setFirstMsisdn(details.getFirstMsisdn());
					foreignerImeiDetails.setSecondImei(details.getSecondImei());
					foreignerImeiDetails.setSecondMsidn(details.getSecondMsidn());
					foreignerImeiDetails.setPassportNumber(foreignerDetails.getPassportNumber());

					foreignerImeiDetailsRepository.save(foreignerImeiDetails);
				}


				return new GenricResponse(200, "Upload SuccessFully");
			}else {

				return new GenricResponse(1002,"Passport number or Visa number any one already exist");
			}

		}catch (Exception e) {
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}



	public List<ForeignerImeiDetails> viewImeidetails(String passportNumber){
		try {

			return 	foreignerImeiDetailsRepository.getByPassportNumber(passportNumber);

		} catch (Exception e) {
			throw new FileStorageException("Exception found.",e);
		}
	}


	public GenricResponse updateInfo(ForeignerRequest foreignerDetails) {
		try {

			ForeignerDetails passporntNumber = foreignerDetailsRepository.getByPassportNumber(foreignerDetails.getPassportNumber());
			if(passporntNumber == null) {

				return new GenricResponse(1003, "Passport number Not Found");
			}else {

				ForeignerDetails foreignerDetailsData = new ForeignerDetails();
				foreignerDetailsData.setCountry(foreignerDetails.getCountry());
				foreignerDetailsData.setEmailId(foreignerDetails.getEmailId());
				foreignerDetailsData.setForeignerId(foreignerDetails.getForeignerId());
				foreignerDetailsData.setName(foreignerDetails.getName());
				foreignerDetailsData.setPassportNumber(foreignerDetails.getPassportNumber());
				foreignerDetailsData.setUpdatedOn(new Date());
				foreignerDetailsData.setVisaExpireDate(foreignerDetails.getVisaExpireDate());
				foreignerDetailsData.setVisaNumber(foreignerDetails.getVisaNumber());

				foreignerDetailsRepository.save(foreignerDetailsData);

				foreignerImeiDetailsRepository.deleteByPassportNumber(foreignerDetails.getPassportNumber());

				for(ImeiInfo details : foreignerDetails.getImeiInfo() )
				{
					ForeignerImeiDetails foreignerImeiDetails = new ForeignerImeiDetails();
					foreignerImeiDetails.setCreatedOn(new Date());
					foreignerImeiDetails.setUpdatedOn(new Date());
					foreignerImeiDetails.setFirstImei(details.getFirstImei());
					foreignerImeiDetails.setFirstMsisdn(details.getFirstMsisdn());
					foreignerImeiDetails.setSecondImei(details.getSecondImei());
					foreignerImeiDetails.setSecondMsidn(details.getSecondMsidn());
					foreignerImeiDetails.setPassportNumber(foreignerDetails.getPassportNumber());

					foreignerImeiDetailsRepository.save(foreignerImeiDetails);
				}

				return new GenricResponse(200, "Update Successfully");
			}

		} catch (Exception e) {
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}



	public ForeignerRequest getPassportNumberDetails(String passportNumber) {
		try {
			List<ImeiInfo> list =new ArrayList<ImeiInfo>();
			ImeiInfo imeiInfo = new ImeiInfo();
			ForeignerDetails passportInfo = foreignerDetailsRepository.getByPassportNumber(passportNumber);

			ForeignerRequest foreignerRequest = new ForeignerRequest();
			foreignerRequest.setCountry(passportInfo.getCountry());
			foreignerRequest.setEmailId(passportInfo.getEmailId());
			foreignerRequest.setForeignerId(passportInfo.getForeignerId());
			foreignerRequest.setName(passportInfo.getName());
			foreignerRequest.setPassportNumber(passportNumber);
			foreignerRequest.setVisaExpireDate(passportInfo.getVisaExpireDate());
			foreignerRequest.setVisaNumber(passportInfo.getVisaNumber());
			foreignerRequest.setCreatedOn(passportInfo.getCreatedOn());
			foreignerRequest.setUpdatedOn(passportInfo.getUpdatedOn());			


			List<ForeignerImeiDetails> info = foreignerImeiDetailsRepository.getByPassportNumber(passportNumber);

			for(ForeignerImeiDetails details :info) {
				imeiInfo.setFirstImei(details.getFirstImei());
				imeiInfo.setSecondImei(details.getSecondImei());
				imeiInfo.setFirstMsisdn(details.getFirstMsisdn());
				imeiInfo.setSecondMsidn(details.getSecondMsidn());

				list.add(imeiInfo);
			}

			foreignerRequest.setImeiInfo(list);

			return foreignerRequest;

		} catch (Exception e) {
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}



	public List<ForeignerDetails> fetchallData(){
		try {

			return 	foreignerDetailsRepository.findAll();

		} catch (Exception e) {
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}


	}

	@Transactional
	public GenricResponse updateImeiActionInfo(ImmegreationImeiDetails immegreationImeiDetails) {

		BlackListImeiDetails blackListImeiDetails = new BlackListImeiDetails();
		blackListImeiDetails.setCreatedOn(new Date());
		blackListImeiDetails.setModifiedOn(new Date());
		blackListImeiDetails.setImei(immegreationImeiDetails.getImei());
		blackListImeiDetails.setSourceType("Immegreation");


		if("Block".equalsIgnoreCase(immegreationImeiDetails.getImeiType())) {

			BlackListImeiDetails imeiInfo = blackListImeiDetailsRepository.getByImei(immegreationImeiDetails.getImei());
			if(imeiInfo == null) {

				if("Default".equalsIgnoreCase(immegreationImeiDetails.getBlockingType()) || immegreationImeiDetails.getBlockingType() == ""){

					StackholderPolicyMapping stackholderPolicyMapping =	stackholderPolicyMappingRepository.getByListType("BlackList");

					String newDate =utility.newDate(stackholderPolicyMapping.getGraceTimePeriod());

					immegreationImeiDetails.setBlockingType("Default");
					immegreationImeiDetails.setBlockingTime(newDate);
				}

				BlackListTrackDetails blackListTrackDetails = new BlackListTrackDetails();
				blackListTrackDetails.setCreatedOn(new Date());
				blackListTrackDetails.setModifiedOn(new Date());
				blackListTrackDetails.setOperation("Add");
				blackListTrackDetails.setSourceType("Immegreation");
				blackListTrackDetails.setImei(immegreationImeiDetails.getImei());
				immegreationImeiDetails.setProcessState(0);


				immegreationImeiDetailsRepository.save(immegreationImeiDetails);

				blackListImeiDetailsRepository.save(blackListImeiDetails);

				blackListTrackDetailsRepository.save(blackListTrackDetails);

				foreignerImeiDetailsRepository.updateUser("Block", immegreationImeiDetails.getPassportNumber(),immegreationImeiDetails.getImei(),immegreationImeiDetails.getImei());

				return new GenricResponse(200, "Block SuccessFully");
			}else {

				return new GenricResponse(1004, "Imei Already Block.");
			}

		}else {
			BlackListImeiDetails imeiInfo = blackListImeiDetailsRepository.getByImei(immegreationImeiDetails.getImei());
			if(imeiInfo == null) {

				return new GenricResponse(1005, "Imei Already UnBlock.");
			}else {

				BlackListTrackDetails blackListTrackDetails = new BlackListTrackDetails();
				blackListTrackDetails.setCreatedOn(new Date());
				blackListTrackDetails.setModifiedOn(new Date());
				blackListTrackDetails.setOperation("Delete");
				blackListTrackDetails.setSourceType("Immegreation");
				blackListTrackDetails.setImei(immegreationImeiDetails.getImei());

				blackListImeiDetailsRepository.deleteByImei(immegreationImeiDetails.getImei());

				foreignerImeiDetailsRepository.updateUser("UnBlock", immegreationImeiDetails.getPassportNumber(),immegreationImeiDetails.getImei(),immegreationImeiDetails.getImei());

				blackListTrackDetailsRepository.save(blackListTrackDetails);

				return new GenricResponse(200, "UnBlock Successfully");
			}
		}
	}












}
