/**
 * 
 */
package uos.foodchaingame;

import java.util.HashMap;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FoodChainController class controls the view from model and view
 * @author Usman Shabir Kousar
 *
 */
public class FoodChainController implements EventHandler{

	protected FoodChainModel model; // food chain model
	protected FoodChainView view; // food chain view
	
	// Strategies for panels in view
	protected DefaultPanelStrategy defaultStrategy;
	protected SelectPanelStrategy selectPanelStrategy;
	protected CorrectMemberStrategy correctMemberStrategy;
	protected WrongMemberStrategy wrongMemberStrategy;
	
	public FoodChainController(FoodChainModel model, FoodChainView view) {
		super();
		this.model = model;
		this.view = view;
		
		view.chooseProducer.setOnAction(this);
		view.choosePrey.setOnAction(this);
		view.choosePredator.setOnAction(this);
		view.check.setOnAction(this);
		
		defaultStrategy = new DefaultPanelStrategy();
		selectPanelStrategy = new SelectPanelStrategy();
		correctMemberStrategy = new CorrectMemberStrategy();
		wrongMemberStrategy = new WrongMemberStrategy();
		
		updateView(); // Update the view for user
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
		
		if (event.getSource() == view.check)
		{
			boolean checkProducer = model.checkPanels(view.chosenProducer, model.producers);
			boolean checkPrey = model.checkPanels(view.chosenPrey, model.prey);
			boolean checkPredator = model.checkPanels(view.chosenPredator, model.predator);
			
//			System.out.println(checkProducer + " " + view.chosenProducer);
//			System.out.println(checkPrey + " " + view.chosenPrey);
//			System.out.println(checkPredator + " " + view.choosePredator);
			
			if (checkProducer)
				view.setPanelStrategy(correctMemberStrategy);
			else
				view.setPanelStrategy(wrongMemberStrategy);
			view.executePanelStrategy(view.selectedProducer);
			
			if (checkPrey)
				view.setPanelStrategy(correctMemberStrategy);
			else
				view.setPanelStrategy(wrongMemberStrategy);
			view.executePanelStrategy(view.selectedPrey);
			
			if (checkPredator)
				view.setPanelStrategy(correctMemberStrategy);
			else
				view.setPanelStrategy(wrongMemberStrategy);
			view.executePanelStrategy(view.selectedPredator);
			
			if (checkProducer && checkPrey && checkPredator)
			{
				view.setWin(true);
			}
			else
			{
				if (view.getChances() < 1)
				{
					view.setLose(true);
					view.getStage().setScene(view.getScene());
					// resetView();
				}
				else
				{
					view.setChances(view.getChances() - 1);
					view.chancesLbl.setText("Chances: " + view.getChances());
				}
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
	
	public void resetView()
	{
		view.resetView();
		view.setPanelStrategy(defaultStrategy);
		view.executePanelStrategy(view.selectedProducer);
		view.executePanelStrategy(view.selectedPrey);
		view.executePanelStrategy(view.selectedPredator);
	}
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

/**
 * 
 * @author Usman Shabir Kousar
 *
 */
class CorrectMemberStrategy implements PanelStrategyIF {

	@Override
	public void execute(Circle panel) {
		panel.setFill(Color.FORESTGREEN);
		
	}
}

/**
 * 
 * @author Usman Shabir Kousar
 *
 */
class WrongMemberStrategy implements PanelStrategyIF {

	@Override
	public void execute(Circle panel) {
		panel.setFill(Color.RED);
		
	}
}
