package IIIEpisode;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class IIIGameRunner extends JFrame {
    
	IIIGameRunner() {
	    initUI();
	}
	
	private void initUI() {
	
	    add(new IIIGameBoard());
	    
	    setSize(400, 300);
	    setResizable(false);
	    // pack();
	    
	    setTitle("Star Wars III Episode");
	    setLocationRelativeTo(null);        
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
	    
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {                
	            IIIGameRunner ex = new IIIGameRunner();
	            ex.setVisible(true);                
	        }
	    });
	}
}
