package com.ceir.CeirCode.repo;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ceir.CeirCode.model.Village;

public interface VillageRepo extends JpaRepository<Village, Long>{
	public List<Village> findByCommuneID(Long communeID);
	public boolean existsByCommuneIDAndVillage(Long communeID,String village);
	public void deleteByVillage(String village);
	@Transactional
	@Modifying
	@Query(value = "update village_db  set MODIFIED_ON=sysdate, village =:village  where COMMUNE_ID =:communeID and village=:oldVillage", nativeQuery = true)
	public int updateVillageName(String village,long communeID,String oldVillage);
	
}
