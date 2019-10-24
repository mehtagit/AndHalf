package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.CustomDetails;

public interface CustomDetailsRepository extends JpaRepository<CustomDetails, Long> {



	public	CustomDetails save(CustomDetails customDetails);




}
