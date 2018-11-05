package application.extend;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertExtend {

	public static void show(AlertType alertType, String title, String headerText, String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

	public static void showInformation(String headerText, String contentText) {
		show(AlertType.INFORMATION, "提示", headerText, contentText);
	}

	public static void showError(String headerText, String contentText) {
		show(AlertType.ERROR, "错误", headerText, contentText);
	}

}
