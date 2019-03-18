package employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Employee;

public class EmployeeTest {
	
	static Employee testEmployee;
	
	@BeforeEach
	void resetEmployee() {
		testEmployee = new Employee("Röi Ukko");
	}
	
	@Test
	@DisplayName("Test getName")
	void getName() {	
		assertEquals("Röi Ukko", testEmployee.getName(), "Employee name not correct.");
	}
	
	@Test
	@DisplayName("Test getName")
	void setName() {
		testEmployee.setName("Röi Akka");
		assertEquals("Röi Akka", testEmployee.getName(), "Employee name not changed.");
	}
	
	@Test
	@DisplayName("Test employeeID")
	void setEmployeeID() {	
		testEmployee.setEmployeeID(2010);
		assertEquals(2010, testEmployee.getEmployeeID(), "Employee ID not changed.");
	}
}
