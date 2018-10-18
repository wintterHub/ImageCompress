package application.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

public class MainBL {

	private static List<File> filelist = new ArrayList<File>();

	/**
	 * 图片压缩
	 * 
	 * @param fileList      文件列表
	 * @param scale         图片缩放，大于1表示放大，小于1表示缩小
	 * @param outputQuality 输出的图片质量，范围：0.0~1.0，1为最高质量
	 * @param outputPath    图片保存目录，为空时覆盖原文件
	 * @throws IOException
	 */
	public static void compress(List<File> fileList, float scale, float outputQuality, File outputPath)
			throws IOException {
		for (File f : fileList) {
			if (outputPath == null) {
				Thumbnails.of(f.getPath()).scale(scale).outputQuality(outputQuality).toFile(f.getPath());
			} else {
				Thumbnails.of(f.getPath()).scale(scale).outputQuality(outputQuality)
						.toFile(outputPath + "/" + f.getName());
			}
		}
	}

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
