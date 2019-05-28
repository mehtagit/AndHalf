package com.learning.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.demo.resource.Student;
import com.learning.demo.resource.StudentIdentity;

@Repository
public interface StudentRepository extends JpaRepository<Student, StudentIdentity> {
	public List<Student> findByStudentIdentityRollno(Long rollno);
}
