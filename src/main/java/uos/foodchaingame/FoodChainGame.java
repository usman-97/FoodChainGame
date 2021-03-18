/**
 * 
 */
package uos.foodchaingame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Usman Shabir Kousar
 *
 */
public class FoodChainGame extends Application{
	
	FlowPane root;
	Scene gameScene;
	Pane jungle, selection;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("FOOD CHAIN - GAME"); // Title of the game on Window
		
		root = new FlowPane(); // New Flow pane
		gameScene = new Scene(root, 1000, 800);
		gameScene.getStylesheets().add(getClass().getResource("gameScene.css").toString());
		primaryStage.setScene(gameScene);
		primaryStage.show();
		
		jungle = new Pane();
		jungle.setPrefSize(1000, 600);
		jungle.setId("jungle");
		
		selection = new Pane();
		selection.setPrefSize(1000, 200);
		selection.setId("selection");
		
		// ImageView jungleBackground = new ImageView(); // Image view to set background image
		// Background image for jungle pane
		// jungleBackground.setImage(new Image(getClass().getResourceAsStream("jungleBackground.jpg")));
		
		root.getChildren().addAll(jungle, selection);
		
	}

}
