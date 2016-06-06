package Enviroment;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Attacks.NonSpriteAttack;
import Attacks.SpriteAttack;
import IIIEpisode.BaseCharacter;
import IIIEpisode.BaseCharacter.Orientation;
import IIIEpisode.DarthVader;
import IIIEpisode.HealthBar;
import IIIEpisode.Ken;

public class BasePhase extends EnviromentBase {
	// Scenery Constants
	final protected int FLOOR_Y = 70;
	final protected int HORIZ_TOLERANCE = 20;
		
	final protected float GRAV = 11f,
						  PIXEL_PROPORTION = 0.04F;
		
	// Attributes
	protected JFrame frame;
	protected BaseCharacter[] characters;
	
	protected BufferedImage XSprite;
	
	// Constructors
	BasePhase (JFrame frame) {
		this.frame = frame;
		
		initEnviroment();
	}	

	// Methods
	@Override
	protected void initEnviroment() {
		float[] hpPos = new float[2];
		hpPos[0] = 350;
		
		characters = new BaseCharacter[2];
		
		characters[0] = new DarthVader(this, 100, 500);
		characters[1] = new DarthVader(this, 500, 500, true, hpPos);
		
		sAttacks = new ArrayList<SpriteAttack>();
		nsAttacks = new ArrayList<NonSpriteAttack>();
		
		XSprite = GenerateSprite (System.getProperty("user.dir") + "\\Imagens\\DarthVader\\Board\\X.png", .8f);
	}
	
	private BufferedImage GenerateSprite (String imageName, double scale) {
		BufferedImage bigImg = null, aux = null;
		
		try {
    		bigImg = ImageIO.read(new File(imageName));
    		
    		// Resize
    		aux = new BufferedImage(
    				(int) (bigImg.getWidth() * scale), (int) (bigImg.getHeight() * scale), BufferedImage.TYPE_INT_ARGB);
    		Graphics2D graphics2D = aux.createGraphics();
    		AffineTransform xform = AffineTransform.getScaleInstance(scale, scale);
    		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    		graphics2D.drawImage(bigImg, xform, null);
    		graphics2D.dispose();
    		
    		bigImg = aux;
    	} catch (IOException e) {
    		System.err.println(imageName);
    		System.exit(0);
    	}
		
		return bigImg;
	}

	@Override
	public void doDrawing (Graphics2D g) {
		int length_ = characters.length;
		
	    for (int i = 0; i < length_; i++) {
	      	BaseCharacter bc = characters[i];
	       	float[] pos = bc.getPosition();
	       	
	       	g.drawImage(bc.getImage(), (int) pos[0], (int) pos[1], frame);
	       	
	       	pos = bc.getHPBarPos();
	       	
	       	bc.DrawHPBar(g, frame, pos);
	    }
	    
	    g.drawImage(XSprite, 312, 0, frame);
	}
		
	@Override
	public void update () {
		int length_ = characters.length;
		
		for (int i = 0; i < length_; i++) {
	       	characters[i].update();
	    }
		
		/*
		 * TODO: Destroy Attacks that were destroyed.
		 */
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		characters[0].keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		characters[0].keyReleased(e);
	}
	
	@Override
	public float[] EnvAcceleration (BaseCharacter bc) {
		float[] acceleration = new float[2];
		
		acceleration[0] = 0;
		
		if (!checkEnviromentCollisionY(bc))
			acceleration[1] += GRAV * PIXEL_PROPORTION;
		else acceleration[1] += 0;
		
		return acceleration;
	}
	
	@Override
	public float getDamage(BaseCharacter bc) {
		float damage = 0f;
		
		for (NonSpriteAttack nsAttack : nsAttacks) {
			if (nsAttack.collided(bc)) {
				damage += nsAttack.getDamage();
				nsAttack.destroy();
			}
			else {
				if (nsAttack.passedOut())
					nsAttack.destroy();
			}
		}
		
		for (SpriteAttack sAttack : sAttacks) {
			if (sAttack.collided(bc)) {
				damage += sAttack.getDamage();
				sAttack.destroy();
			}
		}
		
		return damage;
	};
	
	@Override
	public float[] getOtherPlayerPosition (BaseCharacter bc) {
		int length_ = characters.length;
		
	    for (int i = 0; i < length_; i++) {
	      	BaseCharacter oBc = characters[i];
	      	
	      	if (oBc != bc)
	      		return oBc.getPosition();
	    }
	    
	    return null;
	};

	@Override
	public boolean checkEnviromentCollisionY (BaseCharacter bc) {
		boolean cEnvCol = checkFloorCollision (bc);
			
		return cEnvCol;
	}
		
	private boolean checkFloorCollision(BaseCharacter bc) {
		Rectangle bounds = bc.getBounds();
		float[] velocity = bc.getVelocity();
			
		return (bounds.getMaxY() >= getFloorHeight()) && (velocity[1] > 0);
	}
		
	@Override
	public boolean checkEnviromentRightCollisionX (BaseCharacter bc) {
		float[] vel = bc.getVelocity();
		Rectangle bounds = bc.getBounds();
			
		return (vel[0] > 0) && (bounds.getMaxX() >= getRightWall());
	}
		
	@Override
	public boolean checkEnviromentLeftCollisionX (BaseCharacter bc) {
		float[] vel = bc.getVelocity();
		
		return (vel[0] < 0) && (bc.getPosition()[0] <= getLeftWall());
	}
		
	@Override
	public float getFloorHeight () {
		return frame.getHeight() - FLOOR_Y;
	}
		
	@Override
	public float getLeftWall () {
		return HORIZ_TOLERANCE;
	}
		
	@Override
	public float getRightWall () {
		return frame.getWidth() - HORIZ_TOLERANCE;
	}
		
	@Override
	public float getAllPlayersInteraction (BaseCharacter bc) {
		float position = 0f;
		
		for (int i = 0; i < characters.length; i++) {
			BaseCharacter ch = characters[i];
				
			if (ch != bc) {
				float pos2p = getPlayersInteraction(bc, ch);
				position += pos2p;
			}
		}
		
		return position;
	}
	
	@Override
	public boolean DeadPlayerExist() {
		for (int i = 0; i < characters.length; i++) {
			if (characters[i].IsDead())
				return true;
		}
		
		return false;
	};
	
	public String WinningPlayer () {
		if (characters[0].IsDead())
			return "Player 1";
		else
			return "Player 2";
	}
	
	@Override
	public float getPlayersInteraction (BaseCharacter gP, BaseCharacter pP) {
		// First is the player who suffers the force.
		float position = -1f;
		
		float[] gVel = gP.getVelocity();
		float[] pVel = pP.getVelocity();
		
		Rectangle gBounds = gP.getBounds(),
				  pBounds = pP.getBounds();
		
		if (gBounds.intersects(pBounds)) {
			if (gBounds.x < pBounds.x && gVel[0] > pVel[0]) {
				position = (gBounds.x + (float) pBounds.getMaxX()) / 2;
				position -= gBounds.width;
			}
			else if (gBounds.x > pBounds.x && gVel[0] < pVel[0]) {
				position = (pBounds.x + (float) gBounds.getMaxX()) / 2;
			}
		}
		
		return position;
	}
	
	@Override
	public Orientation getPlayerOrientation (BaseCharacter bc) {
		float bcX = bc.getX();
		
		for (int i = 0; i < characters.length; i++) {
			BaseCharacter ch = characters[i];
			
			if (ch != bc) {
				if (ch.getX() > bcX) return Orientation.RIGHT;
			}
		}
		
		return Orientation.LEFT;
	}

}
