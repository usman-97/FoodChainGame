
package uos.foodchaingame;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Usman Shabir Kousar
 *
 */
public class FoodChainView {

	private Pane root;
	private FoodChainModel model;
	private GraphicsContext gc, gc1;
	
	private Canvas jungle, selection;
	Circle selectedProducer, selectedPrey, selectedPredator;
	ImageView selectedProducerImg, selectedPreyImg, selectedPredatorImg;
	
	Button chooseProducer, choosePrey, choosePredator;
	private Label producerLbl, preyLbl, predatorLbl; 
	
	// ArrayList<String> producers;
	
	protected GameObject chosenProducer, chosenPrey, chosenPredator;
	
	boolean selectProducer, selectPrey, selectPredator;

	public FoodChainView(Pane root, FoodChainModel model) {
		super();
		this.root = root;
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
		
//		producers = new ArrayList<String>();
//		producers.add("treeProducer.png");
//		producers.add("treeProducer1.png");
	}
	
	public void updateView()
	{
		// Producer producer = new Producer(300, 300, gc, 300, 300);
//		Random selectProducer = new Random();
//		int index = selectProducer.nextInt(producers.size() - 1);
//		
//		Factory factory = new Factory(gc);
//		int position = 100;;
//		for (int i = 0; i < 2; i++)
//		{
//			GameObject producer = factory.create("producer", position, 250, 300, 300, producers.get(index));
//			producers.remove(index);
//			model.addChainMember(producer);
//			
//			selectBtn = model.chain.get(producer);
//			selectBtn.setLayoutX(position + 100);
//			selectBtn.setLayoutY(350);
//			root.getChildren().add(selectBtn);
//			
//			position += 400;
//		}
		selectProducers();
	}
	
	private int generateRandomNum(int range)
	{
		Random number = new Random();
		int index = number.nextInt(range);
		return index;
	}
	
	private void selectMember(GameObject member)
	{
		if (selectProducer)
		{
			chosenProducer = member;
			selectedProducerImg.setImage(member.img);
		}
		
		if (selectPrey)
		{
			chosenPrey = member;
			selectedPreyImg.setImage(member.img);
		}
		
		if (selectPredator)
		{
			chosenPrey = member;
			selectedPredatorImg.setImage(member.img);
		}
	}
	
	private void selectProducers()
	{
//		Random selectProducer = new Random();
//		int index = selectProducer.nextInt(producers.size() - 1);
		Producer.addProducers();
		int index = generateRandomNum(Producer.producers.size() - 1);
		
		Factory factory = new Factory(gc);
		int position = 100;;
		for (int i = 0; i < 2; i++)
		{
			GameObject member = (Producer) factory.create("producer", position, 250, 300, 300, Producer.producers.get(index));
			Producer.producers.remove(index);
			model.addProducer(member);
			
			Button selectBtn = model.producers.entrySet().stream()
					.filter(entry -> member.equals(entry.getValue()))
					.map(Map.Entry::getKey)
					.findFirst().get();
			selectBtn.setLayoutX(position + 100);
			selectBtn.setLayoutY(350);
			root.getChildren().add(selectBtn);
			
			EventHandler<ActionEvent> selectBtnAction = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
//					if (selectProducer)
//					{
//						chosenProducer = member;
//						selectedProducerImg.setImage(member.img);
//					}
//					
//					if (selectPrey)
//					{
//						chosenPrey = member;
//						selectedPreyImg.setImage(member.img);
//					}
//					
//					if (selectPredator)
//					{
//						chosenPrey = member;
//						selectedPredatorImg.setImage(member.img);
//					}
					
					selectMember(member);
				}
			};
			selectBtn.setOnAction(selectBtnAction);
			
			position += 400;
		}
	}
}
