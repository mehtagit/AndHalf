package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.UserNotification;

public interface UserNotificationRepositoy extends JpaRepository<UserNotification, String> {

	public List<UserNotification> findByMsisdn(Long msisdn);

	public List<UserNotification> findByImei(Long imei);

	public List<UserNotification> findByMsisdnAndImei(Long msisdn, Long imei);
}
