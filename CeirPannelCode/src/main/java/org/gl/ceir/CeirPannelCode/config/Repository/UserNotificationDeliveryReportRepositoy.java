package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.UserNotification;
import org.gl.ceir.CeirPannelCode.config.Model.UserNotificationDeliveryReport;

public interface UserNotificationDeliveryReportRepositoy extends JpaRepository<UserNotificationDeliveryReport, String> {

	public List<UserNotificationDeliveryReport> findByMsisdn(Long msisdn);

	public List<UserNotificationDeliveryReport> findByImei(Long imei);

	public List<UserNotificationDeliveryReport> findByMsisdnAndImei(Long msisdn, Long imei);
}
