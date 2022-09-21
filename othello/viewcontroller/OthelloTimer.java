package othello.viewcontroller;

import othello.model.Othello;
import util.Observable;
import util.Observer;
import javafx.scene.control.Label;

/**
 * Display game time for specific player
 * 
 * @author Haoqiu Wang
 */
public class OthelloTimer extends Label implements Observer {

	private int sec;
	private int min;
	private long total;
	private long timer;
	private char player;
	private boolean flag;
	private Othello othello;

	/**
	 * Create timer for specific player
	 * 
	 * @param player Player
	 */
	OthelloTimer(char player, Othello othello, long total) {
		this.player = player;
		this.othello = othello;
		this.total = total;
		this.flag = true;
		this.timer = System.currentTimeMillis();
	}

	/**
	 * Start or stop the timer
	 * 
	 * @param o Othello or OthelloTimerRefresher
	 */
	@Override
	public void update(Observable o) {
		if (othello.getWhosTurn() == player && flag == false)
			start();
		if (othello.getWhosTurn() != player && flag == true)
			stop();
		if (flag)
			refresh();
	}

	/**
	 * Start this timer
	 */
	private void start() {
		flag = true;
		timer = System.currentTimeMillis();
	}

	/**
	 * Stop this timer and update total time
	 */
	private void stop() {
		flag = false;
		total += System.currentTimeMillis() - timer;
	}

	/**
	 * Updated every second and displayed to scene
	 */
	private void refresh() {
		int current = (int) ((System.currentTimeMillis() - timer + total) / 1000);
		min = current / 60;
		sec = current % 60;
		this.setText((player == 'X' ? "P1: " : "P2: ") + String.format("%02d", min) + ":" + String.format("%02d", sec));
	}

	/**
	 * Get total time
	 * 
	 * @return Total time
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * String presentation of timer
	 */
	public String toString() {
		min = (int) total / 1000 / 60;
		sec = (int) total / 1000 % 60;
		return (player == 'X' ? "P1: " : "P2: ") + String.format("%02d", min) + ":" + String.format("%02d", sec);
	}
}
