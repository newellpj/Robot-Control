import java.util.Scanner;

import org.apache.log4j.Logger;

import PlayComponents.PlayGrid;

/**
 * Main class for running an object around a grid
 * @author Newelly
 *
 */
public class RobotRunner{
	
	final static Logger logger = Logger.getLogger(RobotRunner.class);
	
	public String printWelcomeInstructionsMessage(){
		
		return "Please enter starting coordinates for your robot or type quit to exit.\n"
		  		+ "If you wish to play on please read below.\n\n"
		  		+ "Your directions must be in the format of: \n\n"+
		    "* M for move in the current direction \n"
		  + "* R for rotate Right 90 degrees \n"
		  + "* L for rotate Left 90 degrees \n \n"
		  + "This is followed by a number to represent the number of grid positions to you wish to move. \n"
		  + "For example:\n\n"
		  + "M2 for moving 2 grid positions in current direction. \n"
		  + "L3 for rotating left 90 degrees 3 times. \n \n"
		  + "Please enter your first coordinates : ";
		
	}
	

	public static void main (String[] args){
	 
		RobotRunner robotRunner = new RobotRunner();
		  
		System.out.println(robotRunner.printWelcomeInstructionsMessage()); 
		  
	    // create a scanner so we can read the command-line input
	    Scanner scanner = new Scanner(System.in);
	
	    PlayGrid playGrid = new PlayGrid(100,100);
	    
	    while(scanner.hasNext()){
	    	
	    	logger.debug("scanner in here");
	    	
	    	String userInput = scanner.next();
	    	
	    	logger.debug("user input : "+userInput);
	    	
	    	if(userInput != null && !"".equals(userInput)){
	    	
	    		userInput = userInput.replaceAll(" ", "");
	
		    	if("quit".equalsIgnoreCase(userInput)){
		    		
		    		//TODO get final position of robot
		    		
		    		System.out.println("I hoped you enjoyed the game!");
		    		scanner.close();
		    		System.exit(0);
		    	}else{
		    		
		    		String returnVal = playGrid.moveObject(userInput);
		    		
		    		if("invalid".equals(returnVal)){
		    			
		    			System.out.println(robotRunner.printWelcomeInstructionsMessage()); 
		    			
		    		}else{
		    			System.out.println(returnVal);
		    		}
		    		
		    	}
		    	
	    	}else{
	    		System.out.print("Your input is not valid - \n");
	    		System.out.println(robotRunner.printWelcomeInstructionsMessage()); 
	    	}
	    	
	    }

	}

}
