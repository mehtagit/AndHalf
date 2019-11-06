package demo;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.demo.DemoApplication;
import com.learning.demo.resource.Employee;
import com.learning.demo.services.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { DemoApplication.class })
public class SomeBusinessMockTest {

	@Resource
	EmployeeService employeeService;

	@Ignore
	@Test
	public void testCase1() {
		Employee emp = new Employee();
		emp.setName("MEHTA");

		Employee empv = employeeService.save(emp);
		// verify(repo, times(1)).save(emp);
		assertEquals("MEHTA", empv.getName());
	}

	@Ignore
	@Test
	public void testCase2() {

		Employee empv = employeeService.find(102L);
		System.out.println("EMPV : " + empv);
		assertEquals("MEHTA", empv.getName());
	}

	// @Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	public static void main(String[] args) {

		ZonedDateTime zoned = LocalDateTime.now().atZone(ZoneId.of("UTC"));
		System.out.println("zoned : " + zoned);
		// zoned : 2015-04-30T13:00+05:30[Asia/Kolkata]
		ZonedDateTime zonedUS = zoned.withZoneSameInstant(ZoneId.of("Pacific/Auckland"));
		System.out.println("zonedUS : " + zonedUS);
		System.out.println(zonedUS.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")));
	}
}
