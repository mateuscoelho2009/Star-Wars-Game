package IIIEpisode;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import Common.Sprite;

/*
 * That is the Character's base class. It will contain the base for
 * the development of subsequent characters.
 */
public abstract class BaseCharacter extends Sprite implements Common.HasMoveset {
	// Constants
	enum State { STOP, BLOCKING, AIR };
	
	// Attributes
	protected float[] velocity,
		    	      acceleration;
	
	protected float mass;
	
	protected State charState;
	
	protected int life,
				  power;
	
	// Constructors
	BaseCharacter (int x, int y) {
		super(x, y);
		
		life = 0;
		
		velocity = new float[2];
		velocity[X] = 0; velocity[Y] = 0;
		
		acceleration = new float[2];
		acceleration[X] = 0; acceleration[Y] = 0;
	}
	
	BaseCharacter () {
		super();
		
		life = 0;
		
		velocity = new float[2];
		velocity[X] = 0; velocity[Y] = 0;
		
		acceleration = new float[2];
		acceleration[X] = 0; acceleration[Y] = 0;
	}
	
	// Methods	
	public BufferedImage getImage () {
		return super.getImage();
	}
	
	public float[] getPosition() {
		return position;
	}
	
	public float[] getVelocity() {
		return velocity;
	}	
}
