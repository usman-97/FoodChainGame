/**
 * 
 */
package uos.foodchaingame;

import java.util.HashMap;

import javafx.scene.control.Button;

/**
 * FoodChainModel class implements the needed source code to update view and 
 * implement logic in controller
 * @author Usman Shabir Kousar
 *
 */
public class FoodChainModel {

	// Map for each type of member
	// Map store all given to choose in the game
	HashMap<Button, GameObject> producers = new HashMap<Button, GameObject>();
	HashMap<Button, GameObject> prey = new HashMap<Button, GameObject>();
	HashMap<Button, GameObject> predator = new HashMap<Button, GameObject>();
	
	/**
	 * Add members to their map 
	 * @param member
	 * @param memberType
	 */
	public void addMember(GameObject member, String memberType)
	{
		Button selectBtn = new Button("Select"); // Select button will be key in the Map
		
		// Add member to their map
		if (memberType.equalsIgnoreCase("producer"))
			producers.put(selectBtn, member);
		if (memberType.equalsIgnoreCase("prey"))
			prey.put(selectBtn, member);
		if (memberType.equalsIgnoreCase("predator"))
			predator.put(selectBtn, member);
	}
	
	/**
	 * Checks if given GameObject is found in the given map
	 * @param member
	 * @param chain
	 * @return
	 */
	public boolean checkPanels(GameObject member, HashMap <Button, GameObject> chain)
	{
		boolean found = false;
		
		for (GameObject chainMember : chain.values())
		{
			// If member is found in the map
			if (member == chainMember)
			{
				found = true; // set found to true
				break;
			}
		}
		return found;
	}
}
