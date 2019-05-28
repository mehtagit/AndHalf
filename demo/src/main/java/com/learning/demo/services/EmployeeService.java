package com.learning.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.learning.demo.repository.EmployeeRepository;
import com.learning.demo.resource.Employee;

@Service
public class EmployeeService {
	@Autowired
	public EmployeeRepository repo;

	public void save(Employee employee) {
		System.out.println("Going to save : " + employee);
		repo.save(employee);
		System.out.println("Saved save : " + employee);
	}

	// @Cacheable(value="book", condition="#name.length < 50")

	@Cacheable(value = "employees", key = "#id", condition = "#id  > 100")
	public Employee find(Long id) {
		System.out.println("Going to get data from database id:" + id);
		Employee employee = repo.findById(id).orElse(new Employee());
		System.out.println("Get data from database id:" + id);
		return employee;
	}

	@Cacheable("myAllCache")
	public List<Employee> findAll() {
		System.out.println("Fetching all employees");
		return repo.findAll();
	}

	@CachePut(value = "employees", key = "#id")
	public Employee update(Employee employee, Long id) {
		System.out.println("Going to update : " + employee);
		employee = repo.save(employee);
		System.out.println("Updated : " + employee);
		return employee;
	}
}
