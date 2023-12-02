package _08_LeagueSnake;



import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    Segment head;
    int foodx;
    int foody;
    int direction = UP;
    int eaten = 0;
    int snakex = 250;
    int snakey = 250;
    ArrayList <Segment> segments = new ArrayList<Segment>();
   
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
        size(500,500);
    }

    @Override
    public void setup() {
        head = new Segment(snakex, snakey);
        frameRate(10);
        dropFood();
    }

    void dropFood() {
        // Set the food in a new random location
    	foodx = ((int)random(50)*10);
    	foody = ((int)random(50)*10);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
        background(200,200,200);
        move();
        drawFood();
        drawTail();
        drawSnake();
        eat();
    }

    void drawFood() {
        // Draw the food
    	fill(200,0,50);
        rect(foodx,foody, 10,10);
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(0, 200, 50);
    	 rect(snakex, snakey, 10,10);
    }

    void drawTail() {
        // Draw each segment of the tail
    	
    	int i = 1;
    	for(Segment s : segments) {
    		
    		fill(0, 200, 50);
    	    rect(s.x, s.y,10,10);
    	    i+=1;
    	}
    	
 
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.
    	checkTailCollision();
    	
    	fill(0, 200, 50);
    	
    	segments.set(0,new Segment(snakex, snakey));
    	drawTail();
	   segments.remove(segments.size() - 1);
	  

    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
        
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
    	
        // Set the direction of the snake according to the arrow keys pressed
        if(keyCode == UP) {
        	direction = UP;
        } else if( keyCode == DOWN) {
        	direction = DOWN;
        	
        } else if( keyCode == RIGHT) {
        	direction = RIGHT;
        }else if( keyCode == LEFT) {
        	direction = LEFT;
        }
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.

        
        if (direction == UP) {
            // Move head up
        	head.y = head.y - 10;
            checkBoundaries();
        } else if (direction == DOWN) {
            // Move head down
        	head.y= head.y + 10;
        	   checkBoundaries();
                
        } else if (direction == LEFT) {
        	head.x= head.x- 10;
        	   checkBoundaries();
            
        } else if (direction == RIGHT) {
        	head.x= head.x + 10;
        	   checkBoundaries();
        }
        
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(snakex >= 500) {
        	snakex = 10;
        }else if( snakex <= 0) {
        	snakex = 490;
        }
        
        if(snakey >= 500) {
        	snakey = 10;
        }else if( snakey <= 0) {
        	snakey = 490;
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
    	if( snakex == foodx && snakey == foody) {
    		
    		eaten = eaten + 1;
    		dropFood();
    		segments.add(new Segment(head.x, head.y));
    	}
        
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
