package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.ListFileDetails;

public interface ListFileDetailsRepository extends JpaRepository<ListFileDetails, Long> {


	List<ListFileDetails> getByListType(String listType);

}