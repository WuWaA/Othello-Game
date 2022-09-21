package othello.viewcontroller;

import othello.model.Othello;
import util.Observable;
import util.Observer;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

/**
 * Refresher refresh the game board
 * 
 * @author Haoqiu Wang
 */
public class OthelloRefresher implements Observer {

	Button btn[];

	/**
	 * Received all buttons represented board
	 * 
	 * @param btn Buttons represented board
	 */
	public OthelloRefresher(Button btn[]) {
		this.btn = btn;
	}

	/**
	 * When move made, update the board and make height lights
	 * 
	 * @param o Othello
	 */
	@Override
	public void update(Observable o) {
		Othello othello = (Othello) o;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				btn[i * 8 + j].setEffect(null);
				btn[i * 8 + j].setText(othello.getToken(j, i) == ' ' ? ""
						: othello.getToken(j, i) == 'X' ? new String("\u26AA") : new String("\u26AB"));
			}
		heightlight(othello);
	}

	/**
	 * Height light all available moves
	 * 
	 * @param othello Current game
	 */
	private void heightlight(Othello othello) {
		InnerShadow heightlight = new InnerShadow();
		heightlight.setColor(new Color(0, 1, 1, 0.5));
		heightlight.setRadius(20);
		heightlight.setChoke(0.5);
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				Othello othelloCopy = othello.copy();
				if (othelloCopy.move(row, col))
					btn[col * 8 + row].setEffect(heightlight);
			}
		}
	}
}
