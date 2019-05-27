package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.UserNotification;

public interface UserNotificationRepositoy extends JpaRepository<UserNotification, String> {

	public List<UserNotification> findByMsisdn(Long msisdn);

	public List<UserNotification> findByImei(Long imei);

	public List<UserNotification> findByMsisdnAndImei(Long msisdn, Long imei);
}
