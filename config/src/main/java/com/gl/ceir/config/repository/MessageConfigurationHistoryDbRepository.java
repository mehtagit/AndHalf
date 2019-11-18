package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gl.ceir.config.model.MessageConfigurationHistoryDb;

public interface MessageConfigurationHistoryDbRepository extends JpaRepository<MessageConfigurationHistoryDb, Long> {

}
