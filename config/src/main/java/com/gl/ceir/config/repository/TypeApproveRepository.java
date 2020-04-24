package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.TypeApprovedDb;


public interface TypeApproveRepository extends JpaRepository<TypeApprovedDb, Long>, JpaSpecificationExecutor<TypeApprovedDb>{

	public TypeApprovedDb save(TypeApprovedDb typeApprovedDb);
	public TypeApprovedDb findById(long id);
	
	@Query(value="select new com.gl.ceir.config.model.ResponseCountAndQuantity(count(t.id) as count) from TypeApprovedDb t "
			+ "where t.approveStatus =:approveStatus and t.userId =:userId")
	public ResponseCountAndQuantity getTypeApproveCount( @Param("approveStatus")Integer approveStatus, @Param("userId")Integer userId);
}
