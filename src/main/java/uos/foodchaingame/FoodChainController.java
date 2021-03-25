/**
 * 
 */
package uos.foodchaingame;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author Usman Shabir Kousar
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
			view.selectProducer = true;
			view.selectPrey = false;
			view.selectPredator = false;
		}
		else if (event.getSource() == view.choosePrey)
		{
			view.selectProducer = false;
			view.selectPrey = true;
			view.selectPredator = false;
		}
		else
		{
			if (event.getSource() == view.choosePredator)
			{
				view.selectProducer = false;
				view.selectPrey = false;
				view.selectPredator = true;
			}
		}
	}
	
	public void addProducers(Producer member)
	{
		model.addProducer(member);
	}
	
	public void updateView()
	{
		view.updateView();
	}
}
