package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.CustomDetails;

public interface CustomDetailsRepository extends JpaRepository<CustomDetails, Long> {



	public	CustomDetails save(CustomDetails customDetails);




}
