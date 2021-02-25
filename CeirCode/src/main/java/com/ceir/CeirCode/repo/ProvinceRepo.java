package com.ceir.CeirCode.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ceir.CeirCode.model.Province;

public interface ProvinceRepo extends JpaRepository<Province, Long> {
	public List<Province> findByCountry(String country);
	public List<Province> findByCountryAndProvince(String country,String province);
	public boolean existsByCountryAndProvince(String country, String province);

	public Province findByProvince(String province);

	@Transactional
	@Modifying
	@Query(value = "update province_db  set MODIFIED_ON=sysdate, province =:currentProvinceName  where province =:province", nativeQuery = true)
	public int updateProvinceName(String currentProvinceName,String province);
	public void deleteByProvince(String province);
}
