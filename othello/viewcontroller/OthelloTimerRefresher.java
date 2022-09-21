package othello.viewcontroller;

import othello.model.Othello;
import util.Observable;
import javafx.animation.AnimationTimer;

/**
 * Refresher of OthelloTimer
 * 
 * @author Haoqiu Wang
 */
public class OthelloTimerRefresher extends Observable {

	AnimationTimer timer;

	/**
	 * Constructor of OthelloTimerRefresher
	 * 
	 * @param othello Current othello game
	 */
	OthelloTimerRefresher(Othello othello) {
		timer = new AnimationTimer() {
			private long time = System.currentTimeMillis();

			/**
			 * This inner method called every second
			 * 
			 * @param now Current time
			 */
			@Override
			public void handle(long now) {
				if (System.currentTimeMillis() - time >= 1000) {
					time = System.currentTimeMillis();
					notifyObservers();
				}
			}
		};
		timer.start();
	}
}
