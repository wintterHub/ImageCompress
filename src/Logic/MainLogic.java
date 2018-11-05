package Logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainLogic {

	private static List<File> filelist = new ArrayList<File>();

	/**
	 * 递归遍历文件夹中所有文件
	 * 
	 * @param directory 需要遍历的文件夹
	 * @return List<File>
	 */
	public static List<File> getFilelist(File file) {
		if (file.isFile()) {
			filelist.add(file);
		} else if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File listFile : listFiles) {
				getFilelist(listFile);
			}
		}
		return filelist;
	}
	
	

}
