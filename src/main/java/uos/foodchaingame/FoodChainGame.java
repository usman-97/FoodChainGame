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
		
		primaryStage.setScene(gameScene);
		primaryStage.show();
		
		model = new FoodChainModel();
		view = new FoodChainView(root, model);
		controller = new FoodChainController(model, view);
	}

}
