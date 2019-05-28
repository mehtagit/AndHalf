package com.learning.demo.resource;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class StudentIdentity implements Serializable {
	@NotNull
	private Long rollno;

	@NotNull
	private Long registraionNo;

	public StudentIdentity() {

	}

	public StudentIdentity(Long rollno, Long registraionNo) {
		this.registraionNo = registraionNo;
		this.rollno = rollno;
	}

	public Long getRollno() {
		return rollno;
	}

	public void setRollno(Long rollno) {
		this.rollno = rollno;
	}

	public Long getRegistraionNo() {
		return registraionNo;
	}

	public void setRegistraionNo(Long registraionNo) {
		this.registraionNo = registraionNo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		StudentIdentity that = (StudentIdentity) o;

		if (!registraionNo.equals(that.registraionNo))
			return false;
		return rollno.equals(that.rollno);
	}

	@Override
	public int hashCode() {
		int result = rollno.hashCode();
		result = 31 * result + registraionNo.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "StudentIdentity [rollno=" + rollno + ", registraionNo=" + registraionNo + "]";
	}

}
