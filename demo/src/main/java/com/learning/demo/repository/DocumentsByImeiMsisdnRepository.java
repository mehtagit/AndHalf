package com.learning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.demo.resource.DocumentsByImeiMsisdn;
import com.learning.demo.resource.ImeiMsisdnIdentity;

public interface DocumentsByImeiMsisdnRepository extends JpaRepository<DocumentsByImeiMsisdn, ImeiMsisdnIdentity> {

}
