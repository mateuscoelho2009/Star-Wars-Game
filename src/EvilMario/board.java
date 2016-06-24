package EvilMario;                                                                           //Include this class in the EvilMario game package

import java.awt.*;                                                                           //Imported to allow use of Image
import java.awt.event.*;                                                                     //Imported to allow use of ActionListener

import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class board extends JPanel implements ActionListener {
        player p;
        oponent o[];
        Image background, menuBg; 
        int count = 0;
        Timer time;                                                                              //A timer
        private menu Menu;
        private MenuSFplayers menuSF;
        private frame Frame;
        
        

        public static enum STATE {MENU,MENUSF,GAME, GAMEOVER, PARABENS};

        public static STATE State = STATE.MENU;

        public board() {
                this.addMouseListener(new mouseInput());
                p = new player();
                o = new oponent[10];
                o[0] = new oponent(600);
                o[1] = new oponent(800);
                o[2] = new oponent(1000);
                o[3] = new oponent(1100);
                o[4] = new oponent(1200);
                o[5] = new oponent(1300);
                o[6] = new oponent(1350);
                o[7] = new oponent(1400);
                o[8] = new oponent(1450);
                o[9] = new oponent(1500);
                
                o[1].playerFacingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oLps.png");  //Image for player while he is     turning left
                o[1].playerFacingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oRps.png");//Image for player while he is turning right
                o[1].playerWalkingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oLs.png");
                o[1].playerWalkingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oRs.png");
                
                o[4].playerFacingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oLps.png");  //Image for player while he is     turning left
                o[4].playerFacingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oRps.png");//Image for player while he is turning right
                o[4].playerWalkingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oLs.png");
                o[4].playerWalkingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oRs.png");
                
                o[7].playerFacingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oLps.png");  //Image for player while he is     turning left
                o[7].playerFacingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oRps.png");//Image for player while he is turning right
                o[7].playerWalkingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oLs.png");
                o[7].playerWalkingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/oRs.png");
                
                o[2].playerFacingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stLp.png");  //Image for player while he is     turning left
                o[2].playerFacingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stRp.png");//Image for player while he is turning right
                o[2].playerWalkingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stL.png");
                o[2].playerWalkingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stR.png");
                
                o[5].playerFacingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stLp.png");  //Image for player while he is     turning left
                o[5].playerFacingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stRp.png");//Image for player while he is turning right
                o[5].playerWalkingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stL.png");
                o[5].playerWalkingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stR.png");
                
                o[8].playerFacingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stLp.png");  //Image for player while he is     turning left
                o[8].playerFacingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stRp.png");//Image for player while he is turning right
                o[8].playerWalkingLeft = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stL.png");
                o[8].playerWalkingRight = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/stR.png");
                
                Menu = new menu();
                menuSF = new MenuSFplayers();

                addKeyListener(new AL());                                                            //Listen for keys
                setFocusable(true);
                ImageIcon i = null;
                //Allows movement
                try {
                	i = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/Menu.png");                  //Image for menu
                } catch (Exception e) {
                	System.out.println(e);
                	System.exit(1);
                }
          
                menuBg = i.getImage();
                i = new ImageIcon(System.getProperty("user.dir") + "/Imagens/Mario/Background.png");  //Image for background
                background = i.getImage();                                                           //Give the background the image
                time = new Timer(20,this);                                                           //Timer set to update "this" class every 20 milliseconds(Approximately 50fps)
                time.start();                                                                        //Actually start the timer
        }

        public void actionPerformed(ActionEvent e) {
                p.move();
                p.jump();
                o[0].move();
                o[1].move();
                o[2].move();
                o[3].move();
                o[4].move();
                o[5].move();
                o[6].move();
                o[7].move();
                o[8].move();
                o[9].move();

                repaint();                                                                        //Repaint
        }

        public void paintComponent(Graphics g) {                                                 //Graphics method
                if(State==STATE.GAME) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    for(int i=0 ; i< count+1; i++){       
                        		int col = Colisao(p,o[i]);
                                
                                System.out.println("Colisão:" + col + " morto:"+ o[1].isdead);
                                
                                if(col == 0 && !o[i].isdead){
	                                g2d.drawImage(background, -p.nx, 0, null);                                   //Draw the background image
	                                g2d.drawImage(background, -p.nx2, 0, null);
	                                
	                                g2d.drawImage(o[i].getImage(), o[i].getX()-p.nx, 285, null);
	
	                                if(-p.nx<-1280)                                                              //If going forwards
	                                        p.nx=-1280;                                                              //Start placing forwards every 575px in front on the last one
	                                else if(-p.nx>1280)                                                          //If going backwards
	                                        p.nx=1280;                                                               //Start placing backwards every 575px behind the last one
	
	                                if(-p.nx2<-1280)                                                             //If going forwards
	                                        p.nx2=-1280;                                                             //Start placing forwards every 575px in front on the last one
	                                else if(-p.nx2>1280)                                                         //If going backwards
	                                        p.nx2=1280;                                                           //Start placing backgrounds every 575px behind the last one
	
	                                g2d.drawImage(p.getImage(), p.getX(), p.getY(), null);
	                                
	                                col = Colisao(p,o[i]);
	                                
                                }
                                else if(col == 1 && !o[i].isdead){
                                	g2d.drawImage(background, -p.nx, 0, null);                                   //Draw the background image
	                                g2d.drawImage(background, -p.nx2, 0, null);
	                                
	                                o[i].isdead = true;
	                                count++;
	                                
	                                if(-p.nx<-1280)                                                              //If going forwards
	                                        p.nx=-1280;                                                              //Start placing forwards every 575px in front on the last one
	                                else if(-p.nx>1280)                                                          //If going backwards
	                                        p.nx=1280;                                                               //Start placing backwards every 575px behind the last one
	
	                                if(-p.nx2<-1280)                                                             //If going forwards
	                                        p.nx2=-1280;                                                             //Start placing forwards every 575px in front on the last one
	                                else if(-p.nx2>1280)                                                         //If going backwards
	                                        p.nx2=1280;                                                              //Start placing backgrounds every 575px behind the last one
	
	                                g2d.drawImage(p.getImage(), p.getX(), p.getY(), null);
	                                
	                                col = Colisao(p,o[i]);
                                }
                                else if(col == 0 && o[i].isdead){
                                	g2d.drawImage(background, -p.nx, 0, null);                                   //Draw the background image
	                                g2d.drawImage(background, -p.nx2, 0, null);
	                                
	                                if(-p.nx<-1280)                                                              //If going forwards
	                                        p.nx=-1280;                                                              //Start placing forwards every 575px in front on the last one
	                                else if(-p.nx>1280)                                                          //If going backwards
	                                        p.nx=1280;                                                               //Start placing backwards every 575px behind the last one
	
	                                if(-p.nx2<-1280)                                                             //If going forwards
	                                        p.nx2=-1280;                                                             //Start placing forwards every 575px in front on the last one
	                                else if(-p.nx2>1280)                                                         //If going backwards
	                                        p.nx2=1280;                                                              //Start placing backgrounds every 575px behind the last one
	
	                                g2d.drawImage(p.getImage(), p.getX(), p.getY(), null);
	                                
	                                col = Colisao(p,o[i]);
                                }
                                else if(col == 2 &&  !o[i].isdead){
                                	State = STATE.GAMEOVER;
                                }
                                else if(count == 10){
                                	State = STATE.PARABENS;
                                }
                }
                            	Font fnt0 = new Font("Proxima Nova", Font.BOLD, 15);
                            	g.setFont(fnt0);
                            	g.setColor(Color.WHITE);
                                g.drawString("MORTES:" + count, 20, 20);
                }
                else if (State==STATE.MENU) {
                        g.drawImage(menuBg, 0, 0, null);
                        menu.render(g);        
                } 
                else if(State == STATE.GAMEOVER){
                	g.drawImage(menuBg, 0, 0,null);
                	Font fnt0 = new Font("Proxima Nova", Font.BOLD, 80);
                	g.setFont(fnt0);
                	g.setColor(Color.WHITE);
                    g.drawString("GAME OVER", 400, 650);
                }
                else if(State == STATE.PARABENS){
                	g.drawImage(menuBg, 0, 0,null);
                	Font fnt0 = new Font("Proxima Nova", Font.BOLD, 80);
                	g.setFont(fnt0);
                	g.setColor(Color.WHITE);
                    g.drawString("PARABÉNS!!", 400, 650);
                }
                else{
                	g.drawImage(menuBg, 0, 0, null);
                    MenuSFplayers.render(g);
                }
        }

        private class AL extends KeyAdapter {                                                    //Action Listener extends key adapter
                public void keyPressed(KeyEvent e) {                                                 //On key press
                        p.keyPressed(e);                                                                 //Send whatever key was pressed  TO the keyPressed  method in the player class
                }
                public void keyReleased(KeyEvent e) {                                                //On key release
                        p.keyReleased(e);                                                                //Send whatever key was released TO the keyReleased method in the player class
                }
        }
        

	int Colisao(player p, oponent o)
	{	
		int left1, left2, right1, right2, top1, top2, bottom1, bottom2;

	      left1 = p.x;
	      left2 = o.x-p.nx;
	      right1 = p.x + p.largura;
	      right2 = o.x-p.nx + o.largura;
	      top1 = p.y;
	      top2 = o.y;
	      bottom1 = p.y + p.altura;
	      bottom2 = o.y + o.altura;
	      
	      System.out.println("Começa");
	      System.out.println("bottom1:"+bottom1+" top2:"+top2);
	      System.out.println("Right1:" +right1 +" left1:"+left1);
	      System.out.println("Right2:" +right2 +" left1:"+left2);
	      System.out.println("Acaba");
	      
	
	/*Teste de rejeição para colisão de polígonos circundantes*/
	      if (bottom1 < top2) return(0);
	      if (top1 > bottom2) return(0);
	
	      if (right1 < left2) return(0);
	      if (left1 > right2) return(0);
	      
	      if (o.isdead) return(0);
	      
	/*Teste de colisão efetiva*/
	      if (bottom1 >= top2 -30 && bottom1 <= top2+30 && right1 >= left2 && right1 <= right2){
	    	  return(1);
	      }
	      else if(bottom1> top2 && right1 >= left2 && right1 <= right2){
	    	  return(2);
	      }
	      else if(bottom1> top2 && left1 <= right2 && left1 >= left2){
	    	  return(2);
	      }
	      else return(0);
	
	};

}