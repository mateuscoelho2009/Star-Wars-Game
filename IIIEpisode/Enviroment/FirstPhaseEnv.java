package Enviroment;

import java.awt.Rectangle;

import javax.swing.JFrame;

import IIIEpisode.BaseCharacter;

public class FirstPhaseEnv extends EnviromentBase {
	final int FLOOR_Y = 70;
	final int HORIZ_TOLERANCE = 10;
	
	// Scenery Constants
	final float GRAV = 9.87f,
				PIXEL_PROPORTION = 0.04F;
	
	private JFrame frame;
	
	public FirstPhaseEnv(JFrame frame) {
		this.frame = frame;
	}

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
	
	@Override
	public boolean checkEnviromentCollisionX (BaseCharacter bc) {
		float[] pos = bc.getPosition();
		float[] vel = bc.getVelocity();
		Rectangle bounds = bc.getBounds();
		
		boolean leftCol = (vel[0] < 0) && (pos[0] >= HORIZ_TOLERANCE),
				rightCol = (vel[0] > 0) && (pos[0] <= frame.getWidth() - HORIZ_TOLERANCE);
		
		return (leftCol || rightCol);
	}

	private boolean checkFloorCollision(BaseCharacter bc) {
		float[] pos = bc.getPosition();
		Rectangle bounds = bc.getBounds();
		
		//System.out.println((bounds.getMaxY() >= frame.getHeight() - FLOOR_Y));
		
		// TODO: Fix this.
		return (bounds.getMaxY() >= frame.getHeight() - FLOOR_Y);
	}
	
	public float getFloorHeight () {
		return frame.getHeight() - FLOOR_Y;
	}

}
