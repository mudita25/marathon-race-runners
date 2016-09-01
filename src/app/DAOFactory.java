package app;

import java.util.Scanner;

/**
 * @author Mudita
 *
 * This class provides user an option to selects the input source from which the data has to be read.
 */
public class DAOFactory {
		
	/**
	 * @param action user input which decides the data source from where runners will be selected
	 * @return MarathonDAO object
	 */
	public static MarathonDAO getDAO(int action){
		MarathonDAO mDAO = null;
		Scanner sc = null;
		switch(action){
			case 1: 
				mDAO = new RunnerDB();
				break;
			case 2: 
				sc = new Scanner(System.in);
				mDAO = new RunnerXMLFile(sc);
				break;
			case 3: 
				sc = new Scanner(System.in);
				mDAO = new RunnerTextFile(sc);		 
				break;
			case 4: 
				mDAO = new RunnerDefault(); 
				break;
			case 5: 
				System.out.println("Thank you for using my Marathon Race Program");
				System.exit(0);
				break;	
			default:
				System.out.println("Invalid Input");
				break;
		}
		return mDAO;
	}
}
