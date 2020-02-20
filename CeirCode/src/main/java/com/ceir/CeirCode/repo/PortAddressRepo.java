package com.ceir.CeirCode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.PortAddress;

public interface PortAddressRepo extends JpaRepository<PortAddress, Long>{

	public List<PortAddress> findByPort(Integer port);
	public PortAddress findById(long id);
}
