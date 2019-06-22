package com.learning.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.demo.repository.CollegeRepository;
import com.learning.demo.resource.College;

@Service
public class CollegeService {
	@Autowired
	public CollegeRepository repo;

	public College save(College employee) {
		System.out.println("Going to save : " + employee);
		return repo.save(employee);
	}

	// @Cacheable(value="book", condition="#name.length < 50")

	// @Cacheable(value = "employees", key = "#id", condition = "#id > 100")
	public College find(Long id) {
		System.out.println("Going to get data from database id:" + id);
		College employee = repo.findById(id).orElse(new College());
		System.out.println("Get data from database id:" + employee.getId());
		return employee;

	}

	// @Cacheable("myAllCache")
	public List<College> findAll() {
		System.out.println("Fetching all employees");
		return repo.findAll();
	}

	// @CachePut(value = "employees", key = "#id")
	public College update(College employee, Long id) {
		System.out.println("Going to update : " + employee);
		employee = repo.save(employee);
		System.out.println("Updated : " + employee);
		return employee;
	}
}
