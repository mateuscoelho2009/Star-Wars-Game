package IIIEpisode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Enviroment.FirstPhaseEnv;

public class IIIGameBoard extends JPanel implements ActionListener {

    private Timer timer;
    private Ken ken;
    private final int DELAY = 10;
    private JFrame frame;

    public IIIGameBoard(JFrame frame) {
        initBoard(frame);
    }
    
    private void initBoard(JFrame frame) {
        this.frame = frame;
    	
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        
        FirstPhaseEnv fpe = new FirstPhaseEnv(frame);

        ken = new Ken(fpe, 50, 0);

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
        
        float[] pos = ken.getPosition();
        
        g2d.drawImage(ken.getImage(), (int) pos[0], (int) pos[1], this);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ken.move();
        
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            ken.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            ken.keyPressed(e);
        }
    }
}
