package othello.viewcontroller;

import othello.model.Othello;
import util.Observable;
import util.Observer;
import javafx.scene.control.Label;

/**
 * Display next movable token
 * 
 * @author Haoqiu Wang
 */
public class OthelloNextToken extends Label implements Observer {

	/**
	 * Update next token display
	 * 
	 * @param o Othello
	 */
	@Override
	public void update(Observable o) {
		Othello othello = (Othello) o;
		char turn = othello.getWhosTurn();
		String token = turn == ' ' ? "" : turn == 'X' ? "\u26AA" : "\u26AB";
		this.setText("Next Move: " + token);
	}
}
