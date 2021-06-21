package com.ceir.CeirCode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.ModemInfoTable;

public interface PortAddressRepo extends JpaRepository<ModemInfoTable, Long> ,JpaSpecificationExecutor<ModemInfoTable>{

	public List<ModemInfoTable> findByPortId(String portId);
	public ModemInfoTable findById(long id);
	public ModemInfoTable save(ModemInfoTable modemInfoTable);
	public void deleteById(long id);
}
