package othello.viewcontroller;

import othello.model.Othello;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * Handler for human player game click
 * 
 * @author Haoqiu Wang
 */
public class OthelloButtonHandler implements EventHandler<ActionEvent> {
	Othello othello;
	ScaleTransition scale;

	/**
	 * Initialize OthelloButtonHandler and take the othello
	 * 
	 * @param othello Current game
	 */
	public OthelloButtonHandler(Othello othello) {
		this.othello = othello;
	}

	/**
	 * When some board location clicked, try to make that move
	 * 
	 * @param event Board position been clicked
	 */
	public void handle(ActionEvent event) {
		Button btn = ((Button) (event.getSource()));
		int col = Integer.parseInt(btn.getId().substring(0, 1));
		int row = Integer.parseInt(btn.getId().substring(1, 2));
		if (this.othello.move(row, col)) {
			this.scale = new ScaleTransition(Duration.millis(50), btn);
			scale.setFromX(1);
			scale.setFromY(1);
			scale.setToX(1.2);
			scale.setToY(1.2);
			scale.play();
			this.scale = new ScaleTransition(Duration.millis(50), btn);
			scale.setFromX(1.2);
			scale.setFromY(1.2);
			scale.setToX(1.0);
			scale.setToY(1.0);
			scale.play();
			scale.setDelay(Duration.millis(50));
		}
		return;
	}
}
