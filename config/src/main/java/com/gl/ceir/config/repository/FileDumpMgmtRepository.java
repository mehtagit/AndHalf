package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.FileDumpMgmt;

public interface FileDumpMgmtRepository extends JpaRepository<FileDumpMgmt, Long> {

	public List<FileDumpMgmt> getByServiceDump(String serviceDump);





}



