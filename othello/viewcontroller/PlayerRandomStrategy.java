package othello.viewcontroller;

import othello.model.Move;
import othello.model.Othello;
import othello.model.PlayerRandom;

/**
 * PlayerRandomStrategy, one strategy of PlayerStrategy
 * 
 * @author Haoqiu Wang
 */
public class PlayerRandomStrategy implements PlayerStrategy {

	Othello othello;
	char player;

	/**
	 * Constructor of PlayerRandomStrategy
	 * 
	 * @param othello Othello
	 * @param player  X (P1) or O (P2)
	 */
	PlayerRandomStrategy(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
	}

	/**
	 * Implement specific move for PlayerRandom
	 */
	@Override
	public void move() {
		PlayerRandom random = new PlayerRandom(othello, player);
		Move move = random.getMove();
		othello.move(move.getRow(), move.getCol());
	}
}
