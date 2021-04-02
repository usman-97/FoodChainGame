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
			
//			System.out.println(checkProducer + " " + view.chosenProducer);
//			System.out.println(checkPrey + " " + view.chosenPrey);
//			System.out.println(checkPredator + " " + view.choosePredator);
			
			// If player selected food chain member is correct
			// Then use correct member strategy
			// if not then use wrong member strategy
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
			
			// If player created food chain is correct then
			if (checkProducer && checkPrey && checkPredator)
			{
				// Set Condition to win
				view.setWin(true);
				// Set result text on result screen 
				view.getResult().setText("Success :)");
			}
			else
			{
				// If player created food chain is not correct
				// If chances are less than 1
				if (view.getChances() < 1)
				{
					// Set condition of lose
					view.setLose(true);
					view.getResult().setText("Game Over :(");
					view.getStage().setScene(view.getScene());
				}
				else
				{
					// Otherwise decrease player chance
					view.setChances(view.getChances() - 1);
					// Update chances on screen
					view.chancesLbl.setText("Chances: " + view.getChances());
				}
			}
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
