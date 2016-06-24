package EvilMario;                                                                               //Include this class in the EvilMario game package

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class oponent {
	int counter = 0;
	int time = 0;
	static int limit = 4;
	static int limit2 = 3;
	static int gravity = 1;
	static int down = 285;
	boolean isdead;
    int x, dx=1, y, dy, nx, largura, altura, nx2, distanceTraveled=0;                                                     //x coordinate,change in x coordinate,y coordinate,1st rep bg,2nd rep bg,dist traveled
    Image player;                                                                                //The player variable
    ImageIcon playerFacingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/opLp.png");  //Image for player while he is     turning left
    ImageIcon playerFacingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/opRp.png");//Image for player while he is turning right
    ImageIcon playerWalkingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/opL.png");
    ImageIcon playerWalkingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/opR.png");
//    ImageIcon playerPrepairUpLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukePrepairUpLeft.png");
//    ImageIcon playerUpLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeUpLeft.png");
//    ImageIcon playerPrepairUpRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukePrepairUpRight.png");
//    ImageIcon playerUpRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeUpRight.png");
//    ImageIcon playerDownLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeDownLeft.png");
//    ImageIcon playerDownRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/LukeDownRight.png");
    
        public oponent(int novox) {
            player = playerFacingRight.getImage();
            largura = 41;
            altura = 66;
            isdead = false;
            x = novox;
            y = 285;                                                                             //The original y position of the player
            nx = -0;                                                                             //Repeating background 1
            nx2 = -575;                                                                          //Repeating background 2
            distanceTraveled = 24;
        }

        public void move() {
        	
            if (dx > 0){
            	counter++;
        		distanceTraveled++;
            	if (player == playerFacingRight.getImage() && counter%(limit+1) == limit){
            		player = playerWalkingRight.getImage();
            		x = x + dx;
            	}
            	else if (player == playerWalkingRight.getImage() && counter%(limit+1) == limit){
            		player = playerFacingRight.getImage();
            		x = x + dx;
            	}
            }
            else if (dx < 0){
            	counter++;
        		distanceTraveled++;
            	if (player == playerFacingLeft.getImage() && counter%(limit+1) == limit){
            		player = playerWalkingLeft.getImage();
            		x = x + dx;
            	}
            	else if (player == playerWalkingLeft.getImage() && counter%(limit+1) == limit){
            		player = playerFacingLeft.getImage();
            		x = x + dx;
            	}
            }
            walk();
        }
        
        public int   getX()     { return x;      }                                               //This method will return the x.      Is used by other classes
        public int   getY()     { return y;      }                                               //This method will return the y.      Is used by other classes
        public Image getImage() { return player; }                                               //This method will return the player. Is used by other classes

        public void walk() {
        	System.out.println(distanceTraveled % 100-50);
           	if((distanceTraveled % 200 - 100) <= 0){
           		dx=-3;
           		if((distanceTraveled % 10 - 5) == 0) player = playerWalkingLeft.getImage();
            }
            	
           	else{
            	dx=3;
            	if((distanceTraveled % 10 - 5) == -1) player = playerWalkingRight.getImage();
            }
        }
}