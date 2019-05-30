package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;

public interface DuplicateImeiMsisdnRepository extends JpaRepository<DuplicateImeiMsisdn, ImeiMsisdnIdentity> {

	public List<DuplicateImeiMsisdn> findByImeiMsisdnIdentityImei(Long imei);
}
