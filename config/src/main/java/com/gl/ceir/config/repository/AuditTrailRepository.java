package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.AuditTrail;

public interface AuditTrailRepository extends JpaRepository<AuditTrail, Long> {

}
