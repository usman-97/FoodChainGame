/**
 * 
 */
package uos.foodchaingame;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Usman Shabir Kousar
 *
 */
public class Prey extends GameObject{

	public static ArrayList<String> prey; // Stores images for prey objects
	
	/**
	 * Constructor of Prey class model which sets the Prey objects position, height
	 * and width
	 * @param x
	 * @param y
	 * @param gc
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public Prey(double x, double y, GraphicsContext gc, double width, double height, String imgPath) {
		super(x, y, gc);
		img = new Image(Prey.class.getResource(imgPath).toExternalForm());
		update(width, height);
	}
	
	/**
	 * Class member method to add all images to prey list
	 */
	public static void addPrey()
	{
		prey = new ArrayList<String>();
		
		prey.add("deer.png");
		prey.add("giraffe.png");
		prey.add("yak.png");
		prey.add("zebra.png");
	}
	
	/**
	 * Class member method to remove given value from
	 * prey list by given index
	 * @param index - index of object to remove
	 */
	public static void removePrey(int index)
	{
		prey.remove(index);
	}
}
