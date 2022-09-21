package othello.model;

/**
 * Determine whether the first player or second player has the advantage when
 * both are playing a Random Strategy.
 */
public class OthelloControllerRandomVSRandom extends OthelloController {
	public OthelloControllerRandomVSRandom() {
		super();
		this.player1 = new PlayerRandom(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerRandom(this.othello, OthelloBoard.P2);
	}
	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 * @param args
	 */
	public static void main(String[] args) {
		int p1wins=0, p2wins=0, numGames=10000;
		for(int i=0;i<numGames;i++) {
			OthelloControllerRandomVSRandom oc = new OthelloControllerRandomVSRandom();
			oc.play();
			if(oc.othello.getWinner()==OthelloBoard.P1)p1wins++;
			if(oc.othello.getWinner()==OthelloBoard.P2)p2wins++;
		}
		System.out.println("Probability P1 wins="+(float)p1wins/numGames);
		System.out.println("Probability P2 wins="+(float)p2wins/numGames);
	}
}
