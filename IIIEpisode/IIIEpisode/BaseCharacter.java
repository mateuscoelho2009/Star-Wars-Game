package IIIEpisode;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import Common.Sprite;
import Enviroment.EnviromentBase;

/*
 * That is the Character's base class. It will contain the base for
 * the development of subsequent characters.
 */
public abstract class BaseCharacter extends Sprite implements Common.HasMoveset {
	// Constants
	enum State { STOP, WALKING, BLOCKING, AIRRISING, AIRFALLING, MOVE1, MOVE2 };
	enum Orientation { RIGHT, LEFT };
	
	// Attributes
	protected float[] velocity,
		    	      acceleration;
	
	protected EnviromentBase enviroment;
	
	protected float mass;
	
	protected State charState;
	protected Orientation orientation;
	
	protected int life,
				  power;
	
	// Constructors
	BaseCharacter (EnviromentBase enviroment, int x, int y) {
		super(x, y);
		
		this.enviroment = enviroment;
		
		life = 0;
		
		velocity = new float[2];
		velocity[X] = 0; velocity[Y] = 0;
		
		acceleration = new float[2];
		acceleration[X] = 0; acceleration[Y] = 0;
		
		charState = State.STOP;
		orientation = Orientation.LEFT;
	}
	
	BaseCharacter (EnviromentBase enviroment) {
		super();
		
		this.enviroment = enviroment;
		
		life = 0;
		
		velocity = new float[2];
		velocity[X] = 0; velocity[Y] = 0;
		
		acceleration = new float[2];
		acceleration[X] = 0; acceleration[Y] = 0;
		
		charState = State.STOP;
		orientation = Orientation.LEFT;
	}
	
	BaseCharacter (EnviromentBase enviroment, int x, int y, State initialState) {
		super(x, y);
		
		this.enviroment = enviroment;
		
		life = 0;
		
		velocity = new float[2];
		velocity[X] = 0; velocity[Y] = 0;
		
		acceleration = new float[2];
		acceleration[X] = 0; acceleration[Y] = 0;
		
		charState = initialState;
		orientation = Orientation.LEFT;
	}
	
	BaseCharacter (EnviromentBase enviroment, State initialState) {
		super();
		
		this.enviroment = enviroment;
		
		life = 0;
		
		velocity = new float[2];
		velocity[X] = 0; velocity[Y] = 0;
		
		acceleration = new float[2];
		acceleration[X] = 0; acceleration[Y] = 0;
		
		charState = initialState;
		orientation = Orientation.LEFT;
	}
	
	// Methods	
	public BufferedImage getImage () {
		
		BufferedImage image = super.getImage();
		
		if (orientation == Orientation.RIGHT) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(- image.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	        image = op.filter(image, null);
		}
        
		return image;
	}
	
	public float[] getPosition() {
		return position;
	}
	
	public float[] getVelocity() {
		return velocity;
	}
	
	public void update () {
		move ();
	}
	
	private void move() {
		float floorHeight = enviroment.getFloorHeight () - getImage().getHeight();
		
		if (position[Y] < floorHeight && velocity[Y] <= 0)
			charState = State.AIRRISING;
		else if (position[Y] < floorHeight)
			charState = State.AIRFALLING;
		else if (velocity[X] != 0)
			charState = State.WALKING;
		else
			charState = State.STOP;
		
		if (enviroment.checkEnviromentCollisionY(this)) {
			position[Y] = floorHeight;
			// velocity[Y] = 0;
		}
		
		if (enviroment.checkEnviromentLeftCollisionX(this)) {
			position[X] = enviroment.getLeftWall();
			//velocity[X] = 0;
		}
		
		if (enviroment.checkEnviromentRightCollisionX(this)) {
			position[X] = enviroment.getRightWall() - getImage().getWidth();
			//velocity[X] = 0;
		}
		
		position[X] += velocity[X];
		position[Y] += velocity[Y];
		
		acceleration = enviroment.EnvAcceleration(this);
		
		velocity[X] += acceleration[X];
		velocity[Y] += acceleration[Y];
	}
	
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
}
