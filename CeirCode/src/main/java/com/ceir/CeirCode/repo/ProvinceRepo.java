package com.ceir.CeirCode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.District;
import com.ceir.CeirCode.model.Province;

public interface ProvinceRepo extends JpaRepository<Province, Long>{
	public List<Province> findAll();
	public boolean existsByCountryAndProvince(String country,String province);
	public Province findByProvince(String province);
}
