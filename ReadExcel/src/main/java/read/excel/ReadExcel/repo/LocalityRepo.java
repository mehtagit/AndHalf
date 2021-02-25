package read.excel.ReadExcel.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import read.excel.ReadExcel.entity.Locality;

public interface LocalityRepo extends JpaRepository<Locality, Long>{
	public boolean existsByCountryAndProvinceAndDistrictAndCommuneAndVillage(String country,String province,String district,String commune,String village);
}
