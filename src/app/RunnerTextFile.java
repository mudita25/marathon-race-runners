package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Mudita
 *
 * This class reads the text file entered by the user, if user does not enter filename, default will be used.
 */
public class RunnerTextFile implements MarathonDAO{
	
	private ArrayList<Runner> runners = null;
	private Path textPath = null;
	private File textFile = null;
	private final String FIELD_SEP = "\t";
	
	/**
	 * @param sc input is scanner object
	 */
	public RunnerTextFile(Scanner sc){
		System.out.print("Enter text file name or press enter to use default file: "); 
		String fileName = sc.nextLine();
		if(fileName != null && fileName.trim().length() != 0){
			textPath = Paths.get(fileName);
			textFile = textPath.toFile();
			if(!Files.exists(textPath)){				
				System.out.println("The " + fileName + " file was not found. Exiting!");
				System.exit(0);
			} 
		}
		else{
			textPath = Paths.get("FinalTextData.txt");
			textFile = textPath.toFile();
		}
	}
	
	public ArrayList<Runner> getRunners(){
		
		// if the file has already been read, don't read it again
        if (runners != null){
        	return runners;
        }
           
        runners = new ArrayList<>();
        
        // prevent the FileNotFoundException
        if(Files.exists(textPath)){
        	try(BufferedReader in = new BufferedReader(new FileReader (textFile))) {
        		
        		// read all products stored in the file
                // into the array list
        		String line = in.readLine();
        		while(line != null){
        			String[] columns = line.split(FIELD_SEP);
        			String name = columns[0];
        			String speed = columns[1];
        			String restPercent = columns[2];
        			Runner r = new Runner(name, Integer.parseInt(speed), Integer.parseInt(restPercent));
        			runners.add(r);
        			line = in.readLine();
        		}
        	}
        	catch(IOException e){
        		System.out.println("The file was not correct");
        		System.out.println(e);
        	}
        }
		return runners;
	}
}
