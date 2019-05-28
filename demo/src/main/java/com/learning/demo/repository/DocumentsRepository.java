package com.learning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.demo.resource.Documents;

public interface DocumentsRepository extends JpaRepository<Documents, Long> {

}
