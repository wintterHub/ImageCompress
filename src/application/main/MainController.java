package application.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.extend.ListViewExtend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController implements Initializable {

	/* 全局的Stage对象 */
	private static Stage primaryStage;

	/* 当前ListView选中项 */
	private File currentFile;

	/* 文件选择器返回的文件列表 */
	private List<File> fileList;

	/* ListView的一些扩展方法类 */
	private static ListViewExtend<File> listViewExtend = new ListViewExtend<File>();

	/* 保存路径 */
	private File savePath;

	@FXML
	private ListView<File> fileListView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		primaryStage = new Main().getPrimaryStage();
		fileListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			currentFile = newValue;
			System.out.println(newValue);
		});
	}

	/**
	 * 打开文件选择器
	 * 
	 * @param event
	 */
	public void showFileChooseDialog(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择文件");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("所有文件", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("BMP", "*.bmp"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));
		fileList = fileChooser.showOpenMultipleDialog(primaryStage);
		listViewExtend.setItems(fileListView, fileList);
	}

	/**
	 * 打开文件保存选择器
	 * 
	 * @param event
	 */
	public void showFileSaveDialog(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("保存");
		savePath = directoryChooser.showDialog(primaryStage);
		if (!savePath.exists()) {
			savePath.mkdirs();
		}
		System.out.println(savePath);
	}

	/**
	 * 打开文件夹选择器
	 * 
	 * @param event
	 */
	public void showDirectoryChooseDialog(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("选择文件夹");
		File file = directoryChooser.showDialog(primaryStage);
		fileList = MainBL.getFilelist(file);
		listViewExtend.setItems(fileListView, fileList);
		System.out.println(file);
	}

	/**
	 * 开始压缩
	 * 
	 * @param event
	 */
	public void startCompress(ActionEvent event) {
		if (fileList != null) {
			try {
				MainBL.compress(fileList, 1f, 0.3f, savePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 清空列表
	 * 
	 * @param event
	 */
	public void clearList(ActionEvent event) {
		fileListView.setItems(null);
		fileList = null;
	}

	/**
	 * 移除选中
	 * 
	 * @param event
	 */
	public void removeSelectedItem(ActionEvent event) {
		listViewExtend.removeItem(fileListView, currentFile);
		System.out.println(currentFile);
	}

}