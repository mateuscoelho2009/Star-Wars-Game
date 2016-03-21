package Enviroment;

import javax.swing.JFrame;

import IIIEpisode.BaseCharacter;

public class FirstPhaseEnv extends EnviromentBase {
	final int FLOOR_Y = 10;
	final int HORIZ_TOLERANCE = 10;
	private JFrame frame;
	
	public FirstPhaseEnv(JFrame frame) {
		this.frame = frame;
	}

	@Override
	protected void initEnviroment() {
		
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
		
		boolean leftCol = (vel[0] < 0) && (pos[0] >= HORIZ_TOLERANCE),
				rightCol = (vel[0] > 0) && (pos[0] <= frame.getWidth() - HORIZ_TOLERANCE);
		
		return (leftCol || rightCol);
	}

	private boolean checkFloorCollision(BaseCharacter bc) {
		float[] pos = bc.getPosition();
		// TODO: Fix this.
		return (pos[1] >= frame.getHeight() - FLOOR_Y);
	}

}
