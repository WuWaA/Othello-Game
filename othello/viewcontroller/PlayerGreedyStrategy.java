package othello.viewcontroller;

import othello.model.Move;
import othello.model.Othello;
import othello.model.PlayerGreedy;

/**
 * PlayerGreedyStrategy, one strategy of PlayerStrategy
 * 
 * @author Haoqiu Wang
 */
public class PlayerGreedyStrategy implements PlayerStrategy {

	Othello othello;
	char player;

	/**
	 * Constructor of PlayerGreedyStrategy
	 * 
	 * @param othello Othello
	 * @param player  X (P1) or O (P2)
	 */
	PlayerGreedyStrategy(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
	}

	/**
	 * Implement specific move for PlayerGreedy
	 */
	@Override
	public void move() {
		PlayerGreedy greedy = new PlayerGreedy(othello, player);
		Move move = greedy.getMove();
		othello.move(move.getRow(), move.getCol());
	}
}
