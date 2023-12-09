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
    int tailx = 250;
    int taily = 240;
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
        drawSnake();
        manageTail();
        checkTailCollision();
        drawTail();
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
    	//for( int i = eaten; i >= 0; i--) {
    	int l = 0;
    	for(Segment s : segments) {
    	
    		fill(0, 200, 50);
		rect(s.x, s.y,10,10);
    	
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
    	
    	drawTail();
    	fill(0, 200, 50);
    	
//    	segments.set(0,new Segment(tailx, taily));
//    	
//	   segments.remove(segments.size() - 1);
    	
    	segments.add(new Segment(snakex, snakey));
    	segments.remove(0);
    	checkTailCollision();

    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
    	for(Segment s : segments) {
    		if( head.x == s.x && head.y == s.y) {
    		
    			eaten = 1;
    			segments = new ArrayList<Segment>();
    			// segments.add( new Segment(head.x, head.y));
    		}
    	}
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
        	snakey = snakey - 10;
        	taily = taily - 10;
            checkBoundaries();
        } else if (direction == DOWN) {
            // Move head down
        	snakey= snakey + 10;
        	taily= taily + 10;
        	   checkBoundaries();
                
        } else if (direction == LEFT) {
        	snakex= snakex- 10;
        	tailx= tailx- 10;
        	   checkBoundaries();
            
        } else if (direction == RIGHT) {
        	snakex= snakex + 10;
        	tailx= tailx+ 10;
        	   checkBoundaries();
        }
        
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(snakex >= 500) {
        	snakex = 0;
        	tailx = 0;
        }else if( snakex <= 0) {
        	snakex = 500;
        	tailx = 500;
        }
        
        if(snakey >= 500 ) {
        	snakey = 0;
        	taily = 0;
        }else if( snakey <= 0) {
        	snakey = 500;
        	taily = 500;
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
    	if( snakex == foodx && snakey == foody) {
    		
    		eaten = eaten + 1;
    		dropFood();
    		segments.add(new Segment(tailx, taily));
    	}
        
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
