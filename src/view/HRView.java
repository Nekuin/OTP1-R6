package view;

import java.util.Collection;

import controller.IController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Driver;

/**
 * View module for the HR managers
 * @author Nekuin
 *
 */
public class HRView {

	private IController controller;
	private BorderPane bpane;
	private ObservableList<Driver> drivers;

	private ListView<Driver> lv;

	/**
	 * Constructor which takes a controller as parameter
	 * @param controller instance of Controller
	 */
	public HRView(IController controller) {
		this.controller = controller;
		this.bpane = new BorderPane();
		this.bpane.setLeft(driverInfo());
	}

	/**
	 * A module which displays info about the Drivers
	 * @return GridPane
	 */
	public GridPane driverInfo() {
		GridPane grid = new GridPane();
		Text title = new Text("Drivers:");
		
		VBox driverBox = new VBox();
		driverBox.setPadding(new Insets(10));
		
		Text driverInfo = new Text("Driver Info: ");
		Text name = new Text("");
		Text driversLicense = new Text("");;
		Text driverID = new Text("");;
		
		
		Button deleteDriver = new Button("Remove");
		deleteDriver.setOnAction(e -> {
			this.controller.deleteDriver(lv.getSelectionModel().getSelectedItem());
		});
		
		
		
		driverBox.getChildren().addAll(driverInfo, name, driverID, driversLicense, deleteDriver);

		drivers = FXCollections.observableArrayList();
		lv = new ListView<>();
		lv.setItems(drivers);

		lv.setOnMouseClicked(e -> {
			Driver clicked = lv.getSelectionModel().getSelectedItem();
			name.setText(clicked.getName());
			driversLicense.setText(clicked.getDriversLicense());
			driverID.setText(Integer.toString(clicked.getEmployeeID()));

		});
		
		
		
		grid.add(title, 0, 0);
		grid.add(lv, 0, 1);
		grid.add(driverBox, 1, 1);

		return grid;
	}
	
	
	/**
	 * Get the whole HRView module
	 * @return BorderPane
	 */
	public BorderPane getHRView() {
		return this.bpane;
	}

	/**
	 * Update the driver list with a Collection of Drivers
	 * @param drivers collection of Drivers
	 */
	public void updateDrivers(Collection<Driver> drivers) {
		this.drivers.clear();
		this.drivers.addAll(drivers);
	}

}
