package PlayComponents;

import java.util.ArrayList;

import org.apache.log4j.Logger;



/**
 * Class that constructs a grid and accepts co-ordinates that can rotate and move an object around a grid
 * @author Newelly
 *
 */
public class PlayGrid {

	final static Logger logger = Logger.getLogger(PlayGrid.class);
	
	private ArrayList<String> orientationArrayList;
	
	private enum directionsEnum{L,R,M} //L - LEFT, R - RIGHT, M - MOVE
	
	private String currentOrientation;
	
	private int xAxisSize;
	
	private int yAxisSize;
	
	private int xAxisCurrentCoord;
	
	private int yAxisCurrentCoord;
	
	
	public PlayGrid (int xAxisSize, int yAxisSize){
		
		this.xAxisSize = xAxisSize;
		this.yAxisSize = yAxisSize;
		
		xAxisCurrentCoord = 0;
		yAxisCurrentCoord = 0;

		currentOrientation = "NORTH";
		
		orientationArrayList = new ArrayList<String>();
		
		orientationArrayList.add("NORTH"); // 0
		orientationArrayList.add("EAST");  // 1
		orientationArrayList.add("SOUTH"); // 2
		orientationArrayList.add("WEST");  // 3
		
	}
	
	/**
	 * Accepts user directions and returns new grid position (X ANB Y AXIS) and current orientation (NORTH, EAST, SOUTH OR WEST)
	 * @param directions - DIRECTIONS IN FORMAT OF M FOR MOVE, R FOR RIGHT AND L FOR LEFT FOLLOWED BY A NUMERICAL VALUE. 
	 * eg. M44 for Moving 44 paces in current direction. L3 for rotating Left 3 times.
	 * @return new grid position (X AND Y coords) and current orientation (NORTH, EAST, SOUTH OR WEST) 
	 * OR a string of invalid if the directions are not in the correct format.
	 */
	public String moveObject(String directions){
		
		if("".equals(directions) || directions == null || Character.isDigit(directions.toCharArray()[0])){
			System.out.println("Your directions value of : "+directions+" is invalid.");
			return "invalid";
		}else if(directions.length() > 1){
			
			try{
				Long.parseLong(directions.substring(1));
			}catch(NumberFormatException nfe){
				
				System.out.println("Your directions value of : "+directions+" is invalid.");
				return "invalid";
			}
	
		}
		
		directionsEnum direction = directionsEnum.valueOf(directions.substring(0,1));
		int rotateOrMoveVal = (directions.length() > 1) ? Integer.valueOf(directions.substring(1)) : 1;
		
		int currentRotationIndex = -1;
		
		int tempOrientationVal = -1;
		
		int newRotationIndex = -1;
		
		switch(direction){
		
			case L:  // ROTATE LEFT
			
				System.out.println("rotating : "+rotateOrMoveVal+" LEFT");
				
				currentRotationIndex = orientationArrayList.indexOf(currentOrientation);

				tempOrientationVal = rotateOrMoveVal % orientationArrayList.size();
				
				newRotationIndex = ((currentRotationIndex - tempOrientationVal)  < 0) ? 
						orientationArrayList.size() + (currentRotationIndex - tempOrientationVal) : currentRotationIndex - tempOrientationVal; 
					
				currentOrientation = orientationArrayList.get(newRotationIndex);
				
				break;
				
			case R: //ROTATE RIGHT
				
				System.out.println("rotating : "+rotateOrMoveVal+" RIGHT");
				
				currentRotationIndex = orientationArrayList.indexOf(currentOrientation);
		
				tempOrientationVal = rotateOrMoveVal % orientationArrayList.size();
				
				newRotationIndex = ((currentRotationIndex + tempOrientationVal) >= orientationArrayList.size()) ?  
						(currentRotationIndex + tempOrientationVal) -  orientationArrayList.size() : currentRotationIndex + tempOrientationVal; 
					
				currentOrientation = orientationArrayList.get(newRotationIndex);
	
				break;
				
			case M: //MOVE
				
				int tempMovementVal = -1;
				
				System.out.println("Moving : "+rotateOrMoveVal+" paces : "+currentOrientation);
				
				if(currentOrientation.equalsIgnoreCase("NORTH") || currentOrientation.equalsIgnoreCase("SOUTH")){
					//y axis
					tempMovementVal = rotateOrMoveVal % this.yAxisSize; 
					
					switch(currentOrientation){
					
						case "NORTH": //MOVE NORTH
							
							yAxisCurrentCoord = (yAxisCurrentCoord + tempMovementVal >= this.yAxisSize) ? 
									(yAxisCurrentCoord + tempMovementVal) -  this.yAxisSize : yAxisCurrentCoord + tempMovementVal;
														
							
							break;
							
						case "SOUTH": //MOVE SOUTH
							
							yAxisCurrentCoord = ((yAxisCurrentCoord - tempMovementVal)  < 0) ? 
									this.yAxisSize + (yAxisCurrentCoord - tempMovementVal) : yAxisCurrentCoord - tempMovementVal; 
							
							
							break;
					}
					
					
				}else{
					//x axis
					
					
					
					tempMovementVal = rotateOrMoveVal % this.xAxisSize;
					
					switch(currentOrientation){
					
						case "EAST": //MOVE EAST
							
							xAxisCurrentCoord = (xAxisCurrentCoord + tempMovementVal >= this.xAxisSize) ? 
									(xAxisCurrentCoord + tempMovementVal) -  this.xAxisSize : xAxisCurrentCoord + tempMovementVal;
							
									
									
							break;
							
						case "WEST": //MOVE WEST
							
							
							xAxisCurrentCoord = ((xAxisCurrentCoord - tempMovementVal)  < 0) ? 
									this.yAxisSize + (xAxisCurrentCoord - tempMovementVal) : xAxisCurrentCoord - tempMovementVal; 
							
							break;
					}
					
				}
				
				
				break;
				
			default:
				break;
		}
		
		
		//System.out.println("current position on grid is x coord : "+xAxisCurrentCoord+" y coord : "+yAxisCurrentCoord+" and facing : "+currentOrientation);
		
		
		return "current position on grid is x coord : "+xAxisCurrentCoord+" y coord : "+yAxisCurrentCoord+" and facing : "+currentOrientation;
	}
	
	
	
	public static void main(String args[]){
		
		PlayGrid pg = new PlayGrid(100, 100);
		
		
//		pg.moveObject("L3");
//		
//		pg.moveObject("R");
//		
//		pg.moveObject("L");
//			
//		pg.moveObject("R6");
//		
//		pg.moveObject("L2");
//		
//		pg.moveObject("R3");
//		
//		pg.moveObject("L11");
//		
//		pg.moveObject("R14");
		
		
		pg.moveObject("M31");
		
		pg.moveObject("R");
		
		pg.moveObject("M");
		
		pg.moveObject("L");
		
		pg.moveObject("M66");
			
		pg.moveObject("M58");
		
		pg.moveObject("R3");
		
		pg.moveObject("M12");
		
		pg.moveObject("M84");
//		
		pg.moveObject("R2");
//		
		pg.moveObject("M22");
//		
		pg.moveObject("M74");
		
		
	}
	
	

	
}
