package othello.viewcontroller;

import java.util.LinkedList;
import othello.model.Othello;
import util.Observable;
import util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Handler of undo button
 * 
 * @author Haoqiu Wang
 */
public class OthelloUndoHandler implements Observer, EventHandler<ActionEvent> {

	private static LinkedList<Othello> history = new LinkedList<Othello>();
	Stage stage;
	Button btn;
	OthelloTimer t1;
	OthelloTimer t2;

	/**
	 * Constructor of OthelloUndoHandler
	 * 
	 * @param othello Current othello
	 * @param stage   Current stage
	 * @param btn     Undo button
	 * @param t1      Time spend of P1
	 * @param t2      Time spend of P2
	 */
	OthelloUndoHandler(Othello othello, Stage stage, Button btn, OthelloTimer t1, OthelloTimer t2) {
		this.stage = stage;
		this.btn = btn;
		this.t1 = t1;
		this.t2 = t2;

	}

	/**
	 * Make the undo action
	 * 
	 * @param event Undo button pressed event
	 */
	@Override
	public void handle(ActionEvent event) {
		OthelloApplication app = new OthelloApplication();
		history.removeLast();
		app.setOthello(history.removeLast());
		app.setTimes(t1.getTotal(), t2.getTotal());
		stage.close();
		try {
			app.start(stage);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * When new move made, add it to history
	 * 
	 * @param o Othello
	 */
	@Override
	public void update(Observable o) {
		history.addLast(((Othello) o).copy());
		if (btn != null) {
			if (history.size() > 1)
				btn.setDisable(false);
			else
				btn.setDisable(true);
		}
	}
}
