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
import javafx.scene.image.ImageView;
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
	Pane root, gameRoot, resultRoot, helpPane;
	Scene start, gameScene, result, helpScreen;
	Canvas homeScreen, resultCanvas, helpCanvas;
	GraphicsContext gc, gc1, gcHelp;
	
	Label gameTitle;
	Button play, help, home, replay, back;
	
	StartView startView;
	StartController startController;
	
	// MVC pattern
	FoodChainModel model;
	FoodChainView view;
	FoodChainController controller;
	
	Label resultText; // game result
	ImageView helpImg; // Guidance for player

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
		helpPane = new Pane();
		
		start = new Scene(root, 1000, 800); // Start screen
		gameScene = new Scene(gameRoot, 1000, 800); // Game screen
		result = new Scene(resultRoot, 1000, 800); // Result screen
		helpScreen = new Scene(helpPane, 1000, 800);
		
//		startView = new StartView(root);
//		startController = new StartController(startView);
//		
//		startView.setStage(primaryStage);
//		startView.setScene(gameScene);
		
		// Canvas for Home screen
		homeScreen = new Canvas(1000, 800);
		gc = homeScreen.getGraphicsContext2D();
		gc.setFill(Color.GREENYELLOW);
		gc.fillRect(0, 0, homeScreen.getWidth(), homeScreen.getHeight());
		
		// Start application with start screen
		primaryStage.setScene(start);
		
		// Title of the game
		gameTitle = new Label("FOOD CHAIN");
		gameTitle.setLayoutX(250);
		gameTitle.setLayoutY(50);
		gameTitle.setStyle("-fx-font-size: 80px; -fx-text-fill: #035719; -fx-font-weight: bold;");
		
		
		// Play button to start the game
		play = new Button();
		play.setPrefWidth(200);
		play.setPrefHeight(200);
		setBackgroundImage("playbtn.png", play);
		
		// Help button to navigate to help screen
		help = new Button();
		help.setPrefWidth(200);
		help.setPrefHeight(200);
		setBackgroundImage("helpbtn.png", help);
		
		// Navigate player to help screen when help button is clicked
		help.setOnAction(event -> primaryStage.setScene(helpScreen));
		
		// Home screen buttons container
		HBox homeButtons = new HBox(10, play, help);
		homeButtons.setLayoutX(250);
		homeButtons.setLayoutY(180);
		
		root.getChildren().addAll(homeScreen, gameTitle, homeButtons);
		
		// MVC pattern implement in this class
//		model = new FoodChainModel(); // Model 
//		view = new FoodChainView(gameRoot, model); // View for visual presentation
//		controller = new FoodChainController(model, view); // Controller to control the logic
//		
//		view.setStage(primaryStage);
//		view.setScene(result);
		
		// Event handler to start the game when play button is clicked
		EventHandler<ActionEvent> startGame = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// resetGame method call
				// Starts new game for player
				resetGame(model, view, controller, primaryStage, gameRoot, result);
				primaryStage.setScene(gameScene); // Change screen to game screen
				
			}};
		play.setOnAction(startGame); // startGame event is triggered when play button is clicked 
		
		// Help screen Canvas
		helpCanvas = new Canvas(1000, 800);
		gcHelp = helpCanvas.getGraphicsContext2D();
		gcHelp.setFill(Color.GREENYELLOW);
		gcHelp.fillRect(0, 0, helpCanvas.getWidth(), helpCanvas.getHeight());
		
		// Back button to navigate player back to home screen
		back = new Button();
		back.setLayoutX(20);
		back.setLayoutY(20);
		back.setPrefWidth(65);
		back.setPrefHeight(65);
		setBackgroundImage("backbtn.png", back);
		
		// When back button is clicked then it navigates player back to home screen
		back.setOnAction(event -> primaryStage.setScene(start));
		
		// Player guidance for the game
		helpImg = new ImageView();
		helpImg.setLayoutX(100);
		helpImg.setLayoutY(100);
		helpImg.setFitWidth(700);
		helpImg.setFitHeight(600);
		helpImg.setImage(new Image(getClass().getResource("help.jpg").toExternalForm()));
		
		helpPane.getChildren().addAll(helpCanvas, back, helpImg);
		
		// Result screen canvas
		resultCanvas = new Canvas(1000, 800);
		gc1 = resultCanvas.getGraphicsContext2D();
		gc1.setFill(Color.GREENYELLOW);
		gc1.fillRect(0, 0, resultCanvas.getWidth(), resultCanvas.getHeight());
		resultRoot.getChildren().add(resultCanvas);
		
		// Result text
		resultText = new Label();
		resultText.setLayoutX(280);
		resultText.setLayoutY(150);
		resultText.setStyle("-fx-padding: 10px; -fx-background-color: #000000; -fx-text-fill: #ffffff; -fx-font-size: 80px; -fx-font-weight: bold;");
		resultRoot.getChildren().add(resultText);
		
		// Home button to navigate to home screen
		home = new Button();
		home.setPrefWidth(250);
		home.setPrefHeight(250);
		setBackgroundImage("homebtn.png", home);
		
		// Replay button to replay the game straightway 
		replay = new Button();
		replay.setPrefWidth(250);
		replay.setPrefHeight(250);
		setBackgroundImage("replaybtn.png", replay);
		
		// Result screen buttons container
		HBox buttonContainer = new HBox(10, home, replay);
		buttonContainer.setLayoutX(200);
		buttonContainer.setLayoutY(300);
		resultRoot.getChildren().add(buttonContainer);
		
		// When home button is clicked then it will trigger an event which
		// will navigate user back to home screen
		home.setOnAction(event ->primaryStage.setScene(start));
		
		// When replay button is clicked then it triggers event will start
		// a new game for player
		replay.setOnAction(event -> {
			// Start new game
			resetGame(model, view, controller, primaryStage, gameRoot, result);
			primaryStage.setScene(gameScene); // Navigate to game screen
		});
		
		primaryStage.show(); // Show the Window
	}
	
	/**
	 * Start new game for player with default settings
	 * @param model
	 * @param view
	 * @param controller
	 * @param stage
	 * @param root
	 * @param scene
	 */
	private void resetGame(FoodChainModel model, FoodChainView view, FoodChainController controller, Stage stage, Pane root, Scene scene)
	{
		// MVC Pattern
		model = new FoodChainModel(); // New instance of FoodChainModel
		view = new FoodChainView(root, model); // New instance of FoodChainView
		controller = new FoodChainController(model, view); // new instance of FoodChainController
		
		view.setStage(stage); // set stage to use
		view.setScene(scene); // set scene to navigate
		view.setResult(resultText); // set text to edit
	}
	
	/**
	 * Set background image for a button
	 * @param imgName
	 * @param button
	 */
	private void setBackgroundImage(String imgName, Button button)
	{
		// Background image instance
		BackgroundImage btnImg = new BackgroundImage(new Image(getClass().getResource(imgName).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background btnBackground = new Background(btnImg); // Background instance to set background image
		
		button.setBackground(btnBackground); // Button to set background image
	}

}
