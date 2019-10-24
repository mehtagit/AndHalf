package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.StackholderPolicyMapping;

public interface StackholderPolicyMappingRepository extends JpaRepository<StackholderPolicyMapping, Long> {


	public 	StackholderPolicyMapping getByListType(String listType);

	public List<StackholderPolicyMapping> findByListTypeOrListType(String listType,String greyList);


}
