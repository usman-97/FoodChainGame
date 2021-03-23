/**
 * 
 */
package uos.foodchaingame;

import java.util.HashMap;

import javafx.scene.control.Button;

/**
 * @author RAJA
 *
 */
public class FoodChainModel {

	HashMap<GameObject, Button> chain = new HashMap<GameObject, Button>();
	
	public void addChainMember(GameObject member)
	{
		Button selectBtn = new Button("Select");
		chain.put(member, selectBtn);
	}
}
