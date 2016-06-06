/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman2;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Leonardo Araujo
 */
public class Board extends JPanel implements Runnable,Commons,ActionListener{
    private Dimension d;
    private int[][] spots = {{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                             {-1,23,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,25,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-3,-2,-4,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,-5,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,31,26,26,26,26,26,26,26,27,26,26,26,26,26,26,26,26,33,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,29,-1,-1,-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,29,-1},
                             {-1,30,26,26,26,26,26,26,26,34,26,26,26,26,26,26,26,26,32,-1},
                             {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}};
    private boolean ingame = false;
    private int pacmanx;
    private int pacmany;
    private int pacmanxspot;
    private int pacmanyspot;
    private int pacmandx;
    private int pacmandy;
    private char pacmandir;
    private char[] ghostdir;
    private char[] directions;
    private int maxghosts = 3;
    private int[] ghostx,ghosty,ghostxspot,ghostyspot;
    private Image pacman,ghost;
    private Random decide_direction;
    private Thread animator;
    private int[] count;
    private boolean[] ghostingame;
    private Timer timer;
    private String message = "Game Over";
    
    public Board(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setPreferredSize(d);
        setBackground(Color.black);
        initVariables();
        gameInit();
        setDoubleBuffered(true);
    }
    public void initVariables(){
        pacman = new ImageIcon(this.getClass().getResource("pacman.png")).getImage();
        ghost = new ImageIcon(this.getClass().getResource("ghost.png")).getImage();
        pacmandx = 2;
        pacmandy = 2;
        pacmanxspot = 1;
        pacmanyspot = 1;
        ghostxspot = new int [] {8,9,10};
        ghostyspot = new int [] {7,7,7};
        pacmanx = pacmanxspot*BLOCK_SIZE + 4;
        pacmany = pacmanyspot*BLOCK_SIZE + 4;
        ghostx = new int[maxghosts];
        ghosty = new int[maxghosts];
        for(int i=0;i<maxghosts;i++){
            ghostx[i] = ghostxspot[i]*BLOCK_SIZE + 4;
            ghosty[i] = ghostyspot[i]*BLOCK_SIZE + 4;
        }
        decide_direction = new Random();
        directions = new char[4];
        directions[0] = 'l';
        directions[1] = 'r';
        directions[2] = 'd';
        directions[3] = 'u';
        count = new int[] {20,20,20};
        ghostdir = new char[maxghosts];
        ghostingame = new boolean[] {false,false,false};
        timer = new Timer(5000, this);
        timer.start();
    }
    @Override
    public void addNotify() {
        super.addNotify();
        gameInit();
    }
    public void gameInit(){
        ingame = true;
        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }
    public void movePlayer(){
        if(spots[pacmanyspot][pacmanxspot] > 20)
            spots[pacmanyspot][pacmanxspot] = spots[pacmanyspot][pacmanxspot] - 20;
        if(pacmandir == 'r' && (spots[pacmanyspot][pacmanxspot + 1] >= 0 || pacmanx%BLOCK_SIZE < BLOCK_SIZE - 22 - 4 )){
            pacmanx += pacmandx;
            pacmanxspot = pacmanx/BLOCK_SIZE;
            pacmany = pacmanyspot*BLOCK_SIZE + 4;
        }
        else if(pacmandir == 'l' && (spots[pacmanyspot][pacmanxspot - 1] >= 0 || pacmanx%BLOCK_SIZE > 4)){
            pacmanx -= pacmandx;
            pacmanxspot = (pacmanx + 22)/BLOCK_SIZE;
            pacmany = pacmanyspot*BLOCK_SIZE + 4;
        }
        else if(pacmandir == 'u' && (spots[pacmanyspot - 1][pacmanxspot] >= 0 || pacmany%BLOCK_SIZE > 4)){
            pacmany -= pacmandy;
            pacmanyspot = (pacmany + 22)/BLOCK_SIZE;
            pacmanx = pacmanxspot*BLOCK_SIZE + 4;
        }
        else if(pacmandir == 'd' && (spots[pacmanyspot + 1][pacmanxspot] >= 0 || pacmany%BLOCK_SIZE < BLOCK_SIZE - 22 - 4)){
            pacmany += pacmandy;
            pacmanyspot = pacmany/BLOCK_SIZE;
            pacmanx = pacmanxspot*BLOCK_SIZE + 4;
        }
        for(int i = 0;i<maxghosts;i++){
            if(ghostyspot[i] == pacmanyspot && ghostxspot[i] == pacmanxspot){
                ingame = false;
                timer.stop();
            }
        }
        
        
    }
    public void moveGhosts(Graphics g){
        for(int i = 0;i < maxghosts; i++){
            if(ghostingame[i]){
                if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 1){
                    directions[0] = 'd';
                    directions[1] = 'd';
                    directions[2] = 'd';
                    directions[3] = 'd';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 2){
                    directions[0] = 'r';
                    directions[1] = 'r';
                    directions[2] = 'r';
                    directions[3] = 'r';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 4){
                    directions[0] = 'l';
                    directions[1] = 'l';
                    directions[2] = 'l';
                    directions[3] = 'l';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 8){
                    directions[0] = 'u';
                    directions[1] = 'u';
                    directions[2] = 'u';
                    directions[3] = 'u';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 3){
                    directions[0] = 'd';
                    directions[1] = 'd';
                    directions[2] = 'r';
                    directions[3] = 'r';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 5){
                    directions[0] = 'd';
                    directions[1] = 'd';
                    directions[2] = 'l';
                    directions[3] = 'l';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 9){
                    directions[0] = ghostdir[i];
                    directions[1] = ghostdir[i];
                    directions[2] = ghostdir[i];
                    directions[3] = ghostdir[i];
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 6){
                    directions[0] = ghostdir[i];
                    directions[1] = ghostdir[i];
                    directions[2] = ghostdir[i];
                    directions[3] = ghostdir[i];
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 10){
                    directions[0] = 'r';
                    directions[1] = 'r';
                    directions[2] = 'u';
                    directions[3] = 'u';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 12){
                    directions[0] = 'l';
                    directions[1] = 'l';
                    directions[2] = 'u';
                    directions[3] = 'u';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 7){
                    directions[0] = 'l';
                    directions[1] = 'r';
                    directions[2] = 'd';
                    directions[3] = 'd';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 11){
                    directions[0] = 'r';
                    directions[1] = 'd';
                    directions[2] = 'u';
                    directions[3] = 'r';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 14){
                    directions[0] = 'l';
                    directions[1] = 'r';
                    directions[2] = 'u';
                    directions[3] = 'u';
                }
                
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 13){
                    directions[0] = 'l';
                    directions[1] = 'd';
                    directions[2] = 'u';
                    directions[3] = 'l';
                }
                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 15){
                    directions[0] = 'l';
                    directions[1] = 'r';
                    directions[2] = 'd';
                    directions[3] = 'u';
                }
                if(count[i] == 20){
                    count[i] = 0;
                    ghostdir[i] = directions[decide_direction.nextInt(4)];
                }
                else
                    count[i] ++;
                if(ghostdir[i] == 'r' && (spots[ghostyspot[i]][ghostxspot[i] + 1] >= 0 || ghostx[i]%BLOCK_SIZE < BLOCK_SIZE - 22 - 4 )){
                    ghostx[i] += pacmandx;
                    ghostxspot[i] = ghostx[i]/BLOCK_SIZE;
                    ghosty[i] = ghostyspot[i]*BLOCK_SIZE + 4;
                }
                else if(ghostdir[i] == 'l' && (spots[ghostyspot[i]][ghostxspot[i] - 1] >= 0 || ghostx[i]%BLOCK_SIZE > 4)){
                    ghostx[i] -= pacmandx;
                    ghostxspot[i] = (ghostx[i] + 22)/BLOCK_SIZE;
                    ghosty[i] = ghostyspot[i]*BLOCK_SIZE + 4;
                }
                else if(ghostdir[i] == 'u' && (spots[ghostyspot[i] - 1][ghostxspot[i]] >= 0 || ghosty[i]%BLOCK_SIZE > 4)){
                    ghosty[i] -= pacmandy;
                    ghostyspot[i] = (ghosty[i] + 22)/BLOCK_SIZE;
                    ghostx[i] = ghostxspot[i]*BLOCK_SIZE + 4;
                }
                else if(ghostdir[i] == 'd' && (spots[ghostyspot[i] + 1][ghostxspot[i]] >= 0 || ghosty[i]%BLOCK_SIZE < BLOCK_SIZE - 22 - 4)){
                    ghosty[i] += pacmandy;
                    ghostyspot[i] = ghosty[i]/BLOCK_SIZE;
                    ghostx[i] = ghostxspot[i]*BLOCK_SIZE + 4;
                }
            }
            drawGhost(g,ghostx[i],ghosty[i]);
        }
    }
    public void drawPlayer( Graphics g){
        if(ingame)
            g.drawImage(pacman, pacmanx, pacmany, this);
    }
    public void drawGhost( Graphics g, int x, int y){
        if(ingame)
            g.drawImage(ghost, x, y, this);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0,BLOCK_SIZE*NUMBER_OF_SPOTS, BLOCK_SIZE*NUMBER_OF_SPOTS);
        if(ingame){
            for (int i = 0; i < NUMBER_OF_SPOTS; i++){
                for (int j = 0;j < NUMBER_OF_SPOTS;j++){
    //                if(spots[i][j] == -1){
    //                    g.setColor(Color.white);
    //                    g.fillRect(j*BLOCK_SIZE, i*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
    //                }
                    if(spots[i][j] > 20){
                        g.setColor(Color.gray);
                        g.fillOval(j*BLOCK_SIZE + 10, i*BLOCK_SIZE+10, 10, 10);
                    }
    //                if(spots[i][j]%20 == 1){
    //                    g.setColor(Color.green);
    //                    g.fillRect(j*BLOCK_SIZE - 10, BLOCK_SIZE*i, 10, BLOCK_SIZE);
    //                }
    //                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 2){
    //                    directions[0] = 'r';
    //                    directions[1] = 'r';
    //                    directions[2] = 'r';
    //                    directions[3] = 'r';
    //                }
    //                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 4){
    //                    directions[0] = 'l';
    //                    directions[1] = 'l';
    //                    directions[2] = 'l';
    //                    directions[3] = 'l';
    //                }
    //                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 8){
    //                    directions[0] = 'u';
    //                    directions[1] = 'u';
    //                    directions[2] = 'u';
    //                    directions[3] = 'u';
    //                }
                    if(spots[i][j]%20 == 3){
                        g.setColor(Color.green);
                        g.fillRect(j*BLOCK_SIZE -10 , BLOCK_SIZE*i-10, BLOCK_SIZE +10,10);
                        g.fillRect(j*BLOCK_SIZE - 10, BLOCK_SIZE*i, 10, BLOCK_SIZE);
                    }
                    if(spots[i][j]%20 == 5){
                        g.setColor(Color.green);
                        g.fillRect(j*BLOCK_SIZE, BLOCK_SIZE*i-10, BLOCK_SIZE +10,10);
                        g.fillRect((j+1)*BLOCK_SIZE, BLOCK_SIZE*(i), 10, BLOCK_SIZE);
                  }
                    if(spots[i][j]%20 == 9){
                        g.setColor(Color.green);
                        g.fillRect((j+1)*BLOCK_SIZE, BLOCK_SIZE*(i), 10, BLOCK_SIZE);
                        g.fillRect(j*BLOCK_SIZE - 10, BLOCK_SIZE*i, 10, BLOCK_SIZE);
                    }
                    if(spots[i][j]%20 == 6){
                        g.setColor(Color.green);
                        g.fillRect(j*BLOCK_SIZE, BLOCK_SIZE*i-10, BLOCK_SIZE,10);
                        g.fillRect(j*BLOCK_SIZE, BLOCK_SIZE*(i+1), BLOCK_SIZE, 10);
                    }
                    if(spots[i][j]%20 == 10){
                        g.setColor(Color.green);
                        g.fillRect(j*BLOCK_SIZE-10, BLOCK_SIZE*(i+1), BLOCK_SIZE+10, 10);
                        g.fillRect(j*BLOCK_SIZE - 10, BLOCK_SIZE*i, 10, BLOCK_SIZE);
                    }
                    else if(spots[i][j]%20 == 12){
                        g.setColor(Color.green);
                        g.fillRect((j+1)*BLOCK_SIZE, BLOCK_SIZE*(i), 10, BLOCK_SIZE);
                        g.fillRect(j*BLOCK_SIZE, BLOCK_SIZE*(i+1), BLOCK_SIZE+10, 10);
                    }
    //                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 7){
    //                    directions[0] = 'l';
    //                    directions[1] = 'r';
    //                    directions[2] = 'd';
    //                    directions[3] = 'd';
    //                }
                    if(spots[i][j]%20 == 11){
                        g.setColor(Color.green);
                        g.fillRect(j*BLOCK_SIZE - 10, BLOCK_SIZE*i, 10, BLOCK_SIZE);
                    }
                    if(spots[i][j]%20 == 14){
                        g.setColor(Color.green);
                        g.fillRect(j*BLOCK_SIZE, BLOCK_SIZE*(i+1), BLOCK_SIZE, 10);
                    }
                    if(spots[i][j]%20 == 13){
                        g.setColor(Color.green);
                        g.fillRect((j+1)*BLOCK_SIZE, BLOCK_SIZE*(i), 10, BLOCK_SIZE);
                    }
    //                else if(spots[ghostyspot[i]][ghostxspot[i]]%20 == 15){
    //                    directions[0] = 'l';
    //                    directions[1] = 'r';
    //                    directions[2] = 'd';
    //                    directions[3] = 'u';
    //                }
                    if(spots[i][j] == -2){
                        g.setColor(Color.green);
                        g.fillRect(j*BLOCK_SIZE, BLOCK_SIZE*(i)-10,BLOCK_SIZE, 10);
                    }
                    if(spots[i][j] == -5){
                        g.setColor(Color.green);
                        g.fillRect((j+1)*BLOCK_SIZE, BLOCK_SIZE*(i), 10, BLOCK_SIZE);
                        g.fillRect(j*BLOCK_SIZE - 10, BLOCK_SIZE*i, 10, BLOCK_SIZE);
                    }
                    if(spots[i][j] == -3){
                        g.setColor(Color.green);
                        g.fillRect(j*BLOCK_SIZE - 10, BLOCK_SIZE*i, 10, BLOCK_SIZE);
                        g.fillRect(j*BLOCK_SIZE - 10, BLOCK_SIZE*i-10, BLOCK_SIZE+10, 10);
                        g.fillRect(j*BLOCK_SIZE - 10, BLOCK_SIZE*(i+1), BLOCK_SIZE+10, 10);
                    }
                    if(spots[i][j] == -4){
                        g.setColor(Color.green);
                        g.fillRect((j+1)*BLOCK_SIZE, BLOCK_SIZE*i, 10, BLOCK_SIZE);
                        g.fillRect(j*BLOCK_SIZE, BLOCK_SIZE*i-10, BLOCK_SIZE+10, 10);
                        g.fillRect(j*BLOCK_SIZE, BLOCK_SIZE*(i+1), BLOCK_SIZE+10, 10);
                    }
                }
            }
            animationCycle(g);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }
    public void animationCycle(Graphics g){
        movePlayer();
        drawPlayer(g);
        moveGhosts(g);
    }
    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (ingame) {
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) 
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }
        gameOver();
    }
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && spots[pacmanyspot][pacmanxspot - 1] >= 0)
            {
                pacmandir = 'l';
            }
            else if(key == KeyEvent.VK_RIGHT && spots[pacmanyspot][pacmanxspot + 1] >= 0){
                pacmandir = 'r';
            }
            if(key == KeyEvent.VK_UP && spots[pacmanyspot - 1][pacmanxspot] >= 0){
                pacmandir = 'u';
            }
            else if(key == KeyEvent.VK_DOWN && spots[pacmanyspot + 1][pacmanxspot] >= 0){
                pacmandir = 'd';
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i =0;i<maxghosts;i++){
            if(!ghostingame[i]){
                ghostxspot[i] = 10;
                ghostyspot[i] = 9;
                ghostdir[i] = 'r';
                ghostingame[i] = true;
                break;
            }
        }
    }
    public void gameOver()
    {
        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BLOCK_SIZE*NUMBER_OF_SPOTS, BLOCK_SIZE*NUMBER_OF_SPOTS);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, (BLOCK_SIZE*NUMBER_OF_SPOTS)/2 - 30, (BLOCK_SIZE*NUMBER_OF_SPOTS)-100, 50);
        g.setColor(Color.white);
        g.drawRect(50, (BLOCK_SIZE*NUMBER_OF_SPOTS)/2 - 30, (BLOCK_SIZE*NUMBER_OF_SPOTS)-100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BLOCK_SIZE*NUMBER_OF_SPOTS - metr.stringWidth(message))/2, 
            (BLOCK_SIZE*NUMBER_OF_SPOTS)/2);
    }
}
