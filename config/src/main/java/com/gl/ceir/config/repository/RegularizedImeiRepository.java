package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.RegularizedImei;

public interface RegularizedImeiRepository extends JpaRepository<RegularizedImei, Long> {

}
