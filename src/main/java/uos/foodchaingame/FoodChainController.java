/**
 * 
 */
package uos.foodchaingame;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author RAJA
 *
 */
public class FoodChainController implements EventHandler{

	FoodChainModel model;
	FoodChainView view;
	
	public FoodChainController(FoodChainModel model, FoodChainView view) {
		super();
		this.model = model;
		this.view = view;
		updateView();
	}

	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		
	}
	
	public void addChainMember(GameObject member)
	{
		model.addChainMember(member);
	}
	
	public void updateView()
	{
		view.updateView();
	}
}
