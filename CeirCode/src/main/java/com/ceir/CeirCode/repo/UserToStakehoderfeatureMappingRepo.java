package com.ceir.CeirCode.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ceir.CeirCode.model.UserToStakehoderfeatureMapping;

public interface UserToStakehoderfeatureMappingRepo extends JpaRepository<UserToStakehoderfeatureMapping, Long>{
	public List<UserToStakehoderfeatureMapping> findByUserTypeFeature_IdOrderByCreatedOnAsc(long id);
	public List<UserToStakehoderfeatureMapping> findByUserTypeFeature_IdAndPeriodOrPeriodAndUserTypeFeature_IdOrderByCreatedOnAsc(long id,String period1,String period2,long id2);
 }    
