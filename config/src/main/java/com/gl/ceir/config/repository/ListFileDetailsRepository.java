package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.ListFileDetails;

public interface ListFileDetailsRepository extends JpaRepository<ListFileDetails, Long> {


	List<ListFileDetails> getByListType(String listType);

}