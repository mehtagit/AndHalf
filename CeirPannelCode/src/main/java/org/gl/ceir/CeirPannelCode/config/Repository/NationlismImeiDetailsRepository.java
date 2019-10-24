package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.gl.ceir.CeirPannelCode.config.Model.NationalismImeiDetails;

public interface NationlismImeiDetailsRepository extends JpaRepository<NationalismImeiDetails, Long> {

	public NationalismImeiDetails save(NationalismImeiDetails nationalismImeiDetails);

	public List<NationalismImeiDetails> getByPassportNumber(String passportNumber);


	@Transactional
	@Modifying
	@Query(value = "update nationalism_imei_details set status=1? where passport_number=2? and first_imei=3? and second_imei=4?",
	nativeQuery = true)
	void updateUser(String taxPaidStatus,String passportNumber,Long firstImei,Long secondImei);




}
