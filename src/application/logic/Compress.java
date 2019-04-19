package application.logic;

import java.io.File;
import java.util.List;
import java.util.Observable;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 定义为抽象类，是为了让启动这个线程的类使用匿名内部类的方式实现run()方法，从而可以在捕获到线程异常后，方便的更新UI
 */
public abstract class Compress extends Observable implements Runnable {

	private double progress;

	/**
	 * 图片压缩
	 * 
	 * @param fileList      文件列表
	 * @param scale         图片缩放，大于1表示放大，小于1表示缩小
	 * @param outputQuality 输出的图片质量，范围：0.0~1.0，1为最高质量
	 * @param outputPath    图片保存目录，为空时覆盖原文件
	 * @throws Exception
	 */
	public void compress(List<File> fileList, float scale, float outputQuality, File outputPath) throws Exception {
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
