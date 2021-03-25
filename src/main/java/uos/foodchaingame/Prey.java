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

	public static ArrayList<String> prey;
	
	public Prey(double x, double y, GraphicsContext gc, double width, double height, String imgPath) {
		super(x, y, gc);
		img = new Image(Prey.class.getResource(imgPath).toExternalForm());
		update(width, height);
	}
	
	public static void addPrey()
	{
		prey.add("deer.png");
		prey.add("giraffe.png");
		prey.add("yak.png");
	}
	
	public static void removePrey(int index)
	{
		prey.remove(index);
	}
}
