package read.excel.ReadExcel.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import read.excel.ReadExcel.entity.Province;

public interface ProvinceRepo extends JpaRepository<Province, Long>{
	public boolean existsByCountryAndProvince(String country,String province);
}
