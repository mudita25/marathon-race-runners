package app;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Mudita
 *
 * This class sets implements marathon race application.
 */
public class MarathonImpl implements MarathonController {
	
	// declare class variables
    private static MarathonDAO mDAO = null;
    private static ArrayList<Runner> runners = null;
    volatile static boolean raceFlag  = false;
	
	public static MarathonController getController(){
		MarathonController controller = new MarathonImpl();
		return controller;
	}

	/**
	 * Displays the App menu options
	 */
    public void displayMenu(){
        System.out.println("Select your data source:\n");
        System.out.println("1.	Derby database");
        System.out.println("2.	XML file");
        System.out.println("3.	Text file");
        System.out.println("4.	Default two runners");
        System.out.println("5.	Exit");
        System.out.println();
    }

    /**
	 * Gets the user choice
	 */
    public void getUserChoice(){
    	//get valid option from user
    	Scanner sc = new Scanner(System.in);
    	int action = Validator.getUserChoice(sc, "Enter your choice: ");  
    	System.out.println();
    	//get database object based on user choice
    	mDAO = DAOFactory.getDAO(action);
    	//extract runners
    	runners = mDAO.getRunners();
    	//if no runners found, exit race
    	if(runners == null || runners.size() == 0){
    		System.out.println("There are no runners. Exiting!");
    		System.exit(0); 
    	}
    }

    /**
	 * Starts the race with selected given runners
	 */
    public void startRace(){
    	System.out.println("\nGet set...Go! \n");
    	//obtain threads for all runners
    	for(Runner r: runners){
    		new ThreadRunner(r.getName(),r.getSpeed(),r.getRestPercent());	
    	}
    }
	
    /**
	 * Marks the race as finished
     * @param runner the runner thread which comes first in the race and hence finishes the race.
     */
    public static synchronized void finishedRace(ThreadRunner runner){
    	if(!raceFlag){
    		//set flag to true, so that other threads won't execute
        	raceFlag = true;
    		System.out.println(runner.runnerName + ":" + " I finished!" + "\n");
    		System.out.println("The race is over! The " + runner.runnerName + " is the winner.\n");
    		for(Runner r: runners){
    			if(r.getName().equals(runner.runnerName)){
    				continue;
    			}
    			else{
    				System.out.println(r.getName() + ": You beat me fair and square.");
    			}
    		}
    	}
    }
}
