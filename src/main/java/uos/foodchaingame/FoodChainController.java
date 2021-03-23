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
		
		view.chooseProducer.setOnAction(this);
		view.choosePrey.setOnAction(this);
		view.choosePredator.setOnAction(this);
		
		updateView();
	}

	@Override
	public void handle(Event event) {
		if (event.getSource() == view.chooseProducer)
		{
			
		}
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
