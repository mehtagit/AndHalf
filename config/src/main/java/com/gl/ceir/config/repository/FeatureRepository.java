package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {

}
