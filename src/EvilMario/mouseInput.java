package EvilMario;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import IIIEpisode.IIIGameRunner;
import IIIEpisode.IIIGameRunnerMult;
import pacman2.Pacman2;

public class mouseInput implements MouseListener{
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        Component frame = null;

        if(my>=570 && my<=600) {
            if(mx>=10 && mx<=310) {
            	if(board.State == board.STATE.MENU) board.State = board.STATE.GAME;
            }
            if(mx>=325 && mx<=625) {
            	
            	if(board.State == board.STATE.MENU) board.State = board.STATE.MENUSF;
            	else IIIGameRunner.main(new String[0]); //1 player

            }
            if(mx>=645 && mx<=945) {
            	
            	if(board.State == board.STATE.MENU)
					try {
						Pacman2.main(new String[0]);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				else{ 
            		IIIGameRunnerMult.main(new String[0]); //2 players
            	}
            }
            if(mx>=960 && mx<=1260) {
            	
            	if(board.State == board.STATE.MENU) JOptionPane.showMessageDialog(frame, "Space");
            }
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}