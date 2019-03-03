package view;

import java.util.Collection;
import java.util.stream.Collectors;

import application.Main;
import controller.IController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Driver;
import model.DrivingShift;

public class DriverReserveView {
	
	private BorderPane bpane;
	private ObservableList<DrivingShift> shifts;
	private ListView<DrivingShift> shiftListView;
	private Text[] shiftDetailTexts;
	
	private IController controller;
	
	public DriverReserveView(IController controller, NavBar navBar) {
		this.controller = controller;
		this.bpane = new BorderPane();
		this.setNavBar(navBar);
		setup();
	}
	
	public DriverReserveView(IController controller) {
		this.controller = controller;
		this.bpane = new BorderPane();
		setup();
	}
	
	private void setup() {
		this.shiftDetailTexts = new Text[4];
		SplitPane split = new SplitPane();
		split.getItems().add(shiftPanel());
		VBox vbox = new VBox();
		vbox.getChildren().add(shiftDetailsPanel());
		
		Button reserveButton = new Button("Reserve");
		reserveButton.setOnAction(e -> {
			Driver driver = this.controller.readDriver(Main.LOGGED_IN_ID);
			this.controller.assignShift(driver, shiftListView.getSelectionModel().getSelectedItem());
			this.updateShiftList(this.controller.readGoodDrivingShifts(driver));
		});
		
		vbox.getChildren().add(reserveButton);
		split.getItems().add(vbox);
		
		this.bpane.setCenter(split);
	}
	
	private VBox shiftPanel() {
		VBox vbox = new VBox();
		shiftListView = new ListView<>();
		
		shifts = FXCollections.observableArrayList();
		shiftListView.setItems(shifts);
		
		shiftListView.setOnMouseClicked(e -> {
			DrivingShift selected = shiftListView.getSelectionModel().getSelectedItem();
			//this.updateShiftDetailText(new String[] {selected.getClient().toString(), "" + selected.getShiftID(), "" + selected.getTotalCargoWeight(), "time"});
			this.updateShiftDetailText(selected.getClient().toString(), "" + selected.getShiftID(), "" + selected.getTotalCargoWeight(), "time");
		});
		
		vbox.getChildren().add(shiftListView);
		return vbox;
	}
	
	private GridPane shiftDetailsPanel() {
		GridPane grid = new GridPane();
		Text title = new Text("Shift details: ");
		grid.add(title, 0, 0);
		
		Text client = new Text("Client: ");
		Text shift = new Text("Shift id: ");
		Text cargo = new Text("Cargo: ");
		Text time = new Text("Time: ");
		
		for(int i = 0; i < shiftDetailTexts.length; i++) {
			this.shiftDetailTexts[i] = new Text("");
		}
		
		grid.add(client, 0, 1);
		grid.add(shiftDetailTexts[0], 1, 1);
		grid.add(shift, 0, 2);
		grid.add(shiftDetailTexts[1], 1, 2);
		grid.add(cargo, 0, 3);
		grid.add(shiftDetailTexts[2], 1, 3);
		grid.add(time, 0, 4);
		grid.add(shiftDetailTexts[3], 1, 4);
		
		return grid;
	}
	
	public void updateShiftDetailText(String... strings) {
		for(int i = 0; i < shiftDetailTexts.length; i++) {
			shiftDetailTexts[i].setText(strings[i]);
		}
	}
	
	
	public void updateShiftList(Collection<DrivingShift> shifts) {
		this.shifts.clear();
		this.shifts.addAll(shifts.stream().filter(shift -> !shift.getShiftTaken()).collect(Collectors.toList()));
	}
	
	public void setNavBar(NavBar navBar) {
		this.bpane.setTop(navBar.getNavBar());
	}
	
	public BorderPane getDriverReserveView() {
		return this.bpane;
	}
	
}