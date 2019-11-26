package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.UserCustomHistoryDb;

public interface UserCustomHistoryDbRepository extends JpaRepository<UserCustomHistoryDb, Long> {

}
