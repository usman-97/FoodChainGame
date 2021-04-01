/**
 * 
 */
package uos.foodchaingame;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author RAJA
 *
 */
public class StartController implements EventHandler {

	StartView view;
	Stage stage;

	public StartController(StartView view) {
		super();
		this.view = view;
		this.stage = stage;
		this.
		
		view.play.setOnAction(this);
	}

	@Override
	public void handle(Event event) {
		if (event.getSource() == view.play)
		{
			view.getStage().setScene(view.getScene());
		}
		
	}
	
	
}
