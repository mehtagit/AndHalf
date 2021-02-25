package read.excel.ReadExcel.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import read.excel.ReadExcel.entity.Commune;

public interface CommuneRepo extends JpaRepository<Commune, Long>{
	public Commune findByCommune(String commune);
	public Commune findByDistrictIDAndCommune(Long DistrictID,String commune);
}
