package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.ImmegreationImeiDetails;

public interface ImmegreationImeiDetailsRepository extends JpaRepository<ImmegreationImeiDetails, Long> {

	public ImmegreationImeiDetails save(ImmegreationImeiDetails immegreationImeiDetails);


}
