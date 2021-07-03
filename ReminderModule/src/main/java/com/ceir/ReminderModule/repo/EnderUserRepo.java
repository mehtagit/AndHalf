package com.ceir.ReminderModule.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.ReminderModule.entity.EndUserDB;

public interface EnderUserRepo extends JpaRepository<EndUserDB, Long>{

}
