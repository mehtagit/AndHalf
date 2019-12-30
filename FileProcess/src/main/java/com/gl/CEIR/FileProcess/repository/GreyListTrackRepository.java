package com.gl.CEIR.FileProcess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.CEIR.FileProcess.model.GreylistDbHistory;


public interface GreyListTrackRepository extends JpaRepository<GreylistDbHistory, Long> {


	public GreylistDbHistory save(GreylistDbHistory greyListTracDetails);

}
