package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

	@FXML
	private Button fileChooseButton;

	@FXML
	private TextField fileChooseTextField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO (don't really need to do anything here).
	}

	public void showFileChooseDialog(ActionEvent event) {
//		System.out.println("Button Clicked!");
//
//		Date now = new Date();
//
//		DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
//		String dateTimeString = df.format(now);
//		// Show in VIEW
//		myTextField.setText(dateTimeString);

//		FileChooser fileChooser = new FileChooser();
//		fileChooser.setTitle("Open Resource File");
//		fileChooser.showOpenDialog(stage);
	}

}