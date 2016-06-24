package IIIEpisode;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class IIIGameRunnerMult extends JFrame {

	final int HEIGHT = 500,
			  WIDTH = 700;
	
	IIIGameRunnerMult() {
	    initUI();
	}
	
	private void initUI() {
	
	    add(new IIIGameBoard(this));
	    
	    setSize(WIDTH, HEIGHT);
	    setResizable(true);
	    // pack();
	    
	    setTitle("Star Wars III Episode");
	    setLocationRelativeTo(null);        
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public static void main(String[] args, boolean mode) {
	    
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {                
	            IIIGameRunnerMult ex = new IIIGameRunnerMult();
	            ex.setVisible(true);
	        }
	    });
	}
}
