package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DriverView {
	
	private BorderPane bpane;
	
	public DriverView() {
		bpane = new BorderPane();
		bpane.setCenter(driverInfo());
	}
	
	public BorderPane getDriverView() {
		return this.bpane;
	}
	
	private VBox driverInfo() {
		VBox vbox = new VBox();
		Text text = new Text("T�ytel�ist� informaatiota kuljettajista.\nLis�tietoja: ei ole");
		
		vbox.getChildren().add(text);
		return vbox;
	}
	
	
}
