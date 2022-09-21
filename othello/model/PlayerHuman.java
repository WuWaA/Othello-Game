package othello.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * PlayerHuman is a type of Player
 */
public class PlayerHuman extends Player {
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
	private static final String IO_ERROR_MESSAGE = "I/O Error";
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Create a PlayerHuman through Player
	 * @param othello
	 * @param player
	 */
	public PlayerHuman(Othello othello, char player) {
		super(othello, player);
	}

	/**
	 * Get move of user
	 * @return Move of user
	 */
	public Move getMove() {
		int row = getMove("row: ");
		int col = getMove("col: ");
		return new Move(row, col);
	}

	/**
	 * Get row or col
	 * @param message Message to print out, row or col
	 * @return Inputed row or col
	 */
	private int getMove(String message) {
		int move, lower = 0, upper = 7;
		while (true) {
			try {
				System.out.print(message);
				String line = PlayerHuman.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move && move <= upper) {
					return move;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			} catch (IOException e) {
				System.out.println(IO_ERROR_MESSAGE);
				break;
			} catch (NumberFormatException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
			}
		}
		return -1;
	}
}
