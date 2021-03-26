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
	HashMap<Button, GameObject> predator = new HashMap<Button, GameObject>();
	
	public void addProducer(GameObject member, String memberType)
	{
		Button selectBtn = new Button("Select");
		if (memberType.equalsIgnoreCase("producer"))
			producers.put(selectBtn, member);
		if (memberType.equalsIgnoreCase("prey"))
			prey.put(selectBtn, member);
		if (memberType.equalsIgnoreCase("predator"))
			predator.put(selectBtn, member);
	}
}
