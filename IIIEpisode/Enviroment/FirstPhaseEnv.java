package Enviroment;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import IIIEpisode.BaseCharacter;
import IIIEpisode.Ken;

public class FirstPhaseEnv extends EnviromentBase {
	// Scenery Constants
	final int FLOOR_Y = 70;
	final int HORIZ_TOLERANCE = 20;
	
	final float GRAV = 9.87f,
				PIXEL_PROPORTION = 0.04F;
	
	// Attributes
	private JFrame frame;
	private BaseCharacter[] characters;
	
	// Constructor
	/*
	 * TODO: Make this constructor depends on the type of the
	 * character that was asked to be constructed.
	 */
	public FirstPhaseEnv(JFrame frame) {
		this.frame = frame;
		
		// initEnviroment();
	}

	// Methods
	@Override
	protected void initEnviroment() {
		characters = new BaseCharacter[2];
		
		characters[0] = new Ken(this, 50, 500);
		characters[1] = new Ken(this, 50, 100);
	}

	@Override
	public void doDrawing (Graphics2D g) {
		int length_ = characters.length;
		
        for (int i = 0; i < length_; i++) {
        	BaseCharacter bc = characters[i];
        	float[] pos = bc.getPosition();
        	
        	g.drawImage(bc.getImage(), (int) pos[0], (int) pos[1], frame);
        }
	}
	
	@Override
	public void update () {
		int length_ = characters.length;
		
		for (int i = 0; i < length_; i++) {
        	characters[i].update();
        }
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
	
	/*
	 * 
	 */
	@Override
	public float[] EnvAcceleration (BaseCharacter bc) {
		float[] acceleration = new float[2];
		
		acceleration[0] = 0;
		
		if (!checkEnviromentCollisionY(bc))
			acceleration[1] = GRAV * PIXEL_PROPORTION;
		else acceleration[1] = 0;
		
		return acceleration;
	}

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

}
