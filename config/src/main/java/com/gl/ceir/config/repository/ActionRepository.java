package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.Action;

public interface ActionRepository extends JpaRepository<Action, Long> {

}
