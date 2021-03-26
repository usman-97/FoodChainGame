/**
 * 
 */
package uos.foodchaingame;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Usman Shabir Kousar
 *
 */
public class FoodChainController implements EventHandler{

	FoodChainModel model;
	FoodChainView view;
	
	DefaultPanelStrategy defaultStrategy;
	SelectPanelStrategy selectPanelStrategy;
	
	public FoodChainController(FoodChainModel model, FoodChainView view) {
		super();
		this.model = model;
		this.view = view;
		
		view.chooseProducer.setOnAction(this);
		view.choosePrey.setOnAction(this);
		view.choosePredator.setOnAction(this);
		
		defaultStrategy = new DefaultPanelStrategy();
		selectPanelStrategy = new SelectPanelStrategy();
		
		updateView();
	}

	@Override
	public void handle(Event event) {
		if (event.getSource() == view.chooseProducer)
		{
			view.selectProducer = true;
			view.selectPrey = false;
			view.selectPredator = false;
			
			view.setPanelStrategy(selectPanelStrategy);
			view.executePanelStrategy(view.selectedProducer);
			
			view.setPanelStrategy(defaultStrategy);
			view.executePanelStrategy(view.selectedPrey);
			view.executePanelStrategy(view.selectedPredator);
		}
		else if (event.getSource() == view.choosePrey)
		{
			view.selectProducer = false;
			view.selectPrey = true;
			view.selectPredator = false;
			
			view.setPanelStrategy(selectPanelStrategy);
			view.executePanelStrategy(view.selectedPrey);
			
			view.setPanelStrategy(defaultStrategy);
			view.executePanelStrategy(view.selectedProducer);
			view.executePanelStrategy(view.selectedPredator);
		}
		else
		{
			if (event.getSource() == view.choosePredator)
			{
				view.selectProducer = false;
				view.selectPrey = false;
				view.selectPredator = true;
				
				view.setPanelStrategy(selectPanelStrategy);
				view.executePanelStrategy(view.selectedPredator);
				
				view.setPanelStrategy(defaultStrategy);
				view.executePanelStrategy(view.selectedProducer);
				view.executePanelStrategy(view.selectedPrey);
			}
		}
	}
	
	public void addProducers(Producer member, String memeberType)
	{
		model.addProducer(member, memeberType);
	}
	
	public void updateView()
	{
		view.updateView();
	}
	
//	private void selectViewPanelStrategy(boolean condition1, boolean condition2, boolean condition3)
//	{
//		view.setPanelStrategy(selectPanelStrategy);
//		if (condition1)
//			
//		view.executePanelStrategy(view.selectedProducer);
//		
//		view.setPanelStrategy(defaultStrategy);
//		view.executePanelStrategy(view.selectedPrey);
//		view.executePanelStrategy(view.selectedPredator);
//	}
}

/**
 * 
 * @author Usman Shabir Kousar
 *
 */
class DefaultPanelStrategy implements PanelStrategyIF {

	@Override
	public void execute(Circle panel) {
		panel.setFill(Color.SADDLEBROWN);
	}
	
}

/**
 * 
 * @author Usman Shabir Kousar
 *
 */
class SelectPanelStrategy implements PanelStrategyIF {

	@Override
	public void execute(Circle panel) {
		panel.setFill(Color.LIGHTSKYBLUE);
	}
	
}
