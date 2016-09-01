package app;

import java.util.ArrayList;

/**
 * @author Mudita
 *
 * This class creates the default runners for the Marathon.
 */
public class RunnerDefault implements MarathonDAO{
	private ArrayList<Runner> runners = null;

	/**
	 * Provides the list of default runners
	 */
	public ArrayList<Runner> getRunners(){
           
        runners = new ArrayList<>();
        Runner r;
        r = new Runner("Tortoise",10,0);
        runners.add(r);
		r = new Runner("Hare", 100, 90);
		runners.add(r);
		return runners;
	}
}
