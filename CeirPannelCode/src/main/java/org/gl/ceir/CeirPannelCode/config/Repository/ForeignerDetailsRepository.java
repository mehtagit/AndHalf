package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.gl.ceir.CeirPannelCode.config.Model.ForeignerDetails;
import org.gl.ceir.CeirPannelCode.config.Model.ForeignerRequest;

public interface ForeignerDetailsRepository extends JpaRepository<ForeignerDetails, Long>{

	public ForeignerDetails findByPassportNumberOrVisaNumber(String passportNumber,String visaNumber);

	public ForeignerDetails save(ForeignerDetails foreignerDetails);

	public List<ForeignerDetails> findAll();



	public ForeignerDetails getByPassportNumber(String passportNumber);





}
