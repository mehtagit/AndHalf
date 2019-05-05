package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.PendingActions;

public interface PendingActionsRepositoy extends JpaRepository<PendingActions, String> {

}
