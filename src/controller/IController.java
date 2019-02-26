package controller;

import java.util.List;

import model.DrivingShift;
import model.IDriver;
import model.IDrivingShift;

public interface IController {

	public void assignShift(IDriver driver, IDrivingShift shift);
	public void createDriver(IDriver driver);
	public void createDrivingShift(IDrivingShift shift);
	public void updateDriver(IDriver driver);
	public void updateDrivingShift(IDrivingShift shift);
	public List<IDriver> readAllDrivers();
	public List<IDrivingShift> readAllDrivingShifts();
}
