package othello.viewcontroller;

import othello.model.Move;
import othello.model.Othello;
import util.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

/**
 * Handler for player switch
 * 
 * @author Haoqiu Wang
 */
public class OthelloToggleHandler extends Observable implements ChangeListener<Toggle> {

	Othello othello;
	Move move;
	char player;
	String current;
	String previous;
	PlayerStrategyContext greedy;
	PlayerStrategyContext random;

	/**
	 * Create handler for specific player
	 * 
	 * @param othello Current game
	 * @param player  The player this handler for
	 */
	public OthelloToggleHandler(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
		greedy = new PlayerStrategyContext(new PlayerGreedyStrategy(othello, player));
		random = new PlayerStrategyContext(new PlayerRandomStrategy(othello, player));
	}

	/**
	 * When player switched, this method called and player changed
	 * 
	 * @param arg1 old value
	 * @param arg2 new value
	 */
	@Override
	public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
		current = ((RadioButton) arg2).getText();
		previous = ((RadioButton) arg1).getText();
		if (current == "Greedy") {
			if (previous == "Random")
				othello.detach(random);
			othello.attach(greedy);
			if (othello.getWhosTurn() == player)
				greedy.executeStrategy();
		}
		if (current == "Random") {
			if (previous == "Greedy")
				othello.detach(greedy);
			othello.attach(random);
			if (othello.getWhosTurn() == player)
				random.executeStrategy();
		}
		if (current == "Human") {
			othello.detach(greedy);
			othello.detach(random);
		}
		this.notifyObservers();
	}
}
