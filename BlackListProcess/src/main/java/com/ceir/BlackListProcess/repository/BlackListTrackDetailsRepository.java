package com.ceir.BlackListProcess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.BlackListProcess.model.BlacklistDbHistory;


public interface BlackListTrackDetailsRepository extends JpaRepository<BlacklistDbHistory, Long> {


	public BlacklistDbHistory save(BlacklistDbHistory blackListTrackDetails);

}
