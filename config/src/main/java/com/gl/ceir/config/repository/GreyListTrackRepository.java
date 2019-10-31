package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.GreylistDbHistory;

public interface GreyListTrackRepository extends JpaRepository<GreylistDbHistory, Long> {


	public GreylistDbHistory save(GreylistDbHistory greyListTracDetails);

}
