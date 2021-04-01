/**
 * 
 */
package uos.foodchaingame;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Producer Class Model and it is a subclass of 
 * GameObject class
 * @author Usman Shabir Kousar
 *
 */
public class Producer extends GameObject{

	public static ArrayList<String> producers; // Stores images for producers objects
	
	/**
	 * Constructor of Producer class model which sets the Producer objects position, height
	 * and width
	 * @param x
	 * @param y
	 * @param gc
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public Producer(double x, double y, GraphicsContext gc, double width, double height, String imgPath) {
		super(x, y, gc);
		img = new Image(Producer.class.getResource(imgPath).toExternalForm());
		
		update(width, height);
	}
	
	/**
	 * Class member method to add all images to producers list
	 */
	public static void addProducers()
	{
		producers = new ArrayList<String>();
		
		producers.add("treeProducer.png");
		producers.add("grass.png");
		producers.add("plant.png");
	}
	
	/**
	 * Class member method to remove given value from
	 * producers list by given index
	 * @param index - index of object to remove
	 */
	public static void removeProducer(int index)
	{
		producers.remove(index);
	}
}