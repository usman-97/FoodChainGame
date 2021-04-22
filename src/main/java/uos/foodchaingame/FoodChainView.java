
package uos.foodchaingame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Usman Shabir Kousar
 *
 */
public class FoodChainView {

	private Stage stage;
	private Scene scene;
	private Pane root; // Root pane
	private FoodChainModel model; // instance of FoodChainModel
	private GraphicsContext gc, gc1;
	
	// Two Canvas
	private Canvas jungle, selection;
	// Panels
	protected Circle selectedProducer, selectedPrey, selectedPredator;
	// Images of selected object on panels
	protected ImageView selectedProducerImg, selectedPreyImg, selectedPredatorImg;
	
	// Button to select panels
	protected Button chooseProducer, choosePrey, choosePredator, check;
	// Panel headings
	private Label producerLbl, preyLbl, predatorLbl;
	
	// Timer
	protected Label minutes, seconds;
	protected Timer countDownTime;
	
	// Chosen food chain members
	protected GameObject chosenProducer, chosenPrey, chosenPredator;
	
	boolean selectProducer, selectPrey, selectPredator;
	private PanelStrategyIF strategy;
	
	// Game end condition variables
	private boolean isWin, isLose;
	
	private Label objectiveHeading, objective;
	
	private int score;
	protected Label scoreLbl;
	
	protected Label errorLbl;
	private Label result;

