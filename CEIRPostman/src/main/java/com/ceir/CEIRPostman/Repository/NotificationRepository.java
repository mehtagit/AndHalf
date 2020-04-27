package com.ceir.CEIRPostman.Repository;
import java.util.List;
import java.util.Queue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.ceir.CEIRPostman.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>, JpaSpecificationExecutor<Notification>{
	public List<Notification> findByStatusAndChannelType(int status,String channelType);
}
