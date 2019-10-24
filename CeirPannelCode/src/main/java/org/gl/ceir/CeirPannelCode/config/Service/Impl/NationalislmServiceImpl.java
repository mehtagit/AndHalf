package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.ForeignerRequest;
import org.gl.ceir.CeirPannelCode.config.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.config.Model.GreyList;
import org.gl.ceir.CeirPannelCode.config.Model.GreyListTracDetails;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiInfo;
import org.gl.ceir.CeirPannelCode.config.Model.NationalismImeiDetails;
import org.gl.ceir.CeirPannelCode.config.Model.NationlismDetails;
import org.gl.ceir.CeirPannelCode.config.Repository.GreyListRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.GreyListTrackRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.NationalismDetailsRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.NationlismImeiDetailsRepository;

@Service
public class NationalislmServiceImpl {

	@Autowired
	NationalismDetailsRepository nationalismDetailsRepository;

	@Autowired
	NationlismImeiDetailsRepository nationlismImeiDetailsRepository;

	@Autowired
	GreyListRepository greyListRepository;

	@Autowired
	GreyListTrackRepository greyListTrackRepository;


	@Transactional
	public GenricResponse saveNationalismData(ForeignerRequest foreignerDetails) {
		try {
			NationlismDetails nationlismDetails = new NationlismDetails();

			NationalismImeiDetails nationalismImeiDetails =new NationalismImeiDetails();

			NationlismDetails passportDetails = nationalismDetailsRepository.findByPassportNumberOrVisaNumber(foreignerDetails.getPassportNumber(), foreignerDetails.getVisaNumber());

			if(passportDetails == null) {

				nationlismDetails.setCountry(foreignerDetails.getCountry());
				nationlismDetails.setCreatedOn(new Date());
				nationlismDetails.setEmailId(foreignerDetails.getEmailId());
				nationlismDetails.setModifiedOn(new Date());
				nationlismDetails.setNationalismId(foreignerDetails.getForeignerId());
				nationlismDetails.setPassportNumber(foreignerDetails.getPassportNumber());
				nationlismDetails.setVisaNumber(foreignerDetails.getVisaNumber());

				nationalismDetailsRepository.save(nationlismDetails);

				for(ImeiInfo info : foreignerDetails.getImeiInfo() ) {

					nationalismImeiDetails.setCreatedOn(new Date());
					nationalismImeiDetails.setFirstImei(info.getFirstImei());
					nationalismImeiDetails.setModifiedOn(new Date());
					nationalismImeiDetails.setPassportNumber(foreignerDetails.getPassportNumber());
					nationalismImeiDetails.setSecondImei(info.getSecondImei());
					nationalismImeiDetails.setStatus(info.getStatus());

					if("NotPaid".equalsIgnoreCase(info.getStatus())) {

						GreyList greyList =new GreyList();
						greyList.setCreatedOn(new Date());
						greyList.setUpdatedOn(new Date());
						greyList.setImei(info.getFirstImei());
						greyList.setSourceType("Cambodiya Nationlism");

						greyListRepository.save(greyList);

						GreyListTracDetails greyListTracDetails = new GreyListTracDetails();
						greyListTracDetails.setCreatedOn(new Date());
						greyListTracDetails.setUpdatedOn(new Date());
						greyListTracDetails.setImei(info.getFirstImei());
						greyListTracDetails.setOperation("Add");

						greyListTrackRepository.save(greyListTracDetails);

					}else {
						nationlismImeiDetailsRepository.save(nationalismImeiDetails);
					}
				}
				return new GenricResponse(200,"Save Successfully");
			}else {

				return new GenricResponse(1004,"PassportNumber Or visaNumber any one exist.");

			}
		}catch (Exception e) {
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	@Transactional
	public GenricResponse updateNationlismData(ForeignerRequest foreignerDetails) {

		NationlismDetails nationlismDetails = new NationlismDetails();

		NationalismImeiDetails nationalismImeiDetails =new NationalismImeiDetails();

		NationlismDetails passportDetails = nationalismDetailsRepository.getByPassportNumber(nationlismDetails.getPassportNumber());

		if(passportDetails == null) {

			return new GenricResponse(1005, "PassportNmber Does Not exist");
		}else {

			for(ImeiInfo info : foreignerDetails.getImeiInfo() ) {

				nationalismImeiDetails.setCreatedOn(new Date());
				nationalismImeiDetails.setFirstImei(info.getFirstImei());
				nationalismImeiDetails.setModifiedOn(new Date());
				nationalismImeiDetails.setPassportNumber(foreignerDetails.getPassportNumber());
				nationalismImeiDetails.setSecondImei(info.getSecondImei());
				nationalismImeiDetails.setStatus(info.getStatus());

				if("NotPaid".equalsIgnoreCase(info.getStatus())) {

					GreyList greyList =new GreyList();
					greyList.setCreatedOn(new Date());
					greyList.setUpdatedOn(new Date());
					greyList.setImei(info.getFirstImei());
					greyList.setSourceType("Cambodiya Nationlism");

					greyListRepository.save(greyList);

					GreyListTracDetails greyListTracDetails = new GreyListTracDetails();
					greyListTracDetails.setCreatedOn(new Date());
					greyListTracDetails.setUpdatedOn(new Date());
					greyListTracDetails.setImei(info.getFirstImei());
					greyListTracDetails.setOperation("Add");

					greyListTrackRepository.save(greyListTracDetails);

				}else {
					nationlismImeiDetailsRepository.save(nationalismImeiDetails);
				}
			}

			return new GenricResponse(200, "Update Successfully.");
		}
	}



	public List<NationalismImeiDetails> getImeiDetails(String passportNumber){
		try {
			return 	nationlismImeiDetailsRepository.getByPassportNumber(passportNumber);

		} catch (Exception e) {
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}


	public NationlismDetails getUserName(String passportNumber) {

		try {

			return 	nationalismDetailsRepository.getByPassportNumber(passportNumber);

		} catch (Exception e) {
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());

		}
	}


	public ForeignerRequest getRecord(String passportNumber) {
		try {
			ForeignerRequest foreignerRequest = new ForeignerRequest();

			List<ImeiInfo> imeiInfoList = new ArrayList<ImeiInfo>();

			NationlismDetails passportDetails = nationalismDetailsRepository.getByPassportNumber(passportNumber);

			foreignerRequest.setCountry(passportDetails.getCountry());
			foreignerRequest.setCreatedOn(passportDetails.getCreatedOn());
			foreignerRequest.setEmailId(passportDetails.getEmailId());
			foreignerRequest.setForeignerId(passportDetails.getNationalismId());
			foreignerRequest.setName(passportDetails.getName());
			foreignerRequest.setPassportNumber(passportNumber);
			foreignerRequest.setUpdatedOn(passportDetails.getModifiedOn());
			foreignerRequest.setVisaExpireDate(passportDetails.getVisaExpireDate());
			foreignerRequest.setVisaNumber(passportDetails.getVisaNumber());

			List<NationalismImeiDetails> list =	nationlismImeiDetailsRepository.getByPassportNumber(passportNumber);

			for(NationalismImeiDetails details : list) {
				ImeiInfo imeiInfo = new ImeiInfo();
				imeiInfo.setFirstImei(details.getFirstImei());
				imeiInfo.setSecondImei(details.getSecondImei());
				imeiInfo.setStatus(details.getStatus());

				imeiInfoList.add(imeiInfo);
			}

			foreignerRequest.setImeiInfo(imeiInfoList);
			return foreignerRequest;

		} catch (Exception e) {
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}



	public GenricResponse updateStatus(NationalismImeiDetails nationalismImeiDetails) {

		nationlismImeiDetailsRepository.updateUser(nationalismImeiDetails.getStatus(), nationalismImeiDetails.getPassportNumber(), nationalismImeiDetails.getFirstImei(), nationalismImeiDetails.getSecondImei());

		greyListRepository.deleteByImei(nationalismImeiDetails.getFirstImei());

		GreyListTracDetails greyListTracDetails = new GreyListTracDetails();
		greyListTracDetails.setCreatedOn(new Date());
		greyListTracDetails.setUpdatedOn(new Date());
		greyListTracDetails.setImei(nationalismImeiDetails.getFirstImei());
		greyListTracDetails.setOperation("Add");

		greyListTrackRepository.save(greyListTracDetails);

		return new GenricResponse(200, "Update Successfully");

	}




}
