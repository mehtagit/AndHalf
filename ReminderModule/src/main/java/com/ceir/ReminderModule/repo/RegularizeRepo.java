package com.ceir.ReminderModule.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.ReminderModule.entity.EndUserDB;

public interface RegularizeRepo extends JpaRepository<EndUserDB, Long>{

}
