package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.gl.ceir.CeirPannelCode.config.Model.DocumentStatus;
import org.gl.ceir.CeirPannelCode.config.Model.Documents;

public interface DocumentsRepository extends JpaRepository<Documents, Long> {

	public List<Documents> findByImeiOrMsisdn(Long imei, Long msisdn);

}
