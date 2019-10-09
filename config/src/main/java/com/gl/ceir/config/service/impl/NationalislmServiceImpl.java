package com.gl.ceir.config.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ForeignerRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.GreyList;
import com.gl.ceir.config.model.GreyListTracDetails;
import com.gl.ceir.config.model.ImeiInfo;
import com.gl.ceir.config.model.NationalismImeiDetails;
import com.gl.ceir.config.model.NationlismDetails;
import com.gl.ceir.config.repository.GreyListRepository;
import com.gl.ceir.config.repository.GreyListTrackRepository;
import com.gl.ceir.config.repository.NationalismDetailsRepository;
import com.gl.ceir.config.repository.NationlismImeiDetailsRepository;

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
