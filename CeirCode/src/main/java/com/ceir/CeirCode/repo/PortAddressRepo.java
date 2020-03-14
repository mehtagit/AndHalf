package com.ceir.CeirCode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.model.UserProfile;

public interface PortAddressRepo extends JpaRepository<PortAddress, Long> ,JpaSpecificationExecutor<PortAddress>{

	public List<PortAddress> findByPort(Integer port);
	public PortAddress findById(long id);
	public PortAddress save(PortAddress portAddress);
	public void deleteById(long id);
}
