package othello.viewcontroller;

import othello.model.Move;
import othello.model.Othello;
import othello.model.PlayerGreedy;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class OthelloHintHandler implements EventHandler<ActionEvent> {

	Othello othello;

	/**
	 * Constructor of OthelloHintHandler
	 * 
	 * @param othello Ohtello
	 */
	public OthelloHintHandler(Othello othello) {
		this.othello = othello;
	}

	/**
	 * When hint button clicked, height light a hint position
	 * 
	 * @param event Hint button clicked event
	 */
	@Override
	public void handle(ActionEvent event) {
		PlayerGreedy greedy = new PlayerGreedy(othello, othello.getWhosTurn());
		Move move = greedy.getMove();
		int row = move.getRow();
		int col = move.getCol();
		Button btn = new Button();
		InnerShadow heightlight = new InnerShadow();
		heightlight.setColor(new Color(1, 1, 0, 0.5));
		heightlight.setRadius(20);
		heightlight.setChoke(0.5);
		ObservableList<Node> all = ((GridPane) ((Button) event.getSource()).getParent()).getChildren();
		for (int i = 0; i < 100; i++)
			if (all.get(i).getClass() == btn.getClass())
				if (all.get(i).getId().equalsIgnoreCase((String.valueOf(col) + String.valueOf(row))))
					((Button) all.get(i)).setEffect(heightlight);
		;
	}
}
