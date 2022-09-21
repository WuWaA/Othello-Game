package othello.viewcontroller;

import othello.model.Othello;
import util.Observable;
import util.Observer;
import javafx.scene.control.Label;

/**
 * Display the token number of specific player
 * 
 * @author Haoqiu Wang
 */
public class OthelloTokenCount extends Label implements Observer {

	private char player;

	/**
	 * Constructor of OthelloTokenCount
	 * 
	 * @param player X (P1) or O (P2)
	 */
	OthelloTokenCount(char player) {
		this.player = player;
	}

	/**
	 * Refresh current token count
	 * 
	 * @param o Othello
	 */
	@Override
	public void update(Observable o) {
		Othello othello = (Othello) o;
		String token = player == 'X' ? "\u26AA" : "\u26AB";
		this.setText("Number of " + token + ": " + othello.getCount(player));
	}

}
