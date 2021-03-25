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

	public static ArrayList<String> producers;
	
	public Producer(double x, double y, GraphicsContext gc, double width, double height, String imgPath) {
		super(x, y, gc);
//		producers = new ArrayList<String>();
//		producers.add("treeProducer.png");
//		producers.add("treeProducer1.png");
		
//		Random selectProducer = new Random();
//		int index = selectProducer.nextInt(producers.size());
		// System.out.println(chosenProducer);
		img = new Image(Producer.class.getResource(imgPath).toExternalForm());
//		producers.remove(index);
		
		update(width, height);
	}
	
	public static void addProducers()
	{
		producers = new ArrayList<String>();
		
		producers.add("treeProducer.png");
		producers.add("treeProducer1.png");
	}
	
	public static void removeProducer(int index)
	{
		producers.remove(index);
	}
}