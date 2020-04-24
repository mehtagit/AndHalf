package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.UserNotification;
import com.gl.ceir.config.model.UserNotificationDeliveryReport;

public interface UserNotificationDeliveryReportRepositoy extends JpaRepository<UserNotificationDeliveryReport, String> {

	public List<UserNotificationDeliveryReport> findByMsisdn(Long msisdn);

	public List<UserNotificationDeliveryReport> findByImei(Long imei);

	public List<UserNotificationDeliveryReport> findByMsisdnAndImei(Long msisdn, Long imei);
}
