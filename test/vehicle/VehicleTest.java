package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.Controller;
import controller.IController;
import model.Vehicle;
import util.TestUtil;

/**
 * 
 * Test class for vehicle objects
 *
 */
public class VehicleTest {

	//TEST CAR
	static Vehicle testCar;
	static IController controller;
	
	/**
	 * Creates a test version of the controller
	 */
	@BeforeAll
	static void setup() {
		controller = new Controller(null, TestUtil.testVersion);
	}
	
	/**
	 * Resets the vehicle
	 */
	@BeforeEach
	void resetVehicle() {
		testCar = new Vehicle("YKS-111", 0.0, 1000, "Sprinter", "Mercedes-Benz", true); 
	}
	
	/**
	 * Testing the empty constructor
	 */
	@Test
	@DisplayName("Empty constructor test")
	void emptyContructorTest() {
		Vehicle temp = new Vehicle();
		boolean test = false;
		if (temp != null) {
			test = true;
		}
		assertTrue(test, "Empty employee was not created.");
	}
	
	
	/**
	 * Tests model getter
	 */
	@Test
	@DisplayName("Test getModel")
	void getModel() {	
		assertEquals("Sprinter", testCar.getModel(), "Car model not correct.");
	}
	
	/**
	 * Tests model setter
	 */
	@Test
	@DisplayName("Test setModel")
	void setModel() {	
		testCar.setModel("GLC");
		assertEquals("GLC", testCar.getModel(), "Car model not changed.");
	}
	
	/**
	 * Tests brand getter
	 */
	@Test
	@DisplayName("Test getBrand")
	void getBrand() {	
		assertEquals("Mercedes-Benz", testCar.getBrand(), "Car brand not correct.");
	}
	
	/**
	 * Tests brand setter
	 */
	@Test
	@DisplayName("Test setBrand")
	void setBrand() {
		testCar.setBrand("Mersu");
		assertEquals("Mersu", testCar.getBrand(), "Car model not changed.");
	}
	
	
	/**
	 * Tests maintained getter
	 */
	@Test
	@DisplayName("Test getMaintained")
	void getMaintained() {	
		assertEquals(true, testCar.getMaintained(), "Maintained false.");
	}
	
	/**
	 * Tests maintained setter
	 */
	@Test
	@DisplayName("Test setMaintained")
	void setMaintained() {	
		testCar.setMaintained(false);
		assertEquals(false, testCar.getMaintained(), "Maintained not false.");
	}
	
	/**
	 * Tests register number getter
	 */
	@Test
	@DisplayName("Test getRegNr")
	void getRegNr() {	
		assertEquals("YKS-111", testCar.getRegNr(), "Register number not correct.");
	}
	
	/**
	 * Tests register number setter
	 */
	@Test
	@DisplayName("Test setRegNr")
	void setRegNr() {	
		testCar.setRegNr("KOO-333");
		assertEquals("KOO-333", testCar.getRegNr(), "Register number not changed.");
	}
	
	/**
	 * Tests driven distance getter
	 */
	@Test
	@DisplayName("Test getDrivenDistance")
	void getDrivenDistance() {	
		assertEquals(0.0, testCar.getDrivenDistance(), "Driven distance not correct.");
	}
	
	/**
	 * Tests adding to driven distance
	 */
	@Test
	@DisplayName("Test addDrivenDistance")
	void addDrivenDistance() {	
		testCar.addDrivenDistance(10.0);
		assertEquals(10.0, testCar.getDrivenDistance(), "Driven distance not changed.");
	}
	
	/**
	 * Test max cargo load getter
	 */
	@Test
	@DisplayName("Test getMaxCargoLoad")
	void getMaxCargoLoad() {	
		assertEquals(1000, testCar.getMaxCargoLoad(), "Max cargo not correct.");
	}
	
	/**
	 * Tests max cargo load setter
	 */
	@Test
	@DisplayName("Test setMaxCargoLoad")
	void setMaxCargoLoad() {
		testCar.setMaxCargoLoad(5000);
		assertEquals(5000, testCar.getMaxCargoLoad(), "Max cargo not correct.");
	}
	
	/**
	 * Tests driven distance setter
	 */
	@Test
	@DisplayName("Test setDrivenDistance")
	void setDrivenDistance() {
		testCar.setDrivenDistance(100);
		assertEquals(100, testCar.getDrivenDistance(), "Driven distance not correct");
	}
	
	/**
	 * Tests toString method
	 */
	@Test
	@DisplayName("toString test")
	void testToString() {
		
		boolean contains = testCar.toString().contains("Sprinter");
		assertTrue(contains, "toString not working properly!");
	}
	
	/**
	 * Tests creating vehicle to database
	 */
	@Test
	@DisplayName("Create vehicle to DB")
	void createVehicle(){
		Vehicle emp = new Vehicle("YKS-111", 0.0, 1000, "Sprinter", "Mercedes-Benz", true);
		controller.createVehicle(emp);
		List<Vehicle> empList = controller.readAllVehicles();
		assertTrue(empList.contains(emp),"Database should have the vehicle!");
		controller.deleteVehicle(emp);
	}
	
	/**
	 * Tests updating vehicle
	 */
	@Test
	@DisplayName("Update vehicle")
	void updateVehicle() {
		Vehicle emp = new Vehicle("YKS-111", 0.0, 1000, "Sprinter", "Mercedes-Benz", true);
		controller.createVehicle(emp);
		int id = emp.getCarID();
		emp.setRegNr("TWO-222");
		controller.updateVehicle(emp);
		assertEquals("TWO-222", controller.readVehicle(id).getRegNr(), "Vehicle was not updated");
		controller.deleteVehicle(emp);
	}
	
	
}
