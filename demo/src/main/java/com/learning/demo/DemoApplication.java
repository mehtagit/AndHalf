package com.learning.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.learning.demo.resource.College;
import com.learning.demo.resource.Student;
import com.learning.demo.resource.StudentIdentity;
import com.learning.demo.services.CollegeService;

@SpringBootApplication
@Component
@EnableAutoConfiguration
@EnableCaching
public class DemoApplication {

	public static int getSubstring(String a) {
		int small = 0;
		Set<Character> set = new HashSet<>();

		for (char s : a.toCharArray()) {
			if ((int) s >= 97 && (int) s < 123)
				set.add(s);
			else
				return -1;
		}

		for (int i = 0; i < a.length() - set.size(); i++) {
			int j = 0;
			while (true) {
				String m = a.substring(i, set.size() + j);
				boolean conatainsAll = true;
				for (char s : set) {
					if (m.contains(s + "")) {
					} else {
						conatainsAll = false;
						break;
					}
				}

				if (conatainsAll) {
					if (small == 0)
						small = m.length();
					if (small > m.length())
						small = m.length();
					break;
				}
				j++;
				if ((set.size() + j) > a.length())
					break;
			}
		}
		return small;
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
//		context.getBean(CollegeService.class).save(getCollege());

		College college = context.getBean(CollegeService.class).find(2L);
		System.out.println(college);

	}

	private static College getCollege() {
		College college = new College();
		college.setName("D.A.V Sen Sec");
		college.setStudents(new HashSet<Student>());
		Student student = new Student();
		student.setStudentIdentity(new StudentIdentity(1L, 102L));
		student.setName("ARUN");
		// student.setCollege();
		college.getStudents().add(student);
		return college;
	}

}
