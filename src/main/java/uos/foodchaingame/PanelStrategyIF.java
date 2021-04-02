/**
 * 
 */
package uos.foodchaingame;

import javafx.scene.shape.Circle;

/**
 * Panel Strategy interface so panel strategies can implement this
 * interface to implement execute method
 * @author Usman Shabir Kousar
 *
 */
public interface PanelStrategyIF {

	public void execute(Circle panel); // Execute strategy on given panel
}
