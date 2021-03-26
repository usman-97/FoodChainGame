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
	
	static ArrayList<String> predators;
	
	public Predator(double x, double y, GraphicsContext gc, double width, double height, String imgPath) {
		super(x, y, gc);
		img = new Image(Predator.class.getResource(imgPath).toExternalForm());
		update(width, height);
	}
	
	public static void addPredators()
	{
		predators = new ArrayList<String>();
		
		predators.add("lion.jpg");
		predators.add("fox.jpg");
	}
	
	public static void removePredator(int index)
	{
		predators.remove(index);
	}
}
