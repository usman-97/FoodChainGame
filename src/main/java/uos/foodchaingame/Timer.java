/**
 * 
 */
package uos.foodchaingame;

/**
 * Timer Class to set the timer in minutes and seconds
 * @author Usman Shabir Kousar
 *
 */
public class Timer {

	// Single Timer object instance
	private static Timer timer = new Timer();
	
	private int seconds, minutes;
	
	// Constructor of Timer is set to private to prevent
	// creating multiple instance of Timer object
	private Timer() {};
	
	/**
	 * Creates Singleton object of Timer class
	 * @return Timer
	 */
	public static Timer getInstance()
	{
		return timer;
	}
	
	// Clas getters and setters
	
	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMinutes() {
		return minutes;
	}
	
	/**
	 * Set timer by given time
	 * @param startTime
	 */
	public void setTimer(int startTime)
	{
		seconds = startTime; // set given time to timer seconds
		
		// If startTime is greater than or equal to 60
		if (startTime >= 60)
		{
			minutes = seconds/60; // calculate minutes
			seconds -= (minutes * 60); // calculate seconds
		}
		else
		{
			minutes = 0;
		}
	}
	
	/**
	 * Decrement the time in timer
	 */
	public void decrementTimer()
	{
		// If seconds are greater than 0
		if (seconds > 0)
			seconds--; // decrement seconds
			
		if (seconds == 0)
		{
			if (minutes > 0)
			{
				minutes--; // Decrement the minute
				seconds = 60; // set seconds to default
			}
		}
	}
}
