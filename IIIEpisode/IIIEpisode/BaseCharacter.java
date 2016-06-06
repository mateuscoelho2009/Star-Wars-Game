package IIIEpisode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.WritableRaster;

import javax.swing.JFrame;

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
			if (charState != State.BLOCKING)
				charState = State.DAMAGE;
			else
				damage = damage / 2;
			
			died = receiveDamage(damage);
		}
		
		move ();
		
		return died;
	}
	
	private void UpdateOrientation () {
		if (!animation.Completed() || charState == State.DYING || charState == State.DEAD) {
			return;
		}
		
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
		
		if (charState == State.DEAD || charState == State.DYING) {
			velocity[X] = 0;
		}
		
		position[X] += velocity[X];
		position[Y] += velocity[Y];
		
		acceleration = enviroment.EnvAcceleration(this);
		
		velocity[X] += acceleration[X];
		velocity[Y] += acceleration[Y];
		
		if (!animation.Completed())
			return;
		
		if (charState == State.DYING || charState == State.DEAD) {
			charState = State.DEAD;
			return;
		}
		
		if (charState == State.DUCKING || charState == State.MOVE2)
			charState = State.DUCKING;
		else if (charState == State.BLOCKING)
			charState = State.BLOCKING;
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
		if (life < d && charState != State.DEAD && charState != State.DYING) {
			charState = State.DYING;
			
			return true;
		}
		
		life -= d;
		hpBar.setDamage(d);
		
		return false;
	}
	
	abstract protected void initHPBar (float maxLife);
	abstract protected void initHPBar (float maxLife, float[] hpBar);
	
	public void DrawHPBar (Graphics2D g, JFrame frame, float[] pos) {
		hpBar.DrawHPBar(g, frame, pos);
	}
	
	public boolean IsDead () {
		return (hpBar.atuLife <= 0);
	}
	
	public static BufferedImage toGrayScale(BufferedImage master) {
        BufferedImage gray = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Automatic conversion....
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(master, gray);

        return gray;
    }
	
	public static BufferedImage toSepia(BufferedImage img, int sepiaIntensity) {
		
		img = toGrayScale(img);

	    BufferedImage sepia = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    // Play around with this.  20 works well and was recommended
	    //   by another developer. 0 produces black/white image
	    int sepiaDepth = 20;

	    int w = img.getWidth();
	    int h = img.getHeight();

	    WritableRaster raster = sepia.getRaster();

	    // We need 3 integers (for R,G,B color values) per pixel.
	    int[] pixels = new int[w * h * 3];
	    img.getRaster().getPixels(0, 0, w, h, pixels);

	    for (int x = 0; x < img.getWidth(); x++) {
	        for (int y = 0; y < img.getHeight(); y++) {

	            int rgb = img.getRGB(x, y);
	            Color color = new Color(rgb, true);
	            int r = color.getRed();
	            int g = color.getGreen();
	            int b = color.getBlue();
	            int gry = (r + g + b) / 3;

	            r = g = b = gry;
	            r = r + (sepiaDepth * 2);
	            g = g + sepiaDepth;

	            if (r > 255) {
	                r = 255;
	            }
	            if (g > 255) {
	                g = 255;
	            }
	            if (b > 255) {
	                b = 255;
	            }

	            // Darken blue color to increase sepia effect
	            b -= sepiaIntensity;

	            // normalize if out of bounds
	            if (b < 0) {
	                b = 0;
	            }
	            if (b > 255) {
	                b = 255;
	            }

	            color = new Color(r, g, b, color.getAlpha());
	            sepia.setRGB(x, y, color.getRGB());

	        }
	    }

	    return sepia;
	}
}
