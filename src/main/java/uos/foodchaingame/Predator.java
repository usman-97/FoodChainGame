/**
 * 
 */
package uos.foodchaingame;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author RAJA
 *
 */
public class Predator extends GameObject{
	
	static ArrayList<String> predators; // Stores images for predator objects
	
	/**
	 * Constructor of Predator class model which sets the Prey objects position, height
	 * and width
	 * @param x
	 * @param y
	 * @param gc
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public Predator(double x, double y, GraphicsContext gc, double width, double height, String imgPath) {
		super(x, y, gc);
		img = new Image(Predator.class.getResource(imgPath).toExternalForm());
		update(width, height);
	}
	
	/**
	 * Class member method to add all images to predator list
	 */
	public static void addPredators()
	{
		predators = new ArrayList<String>();
		
		predators.add("lion.jpg");
		predators.add("fox.png");
		predators.add("wolf.png");
	}
	
	/**
	 * Class member method to remove given value from
	 * predator list by given index
	 * @param index - index of object to remove
	 */
	public static void removePredator(int index)
	{
		predators.remove(index);
	}
}
