/**
 * 
 */
package uos.foodchaingame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
		
		
		root.getChildren().addAll(selectedProducer);
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
