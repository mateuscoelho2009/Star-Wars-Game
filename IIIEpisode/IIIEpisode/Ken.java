package IIIEpisode;

import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Common.Animation;
import Enviroment.EnviromentBase;

public class Ken extends BaseCharacter {
	// Attributes
	private Animation standing,
					  walkingLeft;
	
	// Constructor
	public Ken (EnviromentBase enviroment, int x, int y, State initialState) {
		super(enviroment, x, y, initialState);
		
		initKen();
	}
	
	public Ken (EnviromentBase enviroment, int x, int y) {
		super(enviroment, x, y);
		
		initKen();
	}
	
	public Ken (EnviromentBase enviroment) {
		super(enviroment);
		
		initKen();
	}
	
	// Methods
	private void initKen () {
		try {
			String strPath = System.getProperty("user.dir") + "\\src\\Images";
			loadImage(strPath + "\\ken-sprite-sheet.png");
		} catch (Exception e) {
			System.err.println(e);
		}
		
		animation = standing;
		animation.start();
	}
	
	@Override
	protected void loadImage(String imageName) {
		BufferedImage bigImg = null;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName));
    	} catch (IOException e) {
    		System.err.println("Image not found in: " + imageName);
    		System.exit(0);
    	}
    	
    	sprites = new BufferedImage[22];
    	
    	BufferedImage[] standVector = new BufferedImage[10];
    	
    	atuSprite = 0;
    	
    	// Loading the Stand Left Sprite
    	for (int j = 0; j < 10; j++)
	    {
	        sprites[j] = bigImg.getSubimage(
	            2 + j * 70 + j * 32,
	            5,
	            90,
	            120
	        );
	        
	        standVector[j] = sprites[j];
	    }
    	
    	standing = new Animation(standVector, 10);
    	
    	BufferedImage[] walkingLeftVector = new BufferedImage[11];
    	
    	width = 70;
    	height = 130;
    	
    	walkingLeftVector[0] = bigImg.getSubimage(
	            0,
	            130,
	            90,
	            120
	        );
    	walkingLeftVector[1] = bigImg.getSubimage(
	            110,
	            130,
	            90,
	            120
	        );
    	walkingLeftVector[2] = bigImg.getSubimage(
	            210,
	            130,
	            80,
	            120
	        );
    	walkingLeftVector[3] = bigImg.getSubimage(
	            300,
	            130,
	            70,
	            120
	        );
	    walkingLeftVector[4] = bigImg.getSubimage(
	            380,
	            130,
	            80,
	            120
	        );
    	walkingLeftVector[5] = bigImg.getSubimage(
	            470,
	            130,
	            70,
	            125
	        );        
    	walkingLeftVector[6] = bigImg.getSubimage(
	            550,
	            130,
	            70,
	            120
	        );
	    walkingLeftVector[7] = bigImg.getSubimage(
    			630,
	            130,
	            90,
	            120
	        );
    	walkingLeftVector[8] = bigImg.getSubimage(
    			730,
	            130,
	            90,
	            120
	        );
    	walkingLeftVector[9] = bigImg.getSubimage(
    			830,
	            130,
	            90,
	            120
	        );
    	walkingLeftVector[10] = bigImg.getSubimage(
    			930,
	            135,
	            90,
	            115
	        );
    	
    	walkingLeft = new Animation(walkingLeftVector, 7);
	}
	
	public void update () {
		super.update();
		
		move ();
		
		// Animation Change.
		atuAnimation();
		
		animation.update();
	}
	
	private void atuAnimation () {
		if (charState == State.WALKING && animation != walkingLeft) {
			BufferedImage prevImage = animation.getSprite();
			
			animation.stop();
			animation.reset();
			animation = walkingLeft;
			animation.start();
			
			position[X] = position[X] - (prevImage.getWidth() / 2) + animation.getSprite().getWidth() / 2;
			position[Y] = position[Y] - prevImage.getHeight() + animation.getSprite().getHeight();
		}
		else if (charState == State.STOP && animation != standing) {
			BufferedImage prevImage = animation.getSprite();
			
			animation.stop();
			animation.reset();
			animation = standing;
			animation.start();
			
			position[X] = position[X] - (prevImage.getWidth() / 2) + animation.getSprite().getWidth() / 2;
			position[Y] = position[Y] - prevImage.getHeight() + animation.getSprite().getHeight();
		}
	}
	
	private void move () {
		// super.move();
	}
	
	@Override
	public void keyPressed (KeyEvent ke) {
		int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	velocity[X] = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            velocity[X] = 2;
        }

        if (key == KeyEvent.VK_UP && 
        	(charState != State.AIRRISING && charState != State.AIRFALLING)) {
            velocity[Y] = -10;
        }
	}
	
	@Override
	public void keyReleased (KeyEvent ke) {
		int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	velocity[X] = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            velocity[X] = 0;
        }
	}
	
	@Override
	public BufferedImage getImage () {
		
		BufferedImage image = animation.getSprite();
		
		if (orientation == Orientation.RIGHT) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(- image.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	        image = op.filter(image, null);
		}
		
		return image;
	}

	@Override
	public void Move1() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Move2() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Move3() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Move4() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Especial1() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Especial2() {
		// TODO Auto-generated method stub

	}

}
