package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.ImmegreationFileDetails;

public interface ImmegreationFileDertailsRepository extends JpaRepository<ImmegreationFileDetails, Long> {


	public 	ImmegreationFileDetails save(ImmegreationFileDetails immegreationFileDetails);

}
