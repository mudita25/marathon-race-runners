package app;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Mudita
 *
 * This class reads the xml file entered by the user, if user does not enter filename, default will be used.
 */
public class RunnerXMLFile implements MarathonDAO{
	
	private Path xmlPath = null;
	private ArrayList<Runner> runners = null;
	
	/**
	 * @param sc input is scanner object
	 * Constructs the Runners from given XML file definition
	 */
	public RunnerXMLFile(Scanner sc){
		System.out.print("Enter XML file name or press enter to use default file: "); 
		String fileName = sc.nextLine();
		if(fileName != null && fileName.trim().length() != 0){
			xmlPath = Paths.get(fileName);
			if(!Files.exists(xmlPath)){				
				System.out.println("The " + fileName + " file was not found. Exiting!");
				System.exit(0);
			} 
		}
		else{
			xmlPath = Paths.get("FinalXMLdata.xml");
		}
	}

	/**
	 * Returns the list of runners from XML file
     */
	public ArrayList<Runner> getRunners(){
		
		//if the xml is already read, don't read it again
		if(runners != null) {
			return runners;
		}
		
		runners = new ArrayList<>();      
	    Runner r = null;    
	    
		// prevent the FileNotFoundException
		if(Files.exists(xmlPath)){	    
			
			//create the XMLInputFactory
			XMLInputFactory inputFactory =  XMLInputFactory.newFactory();
			try{
				
				// create a XMLStreamReader object
				FileReader fileReader = new FileReader(xmlPath.toFile());
				XMLStreamReader reader = inputFactory.createXMLStreamReader(fileReader);
				
				// read the products from the file
				while(reader.hasNext()){
					int eventType = reader.getEventType();
					switch(eventType){
						case XMLStreamConstants.START_ELEMENT:
							String elementName = reader.getLocalName();
							if(elementName.equals("Runner")){
								r = new Runner();
								String name = reader.getAttributeValue(0);
								r.setName(name);
							}
							if(elementName.equals("RunnersMoveIncrement")){
								String speedText = reader.getElementText();
								int speed = Integer.parseInt(speedText);
								r.setSpeed(speed);
							}
							
							if(elementName.equals("RestPercentage")){
								String restText = reader.getElementText();
								int restPercent = Integer.parseInt(restText);
								r.setRestPercent(restPercent);
							}
							break;
						case XMLStreamConstants.END_ELEMENT:
							elementName = reader.getLocalName();
							if(elementName.equals("Runner")){
								runners.add(r);
							}
							break;
						default:
							break;
					}
					reader.next();
				}
			} 
			catch(IOException | XMLStreamException e){
				System.out.println("The file was not correct");
				System.out.println(e);				
			}	
		}		
		return runners;
	}
}
