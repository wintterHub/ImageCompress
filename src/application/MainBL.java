package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

public class MainBL {

	/**
	 * 图片压缩
	 * 
	 * @param fileList      文件列表
	 * @param scale         图片缩放，大于1表示放大，小于1表示缩小
	 * @param outputQuality 输出的图片质量，范围：0.0~1.0，1为最高质量
	 * @param outputPath    图片保存目录，为空时覆盖原文件
	 */
	public static void compress(List<File> fileList, float scale, float outputQuality, String outputPath) {
		try {
			for (File f : fileList) {
				if (outputPath.isEmpty()) {
					Thumbnails.of(f.getPath()).scale(scale).outputQuality(outputQuality).toFile(f.getPath());
				} else {
					Thumbnails.of(f.getPath()).scale(scale).outputQuality(outputQuality)
							.toFile(outputPath + f.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
