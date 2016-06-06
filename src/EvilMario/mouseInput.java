package EvilMario;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import IIIEpisode.IIIGameRunner;

public class mouseInput implements MouseListener{
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        Component frame = null;

        if(my>=570 && my<=600) {
            if(mx>=10 && mx<=310) {
                board.State = board.STATE.GAME;
            }
            if(mx>=325 && mx<=625) {
				IIIGameRunner.main(new String[0]);
            }
            if(mx>=645 && mx<=945) {
            	JOptionPane.showMessageDialog(frame, "Pac");
            }
            if(mx>=960 && mx<=1260) {
            	JOptionPane.showMessageDialog(frame, "Space");
            }
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}