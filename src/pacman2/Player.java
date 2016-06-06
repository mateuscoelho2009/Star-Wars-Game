/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman2;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

/**
 *
 * @author Leonardo Araujo
 */
public class Player extends Sprite implements Commons{
    private final int START_Y = 0; 
    private final int START_X = 0;
    private int width;
    private int height;
    private final String player = "pacman.png";
    
    public Player(){
        ImageIcon ii = new ImageIcon(this.getClass().getResource(player));
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }
    
    public void act(){
        x += dx;
        y += dy;
//        if(x < 2)
//            x = 2;
//        if(x  + width + BORDER_WIDTH + 2 >= BOARD_WIDTH)
//            x = BOARD_WIDTH - width - BORDER_WIDTH - 2;
//        if(y < 2)
//            y = 2;
//        if(y + height + BORDER_HEIGHT +2 >= BOARD_HEIGHT)
//            y = BOARD_HEIGHT - height - BORDER_HEIGHT - 2;
        x_spot = (x + 11)/22;
        y_spot = (y + 11)/22;
    }
    
    public void keyPressed (KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT)
        {
            dx = -2;
            dy = 0;
        }
        else if(key == KeyEvent.VK_RIGHT){
            dx = 2;
            dy = 0;
        }
        if(key == KeyEvent.VK_UP){
            dx = 0;
            dy = -2;
        }
        else if(key == KeyEvent.VK_DOWN){
            dy = 2;
            dx= 0;
        }
    }
    
}
