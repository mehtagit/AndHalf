package com.ceir.CeirCode.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.model.AddressObject;
import com.ceir.CeirCode.model.AuditTrail;
import com.ceir.CeirCode.model.Commune;
import com.ceir.CeirCode.model.District;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.FilterRequest;
import com.ceir.CeirCode.model.Locality;
import com.ceir.CeirCode.model.Province;
import com.ceir.CeirCode.model.Village;
import com.ceir.CeirCode.repo.AuditTrailRepo;
import com.ceir.CeirCode.repo.CommuneRepo;
import com.ceir.CeirCode.repo.DistrictRepo;
import com.ceir.CeirCode.repo.LocalityRepo;
import com.ceir.CeirCode.repo.ProvinceRepo;
import com.ceir.CeirCode.repo.VillageRepo;
import com.ceir.CeirCode.response.tags.RegistrationTags;
import com.ceir.CeirCode.service.LocalityService;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class AddressManagementController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	DistrictRepo districtRepo;
	@Autowired
	CommuneRepo communeRepo;
	@Autowired
	VillageRepo villageRepo;
	@Autowired
	ProvinceRepo provinceRepo;
	@Autowired
	LocalityRepo localityRepo;
	@Autowired
	LocalityService localityService;
	@Autowired
	AuditTrailRepo auditTrailRepo;

	@ApiOperation(value = "getDistrict")
	@PostMapping("/getDistrict")
	public ResponseEntity<?> getDistrictList(@RequestBody AddressObject addressObject) {
		Optional<List<District>> list = Optional.ofNullable(districtRepo.findByProvince(addressObject.getProvince()));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "getCommune")
	@PostMapping("/getCommune")
	public ResponseEntity<?> getCommuneList(@RequestBody AddressObject addressObject) {
		Optional<List<Commune>> list = Optional.ofNullable(communeRepo.findByDistrictID(addressObject.getDistrictID()));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "getVillage")
	@PostMapping("/getVillage")
	public ResponseEntity<?> getVillageList(@RequestBody AddressObject addressObject) {
		Optional<List<Village>> list = Optional.ofNullable(villageRepo.findByCommuneID(addressObject.getCommuneID()));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/*************************** get Records ***************/

	@ApiOperation(value = "Locality data.")
	@PostMapping("/viewAllLocality")
	public MappingJacksonValue viewRecord(@RequestBody AddressObject filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) {
		MappingJacksonValue mapping = null;
		if (file == 0) {
			Page<Locality> response = localityService.viewAll(filterRequest, pageNo, pageSize);
			mapping = new MappingJacksonValue(response);

		} else {
			FileDetails fileDetails = localityService.getFile(filterRequest);
			mapping = new MappingJacksonValue(fileDetails);
		}
		return mapping;
	}

	/************************** SAVE ************************/

	@ApiOperation(value = "saveProvince")
	@PostMapping("/saveProvince")
	public ResponseEntity<?> saveProvince(@RequestBody Province province) {
		HttpResponse response;
		try {
			Boolean isExistInDB = provinceRepo.existsByCountryAndProvince(province.getCountry(),
					province.getProvince());
			if (isExistInDB) {
				response = new HttpResponse(RegistrationTags.exist.getMessage(), 409, RegistrationTags.exist.getTag());

			} else {
				provinceRepo.save(province);
				response = new HttpResponse(RegistrationTags.Success_Save.getMessage(), 200,
						RegistrationTags.Success_Save.getTag());
				

			}
		} catch (Exception e) {
			response = new HttpResponse(RegistrationTags.Exception.getMessage(), 500,
					RegistrationTags.Exception.getTag());

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "saveDistrict")
	@PostMapping("/saveDistrict")
	public ResponseEntity<?> saveDistrict(@RequestBody District district) {
		HttpResponse response;
		try {
			Boolean isExistInDB = districtRepo.existsByProvinceAndDistrict(district.getProvince(),
					district.getDistrict());
			if (isExistInDB) {
				response = new HttpResponse(RegistrationTags.exist.getMessage(), 409, RegistrationTags.exist.getTag());

			} else {
				districtRepo.save(district);
				response = new HttpResponse(RegistrationTags.Success_Save.getMessage(), 200,
						RegistrationTags.Success_Save.getTag());
			//	audit(auditTrail);

			}
		} catch (Exception e) {
			response = new HttpResponse(RegistrationTags.Exception.getMessage(), 500,
					RegistrationTags.Exception.getTag());

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "saveCommune")
	@PostMapping("/saveCommune")
	public ResponseEntity<?> saveCommune(@RequestBody Commune commune) {
		HttpResponse response;
		try {
			Boolean isExistInDB = communeRepo.existsByDistrictIDAndCommune(commune.getDistrictID(),
					commune.getCommune());
			if (isExistInDB) {
				response = new HttpResponse(RegistrationTags.exist.getMessage(), 409, RegistrationTags.exist.getTag());

			} else {
				communeRepo.save(commune);
				response = new HttpResponse(RegistrationTags.Success_Save.getMessage(), 200,
						RegistrationTags.Success_Save.getTag());

			}
		} catch (Exception e) {
			response = new HttpResponse(RegistrationTags.Exception.getMessage(), 500,
					RegistrationTags.Exception.getTag());

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "saveVillage")
	@PostMapping("/saveVillage")
	public ResponseEntity<?> saveVillage(@RequestBody Village village) {
		HttpResponse response;
		try {
			Boolean isExistInDB = villageRepo.existsByCommuneIDAndVillage(village.getCommuneID(), village.getVillage());
			if (isExistInDB) {
				response = new HttpResponse(RegistrationTags.exist.getMessage(), 409, RegistrationTags.exist.getTag());

			} else {
				villageRepo.save(village);
				Locality locality = new Locality();
				Optional<Commune> communeDetail = communeRepo.findById(village.getCommuneID());
				if (communeDetail.isPresent()) {
					Commune getCommuneDetail = communeDetail.get();
					locality.setCommune(getCommuneDetail.getCommune());

					Optional<District> districtDetail = districtRepo.findById(getCommuneDetail.getDistrictID());
					if (districtDetail.isPresent()) {
						District getDistrictDetail = districtDetail.get();
						locality.setDistrict(getDistrictDetail.getDistrict());

						Province provinceDetail = provinceRepo.findByProvince(getDistrictDetail.getProvince());
						locality.setCountry(provinceDetail.getCountry());
						locality.setProvince(provinceDetail.getProvince());
						locality.setVillage(village.getVillage());
						localityRepo.save(locality);

						log.info("successfully save village : +" + village.getVillage() + "+  in Master table");

					}
				}
				response = new HttpResponse(RegistrationTags.Success_Save.getMessage(), 200,
						RegistrationTags.Success_Save.getTag());

			}
		} catch (Exception e) {
			response = new HttpResponse(RegistrationTags.Exception.getMessage(), 500,
					RegistrationTags.Exception.getTag());

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "getProvince")
	@GetMapping("/getProvince")
	public ResponseEntity<?> getProvinceList() {
		Optional<List<Province>> list = Optional.ofNullable(provinceRepo.findAll());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "deleteRow")
	@DeleteMapping("/deleteRow")
	@Transactional
	public ResponseEntity<?> deleteRow(@RequestBody FilterRequest filterRequest) {

		HttpResponse response;
		try {

			Optional<Locality> localityDetail = localityRepo.findById(filterRequest.getId());
			if (localityDetail.isPresent()) {
				Locality locality = localityDetail.get();
				villageRepo.deleteByVillage(locality.getVillage());
				log.info("Village Successfully deleted");
			}
			localityRepo.deleteById(filterRequest.getId());
			response = new HttpResponse(RegistrationTags.delete_successfully.getMessage(), 200,
					RegistrationTags.delete_successfully.getTag());
		} catch (Exception e) {
			log.info("exception" + e.toString());
			response = new HttpResponse(RegistrationTags.Exception.getMessage(), 500,
					RegistrationTags.Exception.getTag());
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public void audit(AuditTrail auditTrail) {
		/*
		 * AuditTrail auditTrail = new AuditTrail(userId, username, userTypeId,
		 * userType, featureId, feature, subFeature, "0", "NA", userType);
		 */
		auditTrailRepo.save(auditTrail);
	}
}
