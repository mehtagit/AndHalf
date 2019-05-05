package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;

public interface DuplicateImeiMsisdnRepository extends JpaRepository<DuplicateImeiMsisdn, ImeiMsisdnIdentity> {

}
