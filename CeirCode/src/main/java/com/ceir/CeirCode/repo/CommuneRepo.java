package com.ceir.CeirCode.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.Commune;

public interface CommuneRepo extends JpaRepository<Commune, Long>{
	public List<Commune> findByDistrictID(Long districtID);
	public boolean existsByDistrictIDAndCommune(Long districtID,String commune); 
	
}
