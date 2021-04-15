/**
 * 
 */
package uos.foodchaingame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	protected Map<Button, GameObject> producers = new HashMap<Button, GameObject>();
	protected Map<Button, GameObject> prey = new HashMap<Button, GameObject>();
	protected Map<Button, GameObject> predator = new HashMap<Button, GameObject>();
	
	// Stores the player's created food chains
	protected ArrayList<ArrayList<GameObject>> foodChains = new ArrayList<ArrayList<GameObject>>();
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
	public boolean checkPanels(GameObject member, Map <Button, GameObject> chain)
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
	
	/**
	 * Adds player successfully created food chain to FoodChain list
	 * @param foodChain
	 */
	public void addFoodChain(ArrayList<GameObject> foodChain)
	{
		foodChains.add(foodChain);
	}
	
	/**
	 * Check if given food chain doesn't already exist in food chain list
	 * @param chain
	 * @return
	 */
	public boolean checkFoodChain(ArrayList<GameObject> chain)
	{
		boolean found = false;
		for (ArrayList<GameObject> foodChain : foodChains)
		{
			if (chain.equals(foodChain))
			{
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	/**
	 * Total number of possible food chains
	 * @return
	 */
	public int totalFoodChains()
	{
		return producers.size() * prey.size() * predator.size();
	}
	
	/**
	 * Calculate total player score
	 * @param score
	 * @return
	 */
	public int calculateTotalScore(int score)
	{
		double totalScore = totalFoodChains() * 10;
		double finalScore =  score / totalScore;
		return (int)(finalScore * 100);
	}
}
