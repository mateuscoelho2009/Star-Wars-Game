/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman2;
import javax.swing.JFrame;
/**
 *
 * @author Leonardo Araujo
 */
public class Pacman2 extends JFrame implements Commons{
    
    public Pacman2() 
    {
        add(new Board());
        setTitle("PacMan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BLOCK_SIZE*NUMBER_OF_SPOTS + BORDER_WIDTH, BLOCK_SIZE*NUMBER_OF_SPOTS + BORDER_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Pacman2();
    }
    
}
