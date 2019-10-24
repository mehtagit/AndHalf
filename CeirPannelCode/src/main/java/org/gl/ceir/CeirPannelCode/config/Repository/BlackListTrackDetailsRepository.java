package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.BlackListTrackDetails;

public interface BlackListTrackDetailsRepository extends JpaRepository<BlackListTrackDetails, Long> {


	public BlackListTrackDetails save(BlackListTrackDetails blackListTrackDetails);


}
