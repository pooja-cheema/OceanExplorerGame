package application;

import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** This class represents ocean map.
 * It initializes the map and randomly places island on the map
 */
public class OceanMap {
	
	final int DIMENSION = 10;
	final int SCALING_FACTOR = 50; 
	ImageView shipImageView; 
    private boolean[][] islandMap = new boolean[DIMENSION][DIMENSION];
    Ship ship;

    // Initialize Ocean Map grid
    public OceanMap(int dimension) {

        // Initialize the grid array with all cells as empty (set to false)
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
            	this.islandMap[row][col] = false;  // false represents empty cell, that is, ocean
            }
        }
    }  
    
    /** This method places the islands randomly on the ocean grid each time it is called
     * @param root
     * @param gridSize
     * @param islandCount
     * @param scalingFactor
     * @return root
     */
    	public Pane placeIsland(Pane root, int gridSize, int islandCount, int scalingFactor) {
    		Random random = new Random();
    		ship = new Ship(0, 0);
    		int row = ship.getShipX();  // x coordinate of ship's current location 
    		int col = ship.getShipY();  // y coordinate of ship's current location
    		
    	    for (int i = 0; i < islandCount; i++) {
    	        int x, y;
    	        
    	        /* generate random x and y coordinates for island placement,
    	         * while also making sure that 2 islands are not placed in the same cell,
    	         * and island is not placed in the cell containing the ship
    	         *  
    	         */
    	        do {
    	            x = random.nextInt(gridSize);
    	            y = random.nextInt(gridSize);
    	        } while (this.islandMap[x][y] || (x==row && y==col));
    	        
    	        // Mark the position as an island by marking it true
    	        this.islandMap[x][y] = true;

    	        // Create a rectangle to represent the island
    	        Rectangle rect = new Rectangle(x * scalingFactor, y * scalingFactor, scalingFactor, scalingFactor);
    	        rect.setFill(Color.GREEN);
    	        
    	        // Add the island to the grid
    	        root.getChildren().add(rect);
    	    }
			return root;
    	}
    	
    	// return islandMap
    	public boolean[][] getIsland() {
    		return this.islandMap;
    		}
    	
}


