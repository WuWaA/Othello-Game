package othello.viewcontroller;

import othello.model.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Othello Game GUI
 * 
 * @author Haoqiu Wang
 */
public class OthelloApplication extends Application {
	// REMEMBER: To run this in the lab put
	// --module-path "/usr/share/openjfx/lib" --add-modules
	// javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.

	private Othello othello = new Othello();
	private Long time_1 = 0L;
	private Long time_2 = 0L;

	/**
	 * Start the stage (program window)
	 * 
	 * @param stage Stage to be created
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// Create and hook up the Model, View and the Controller
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(5));

		// MODEL
		Othello othello = getOthello();
		Button hint = new Button("Hint");
		Button undo = new Button("Undo");
		Button restart = new Button("Restart");
		Label player_1 = new Label("P1: \u26AA");
		Label player_2 = new Label("P2: \u26AB");
		Button btn[] = new Button[64];
		OthelloNextToken next = new OthelloNextToken();
		OthelloGameStatus status = new OthelloGameStatus();
		OthelloRefresher refresher = new OthelloRefresher(btn);
		OthelloTokenCount count_1 = new OthelloTokenCount(OthelloBoard.P1);
		OthelloTokenCount count_2 = new OthelloTokenCount(OthelloBoard.P2);
		OthelloCurrentPlayer cplayer_1 = new OthelloCurrentPlayer(OthelloBoard.P1);
		OthelloCurrentPlayer cplayer_2 = new OthelloCurrentPlayer(OthelloBoard.P2);
		OthelloTimer timer_1 = new OthelloTimer(OthelloBoard.P1, othello, time_1);
		OthelloTimer timer_2 = new OthelloTimer(OthelloBoard.P2, othello, time_2);
		OthelloTimerRefresher trefresher = new OthelloTimerRefresher(othello);
		ToggleGroup player_1_mode = new ToggleGroup();
		ToggleGroup player_2_mode = new ToggleGroup();
		RadioButton human_1 = new RadioButton("Human");
		RadioButton human_2 = new RadioButton("Human");
		RadioButton greedy_1 = new RadioButton("Greedy");
		RadioButton greedy_2 = new RadioButton("Greedy");
		RadioButton random_1 = new RadioButton("Random");
		RadioButton random_2 = new RadioButton("Random");

		// CONTROLLER->MODEL Hook Up
		OthelloButtonHandler bhandler = new OthelloButtonHandler(othello);
		OthelloToggleHandler thandler_1 = new OthelloToggleHandler(othello, OthelloBoard.P1);
		OthelloToggleHandler thandler_2 = new OthelloToggleHandler(othello, OthelloBoard.P2);
		OthelloHintHandler hhandler = new OthelloHintHandler(othello);
		OthelloUndoHandler uhandler = new OthelloUndoHandler(othello, stage, undo, timer_1, timer_2);
		OthelloRestartHandler rhandler = new OthelloRestartHandler(stage);

		// VIEW->CONTROLLER Hook Up

		// Buttons
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				btn[row * 8 + col] = new Button("");
				btn[row * 8 + col].setId(String.valueOf(row) + String.valueOf(col));
				btn[row * 8 + col].setOnAction(bhandler);
				btn[row * 8 + col].setMinSize(30, 30);
				grid.add(btn[row * 8 + col], row + 1, col + 1);
			}
		}

		// Labels
		for (int i = 0; i < 8; i++) {
			Label leb;
			leb = new Label(String.valueOf(i));
			leb.setMinSize(30, 20);
			leb.setAlignment(Pos.CENTER);
			grid.add(leb, i + 1, 0);
			leb = new Label(String.valueOf(i));
			leb.setMinSize(20, 30);
			leb.setAlignment(Pos.CENTER);
			grid.add(leb, 0, i + 1);
			leb = new Label(String.valueOf(i));
			leb.setMinSize(30, 20);
			leb.setAlignment(Pos.CENTER);
			grid.add(leb, i + 1, 9);
			leb = new Label(String.valueOf(i));
			leb.setMinSize(20, 30);
			leb.setAlignment(Pos.CENTER);
			grid.add(leb, 9, i + 1);
		}

		// Others
		next.setText("Next Move: \u26AA");
		status.setText("Status: In Progress");
		count_1.setText("Number of \u26AA: 2");
		count_2.setText("Number of \u26AB: 2");
		cplayer_1.setText("P1: Human");
		cplayer_2.setText("P2: Human");
		timer_1.setText(timer_1.toString());
		timer_2.setText(timer_2.toString());
		hint.setPrefWidth(68);
		undo.setPrefWidth(68);
		restart.setPrefWidth(68);
		hint.setOnAction(hhandler);
		undo.setOnAction(uhandler);
		restart.setOnAction(rhandler);
		human_1.setToggleGroup(player_1_mode);
		greedy_1.setToggleGroup(player_1_mode);
		random_1.setToggleGroup(player_1_mode);
		human_1.setSelected(true);
		player_1_mode.selectedToggleProperty().addListener(thandler_1);
		human_2.setToggleGroup(player_2_mode);
		greedy_2.setToggleGroup(player_2_mode);
		random_2.setToggleGroup(player_2_mode);
		human_2.setSelected(true);
		player_2_mode.selectedToggleProperty().addListener(thandler_2);
		grid.add(next, 0, 10, 5, 1);
		grid.add(status, 5, 10, 5, 1);
		grid.add(count_1, 0, 11, 5, 1);
		grid.add(count_2, 5, 11, 5, 1);
		grid.add(cplayer_1, 0, 12, 5, 1);
		grid.add(cplayer_2, 5, 12, 5, 1);
		grid.add(timer_1, 10, 8);
		grid.add(timer_2, 10, 9);
		grid.add(hint, 10, 10);
		grid.add(undo, 10, 11);
		grid.add(restart, 10, 12);
		grid.add(player_1, 10, 0);
		grid.add(human_1, 10, 1);
		grid.add(greedy_1, 10, 2);
		grid.add(random_1, 10, 3);
		grid.add(player_2, 10, 4);
		grid.add(human_2, 10, 5);
		grid.add(greedy_2, 10, 6);
		grid.add(random_2, 10, 7);

		// MODEL->VIEW Hook Up
		othello.attach(next);
		othello.attach(status);
		othello.attach(count_1);
		othello.attach(count_2);
		othello.attach(timer_1);
		othello.attach(timer_2);
		othello.attach(refresher);
		othello.attach(uhandler);
		thandler_1.attach(cplayer_1);
		thandler_2.attach(cplayer_2);
		trefresher.attach(timer_1);
		trefresher.attach(timer_2);
		othello.notifyObservers();

		// SCENE
		Scene scene = new Scene(grid);
		stage.setTitle("Othello");
		stage.setScene(scene);
		stage.setResizable(false);

		// LAUNCH THE GUI
		stage.show();
	}

	/**
	 * Specific othello game status, used to create the game
	 * 
	 * @return Specific othello game
	 */
	private Othello getOthello() {
		return othello;
	}

	/**
	 * Set specific othello game status which want to be created
	 * 
	 * @param othello Specific othello to be created
	 */
	public void setOthello(Othello othello) {
		this.othello = othello;
	}

	/**
	 * Set the time already passed before undo action
	 * 
	 * @param time_1
	 * @param time_2
	 */
	public void setTimes(long time_1, long time_2) {
		this.time_1 = time_1;
		this.time_2 = time_2;
	}

	/**
	 * Entrance of this othello game
	 * 
	 * @param args Fundamental arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
