/**
 * 
 */
package uos.foodchaingame;

/**
 * @author RAJA
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
	
	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMinutes() {
		return minutes;
	}
	
	
	
	public void setTimer(int startTime)
	{
		// this.startTime = startTime;
		seconds = startTime;
		
		if (startTime >= 60)
		{
			minutes = seconds/60;
			seconds -= (minutes * 60);
		}
		else
		{
			minutes = 0;
		}
	}
	
	public void decrementTimer()
	{
		if (seconds > 0)
			seconds--;
			
		if (seconds == 0)
		{
			if (minutes > 0)
			{
				minutes--;
				seconds = 60;
			}
		}
	}
}
