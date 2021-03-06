package cargo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.Controller;
import controller.IController;
import model.Cargo;
import model.Client;
import model.DrivingShift;
import util.TestUtil;

/**
 * Tests for cargo objects
 *
 */
public class CargoTest {
	
	static Cargo testCargo;
	static IController controller;
	
	/**
	 * Creates a new cargo object
	 */
	@BeforeEach
	void resetCargo() {
		testCargo = new Cargo(2300, false);
	}
	
	/**
	 * Creates test version of the controller
	 */
	@BeforeAll
	static void init() {
		//use test version of controller by passing true as the last argument
		controller = new Controller(null, TestUtil.testVersion);
	}
	
	
	/**
	 * Tests if we can create a cargo object, store it in the database
	 * and retrieve it
	 */
	@Test
	@DisplayName("Testing create to database")
	void createCargo() {
		//create a new cargo that weights 50 and is not hazardous
		Cargo cargo = new Cargo(50, false);
		controller.createCargo(cargo);
		List<Cargo> cargoList = controller.readAllCargo();
		assertTrue(cargoList.contains(cargo),"Database should have the cargo!");
		controller.deleteCargo(cargo);
	}
	
	/**
	 * Tests updating cargo
	 */
	@Test
	@DisplayName("Update cargo")
	void updateCargo() {
		Cargo cargo = new Cargo(50, false);
		controller.createCargo(cargo);
		int id = cargo.getCargoID();
		cargo.setWeight(100);
		controller.updateCargo(cargo);
		assertEquals(100, controller.readCargo(id).getWeight(), "Cargo was not updated.");
		controller.deleteCargo(cargo);
	}
	
	/**
	 * Tests reading all unassigned cargo from the database 
	 */
	@Test
	@DisplayName("Testing read all unassigned from db")
	void readAllUnassigned() {
		Cargo cargo = new Cargo(50, false);
		controller.createCargo(cargo);
		List<Cargo> cargoList = controller.readAllUnassignedCargo();
		assertTrue(cargoList.contains(cargo),"Database should have the cargo!");
		controller.deleteCargo(cargo);
	}
	
	
	/**
	 * Tests the setter and getter of weight of a cargo object
	 */
	@Test
	@DisplayName("Test getWeight")
	void getWeight() {	
		assertEquals(2300, testCargo.getWeight(), "Cargo weight not correct.");
	}
	
	/**
	 * Tests the setter and getter of weight of a cargo object
	 */
	@Test
	@DisplayName("Test setWeight")
	void setWeight() {	
		testCargo.setWeight(3003);
		assertEquals(3003, testCargo.getWeight(), "Cargo weight not changed.");
	}
	
	/**
	 * Tests if the cargo is hazardous or not
	 */
	@Test
	@DisplayName("Test isHazardous")
	void isHazardous() {	
		assertEquals(false, testCargo.isHazardous(), "Cargo hazardous status not correct.");
	}
	
	/**
	 * Tests if the cargo is hazardous or not, after setting value to be true
	 */
	@Test
	@DisplayName("Test setHazardous")
	void setHazardous() {
		testCargo.setHazardous(true);
		assertEquals(true, testCargo.isHazardous(), "Cargo hazardous status not changed.");
	}
	
	/**
	 * Tests driving shift setter
	 */
	@Test
	@DisplayName("Test setDrivingshift")
	void setDrivingShift() {
		DrivingShift s = new DrivingShift();
		testCargo.setShift(s);
		assertEquals(s, testCargo.getShift(), "Setting shift didn't work.");
	}
	
	/**
	 * Tests toString method
	 */
	@Test
	@DisplayName("toString test")
	void testToString() {
		Cargo cargo = new Cargo(50.0);
		boolean contains = cargo.toString().contains("Hazardous");
		assertFalse(contains, "toString prints with Hazardous text!");
		cargo.setHazardous(true);
		contains = cargo.toString().contains("Hazardous");
		assertTrue(contains, "toString prints without Hazardous text!");
	}
	
	/**
	 * Tests reading unassigned cargo objects from database
	 */
	@Test
	@DisplayName("unassigned cargo test")
	void unassignedCargo() {
		Cargo c1 = new Cargo(50);
		Cargo c2 = new Cargo(60);
		DrivingShift shift = new DrivingShift(new Client(""), c1, LocalDate.now());
		controller.createDrivingShift(shift);
		controller.createCargo(c2);
		
		List<Cargo> cargo = controller.readAllUnassignedCargo();
		assertEquals(1, cargo.size(), "more than 1 cargo");
		controller.deleteShift(shift);
		controller.deleteCargo(c2);
	}
}
