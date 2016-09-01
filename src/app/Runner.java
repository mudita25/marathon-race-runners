package app;

/**
 * @author Mudita
 *
 * This class will create a runner object for each runner and initialize it.
 */
public class Runner {
	private String name;
	private int speed;
	private int restPercent;
	
	
	/**
	 * @param name runner name
	 * @param speed runner speed
	 * @param restPercent  runner rest percent
	 */
	public Runner(String name, int speed, int restPercent){
		this.name = name;
		this.speed = speed;
		this.restPercent = restPercent;
	}

	public Runner() {
		 this("", 0, 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getRestPercent() {
		return restPercent;
	}

	public void setRestPercent(int restPercent) {
		this.restPercent = restPercent;
	}

}
