package com.learning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.demo.resource.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
}
