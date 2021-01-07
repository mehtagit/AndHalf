package com.ceir.CeirCode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ceir.CeirCode.model.AddressObject;
import com.ceir.CeirCode.model.District;
public interface DistrictRepo extends JpaRepository<District, Long>{
	public List<District> findByProvince(String province);
	public boolean existsByProvinceAndDistrict(String province,String district);
	/*
	 * @Query("select PROVINCE,DISTRICT,COMMUNE,VILLAGE from district_db d INNER JOIN commune_db c ON d.ID=c.DISTRICT_ID INNER JOIN village_db v ON c.ID=v.COMMUNE_ID"
	 * ) List<AddressObject> fetchRecordInnerJoin();
	 */
	
}
