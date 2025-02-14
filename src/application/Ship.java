package application;

import java.awt.Point;

/** This class contains information regarding the Ship coordinates,
 * and ship's movement on the map
 */
public class Ship {
	
	private Point currentLocation;
	int startX;
	int startY;
	
	// set starting coordinates of ship on the map
	public Ship(int startX, int startY) {
		this.startX = 6;
		this.startY = 4;
		this.currentLocation = new Point(this.startX, this.startY);
		}
	
	// get x coordinate of ship
	public int getShipX() {
		return this.startX;
	}
	
	// get y coordinate of ship
	public int getShipY() {
		return this.startY;
	}
	
	// get ship's current location
	public Point getShipLocation() {
		return this.currentLocation;
		}
	
	/** setting coordinates to enable ship's rightward movement
	 * @param islandMap
	 */
	public void goEast(boolean[][] islandMap) {
		int newShipX = this.startX;
		newShipX++;
		
		/* check if there is a cell available to the right, 
		 *  and that, available cell does not contain an island
		 */
		if((this.startX < 9) && (islandMap[newShipX][this.startY] != true) ) { 
				this.startX = newShipX;
                this.currentLocation = new Point(this.startX, this.startY); // update ship's current location
		}
	}
	
	/** setting coordinates to enable ship's leftward movement
	 * @param islandMap
	 */
	public void goWest(boolean[][] islandMap) {
		int newShipX = this.startX;
		newShipX--;
		/* check if there is a cell available to the left, 
		 *  and that, available cell does not contain an island
		 */
		if ((this.startX > 0) && (islandMap[newShipX][this.startY] != true) ) {
			this.startX = newShipX;
			this.currentLocation = new Point(this.startX, this.startY); // update ship's current location
		}	
	}
	
	/** setting coordinates to enable ship's upward movement
	 * @param islandMap
	 */
	public void goNorth(boolean[][] islandMap) {
		int newShipY = this.startY;
		newShipY--;	
		/* check if there is a cell available upwards, 
		 *  and that, available cell does not contain an island
		 */
		if ((this.startY > 0) && (islandMap[this.startX][newShipY] != true) ) {
			this.startY = newShipY;
			this.currentLocation = new Point(this.startX, this.startY); // update ship's current location
		}	
	}
		
	/** setting coordinates to enable ship's downward movement
	 * @param islandMap
	 */
	public void goSouth(boolean[][] islandMap) {
		int newShipY = this.startY;	
		newShipY++;	
		/* check if there is a cell available downwards, 
		 *  and that, available cell does not contain an island
		 */
		if( (this.startY < 9) && (islandMap[this.startX][newShipY] != true) ) {  // true for island
			this.startY = newShipY;
			this.currentLocation = new Point(this.startX, this.startY); // update ship's current location
		}
	}
	
}
