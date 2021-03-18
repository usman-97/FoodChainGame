
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
	protected int x, y; // Coordinates for object
	protected GraphicsContext gc; // graphics context to draw the object
	
	/**
	 * Constructor for GameObject class
	 * @param x - x coordinate for the object
	 * @param y - y coordinate for the object
	 * @param gc - graphics context to draw object
	 */
	public GameObject(int x, int y, GraphicsContext gc) {
		this.x = x;
		this.y = y;
		this.gc = gc;
	}

	/**
	 * Update the object
	 */
	public void update()
	{
		if (img != null)
		{
			gc.drawImage(img, x, y, 30, 30);
		}
	}
}
