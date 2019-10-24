package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.gl.ceir.CeirPannelCode.config.Model.DuplicateImeiMsisdn;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;

public interface DuplicateImeiMsisdnRepository extends JpaRepository<DuplicateImeiMsisdn, ImeiMsisdnIdentity> {

	public List<DuplicateImeiMsisdn> findByImeiMsisdnIdentityImei(Long imei);

}
