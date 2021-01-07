package com.ceir.CeirCode.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.Village;

public interface VillageRepo extends JpaRepository<Village, Long>{
	public List<Village> findByCommuneID(Long communeID);
	public boolean existsByCommuneIDAndVillage(Long communeID,String village);
	public void deleteByVillage(String village);
}
