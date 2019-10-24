package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.BlackListImeiDetails;

public interface BlackListImeiDetailsRepository extends JpaRepository<BlackListImeiDetails,Long> {




	public 	BlackListImeiDetails save(BlackListImeiDetails blackListImeiDetails);

	public BlackListImeiDetails getByImei(Long imei);


	public void deleteByImei(Long imei);


}
