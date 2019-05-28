package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.Documents;

public interface DocumentsRepository extends JpaRepository<Documents, Long> {

}
