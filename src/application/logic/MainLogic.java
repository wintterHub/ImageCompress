package application.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

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

	/**
	 * 判断是否为图片
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isImage(Object file) {
		if (file instanceof File) {
			ImageInputStream iis = null;
			try {
				iis = ImageIO.createImageInputStream(file);
			} catch (IOException e) {
				return false;
			}

			if (iis == null) {
				return false;
			}

			Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
			if (!readers.hasNext()) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 检查是否有非图片文件
	 * 
	 * @param fileList
	 * @return
	 */
	public static boolean hasNonImage(List<File> fileList) {
		for (File f : fileList) {
			if (!isImage(f)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 过滤非图片文件
	 * 
	 * @param fileList
	 */
	public static List<File> nonImageFilte(List<File> fileList) {
		List<File> newlist = new ArrayList<>();
		for (File f : fileList) {
			if (isImage(f)) {
				newlist.add(f);
			}
		}
		return newlist;
	}

}
