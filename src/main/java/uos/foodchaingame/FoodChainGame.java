/**
 * 
 */
package uos.foodchaingame;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author Usman Shabir Kousar
 *
 */
public class FoodChainGame extends Application{
	
	// FlowPane root;
	Pane root;
	Scene gameScene;
	// Pane jungle, selection;
	Canvas jungle, selection;
	GraphicsContext gc, gc1;
	// ArrayList<GameObject> producers = new ArrayList<GameObject>();
	
	FoodChainModel model;
	FoodChainView view;
	FoodChainController controller;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("FOOD CHAIN - GAME"); // Title of the game on Window
		
		root = new Pane(); // Root pane
		gameScene = new Scene(root, 1000, 800);
		// gameScene.getStylesheets().add(getClass().getResource("gameScene.css").toString());
		
//		jungle = new Canvas(1000, 600);
//		gc = jungle.getGraphicsContext2D();
//		jungle.setId("jungle");
//		gc.drawImage(new Image(getClass().getResourceAsStream("jungleBackground.jpg")), 0, 0, jungle.getWidth(), jungle.getHeight());
//		
//		selection = new Canvas(1000, 200);
//		selection.setLayoutY(600);
//		gc1 = selection.getGraphicsContext2D();
//		gc1.setFill(Color.SANDYBROWN);
//		gc1.fillRect(0, 0, selection.getWidth(), selection.getHeight());
		
		primaryStage.setScene(gameScene);
		primaryStage.show();
		
//		root.getChildren().addAll(jungle, selection);
		
		model = new FoodChainModel();
		view = new FoodChainView(root, model);
		controller = new FoodChainController(model, view);
		
		// Producer tree = new Producer(100, 300, gc, 300, 300);
//		Factory factory = new Factory(gc);
//		int position = 100;
//		for (int i = 0; i < 2; i++)
//		{
//			producers.add(factory.create("producer", position, 250, 300, 300));
//			position += 400;
//		}
	}

}
