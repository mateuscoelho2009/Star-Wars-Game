package Enviroment;

import java.awt.Rectangle;

import javax.swing.JFrame;

import IIIEpisode.BaseCharacter;

public class FirstPhaseEnv extends EnviromentBase {
	final int FLOOR_Y = 70;
	final int HORIZ_TOLERANCE = 20;
	
	// Scenery Constants
	final float GRAV = 9.87f,
				PIXEL_PROPORTION = 0.04F;
	
	private JFrame frame;
	
	// Constructor
	public FirstPhaseEnv(JFrame frame) {
		this.frame = frame;
	}

	// Methods
	@Override
	protected void initEnviroment() {
		
	}
	
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
