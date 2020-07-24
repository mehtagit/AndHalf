package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.MessageConfigurationDb;

public interface MessageConfigurationDbRepository extends JpaRepository<MessageConfigurationDb, Long> {


	public MessageConfigurationDb getByTag(String tagValue);

	public MessageConfigurationDb getById(Long id);

	public MessageConfigurationDb getByTagAndActive(String tagValue, int active);
}

