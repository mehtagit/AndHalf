package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.ImmegreationImeiDetails;

public interface ImmegreationImeiDetailsRepository extends JpaRepository<ImmegreationImeiDetails, Long> {

	public ImmegreationImeiDetails save(ImmegreationImeiDetails immegreationImeiDetails);


}
