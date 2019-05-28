package com.learning.demo.resource;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Student {
	@EmbeddedId
	private StudentIdentity studentIdentity;

	private String name;

	@Enumerated(EnumType.STRING)
	private ClassNames className;

	public StudentIdentity getStudentIdentity() {
		return studentIdentity;
	}

	public void setStudentIdentity(StudentIdentity studentIdentity) {
		this.studentIdentity = studentIdentity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassNames getClassName() {
		return className;
	}

	public void setClassName(ClassNames className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "Student [studentIdentity=" + studentIdentity + ", name=" + name + "]";
	}

}
