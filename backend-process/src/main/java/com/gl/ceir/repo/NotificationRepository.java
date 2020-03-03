package com.gl.ceir.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>, 
JpaSpecificationExecutor<Notification>{

}
