/**
 * 
 */
package uos.foodchaingame;

/**
 * Factory interface to which will be implemented
 * by Factory class to create objects
 * @author Usman Shabir Kousar
 *
 */
public interface FactoryIF {
	
	// Create objects of different classes
	GameObject create(String discrim, double x, double y, double width, double height, String imgPath); 
}
