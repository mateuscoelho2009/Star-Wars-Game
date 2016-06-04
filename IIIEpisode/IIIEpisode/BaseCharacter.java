package IIIEpisode;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import localInterfaces.UserControlled;
import Common.Sprite;
import Enviroment.EnviromentBase;

/*
 * That is the Character's base class. It will contain the base for
 * the development of subsequent characters.
 */
public abstract class BaseCharacter extends Sprite implements Common.HasMoveset , UserControlled {
	// Constants
	enum State { STOP, WALKING, BLOCKING, AIRRISING, AIRFALLING, MOVE1, MOVE2, DAMAGE, DUCKING, DEAD, DYING };
	public enum Orientation { RIGHT, LEFT };
	
	// Attributes
	protected float[] velocity,
		    	      acceleration;
	
	protected EnviromentBase enviroment;
	
	protected float mass;
	protected HealthBar hpBar;
	
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
	
	public BufferedImage getHPBar () {
		return hpBar.getImage();
	}
	
	public float[] getHPBarPos () {
		float[] pos = new float[2];
		
		pos[0] = hpBar.getX();
		pos[1] = hpBar.getY();
		
		return pos;
	}
	
	public float[] getPosition() {
		return position;
	}
	
	public float[] getVelocity() {
		return velocity;
	}
	
	public boolean update () {
		float damage = enviroment.getDamage(this);
		boolean died = false;
		
		UpdateOrientation();
		
		if (damage > 0) {
			charState = State.DAMAGE;
			died = receiveDamage(damage);
		}
		
		move ();
		
		return died;
	}
	
	private void UpdateOrientation () {
		orientation = enviroment.getPlayerOrientation(this);
	}
	
	private void move() {
		float pos = enviroment.getAllPlayersInteraction(this);
		if (pos >= 0) {
			position[X] = pos;
		}
		
		float floorHeight = enviroment.getFloorHeight () - getImage().getHeight();
		
		if (enviroment.checkEnviromentCollisionY(this)) {
			position[Y] = floorHeight;
		}
		
		if (enviroment.checkEnviromentLeftCollisionX(this)) {
			position[X] = enviroment.getLeftWall();
		}
		
		if (enviroment.checkEnviromentRightCollisionX(this)) {
			position[X] = enviroment.getRightWall() - getImage().getWidth();
		}
		
		if (charState == State.DEAD) {
			velocity[X] = 0;
		}
		
		position[X] += velocity[X];
		position[Y] += velocity[Y];
		
		acceleration = enviroment.EnvAcceleration(this);
		
		velocity[X] += acceleration[X];
		velocity[Y] += acceleration[Y];
		
		if (!animation.Completed() && charState == State.DEAD)
			return;
		
		if (charState == State.DYING) {
			charState = State.DEAD;
			return;
		}
		
		if (charState == State.DUCKING || charState == State.MOVE2)
			charState = State.DUCKING;
		else if (position[Y] < floorHeight && velocity[Y] <= 0)
			charState = State.AIRRISING;
		else if (position[Y] < floorHeight)
			charState = State.AIRFALLING;
		else if (velocity[X] != 0)
			charState = State.WALKING;
		else
			charState = State.STOP;
	}
	
	@Override
	public Rectangle getBounds() {
        return new Rectangle((int) position[X], 
        					 (int) position[Y],
        					 sprites[atuSprite].getWidth(),
        					 sprites[atuSprite].getHeight());
    }
	
	protected boolean receiveDamage (float d) {
		if (life > 0 && life < d) {
			charState = State.DYING;
			System.out.println("Pokemon");
			
			return true;
		}
		
		life -= d;
		hpBar.setDamage(d);
		
		return false;
	}
	
	abstract protected void initHPBar (float maxLife);
	abstract protected void initHPBar (float maxLife, float[] hpBar);
}
