
package uos.foodchaingame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Game object model class presents the objects in 
 * the game
 * @author Usman Shabir Kousar
 *
 */
public class GameObject {

	protected Image img; // Image of the object
	protected double x, y; // Coordinates for object
	// double y;
	protected GraphicsContext gc; // graphics context to draw the object
	
	/**
	 * Constructor for GameObject class
	 * @param x - x coordinate for the object
	 * @param y - y coordinate for the object
	 * @param gc - graphics context to draw object
	 */
	public GameObject(double x, double y, GraphicsContext gc) {
		this.x = x;
		this.y = y;
		this.gc = gc;
	}

	/**
	 * Update the object
	 */
	public void update(double dx, double dy)
	{
		// If image is set then draw it on canvas
		if (img != null)
		{
			gc.drawImage(img, x, y, dx, dy);
		}
	}
}
