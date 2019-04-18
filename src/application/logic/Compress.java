package application.logic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;

import net.coobird.thumbnailator.Thumbnails;

public class Compress extends Observable implements Runnable {

	private List<File> fileList;
	private float scale;
	private float outputQuality;
	private File outputPath;
	private double progress;

	public Compress(List<File> fileList, float scale, float outputQuality, File outputPath) {
		this.fileList = fileList;
		this.scale = scale;
		this.outputQuality = outputQuality;
		this.outputPath = outputPath;
	}

	@Override
	public void run() {
		try {
			compress(fileList, scale, outputQuality, outputPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片压缩
	 * 
	 * @param fileList      文件列表
	 * @param scale         图片缩放，大于1表示放大，小于1表示缩小
	 * @param outputQuality 输出的图片质量，范围：0.0~1.0，1为最高质量
	 * @param outputPath    图片保存目录，为空时覆盖原文件
	 * @throws Exception
	 */
	private void compress(List<File> fileList, float scale, float outputQuality, File outputPath) throws Exception {
		progress = 0;
		double count = 0;
		double size = fileList.size();
		for (File f : fileList) {
			count++;
			if (outputPath == null) {
				Thumbnails.of(f.getPath()).scale(scale).outputQuality(outputQuality).toFile(f.getPath());
			} else {
				Thumbnails.of(f.getPath()).scale(scale).outputQuality(outputQuality)
						.toFile(outputPath + "/" + f.getName());
			}
			progress = count / size;
			this.setChanged();
			this.notifyObservers();
		}
	}

	public double getProgress() {
		return progress;
	}

}
