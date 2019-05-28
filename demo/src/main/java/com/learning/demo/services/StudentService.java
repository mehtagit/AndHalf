package com.learning.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.demo.repository.StudentRepository;
import com.learning.demo.resource.Student;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repo;

	public List<Student> viewAll() {
		return repo.findByStudentIdentityRollno(2L);
	}

}
