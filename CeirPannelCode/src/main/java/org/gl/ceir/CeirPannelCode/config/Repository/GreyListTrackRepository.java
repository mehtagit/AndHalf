package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.GreyListTracDetails;

public interface GreyListTrackRepository extends JpaRepository<GreyListTracDetails, Long> {


	public GreyListTracDetails save(GreyListTracDetails greyListTracDetails);

}
