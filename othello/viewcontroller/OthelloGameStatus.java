package othello.viewcontroller;

import othello.model.Othello;
import util.Observable;
import util.Observer;
import javafx.scene.control.Label;

/**
 * Display current game status
 * 
 * @author Haoqiu Wang
 */
public class OthelloGameStatus extends Label implements Observer {

	/**
	 * Update current game status
	 * 
	 * @param o Othello
	 */
	@Override
	public void update(Observable o) {
		Othello othello = (Othello) o;
		if (othello.getWinner() != ' ') {
			String token = othello.getWinner() == 'X' ? "\u26AA" : "\u26AB";
			this.setText("Status: " + token + " Wins");
		}
	}
}
