package othello.viewcontroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Handler of restart button
 * 
 * @author Haoqiu Wang
 */
public class OthelloRestartHandler implements EventHandler<ActionEvent> {

	Stage stage;

	/**
	 * Constructor of OthelloRestartHandler
	 * 
	 * @param stage Current stage
	 */
	OthelloRestartHandler(Stage stage) {
		this.stage = stage;
	}

	/**
	 * When restart button clicked, restart the application
	 * 
	 * @param event Restart pressed event
	 */
	@Override
	public void handle(ActionEvent event) {
		stage.close();
		OthelloApplication app = new OthelloApplication();
		try {
			app.start(stage);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
