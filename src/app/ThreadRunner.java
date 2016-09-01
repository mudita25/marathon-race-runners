package app;

/**
 * @author Mudita
 *
 * This class fetches the details of runners and starts all the threads.
 */
public class ThreadRunner extends Thread {
	String runnerName;
	int runnerSpeed;
	int restPercent;
	int distance;
	int random;
	
	/** 
	 * @param runnerName - runner name
	 * @param runnerSpeed - runner speed
	 * @param restPercent - runner rest percentage
	 */
	ThreadRunner(String runnerName,	int runnerSpeed, int restPercent){
		this.runnerName = runnerName;
		this.runnerSpeed = runnerSpeed;
		this.restPercent = restPercent;	
		distance = 0;
		start();
	}
	
	public void run() {
		while(distance < IConstants.TRACK_LENGTH && (!MarathonImpl.raceFlag)){
			
			random = 1 + ((int)(Math.random() * 100));
			if(random > restPercent){
				distance += runnerSpeed;	
			}
			
			if(distance > 0 && !MarathonImpl.raceFlag){
				System.out.println(runnerName+"  " + distance);
			}
			
			try {
				sleep(IConstants.SLEEP_TIME); 	
			} catch (InterruptedException e) {
				break;
			}			
		}
		
		if(distance >= IConstants.TRACK_LENGTH && (!MarathonImpl.raceFlag)){
			MarathonImpl.finishedRace(this);
		}
	}
}
