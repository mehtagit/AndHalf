package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gl.ceir.config.model.MessageConfigurationDb;

public interface MessageConfigurationDbRepository extends JpaRepository<MessageConfigurationDb, Long> {

}
