/**
 * 
 */
package uos.foodchaingame;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FoodChainController class controls the view from model and view
 * Implements EventHandler interface and its abstract methods
 * @author Usman Shabir Kousar
 *
 */
public class FoodChainController implements EventHandler{

	private FoodChainModel model; // food chain model
	private FoodChainView view; // food chain view
	
	// Strategies for panels in view
	protected DefaultPanelStrategy defaultStrategy;
	protected SelectPanelStrategy selectPanelStrategy;
	protected CorrectMemberStrategy correctMemberStrategy;
	protected WrongMemberStrategy wrongMemberStrategy;
	
	/**
	 * Constructor for FoodChainController which set instance of controller object
	 * @param model
	 * @param view
	 */
	public FoodChainController(FoodChainModel model, FoodChainView view) {
		super();
		this.model = model; // FoodChainModel instance 
		this.view = view; // FoodChainView instance
		
		// Buttons from view are set to trigger events
		view.chooseProducer.setOnAction(this);
		view.choosePrey.setOnAction(this);
		view.choosePredator.setOnAction(this);
		view.check.setOnAction(this);
		
		// Strategy Pattern strategies for panels
		defaultStrategy = new DefaultPanelStrategy();
		selectPanelStrategy = new SelectPanelStrategy();
		correctMemberStrategy = new CorrectMemberStrategy();
		wrongMemberStrategy = new WrongMemberStrategy();
		
		updateView(); // Update the view for user
	}

	/**
	 * Triggers events for button in FoodChainView object
	 * @param event
	 */
	@Override
	public void handle(Event event) {
		// If one of the 'choose' button on panel is clicked
		if (event.getSource() == view.chooseProducer)
		{
			view.selectProducer = true;
			view.selectPrey = false;
			view.selectPredator = false;
			
			// Set select panel strategy which will mark the selected panel
			view.setPanelStrategy(selectPanelStrategy);
			view.executePanelStrategy(view.selectedProducer);
			
			// Set default strategy for other panels
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
		
		// If check button is clicked
		if (event.getSource() == view.check)
		{
			// check player created food chain
			boolean checkProducer = model.checkPanels(view.chosenProducer, model.producers);
			boolean checkPrey = model.checkPanels(view.chosenPrey, model.prey);
			boolean checkPredator = model.checkPanels(view.chosenPredator, model.predator);
			
			ArrayList<GameObject> foodChain = new ArrayList<GameObject>();
			foodChain.add(view.chosenProducer);
			foodChain.add(view.chosenPrey);
			foodChain.add(view.chosenPredator);
			
			// If player selected food chain member is correct
			// Then use correct member strategy
			// if not then use wrong member strategy
			// Check if player created Food Chain doesn't exist yet
			if (!model.checkFoodChain(foodChain))
			{
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
					model.addFoodChain(foodChain);
					view.setScore(view.getScore() + 10);
					
					// Clear all panels
					view.chosenProducer = null;
					view.selectedProducerImg.setImage(null);
					
					view.chosenPrey = null;
					view.selectedPreyImg.setImage(null);
					
					view.chosenPredator = null;
					view.selectedPredatorImg.setImage(null);
				}
				else
				{
					// If player's created food chain is not valid then
					// decrement the player score
					if (view.getScore() >= 5)
					{
						view.setScore(view.getScore() - 5);
					}
				}
			}
			else
			{
				view.setPanelStrategy(wrongMemberStrategy);
				view.executePanelStrategy(view.selectedProducer);
				view.executePanelStrategy(view.selectedPrey);
				view.executePanelStrategy(view.selectedPredator);
				
				if (view.getScore() >= 5)
				{
					view.setScore(view.getScore() - 5);
				}
				view.errorLbl.setText("You have already created this FOOD CHAIN");
			}
			view.scoreLbl.setText("Score: " + view.getScore());
		}
	}
	
	/**
	 * Add members to 
	 * @param member
	 * @param memeberType
	 */
//	public void addMember(Producer member, String memeberType)
//	{
//		model.addMember(member, memeberType);
//	}
	
	/**
	 * Update View
	 */
	public void updateView()
	{
		view.updateView();
	}
}

// Strategy Pattern

/**
 * Set Panel to default
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
 * Mark the Panel to show it is the current selected panel
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
 * If player selected member is chosen in correct panel
 * then change the background to show it correct
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
 * If player chosen member is wrong then change the background
 * colour to inform player
 * @author Usman Shabir Kousar
 *
 */
class WrongMemberStrategy implements PanelStrategyIF {

	@Override
	public void execute(Circle panel) {
		panel.setFill(Color.RED);
		
	}
}
