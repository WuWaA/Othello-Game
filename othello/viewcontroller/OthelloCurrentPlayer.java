package othello.viewcontroller;

import util.Observable;
import util.Observer;
import javafx.scene.control.Label;

/**
 * Display current player
 * 
 * @author Haoqiu Wang
 */
public class OthelloCurrentPlayer extends Label implements Observer {
	private char player;

	OthelloCurrentPlayer(char player) {
		this.player = player;
	}

	/**
	 * Update current player
	 */
	@Override
	public void update(Observable o) {
		if (player == 'X')
			this.setText("P1: " + ((OthelloToggleHandler) o).current);
		if (player == 'O')
			this.setText("P2: " + ((OthelloToggleHandler) o).current);
	}
}
