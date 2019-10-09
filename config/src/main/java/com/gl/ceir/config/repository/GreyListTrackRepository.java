package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.GreyListTracDetails;

public interface GreyListTrackRepository extends JpaRepository<GreyListTracDetails, Long> {


	public GreyListTracDetails save(GreyListTracDetails greyListTracDetails);

}
