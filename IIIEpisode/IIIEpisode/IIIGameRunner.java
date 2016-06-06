package IIIEpisode;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class IIIGameRunner extends JFrame {
    
	final int HEIGHT = 500,
			  WIDTH = 700;
	
	IIIGameRunner() {
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
	
	/*public static void main(String[] args) {
	    
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {                
	            IIIGameRunner ex = new IIIGameRunner();
	            ex.setVisible(true);
	        }
	    });
	}*/
}
