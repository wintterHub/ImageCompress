package application.extend;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ListViewExtend<T> {

	private ObservableList<T> fileData = FXCollections.observableArrayList();

	public void setItems(ListView<T> fileListView, List<T> fileList) {
		if (fileList != null) {
			for (T f : fileList) {
				if (!fileData.contains(f)) {
					fileData.add(f);
				}
			}
			fileListView.setItems(fileData);
		}
	}

	public void removeItem(ListView<T> fileListView, T item) {
		fileData = fileListView.getItems();
		fileData.remove(item);
		fileListView.setItems(fileData);
	}

}
