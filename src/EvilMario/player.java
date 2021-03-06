package EvilMario;                                                                               //Include this class in the EvilMario game package

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class player {
	int counter = 0;
	int time = 0;
	static int limit = 4;
	static int limit2 = 3;
	static int gravity = 1;
	static int down = 285;
    int x, dx, y, dy, nx, nx2, largura, altura, distanceTraveled;                                                     //x coordinate,change in x coordinate,y coordinate,1st rep bg,2nd rep bg,dist traveled
    Image player;                                                                                //The player variable
    ImageIcon playerFacingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeLeft.png");  //Image for player while he is     turning left
    ImageIcon playerFacingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeRight.png");//Image for player while he is turning right
    ImageIcon playerWalkingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeWalkingLeft.png");
    ImageIcon playerWalkingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeWalkingRight.png");
    ImageIcon playerPrepairUpLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukePrepairUpLeft.png");
    ImageIcon playerUpLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeUpLeft.png");
    ImageIcon playerPrepairUpRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukePrepairUpRight.png");
    ImageIcon playerUpRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeUpRight.png");
    ImageIcon playerDownLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeDownLeft.png");
    ImageIcon playerDownRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeDownRight.png");
    
        public player() {
            player = playerFacingRight.getImage();                                               //Give the player the image
            x = 75;                                                                              //The original x position of the player
            y = 285;  
            largura = 41;
            altura = 66;
            nx = -0;                                                                             //Repeating background 1
            nx2 = -1280;                                                                          //Repeating background 2
            distanceTraveled = 24;
        }

        public void move() {
            if(x>0 && x<300) {                                                                   //If the player is within the moving area
                x = x+dx;                                                                        //The x position is updated to become itself+the amount you moved
                nx = nx+dx;                                                                      //Place the repeating background at regular speed
                nx2 = nx2+dx;
            }
            
            if(x<=0) {                                                                           //If the player has reached he very left side of the screen(0px)
                x=1;                                                                             //Move him up a pixel so he can move again
                nx = nx+(dx*(int)0.5);                                                           //Place the background at a slower speed since Mario stops moving
                nx2 = nx2+(dx*(int)0.5);                                                         //Place the background at a slower speed since Mario stops moving
            }
            if(x>=300) {                                                                         //If the player has reached the center of the screen(300px)
                x=299;                                                                           //Move him down a pixel so he can move again
                nx = nx+(dx*(int)0.5);                                                           //Place the background at a slower speed since Mario stops moving
                nx2 = nx2+(dx*(int)0.5);                                                         //Place the background at a slower speed since Mario stops moving
            }
            if(distanceTraveled>104)x=299;
            if(x==1 && dx<0)
                distanceTraveled++;
            if(distanceTraveled<104){
                nx=0;
                nx2=-1280;
            }
            if (dx > 0){
            	counter++;
        		distanceTraveled++;
            	if (player == playerFacingRight.getImage() && counter%(limit+1) == limit){
            		player = playerWalkingRight.getImage();
            	}
            	else if (player == playerWalkingRight.getImage() && counter%(limit+1) == limit){
            		player = playerFacingRight.getImage();
            	}
            }
            else if (dx < 0){
            	counter++;
        		distanceTraveled--;
            	if (player == playerFacingLeft.getImage() && counter%(limit+1) == limit)
            		player = playerWalkingLeft.getImage();
            	else if (player == playerWalkingLeft.getImage() && counter%(limit+1) == limit)
            		player = playerFacingLeft.getImage();
            }
        }
        
        public void jump() {

        	if (y>0 && y<=285){
        		counter++;
        		time++;
        		y = y + dy + gravity*time;
        		if(player == playerPrepairUpLeft.getImage() && counter%(limit2+1) == limit2)
            		player = playerUpLeft.getImage();
            	else if(player == playerPrepairUpRight.getImage() && counter%(limit2+1) == limit2)
            		player = playerUpRight.getImage();
        	}
        	if (y > 285 && player != playerDownLeft.getImage() && player != playerDownRight.getImage()){
        		y = 285;
        	}
        	else if (player == playerDownLeft.getImage() || player == playerDownRight.getImage()){
        		y = 299;
        	}
        }
        
        public int   getX()     { return x;      }                                               //This method will return the x.      Is used by other classes
        public int   getY()     { return y;      }                                               //This method will return the y.      Is used by other classes
        public Image getImage() { return player; }                                               //This method will return the player. Is used by other classes

        public void keyPressed(KeyEvent e) {                                                     //Called from the board class, the argument is whatever key was pressed
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
            	player = playerWalkingLeft.getImage();
            	if(distanceTraveled<104)dx=-4;else dx=-3;
            }

            if(key == KeyEvent.VK_RIGHT) {
            	player = playerWalkingRight.getImage();
            	if(distanceTraveled<104)dx=4;else dx=3;
            }
            if (key == KeyEvent.VK_UP){
            	time = 0;
            	if(player == playerWalkingLeft.getImage() || player == playerFacingLeft.getImage()){
            		player = playerPrepairUpLeft.getImage();
            		y = down;
            	}
            	else if(player == playerWalkingRight.getImage() || player == playerFacingRight.getImage()){
            		player = playerPrepairUpRight.getImage();
            		y = down;
            	}
            	if (y >= 142)
            		dy = -15;
            	else
            		dy=0;
            }
            if (key == KeyEvent.VK_DOWN){
            	if(player == playerWalkingLeft.getImage() || player == playerFacingLeft.getImage()){
            		player = playerDownLeft.getImage();
            		y = down;
            	}
            	else if(player == playerWalkingRight.getImage() || player == playerFacingRight.getImage()){
            		player = playerDownRight.getImage();
            		y = down;
            	}
            	y = y+14;
            }
        }
        public void keyReleased(KeyEvent e) {                                                    //Called from the board class, the argument is whatever key was released
           int key = e.getKeyCode();                                                           //The key originally sent from the board class
           if(key == KeyEvent.VK_LEFT ) {                             //If the left or right key was released
               player = playerFacingLeft.getImage();
               dx = 0;   
           }
           else if(key == KeyEvent.VK_RIGHT){
               player = playerFacingRight.getImage();
               dx = 0; 
           }
           else if(key == KeyEvent.VK_UP){
        	   if(player == playerUpLeft.getImage())
        		   player = playerFacingLeft.getImage();
        	   else if(player == playerUpRight.getImage())
        		   player = playerFacingRight.getImage();
           }
           else if(key == KeyEvent.VK_DOWN){
        	   if(player == playerDownLeft.getImage())
        		   player = playerFacingLeft.getImage();
        	   else if(player == playerDownRight.getImage())
        		   player = playerFacingRight.getImage();
        	   y = 285;
           }
       }
}