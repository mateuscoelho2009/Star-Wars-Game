/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman2;
import java.io.IOException;

import javax.swing.JFrame;
/**
 *
 * @author Leonardo Araujo
 */
public class Pacman2 extends JFrame implements Commons{
    
    public Pacman2() throws IOException 
    {
        add(new BoardMult());
        setTitle("PacMan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BLOCK_SIZE*NUMBER_OF_SPOTS + BORDER_WIDTH, BLOCK_SIZE*NUMBER_OF_SPOTS + BORDER_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        new Pacman2();
    }
    
}