	/**
	 * Constructor of Food Chain View
	 * @param root - Root pane
	 * @param model - model of Food Chain
	 */
	public FoodChainView(Pane root, FoodChainModel model) {
		super();
		this.root = root;
		this.model = model;
		countDownTime = Timer.getInstance(); // Singleton and the only instance of Timer class
		score = 0;
		
		// Canvas to display all food chain members
		jungle = new Canvas(1000, 600);
		gc = jungle.getGraphicsContext2D();
		// Set background image for jungle canvas
		gc.drawImage(new Image(getClass().getResourceAsStream("jungleBackground.jpg")), 0, 0, jungle.getWidth(), jungle.getHeight());
		
		// Canvas to allow user to select food chain members
		selection = new Canvas(1000, 200);
		selection.setLayoutY(600);
		gc1 = selection.getGraphicsContext2D();
		// Canvas background colour
		gc1.setFill(Color.SANDYBROWN);
		gc1.fillRect(0, 0, selection.getWidth(), selection.getHeight());
		
		// Add jungle and selection canvas to root pane
		root.getChildren().addAll(jungle, selection);
		
		// Selected Producer panel
		selectedProducer = new Circle(200, 700, 80);
		selectedProducer.setFill(Color.SADDLEBROWN);
		
		// Selected Prey panel
		selectedPrey = new Circle(400, 700, 80);
		selectedPrey.setFill(Color.SADDLEBROWN);
		
		// Selected Predator panel
		selectedPredator = new Circle(600, 700, 80);
		selectedPredator.setFill(Color.SADDLEBROWN);
		
		// Container for Panels
		HBox selectionSlots = new HBox(50, selectedProducer, selectedPrey, selectedPredator);
		selectionSlots.setLayoutX(100);
		selectionSlots.setLayoutY(630);
		
		// Buttons to choose food chain members for panels
		chooseProducer = new Button("Choose");
		chooseProducer.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		
		choosePrey = new Button("Choose");
		choosePrey.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		
		choosePredator = new Button("Choose");
		choosePredator.setStyle("-fx-background-color: #000000; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
		
		// Button container
		HBox chooseBtn = new HBox(110, chooseProducer, choosePrey, choosePredator);
		chooseBtn.setLayoutX(135);
		chooseBtn.setLayoutY(745);
		chooseBtn.setStyle("-fx-font-size: 20px;");
		
		// Panel headings
		producerLbl = new Label("Producer");
		preyLbl = new Label("Prey");
		predatorLbl = new Label("Predator");
		
		// Panel heading container
		HBox labels = new HBox(155, producerLbl, preyLbl, predatorLbl);
		labels.setStyle("-fx-font-size: 20px;");
		labels.setLayoutX(150);
		labels.setLayoutY(605);
		
		// Images of selected food chain members
		// ImageView display to display the images of 
		// selected food chain member in panels
		selectedProducerImg = new ImageView();
		selectedProducerImg.setFitWidth(80);
		selectedProducerImg.setFitHeight(80);
		
		selectedPreyImg = new ImageView();
		selectedPreyImg.setFitWidth(80);
		selectedPreyImg.setFitHeight(80);
		
		selectedPredatorImg = new ImageView();
		selectedPredatorImg.setFitWidth(80);
		selectedPredatorImg.setFitHeight(80);
		
		HBox selectedImages = new HBox(130, selectedProducerImg, selectedPreyImg, selectedPredatorImg);
		selectedImages.setLayoutX(150);
		selectedImages.setLayoutY(650);
		
		root.getChildren().addAll(selectionSlots, chooseBtn, labels, selectedImages);
		
		// Check if use has selected right members on right panels
		check = new Button("Check");
		check.setLayoutX(900);
		check.setLayoutY(650);
		check.setStyle("-fx-background-color: #ffb940; -fx-font-size: 20px; -fx-border-color: black; -fx-font-weight: bold; -fx-border-width: 3px;");
		root.getChildren().add(check);
		
		errorLbl = new Label();
		errorLbl.setLayoutX(670);
		errorLbl.setLayoutY(750);
		errorLbl.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
		root.getChildren().add(errorLbl);
		
		// Display timer seconds
		seconds = new Label();
		seconds.setStyle("-fx-text-fill: white;");
		
		// Display timer minutes
		minutes = new Label();
		minutes.setStyle("-fx-text-fill: white;");
		
		HBox timeContainer = new HBox(2, minutes, seconds);
		timeContainer.setLayoutX(10);
		timeContainer.setLayoutY(10);
		timeContainer.setStyle("-fx-padding: 10px;-fx-background-color: black; -fx-font-size: 30px;");
		root.getChildren().add(timeContainer);
		
		// Objective or goal of the game
		objectiveHeading = new Label("Objective");
		objectiveHeading.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30px; -fx-font-weight: bold;");
		
		objective = new Label("* Create as many Food Chain as \npossible in given time.");
		objective.setStyle("-fx-text-fill: #FFFFFF;-fx-font-size: 20px;");
		
		VBox gameObjective = new VBox(5, objectiveHeading, objective);
		gameObjective.setLayoutX(650);
		gameObjective.setLayoutY(10);
		gameObjective.setStyle("-fx-padding: 10px; -fx-background-color: black;");
		root.getChildren().add(gameObjective);
		
		// Display player score
		scoreLbl = new Label("Score: " + score);
		scoreLbl.setLayoutX(200);
		scoreLbl.setLayoutY(10);
		scoreLbl.setStyle("-fx-padding: 10px; -fx-background-color: black; -fx-text-fill: white; -fx-font-size: 20px");
		root.getChildren().add(scoreLbl);
	}
	
	/**
	 * Update the view for user
	 * Add all food chain members and starts the 
	 * count down timer
	 */
	public void updateView()
	{
		addMembers("producer", 200, 480, 100, 100, 400); // Add Producers
		addMembers("prey", 10, 350, 150, 150, 250); // Add Prey
		addMembers("predator", 500, 350, 150, 150, 200); // Add Predators
		updateTimer(60); // Start the count down timer
	}
	
	/**
	 * Generate random number in the range of given number
	 * @param range
	 * @return index
	 */
	private int generateRandomNum(int range)
	{
		Random number = new Random();
		int index = number.nextInt(range);
		return index;
	}
	
	/**
	 * Allow user to choose or select food chain members
	 * Sets the image of member to chosen panel
	 * @param member
	 */
	private void selectMember(GameObject member)
	{
		if (selectProducer)
		{
			chosenProducer = member;
			selectedProducerImg.setImage(member.img);
			// System.out.println(chosenProducer);
		}
		
		if (selectPrey)
		{
			chosenPrey = member;
			selectedPreyImg.setImage(member.img);
			// System.out.println(chosenPrey);
		}
		
		if (selectPredator)
		{
			chosenPredator = member;
			selectedPredatorImg.setImage(member.img);
			// System.out.println(chosenPredator);
		}
	}
	
	/**
	 * Adds food chain member by given type of the member
	 * @param chainMember
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param distance
	 */
	private void addMembers(String chainMember, double x, double y, double width, double height, double distance)
	{
		Factory factory = new Factory(gc); // Factory object to create food chain members
		int range = 0; // numbers of members to add 
		ArrayList<String> foodChainMembers = new ArrayList<String>(); // temporary list to store member type images
		Map<Button, GameObject> selectedMembers = new HashMap<Button, GameObject>(); // temporary map to store members
		
		// Give values to variables by given member type
		if (chainMember.equalsIgnoreCase("producer"))
		{
			Producer.addProducers();
			foodChainMembers = Producer.producers;
			selectedMembers = model.producers;
			range = 3;
		}
		else if (chainMember.equalsIgnoreCase("prey"))
		{
			Prey.addPrey();
			foodChainMembers = Prey.prey;
			selectedMembers = model.prey;
			range = 3;
		}
		else
		{
			if (chainMember.equalsIgnoreCase("predator"))
			{
				Predator.addPredators();
				foodChainMembers = Predator.predators;
				selectedMembers = model.predator;
				range = 3;
			}
		}

		for (int i = 1; i < range; i++)
		{
			// index to select image from member image list
			int index = generateRandomNum(foodChainMembers.size());
			
			// Create food chain member using factory object
			GameObject member = factory.create(chainMember, x, y, width, height, foodChainMembers.get(index));
			foodChainMembers.remove(index); // remove the image from the list once it was selected for a member
			model.addMember(member, chainMember); // Add member to selected member map in food chain model
			
			// Get the key value from model member map
			Button selectBtn = selectedMembers.entrySet().stream()
					.filter(entry -> member.equals(entry.getValue()))
					.map(Map.Entry::getKey)
					.findFirst().get();
			selectBtn.setLayoutX(x + 50);
			selectBtn.setLayoutY(y + 50);
			selectBtn.setStyle("-fx-background-color: #02008a; -fx-text-fill: #ffffff; -fx-font-size: 12px; -fx-font-weight: bold;");
			root.getChildren().add(selectBtn);
			
			// Event handle to select the member and add it to user chosen panel
			EventHandler<ActionEvent> selectBtnAction = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					selectMember(member);
					// MediaPlayer mediaPlayer = newMediaPlayer("buttonClick1.mp3");
					// mediaPlayer.play();
				}
			};
			selectBtn.setOnAction(selectBtnAction);
			
			x += distance; // increase the distance between new created members and other members
		}
	}
	
	/**
	 * Selects strategy for panels
	 * @param strategy
	 */
	public void setPanelStrategy(PanelStrategyIF strategy)
	{
		this.strategy = strategy;
	}
	
	/**
	 * Executes the selected strategy
	 * @param panel
	 */
	public void executePanelStrategy(Circle panel)
	{
		this.strategy.execute(panel);
	}
	
	/**
	 * Display the count down timer seconds
	 */
	private void setSeconds()
	{
		if (countDownTime.getSeconds() < 10)
			this.seconds.setText("0" + countDownTime.getSeconds());
		else
			this.seconds.setText("" + countDownTime.getSeconds());
	}
	
	/**
	 * Display the count down timer minutes
	 */
	private void setMinutes()
	{
		if (countDownTime.getMinutes() < 10)
			minutes.setText("0" + countDownTime.getMinutes() + " : ");
		else
			minutes.setText("" + countDownTime.getMinutes() + " : ");
	}
	
	/**
	 * Sets and update the count down timer
	 * @param sec
	 */
	private void updateTimer(int sec)
	{
		countDownTime.setTimer(sec); // Set the count down timer
		
		Timeline timeline = new Timeline(); // Timeline instance
		timeline.setCycleCount(Timeline.INDEFINITE); // set count to indefinite
		// stop the timer if time is not set
		if (timeline != null)
			timeline.stop();
		
		// Keyframe instance to keep the track of minutes and seconds
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Timer class method call
				// reduce the time by one second
				countDownTime.decrementTimer(); 
				setMinutes(); // Display updated minutes
				setSeconds(); // Display updated seconds
				
				// New media instance to play countdown timer sound
				// MediaPlayer mediaPlayer = newMediaPlayer("countdowntimer.mp3"); // newMedia internal method call
				// String music = getClass().getResource("countdowntimer.mp3").toString();
				
				// Media sound = new Media(getClass().getResource("countdowntimer.mp3").toString());
				// MediaPlayer mediaPlayer = new MediaPlayer(sound);
				// AudioClip audio = new AudioClip(getClass().getResourceAsStream("countdowntimer.mp3").toString());
				
				if (countDownTime.getMinutes() == 0 && countDownTime.getSeconds() <= 10)
				{
					// mediaPlayer.play(); // play the sound when 10 seconds are left
					// audio.play();
					
					// Change the countdown timer colour when 10 seconds are left
					if (countDownTime.getSeconds() % 2 == 0)
					{
						minutes.setStyle("-fx-text-fill: red;");
						seconds.setStyle("-fx-text-fill: red;");
					}
					else
					{
						minutes.setStyle("-fx-text-fill: white;");
						seconds.setStyle("-fx-text-fill: white;");
					}
						
				}
				
				// If count down time is over then stop the timer or if 
				// player have guessed all possible food chains
				if ((countDownTime.getMinutes() <= 0 && countDownTime.getSeconds() <= 0) || model.totalFoodChains() == model.foodChains.size())
				{
					timeline.stop(); // stop the timer
					// mediaPlayer.pause();
					// mediaPlayer.stop();
					
					// When given time ends then cause 2 seconds delay
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setResultText(); // Calculate the final percentage
					stage.setScene(scene); // change the scene to result screen
				}
			}});
		timeline.getKeyFrames().add(frame);
		timeline.playFromStart();
	}
	
	/**
	 * Caculate the final percentage of the player
	 */
	private void setResultText()
	{
		int score = model.calculateTotalScore(this.score); // Player final score percentage
		result.setText(score + "% ");
		
		// MediaPlayer mediaPlayer = null;
		
		// Set the final percentage on result screen with a message
		// according to player's achieved percentage
		if (score < 40)
		{
			result.setStyle("-fx-padding: 10px; -fx-background-color: red; -fx-text-fill: #ffffff; -fx-font-size: 80px; -fx-font-weight: bold;");
			result.setText(result.getText() + ":(");
			// mediaPlayer = newMediaPlayer("fail.mp3");
		}
		
		if (score >= 40 && score < 65)
		{
			result.setStyle("-fx-padding: 10px; -fx-background-color: orange; -fx-font-size: 60px; -fx-font-weight: bold;");
			result.setText(result.getText() + " bit faster next time");
			// mediaPlayer = newMediaPlayer("success2.mp3");
		}
		
		if (score >= 65 && score < 90)
		{
			result.setStyle("-fx-padding: 10px; -fx-background-color: #87ff69; -fx-font-size: 80px; -fx-font-weight: bold;");
			result.setText(result.getText() + " Well Done");
			// mediaPlayer = newMediaPlayer("success1.mp3");
		}
		
		if (score >= 90)
		{
			result.setStyle("-fx-padding: 10px; -fx-background-color: green; -fx-text-fill: #ffffff; -fx-font-size: 80px; -fx-font-weight: bold;");
			result.setText(result.getText() + ":) LEGEND");
			// mediaPlayer = newMediaPlayer("success1.mp3");;
		}
		
		// mediaPlayer.play();
	}
	
	/**
	 * Creates new instance of MediaPlayer
	 * @param filename
	 * @return MediaPlayer
	 */
//	public MediaPlayer newMediaPlayer(String filename)
//	{
//		String music = getClass().getResource(filename).toExternalForm();
//		
//		Media sound = new Media(music);
//		MediaPlayer mediaPlayer = new MediaPlayer(sound);
//		return mediaPlayer;
//	}
	
	// Getters and Setters methods for Fields or class variables 
	
	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWinner) {
		this.isWin = isWinner;
	}

	public boolean isLose() {
		return isLose;
	}

	public void setLose(boolean isLoser) {
		this.isLose = isLoser;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int chances) {
		this.score = chances;
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

	public Label getResult() {
		return result;
	}

	public void setResult(Label result) {
		this.result = result;
	}
	
	
}
