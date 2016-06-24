package IIIEpisode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Enviroment.FirstPhaseMult;

public class IIIGameBoardMult extends JPanel implements ActionListener {
    private Timer timer;
    private final int DELAY = 10;
    private JFrame frame;
    FirstPhaseMult fpe;

    public IIIGameBoardMult(JFrame frame) {
        initBoard(frame);
    }
    
    private void initBoard(JFrame frame) {
        this.frame = frame;
    	
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        
        fpe = new FirstPhaseMult(frame);

        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        fpe.doDrawing(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    	fpe.update();
    	
    	if (fpe.DeadPlayerExist()) {
    		String wName = fpe.WinningPlayer();
    		
    		javax.swing.JDialog f=new javax.swing.JDialog();  
    		f.setSize(250,150);  
    		javax.swing.JOptionPane.showMessageDialog(f,"Winning Player: " + wName + ".","Congratulations " + wName + "!!!",javax.swing.JOptionPane.DEFAULT_OPTION);
    		f.setVisible(true);
    		f.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    		
    		fpe = new FirstPhaseMult(frame);
    	}
        
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            fpe.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            fpe.keyPressed(e);
        }
    }
    
    
}
