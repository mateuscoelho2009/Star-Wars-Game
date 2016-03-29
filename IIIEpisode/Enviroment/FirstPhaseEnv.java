package Enviroment;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Common.Animation;
import IIIEpisode.BaseCharacter;
import IIIEpisode.Ken;

public class FirstPhaseEnv extends BasePhase {	
	// Constructor
	/*
	 * TODO: Make this constructor depends on the type of the
	 * character that was asked to be constructed.
	 */
	public FirstPhaseEnv(JFrame frame) {
		super (frame);
		
		initEnviroment();
	}
	
	@Override
	protected void initEnviroment () {
		super.initEnviroment();
		
		try {
			String strPath = System.getProperty("user.dir") + "\\src\\Images";
			loadImage(strPath + "\\ken-sprite-sheet.png");
		} catch (Exception e) {
			System.err.println(e);
		}
		
		animation.start();
	}
	
	private void loadImage (String imageName) {
		BufferedImage bigImg = null;
		
		try {
    		bigImg = ImageIO.read(new File(imageName));
    	} catch (IOException e) {
    		System.err.println("Image not found in: " + imageName);
    		System.exit(0);
    	}
		
		scenary = new BufferedImage[1];
		
		scenary[0] = bigImg.getSubimage(
	            30,
	            800,
	            380,
	            220
	        );
		
		animation = new Animation(scenary, 10);
	}
	
	@Override
	public void doDrawing (Graphics2D g2d) {
		g2d.drawImage(animation.getSprite(), 0, 0, frame.getWidth(), frame.getHeight(), frame);
		
		super.doDrawing(g2d);
	}
}
