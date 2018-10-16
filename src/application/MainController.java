package application;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController implements Initializable {

	private static Stage primaryStage;

	@FXML
	private ListView<File> fileListView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		primaryStage = new Main().getPrimaryStage();
	}

	private List<File> fileList;

	public void showFileChooseDialog(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择文件");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("所有文件", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("BMP", "*.bmp"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));
		List<File> fileList = fileChooser.showOpenMultipleDialog(primaryStage);
		if (fileList != null) {
			this.fileList = fileList;
			ObservableList<File> fileData = FXCollections.observableArrayList();
			fileData.addAll(fileList);
			fileListView.setItems(fileData);
		}
	}

	public void showFileSaveDialog(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("保存");
		File showOpenDialog = directoryChooser.showDialog(primaryStage);
		System.out.println(showOpenDialog);
	}

	public void showDirectoryChooseDialog(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("选择文件夹");
		File showOpenDialog = directoryChooser.showDialog(primaryStage);
		System.out.println(showOpenDialog);
	}

	public void startCompress(ActionEvent event) {
		if (fileList != null) {
			MainBL.compress(fileList, 1f, 0.3f, null);
		}
	}

	public void clearList(ActionEvent event) {
		fileListView.setItems(null);
		fileList = null;
	}

}