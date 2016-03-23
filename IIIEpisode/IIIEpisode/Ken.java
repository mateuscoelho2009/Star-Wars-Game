package IIIEpisode;

import java.awt.event.KeyEvent;
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
					  walkingLeft,
					  animation;
	
	// Constructor
	Ken (EnviromentBase enviroment, int x, int y, State initialState) {
		super(enviroment, x, y, initialState);
		
		initKen();
	}
	
	Ken (EnviromentBase enviroment, int x, int y) {
		super(enviroment, x, y);
		
		initKen();
	}
	
	Ken (EnviromentBase enviroment) {
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
    	
    	BufferedImage[] walkingLeftVector = new BufferedImage[12];
    	
    	// Loading the Walking Left Sprite
    	for (int j = 10; j < 22; j++)
	    {
    		if (j == 10)
		        sprites[j] = bigImg.getSubimage(
		            0 + (j - 10) * 70 + (j - 10) * 10,
		            140,
		            70,
		            140
		        );
    		else if (j == 11)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        70,
    		        140
    		    );
    		else if (j == 12)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        70,
    		        100
    		    );
    		else if (j == 13)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        70,
    		        100
    		    );
    		else if (j == 14)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        70,
    		        100
    		    );
    		else if (j == 15)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        70,
    		        100
    		    );
    		else if (j == 16)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        70,
    		        100
    		    );
    		else if (j == 17)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        70,
    		        100
    		    );
    		else if (j == 18)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        70,
    		        120
    		    );
    		else if (j == 19)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        70,
    		        130
    		    );
    		else if (j == 20)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        90,
    		        130
    		    );
    		else if (j == 21)
    			sprites[j] = bigImg.getSubimage(
    		        19 + (j - 10) * 70 + (j - 10) * 10,
    		        140,
    		        90,
    		        130
    		    );
    		
    		walkingLeftVector[j - 10] = sprites[j - 10];
	    }
    	
    	walkingLeft = new Animation(walkingLeftVector, 10);
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
			
			
			animation.stop();
			animation.reset();
			animation = walkingLeft;
			animation.start();
		}
		else if (charState == State.STOP && animation != standing) {
			animation.stop();
			animation.reset();
			animation = standing;
			animation.start();
		}
	}
	
	private void move () {
		// super.move();
	}
	
	public void keyPressed (KeyEvent ke) {
		int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	velocity[X] = -4;
        }

        if (key == KeyEvent.VK_RIGHT) {
            velocity[X] = 4;
        }

        if (key == KeyEvent.VK_UP && 
        	(charState != State.AIRRISING && charState != State.AIRFALLING)) {
            velocity[Y] = -10;
        }
	}
	
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
		return animation.getSprite();
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
