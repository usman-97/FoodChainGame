
package uos.foodchaingame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author RAJA
 *
 */
public class FoodChainView {

	Pane root;
	FoodChainModel model;
	GraphicsContext gc, gc1;
	
	Canvas jungle, selection;
	Circle selectedProducer, selectedPrey, selectedPredator;
	
	Button chooseProducer, choosePrey, choosePredator;
	Label producerLbl, preyLbl, predatorLbl; 
	
	public FoodChainView(Pane root, FoodChainModel model) {
		super();
		this.root = root;
//		this.gc = gc;
//		this.gc1 = gc1;
		this.model = model;
		
		jungle = new Canvas(1000, 600);
		gc = jungle.getGraphicsContext2D();
		jungle.setId("jungle");
		gc.drawImage(new Image(getClass().getResourceAsStream("jungleBackground.jpg")), 0, 0, jungle.getWidth(), jungle.getHeight());
		
		selection = new Canvas(1000, 200);
		selection.setLayoutY(600);
		gc1 = selection.getGraphicsContext2D();
		gc1.setFill(Color.SANDYBROWN);
		gc1.fillRect(0, 0, selection.getWidth(), selection.getHeight());
		
		root.getChildren().addAll(jungle, selection);
		
		selectedProducer = new Circle(200, 700, 80);
		selectedProducer.setFill(Color.SADDLEBROWN);
		
		selectedPrey = new Circle(400, 700, 80);
		selectedPrey.setFill(Color.SADDLEBROWN);
		
		selectedPredator = new Circle(600, 700, 80);
		selectedPredator.setFill(Color.SADDLEBROWN);
		
		HBox selectionSlots = new HBox(50, selectedProducer, selectedPrey, selectedPredator);
		selectionSlots.setLayoutX(100);
		selectionSlots.setLayoutY(630);
		
		chooseProducer = new Button("Choose");
		choosePrey = new Button("Choose");
		choosePredator = new Button("Choose");
		
		HBox chooseBtn = new HBox(155, chooseProducer, choosePrey, choosePredator);
		chooseBtn.setLayoutX(150);
		chooseBtn.setLayoutY(750);
		
		producerLbl = new Label("Producer");
		preyLbl = new Label("Prey");
		predatorLbl = new Label("Predator");
		
		HBox labels = new HBox(155, producerLbl, preyLbl, predatorLbl);
		labels.setStyle("-fx-font-size: 20px;");
		labels.setLayoutX(150);
		labels.setLayoutY(605);
		
		root.getChildren().addAll(selectionSlots, chooseBtn, labels);
	}
	
	public void updateView()
	{
		// Producer producer = new Producer(300, 300, gc, 300, 300);
		
		Factory factory = new Factory(gc);
		int position = 100;
		for (int i = 0; i < 2; i++)
		{
			GameObject producer = factory.create("producer", position, 250, 300, 300);
			model.addChainMember(producer);
			
			Button selectBtn = model.chain.get(producer);
			selectBtn.setLayoutX(position + 100);
			selectBtn.setLayoutY(350);
			root.getChildren().add(selectBtn);
			
			position += 400;
		}
	}
}
