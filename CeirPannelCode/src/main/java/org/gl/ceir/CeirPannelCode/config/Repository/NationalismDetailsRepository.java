package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.NationlismDetails;

public interface NationalismDetailsRepository extends JpaRepository<NationlismDetails, Long> {



	public NationlismDetails  save(NationlismDetails nationlismDetails);

	public NationlismDetails findByPassportNumberOrVisaNumber(String passportNumber ,String visaNumber);

	public NationlismDetails getByPassportNumber(String passportNumber);

	
	
	
}
