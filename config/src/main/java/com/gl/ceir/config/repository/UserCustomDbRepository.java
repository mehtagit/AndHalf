package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.UserCustomDb;

public interface UserCustomDbRepository extends JpaRepository<UserCustomDb, Long> {


	public UserCustomDb  getByDeviceSerialNumber(String serialNumber);


	public void deleteByDeviceSerialNumber(String serialNumber);


	public List<UserCustomDb> getByNid(String nid);
}
