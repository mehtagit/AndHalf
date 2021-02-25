package read.excel.ReadExcel.repo;

import read.excel.ReadExcel.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DistrictRepo extends JpaRepository<District, Long>{
	public boolean existsByProvinceAndDistrict(String province,String district);
	public District findByDistrictAndProvince(String district,String province);
	public District findByDistrict(String district);
}
