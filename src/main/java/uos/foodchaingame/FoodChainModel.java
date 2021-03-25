/**
 * 
 */
package uos.foodchaingame;

import java.util.HashMap;

import javafx.scene.control.Button;

/**
 * @author Usman Shabir Kousar
 *
 */
public class FoodChainModel {

	HashMap<Button, GameObject> producers = new HashMap<Button, GameObject>();
	HashMap<Button, GameObject> prey = new HashMap<Button, GameObject>();
//	HashMap<Producer, Button> producers = new HashMap<Producer, Button>();
	
	public void addProducer(GameObject member)
	{
		Button selectBtn = new Button("Select");
		producers.put(selectBtn, member);
	}
}
