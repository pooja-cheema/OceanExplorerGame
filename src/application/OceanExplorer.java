package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/** Main class responsible for the GUI
 */
public class OceanExplorer extends Application {
	
	final int DIMENSION = 10;         // grid size 10*10
	final int SCALING_FACTOR = 50;
	final int ISLAND_COUNT = 10;
    boolean[][] islandMap;
    Image shipImage;
    ImageView shipImageView;
    OceanMap oceanMap;
    Scene scene;
    Ship ship;
    Pane root;   
    
    /**
     * 	 This method create a 10*10 grid ocean map 
     */
    public void drawMap() {  	
    	 for (int row = 0; row < DIMENSION; row++) {
             for (int col = 0; col < DIMENSION; col++) {
             	Rectangle rect = new Rectangle(row*SCALING_FACTOR, col*SCALING_FACTOR, SCALING_FACTOR, SCALING_FACTOR);
                 rect.setStroke(Color.BLACK);    // Black outline
                 rect.setFill(Color.PALETURQUOISE);   // Background Color of Grid
                 this.root.getChildren().add(rect);      // Add to node tree in the pane
             }
         }
    }
    
    /**  Method to load the ship image on ocean map
     */
    public void loadShipImage() {
    	
    	// Load ship image
    	Image shipImage = new Image("ship.png", 50, 50, true, true);
    	shipImageView = new ImageView(shipImage);
    	   	
    	shipImageView.setX(ship.getShipLocation().getX() * SCALING_FACTOR);
    	shipImageView.setY(ship.getShipLocation().getY() * SCALING_FACTOR);
    	
    	this.root.getChildren().add(shipImageView);   // add shipImageView to root	
    }
    
    /**	Method to enable ship movement (left, right, up, down) on ocean map
     */
    public void startSailing() {
    	islandMap = oceanMap.getIsland();
    	
    	// event: key presses
    	scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
    		
    		@Override
    		public void handle(KeyEvent ke) {
    			switch(ke.getCode()) {
    			case RIGHT : 
    				ship.goEast(islandMap); // move ship rightwards on right arrow key press
    				break;
    			case LEFT : 
    				ship.goWest(islandMap); // move ship leftwards on up left key press
    				break;
    			case UP : 
    				ship.goNorth(islandMap); // move ship upwards on up up key press
    				break;
    			case DOWN :
    				ship.goSouth(islandMap); // move ship downwards on down arrow key press
    				break;
    			default :
    				break;
    			}
    			
    			shipImageView.setX(ship.getShipLocation().x * SCALING_FACTOR);
    			shipImageView.setY(ship.getShipLocation().y * SCALING_FACTOR); 			
    		}
    	});  	
    }
    
    @Override
    public void start(Stage mapStage) throws Exception {
    	
    	// Initialize ship
        ship = new Ship(0,0);
    	
        // Initialize oceanMap
    	oceanMap = new OceanMap(DIMENSION);     	
    	
    	root = new AnchorPane();
    	drawMap();   // call drawMap method to create ocean map on the pane
    	
    	// change root Pane according to island placement on the ocean map
    	this.root = oceanMap.placeIsland(this.root, DIMENSION, ISLAND_COUNT, SCALING_FACTOR);
    	
    	// method called to load ship image on map
    	loadShipImage();
        
    	// setting the scene on the stage
        scene = new Scene(root, 500, 500);
        mapStage.setTitle("Christopher Columbus Sails the Ocean Blue");
        mapStage.setScene(scene);
        mapStage.show();
        
        // method called to set the ship sailing, that is, move up, down, left and right
        startSailing();
    } 
    
    public static void main(String[] args) {
        launch(args);
    }
	
}

