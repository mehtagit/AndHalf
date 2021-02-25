package read.excel.ReadExcel.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import read.excel.ReadExcel.repo.DistrictRepo;
import read.excel.ReadExcel.service.ReadExcelService;
@Service
public class DistrictDao implements ReadExcelService {

	@Autowired
	DistrictRepo districtRepo;
	
	@Override
	public boolean existsByProvinceAndDistrict(String province, String district) {
		// TODO Auto-generated method stub
		Boolean  isExist = districtRepo.existsByProvinceAndDistrict(province, district);
		if(isExist) {
			return true;
		}
		return false;
	}

}
