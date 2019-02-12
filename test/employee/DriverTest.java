package employee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;

import model.*;
import util.HibernateUtil;

@ExtendWith(MockitoExtension.class)
public class DriverTest {
	
	@Mock
	static HibernateUtil util;
	static IDriver testdriver;

	
	@BeforeAll
	public static void setup() {
		System.out.println("testing");
		util = new HibernateUtil();
		HibernateUtil.getSessionFactory();
	}
	
	@BeforeEach
	public void resetDriver() {
		testdriver = new Driver("Kalle", 1, "AB");
	}

	@Test
	@DisplayName("testing the tests")
	void test() {
		assertEquals(true, true, "true was not true");
	}
	
	// not working, lets use mock objects
	@Test
	@DisplayName("Make driver")
	void createDriver() {
		//not yet implemented
		
	}
	
	@Test
	@DisplayName("Driver ID")
	void testID() {
		testdriver.setEmployeeID(11);
		assertEquals(11, testdriver.getEmployeeID(), "Driver ID did not change!");
	}
	
	@Test
	@DisplayName("Drivers License")
	void getDLicense() {
		
		assertEquals("AB", testdriver.getDriversLicense(), "Drivers license is not right!");
		
	}
	
	@Test
	@DisplayName("Change drivers license")
	void changeDLicense() {
		testdriver.setDriversLicense("C1");
		assertEquals("C1", testdriver.getDriversLicense(), "Drivers license has not changed!");
	}
	
	@Test
	@DisplayName("Delete driver")
	void deleteDriver() {
		//not yet implemented
	}
}
