package app;

import java.util.Scanner;

/**
 * @author Mudita
 *
 * This application starts a marathon between runners. The user selects input from which the data has to be read.
 */
public class MarathonRaceApp {
    
    /**
     * @param args not used
     */
	private static Scanner sc = null;
	
    public static void main(String args[]){
             
        // get hold of controller
    	MarathonController controller = MarathonImpl.getController();
    	sc = new Scanner(System.in);
    	//run until user choose to exit
    	while(true){  
    		// display welcome message
            System.out.println("Welcome to the Marathon Race Runner Program\n");
    		controller.displayMenu();
        	controller.getUserChoice();
        	controller.startRace();
        	
        	//sleep while race is going on
        	while(!MarathonImpl.raceFlag){
        		try{
        			Thread.sleep(IConstants.SLEEP_TIME_MAIN);
        		}
        		catch (InterruptedException e){
        			break;
        		}
        	}
        	
        	//resume after race and ask user to press key
        	System.out.println("\nPress any key to continue . . .");
        	System.out.println();
	        	if(sc.hasNextLine()){
	        		try{
	        			Thread.sleep(2*IConstants.SLEEP_TIME);
	        			sc.nextLine();
	        		}
	        		catch (InterruptedException e){
	        			break;
	        		}
	        		MarathonImpl.raceFlag = false;
	        	}
	        	else
	        	{
	        		break;
	        	}  	
    	}	      
    }
}
