/**
 * 
 */
package uos.foodchaingame;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author RAJA
 *
 */
public class Producer extends GameObject{

	static ArrayList<String> producers;
	
	public Producer(double x, double y, GraphicsContext gc, double width, double height) {
		super(x, y, gc);
		producers = new ArrayList<String>();
		producers.add("treeProducer.png");
		producers.add("treeProducer1.png");
		
		Random selectProducer = new Random();
		int index = selectProducer.nextInt(producers.size());
		// System.out.println(chosenProducer);
		img = new Image(Producer.class.getResource(producers.get(index)).toExternalForm());
		producers.remove(index);
		
		update(width, height);
	}
	
//	public void createProducer(int index)
//	{
//		img = new Image(Producer.class.getResource(producers.get(index)).toExternalForm());
//		update(100, 200);
//	}
	
//	public int getProducersSize()
//	{
//		return producers.size();
//	}
}