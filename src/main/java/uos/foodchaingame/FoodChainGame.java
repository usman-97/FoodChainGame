/**
 * 
 */
package uos.foodchaingame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FoodChainGame is a main class model for this Food Chain game.
 * MVC pattern is executed in this class model.
 * @author Usman Shabir Kousar
 *
 */
public class FoodChainGame extends Application{
	
	// Root panes for scenes
	Pane root, gameRoot, resultRoot;
	Scene start, gameScene, result;
	Canvas homeScreen, resultCanvas;
	GraphicsContext gc, gc1;
	
	Label gameTitle;
	
	// Scenes for different screens
	Button play, help, home, replay;
	
	StartView startView;
	StartController startController;
	
	// MVC pattern
	FoodChainModel model;
	FoodChainView view;
	FoodChainController controller;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

	/**
	 * Start the application
	 * @param primaryStage - The window object 
	 */
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("FOOD CHAIN - GAME"); // Title of the game on Window
		
		root = new Pane(); // Root pane for start screen
		gameRoot = new Pane(); // Root pane for actual game
		resultRoot = new Pane(); // Root pane for final result
		
		start = new Scene(root, 1000, 800); // Start screen
		gameScene = new Scene(gameRoot, 1000, 800); // Game screen
		result = new Scene(resultRoot, 1000, 800); // Result screen
		
//		startView = new StartView(root);
//		startController = new StartController(startView);
//		
//		startView.setStage(primaryStage);
//		startView.setScene(gameScene);
		
		homeScreen = new Canvas(1000, 800);
		gc = homeScreen.getGraphicsContext2D();
		gc.setFill(Color.GREENYELLOW);
		gc.fillRect(0, 0, homeScreen.getWidth(), homeScreen.getHeight());
		
		// Start application with start screen
		primaryStage.setScene(start);
		
		gameTitle = new Label("FOOD CHAIN");
		gameTitle.setLayoutX(250);
		gameTitle.setLayoutY(50);
		gameTitle.setStyle("-fx-font-size: 80px; -fx-text-fill: #035719; -fx-font-weight: bold;");
		
//		BackgroundImage playBtnImg = new BackgroundImage(new Image(getClass().getResourceAsStream("playbtn.png")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//		Background playBtnBackground = new Background(playBtnImg);
		
		// Play button to start the game
		play = new Button();
		play.setLayoutX(250);
		play.setLayoutY(180);
		play.setPrefWidth(200);
		play.setPrefHeight(200);
		setBackgroundImage("playbtn.png", play);
		// play.setStyle("-fx-background-image: url('lion.jpg')");
		// play.setStyle("-fx-font-size: 30px; -fx-background-color: #035719; -fx-text-fill: #FFFFFF");
		// play.setBackground(playBtnBackground);
		
		root.getChildren().addAll(homeScreen, gameTitle, play);
		
		// MVC pattern implement in this class
		model = new FoodChainModel(); // Model 
		view = new FoodChainView(gameRoot, model); // View for visual presentation
		controller = new FoodChainController(model, view); // Controller to control the logic
		
		view.setStage(primaryStage);
		view.setScene(result);
		
		// Event handler to start the game when play button is clicked
		EventHandler<ActionEvent> startGame = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(gameScene); // Change screen to game screen
				
			}};
		play.setOnAction(startGame);
		
		resultCanvas = new Canvas(1000, 800);
		gc1 = resultCanvas.getGraphicsContext2D();
		gc1.setFill(Color.GREENYELLOW);
		gc1.fillRect(0, 0, resultCanvas.getWidth(), resultCanvas.getHeight());
		resultRoot.getChildren().add(resultCanvas);
		
		home = new Button();
		home.setPrefWidth(250);
		home.setPrefHeight(250);
		setBackgroundImage("homebtn.png", home);
		
		replay = new Button();
		replay.setPrefWidth(250);
		replay.setPrefHeight(250);
		setBackgroundImage("replaybtn.png", replay);
		
		HBox buttonContainer = new HBox(10, home, replay);
		buttonContainer.setLayoutX(200);
		buttonContainer.setLayoutY(300);
		resultRoot.getChildren().add(buttonContainer);
		
		home.setOnAction(event -> {

			resetGame(model, view, controller, primaryStage, gameRoot, result);
			
			primaryStage.setScene(start);
		});
		replay.setOnAction(event -> {
			resetGame(model, view, controller, primaryStage, gameRoot, result);
			primaryStage.setScene(gameScene);
		});
		
		primaryStage.show(); // Show the Window
	}
	
	/**
	 * 
	 * @param model
	 * @param view
	 * @param controller
	 * @param stage
	 * @param root
	 * @param scene
	 */
	private void resetGame(FoodChainModel model, FoodChainView view, FoodChainController controller, Stage stage, Pane root, Scene scene)
	{
		model = new FoodChainModel();
		view = new FoodChainView(root, model);
		controller = new FoodChainController(model, view);
		
		view.setStage(stage);
		view.setScene(scene);
	}
	
	private void setBackgroundImage(String imgName, Button button)
	{
		BackgroundImage btnImg = new BackgroundImage(new Image(getClass().getResource(imgName).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background btnBackground = new Background(btnImg);
		
		button.setBackground(btnBackground);
	}

}
