package IIIEpisode;

import java.util.Vector.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/*
 * That is the Character's base class. It will contain the base for
 * the development of subsequent characters.
 */
public abstract class BaseCharacter implements Common.HasMoveset {
	// Constants
	public final int X = 0,
					 Y = 1;
	
	// Attributes
	protected float[] position,
		    	    velocity;
	
	protected int life,
				  power;
	
	protected Image sprite;
	
	// Constructors
	BaseCharacter () {
		life = 0;
		
		position = new float[2];
		position[X] = 0; position[Y] = 0;
		
		velocity = new float[2];
		velocity[X] = 0; velocity[Y] = 0;
	}
	
	// Methods
	public Image getImage () {
		return sprite;
	}
	
	public float[] getPosition() {
		return position;
	}
	
	public float[] getVelocity() {
		return velocity;
	}	
}
