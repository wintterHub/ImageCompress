package application.logic;

import java.util.Observable;
import java.util.Observer;

public abstract class CompressObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Compress) {
			Compress compress = (Compress) o;
			double progress = compress.getProgress();
			onProgressChange(progress);
		}
	}

	abstract public void onProgressChange(double progress);

}
