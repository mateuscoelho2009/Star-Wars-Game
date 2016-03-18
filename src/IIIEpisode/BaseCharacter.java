package IIIEpisode;

import java.util.Vector.*;

/*
 * That is the Character's base class. It will contain the base for
 * the development of subsequent characters.
 */
public abstract class BaseCharacter implements Common.HasMoveset {
	// Attributes
	protected float[] position,
		    	    velocity;
	
	protected int life,
				  power;
	
	BaseCharacter () {
		life = 0;
		
		position = new float[2];
		position[0] = 0; position[1] = 0;
		
		velocity = new float[2];
		velocity[0] = 0; velocity[1] = 0;
	}
	
	// Methods
	float[] getPosition() {
		return position;
	}
	
	float[] getVelocity() {
		return velocity;
	}	
}
