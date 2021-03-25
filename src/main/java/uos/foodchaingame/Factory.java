/**
 * 
 */
package uos.foodchaingame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Usman Shabir Kousar
 *
 */
public class Factory implements FactoryIF{

	GraphicsContext gc;
	
	@Override
	/**
	 * Create object from given class
	 * @param discrim - name of the class or object
	 * @param x
	 * @param y
	 */
	public GameObject create(String discrim, double x, double y, double width, double height, String imgPath) {
		if (discrim.equalsIgnoreCase("producer"))
		{
			return new Producer(x, y, gc, width, height, imgPath);
		}
		else if (discrim.equalsIgnoreCase("prey"))
			return null; // instantiate Prey
		else if (discrim.equalsIgnoreCase("predator"))
			return null; // instantiate Predator
		return null;
	}

	/**
	 * Constructor of Factory class
	 * @param gc
	 */
	public Factory(GraphicsContext gc) {
		super();
		this.gc = gc;
	}
}
