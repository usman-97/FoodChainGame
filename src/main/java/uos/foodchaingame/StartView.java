package uos.foodchaingame;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartView {

	private Stage stage;
	private Scene scene;
	private Pane root;
	private Canvas homeScreen;
	private GraphicsContext gc;
	private Label gameTitle;
	protected Button play;
	
	public StartView(Pane root) 
	{
		this.root = root;
		
		homeScreen = new Canvas(1000, 800);
		gc = homeScreen.getGraphicsContext2D();
		gc.setFill(Color.GREENYELLOW);
		gc.fillRect(0, 0, homeScreen.getWidth(), homeScreen.getHeight());
		
		gameTitle = new Label("FOOD CHAIN");
		gameTitle.setLayoutX(300);
		gameTitle.setLayoutY(50);
		
		// Play button to start the game
		play = new Button("Play");
		play.setLayoutX(500);
		play.setLayoutY(500);
		play.setStyle("-fx-font-size: 30px; -fx-background-color: blue;");
		
		root.getChildren().addAll(homeScreen, gameTitle, play);
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
}
