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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

	/* 覆盖原文件按钮是否选中 */
	private Boolean isOverWriteCheckBoxSelected = false;

	private int clickCount = 0;

	@FXML
	private ListView<File> fileListView;

	@FXML
	private TextField savePathTextField;

	@FXML
	private CheckBox overWriteCheckBox;

	@FXML
	private Button savePathChooseButton;

	@FXML
	private Label messageTipLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		primaryStage = new Main().getPrimaryStage();
		fileListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			currentFile = newValue;
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
		if (savePath != null) {
			messageTipLabel.setText("");
			savePathTextField.setText(savePath.getPath());
		}
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
	}

	/**
	 * 覆盖文件复选框事件
	 * 
	 * @param event
	 */
	public void overWriteAction(ActionEvent event) {
		if (overWriteCheckBox.isSelected()) {
			messageTipLabel.setText("");
			isOverWriteCheckBoxSelected = true;
			savePathTextField.disableProperty().set(true);
			savePathChooseButton.disableProperty().set(true);
		} else {
			isOverWriteCheckBoxSelected = false;
			savePathTextField.disableProperty().set(false);
			savePathChooseButton.disableProperty().set(false);
		}
	}

	/**
	 * 开始压缩
	 * 
	 * @param event
	 */
	public void startCompress(ActionEvent event) {
		clickCount++;
		if (fileList != null && fileList.size() > 0) {
			// 判断覆盖或者另存为
			if (isOverWriteCheckBoxSelected) {
				if (clickCount == 1) {
					messageTipLabel.setText("覆盖文件模式请谨慎操作，需再次点击确认");
				} else if (clickCount == 2) {
					clickCount = 0;
					messageTipLabel.setText("");
					try {
						MainBL.compress(fileList, 1f, 0.3f, null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				if (savePath == null) {
					messageTipLabel.setText("请选择目标文件夹");
				} else {
					if (!savePath.exists()) {
						savePath.mkdirs();
					}
					try {
						MainBL.compress(fileList, 1f, 0.3f, savePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			messageTipLabel.setText("请添加文件");
		}
	}

	/**
	 * 清空列表
	 * 
	 * @param event
	 */
	public void clearList(ActionEvent event) {
		listViewExtend.clear();
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
	}

}