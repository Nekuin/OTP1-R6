package drivingshift;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import model.Driver;
import model.DrivingShift;
import model.Vehicle;
import util.TestUtil;

/**
 * Tests for driving shift objects
 * 
 *
 */
public class DrivingShiftTest {
	static DrivingShift testShift;
	static Driver testDriver;
	static IController controller;
	
	/**
	 * Create a test driver and controller to be used in database tests
	 */
	@BeforeAll
	public static void setup() {
		testDriver = new Driver("Kalle", "AB");
		//we don't have an instance of view => null as first parameter
		//use false as the second if you want to test locally
		//and use true if you want Jenkins to test
		controller = new Controller(null, TestUtil.testVersion);
	}
	
	/**
	 * Resets testShift to the default constructor object
	 */
	@BeforeEach
	public void resetDrivingShift() {
		testShift = new DrivingShift();
	}
	
	
	/**
	 * Tests Shift finish time set/get
	 */
	@Test
	@DisplayName("Shift finish time")
	void shiftFinishTime(){
		testShift.setFinishTime("16:00");
		assertEquals("16:00", testShift.getFinishTime(), "Finishing time is wrong!");
	}
	
	/**
	 * Tests if the shift is driven or not by default
	 */
	@Test
	@DisplayName("Is shift driven?")
	void shiftIsDriven() {
		
		assertEquals(false, testShift.isShiftDriven());
	}
	
	/**
	 * Tests if the shift is actually set as driven
	 */
	@Test
	@DisplayName("Drive shift")
	void driveShift() {
		testShift.setShiftDriven(true);
		
		assertEquals(true, testShift.isShiftDriven(), "Shift was not driven!");
	}
	
	/**
	 * Tests if the shift driver is being set correctly
	 */
	@Test
	@DisplayName("Shift driver")
	void shiftDriver() {
		testShift.setShiftDriver(testDriver);
		assertEquals(testDriver, testShift.getShiftDriver(), "Shift driver is not correct!");
	}
	
	/**
	 * Tests if we can create a shift to the database
	 * and if we can read a shift from the database
	 */
	@Test
	@DisplayName("DrivingShift DB test")
	void createDrivingShift() {
		DrivingShift s = new DrivingShift(new Client(), new Cargo(), LocalDate.now());
		controller.createDrivingShift(s);
		List<DrivingShift> shifts = controller.readAllDrivingShifts();
		assertTrue(shifts.contains(s));
		controller.deleteShift(s);
	}
	
	/**
	 * Test shift id getter
	 */
	@Test
	@DisplayName("Driving shift id")
	void testID() {
		DrivingShift s = new DrivingShift(new Client(), new Cargo(), LocalDate.now());
		controller.createDrivingShift(s);
		int id = s.getShiftID();
		assertEquals(id, s.getShiftID(), "Shift id is not correct");
		controller.deleteShift(s);
	}
	
	/**
	 * Tests updating driving shift to database
	 */
	@Test
	@DisplayName("Update driving shift")
	void updateDrivingShift() {
		DrivingShift s = new DrivingShift(new Client(), new Cargo(), LocalDate.now());
		Client client = new Client("OK");
		controller.createDrivingShift(s);
		s.setClient(client);
		controller.updateDrivingShift(s);
		assertEquals("OK", s.getClient().getName(), "Update was not completed.");
		controller.deleteShift(s);
		
	}
	
	/**
	 * Tests if we can set the Deadline correctly
	 */
	@Test
	@DisplayName("Shift Deadline test")
	void testDeadline() {
		LocalDate date = LocalDate.now();
		testShift.setDeadline(date);
		assertEquals(date, testShift.getDeadline(), "Deadline was incorrect");
	}
	
	/**
	 * Tests if we can set the Start time correctly
	 */
	@Test
	@DisplayName("Shift Start time test")
	void testStartTime() {
		String time = "08:00";
		testShift.setStartTime(time);
		assertEquals(time, testShift.getStartTime());
	}
	
	/**
	 * Tests if we can set the Vehicle correctly
	 */
	@Test
	@DisplayName("Shift vehicle test")
	void testVehicle() {
		Vehicle v = new Vehicle();
		testShift.setVehicle(v);
		assertEquals(v, testShift.getVehicle(), "Vehicle was not the same");
	}
	
	/**
	 * Tests if we can set the Client correctly
	 */
	@Test
	@DisplayName("Shift Client test")
	void testClient() {
		Client c = new Client();
		testShift.setClient(c);
		assertEquals(c, testShift.getClient(), "Client was not the same! expected " + c);
	}
	
	/**
	 * Tests if we can set the Cargo correctly
	 */
	@Test
	@DisplayName("Shift cargo test")
	void testCargo() {
		Cargo c = new Cargo();
		testShift.addCargo(c);
		List<Cargo> cargo = testShift.getCargo();
		assertTrue(cargo.contains(c));
	}
	
	/**
	 * Tests if we can set the shift as taken
	 */
	@Test
	@DisplayName("Shift set/get Taken test")
	void testShiftTaken() {
		testShift.setShiftTaken(true);
		assertTrue(testShift.getShiftTaken(), "Shift was not taken! expected true");
	}
	
	/**
	 * Tests shifts total cargo weight calculation
	 */
	@Test
	@DisplayName("Test shift total cargo weight")
	void testCargoWeight() {
		Cargo c = new Cargo(50, false);
		Cargo c1 = new Cargo(25, false);
		testShift.addCargo(c);
		testShift.addCargo(c1);
		double expectedWeight = 75.0;
		assertEquals(expectedWeight, testShift.getTotalCargoWeight(), "Cargo weight was wrong");
	}
	
	/**
	 * Tests shifts reported status getter and setter
	 */
	@Test
	@DisplayName("Test shift reported")
	void shiftReported() {
		testShift.setShiftReported(true);
		assertTrue(testShift.getShiftReported(), "Shift was not reported!");
	}
	
	/**
	 * Tests driven date getter and setter
	 */
	@Test
	@DisplayName("Driven date test")
	void drivenDate() {
		LocalDate driven = LocalDate.of(2019, 5, 3);
		testShift.setDrivenDate(driven);
		assertEquals(driven, testShift.getDrivenDate(), "Driven date is incorrect.");
	}
	
	/**
	 * Tests reading reported shifts
	 */
	@Test
	@DisplayName("Test reading reported shifts")
	void readReported() {
		DrivingShift s = new DrivingShift(new Client(), new Cargo(), LocalDate.now());
		s.setShiftReported(true);
		controller.createDrivingShift(s);
		List<DrivingShift> shifts = controller.readReportedShifts();
		assertTrue(shifts.contains(s), "Reported shift was not found.");
		controller.deleteShift(s);
	}
}
