package demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.learning.demo.repository.EmployeeRepository;
import com.learning.demo.resource.Employee;
import com.learning.demo.services.EmployeeService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusineesServiceTest {

	@Mock
	EmployeeRepository employeeRepository;

	@InjectMocks
	EmployeeService  employeeService;
	
	@Ignore
	@Test
	public void testFindTheGreatestFromAllData() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Arun Meht");
		Optional<Employee> OpEmp = Optional.of(employee);
		
		Employee ExpectedEmployee = new Employee();
		ExpectedEmployee.setId(1L);
		ExpectedEmployee.setName("Arun Meht");
		
		when(employeeRepository.findById(1L)).thenReturn(OpEmp);
		assertEquals(ExpectedEmployee.getName(), employeeService.find(1L).getName());
	}
}
	
