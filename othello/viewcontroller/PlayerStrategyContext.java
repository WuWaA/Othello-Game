package othello.viewcontroller;

import util.Observable;
import util.Observer;

/**
 * Context of PlayerStrategyContext
 * 
 * @author Haoqiu Wang
 */
public class PlayerStrategyContext implements Observer {
	PlayerStrategy strategy;

	/**
	 * Set strategy
	 * 
	 * @param strategy Strategy
	 */
	public PlayerStrategyContext(PlayerStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Run specific strategy
	 */
	public void executeStrategy() {
		strategy.move();
	}

	/**
	 * When othello changes, run strategy
	 * 
	 * @param o Othello
	 */
	@Override
	public void update(Observable o) {
		executeStrategy();
	}
}
