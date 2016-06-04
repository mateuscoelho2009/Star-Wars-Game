package IIIEpisode;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Attacks.SimpleAttack;
import Common.Animation;
import Enviroment.EnviromentBase;

public class DarthVader extends BaseCharacter {
	private static final int MAX_HEALTH_POINTS = 500;
	// Attributes
	private Animation standing,
					  walkingLeft,
					  blocking,
					  attack1,
					  jumping,
					  attack2,
					  ducking,
					  dying,
					  deadAnimation;
	
	private boolean hasOwnIntelligence;
	
	// Constructor
	public DarthVader (EnviromentBase enviroment, int x, int y, State initialState) {
		super(enviroment, x, y, initialState);
		
		initVader();
	}
	
	public DarthVader (EnviromentBase enviroment, int x, int y) {
		super(enviroment, x, y);
		
		initVader();
	}
	
	public DarthVader (EnviromentBase enviroment) {
		super(enviroment);
		
		initVader();
	}
	
	public DarthVader (EnviromentBase enviroment, int x, int y, State initialState, boolean isCPU) {
		super(enviroment, x, y, initialState);
		
		initVader(isCPU);
	}
	
	public DarthVader (EnviromentBase enviroment, int x, int y, boolean isCPU) {
		super(enviroment, x, y);
		
		initVader(isCPU);
	}
	
	public DarthVader (EnviromentBase enviroment, boolean isCPU) {
		super(enviroment);
		
		initVader(isCPU);
	}
	
	public DarthVader (EnviromentBase enviroment, int x, int y, State initialState, float[] hpPos) {
		super(enviroment, x, y, initialState);
		
		initVader(hpPos);
	}
	
	public DarthVader (EnviromentBase enviroment, int x, int y, float[] hpPos) {
		super(enviroment, x, y);
		
		initVader(hpPos);
	}
	
	public DarthVader (EnviromentBase enviroment, float[] hpPos) {
		super(enviroment);
		
		initVader(hpPos);
	}
	
	public DarthVader (EnviromentBase enviroment, int x, int y, State initialState, boolean isCPU, float[] hpPos) {
		super(enviroment, x, y, initialState);
		
		initVader(isCPU, hpPos);
	}
	
	public DarthVader (EnviromentBase enviroment, int x, int y, boolean isCPU, float[] hpPos) {
		super(enviroment, x, y);
		
		initVader(isCPU, hpPos);
	}
	
	public DarthVader (EnviromentBase enviroment, boolean isCPU, float[] hpPos) {
		super(enviroment);
		
		initVader(isCPU, hpPos);
	}
	
	// Methods
	private void initVader (boolean isCPU) {
		try {
			String strPath = System.getProperty("user.dir") + "\\Imagens\\DarthVader";
			loadImage(strPath);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		hasOwnIntelligence = isCPU;
		
		mass = 100;
		life = MAX_HEALTH_POINTS;
		power = 30;
		
		initHPBar(MAX_HEALTH_POINTS);
		
		animation = standing;
		animation.start();
	}
	
	private void initVader () {
		try {
			String strPath = System.getProperty("user.dir") + "\\Imagens\\DarthVader";
			loadImage(strPath);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		hasOwnIntelligence = false;
		
		mass = 100;
		life = MAX_HEALTH_POINTS;
		power = 30;
		
		initHPBar(MAX_HEALTH_POINTS);
		
		animation = standing;
		animation.start();
	}
	
	private void initVader (boolean isCPU, float[] hpPos) {
		try {
			String strPath = System.getProperty("user.dir") + "\\Imagens\\DarthVader";
			loadImage(strPath);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		hasOwnIntelligence = isCPU;
		
		mass = 100;
		life = MAX_HEALTH_POINTS;
		power = 50;
		
		initHPBar(MAX_HEALTH_POINTS, hpPos);
		
		animation = standing;
		animation.start();
	}
	
	private void initVader (float[] hpPos) {
		try {
			String strPath = System.getProperty("user.dir") + "\\Imagens\\DarthVader";
			loadImage(strPath);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		hasOwnIntelligence = false;
		
		mass = 100;
		life = MAX_HEALTH_POINTS;
		power = 30;
		
		initHPBar(MAX_HEALTH_POINTS, hpPos);
		
		animation = standing;
		animation.start();
	}
	
	@Override
	protected void loadImage(String imageName) {
		BufferedImage bigImg = null, aux = null;
		double scale = 0.45;
		
		bigImg = GenerateSprite(imageName + "\\andando\\star wars fight-13.png", scale);
    	
    	BufferedImage[] standVector = new BufferedImage[3];
    	
    	standVector[0] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado02.png", scale);
    	
    	standVector[1] = bigImg; 
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado12.png", scale);
    	
    	standVector[2] = bigImg;
    	
    	standing = new Animation(standVector, 20);
    	
    	BufferedImage[] walkingLeftVector = new BufferedImage[4];
    	
    	bigImg = GenerateSprite(imageName + "\\andando\\star wars fight-13.png", scale);
    	
    	walkingLeftVector[0] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\andando\\star wars fight-14.png", scale);
    	
    	walkingLeftVector[1] = bigImg;
    	walkingLeftVector[3] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\andando\\star wars fight-15.png", scale);
    	
    	walkingLeftVector[2] = bigImg;
    	
    	walkingLeft = new Animation(walkingLeftVector, 14);
    	
    	BufferedImage[] blockingVector = new BufferedImage[1];
    	
    	bigImg = GenerateSprite(imageName + "\\forca e defesa\\star wars fight-21.png", scale);
    	
    	blockingVector[0] = bigImg;
    	
    	BufferedImage[] attack1Vector = new BufferedImage[5];
    	
    	bigImg = GenerateSprite(imageName + "\\golpe\\star wars fight-17.png", scale);
    	attack1Vector[0] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\golpe\\star wars fight-18.png", scale);
    	
    	attack1Vector[1] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\golpe\\star wars fight-19.png", scale);
    	
    	attack1Vector[2] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\golpe\\star wars fight-20.png", scale);
    	
    	attack1Vector[3] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\golpe\\star wars fight-16.png", scale);
    	
    	attack1Vector[4] = bigImg;
    	
    	attack1 = new Animation(attack1Vector, 10, true);
    	
    	//
    	BufferedImage[] jumpingVector = new BufferedImage[12];
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado01.png", scale);
    	
    	jumpingVector[0] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado02.png", scale);
    	
    	jumpingVector[1] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado03.png", scale);
    	
    	jumpingVector[2] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado04.png", scale);
    	
    	jumpingVector[3] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado05.png", scale);
    	
    	jumpingVector[4] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado06.png", scale);
    	
    	jumpingVector[5] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado07.png", scale);
    	
    	jumpingVector[6] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado08.png", scale);
    	
    	jumpingVector[7] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado09.png", scale);
    	
    	jumpingVector[8] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado10.png", scale);
    	
    	jumpingVector[9] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado11.png", scale);
    	
    	jumpingVector[10] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado12.png", scale);
    	
    	jumpingVector[11] = bigImg;
    	
    	jumping = new Animation(jumpingVector, 6, true);
    	
    	BufferedImage[] duckingVector = new BufferedImage[1];
    	
    	bigImg = GenerateSprite(imageName + "\\agachando e golpeando\\star wars fight-22.png", scale);
    	
    	duckingVector[0] = bigImg;
    	
    	ducking = new Animation(duckingVector, 10);
    	
    	BufferedImage[] attack2Vector = new BufferedImage[4];
    	
    	bigImg = GenerateSprite(imageName + "\\agachando e golpeando\\star wars fight-22.png", scale);
    	
    	attack2Vector[0] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\agachando e golpeando\\star wars fight-23.png", scale);
    	
    	attack2Vector[1] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\agachando e golpeando\\star wars fight-25.png", scale);
    	
    	attack2Vector[2] = bigImg;
    	
    	bigImg = GenerateSprite(imageName + "\\agachando e golpeando\\star wars fight-24.png", scale);
    	
    	attack2Vector[3] = bigImg;
    	
    	attack2 = new Animation(attack2Vector, 10, true);
    	
    	BufferedImage[] dyingVector = new BufferedImage[1];
    	
    	bigImg = GenerateSprite(imageName + "\\agachando e golpeando\\star wars fight-22.png", scale);
    	
    	attack2Vector[0] = bigImg;
	}
	
	private BufferedImage GenerateSprite (String imageName) {
		BufferedImage bigImg = null, aux = null;
		float scale = 1;
		
		try {
    		bigImg = ImageIO.read(new File(imageName));
    		
    		// Resize
    		aux = new BufferedImage(
    				(int) (bigImg.getWidth() * scale), (int) (bigImg.getHeight() * scale), BufferedImage.TYPE_INT_ARGB);
    		Graphics2D graphics2D = aux.createGraphics();
    		AffineTransform xform = AffineTransform.getScaleInstance(scale, scale);
    		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    		graphics2D.drawImage(bigImg, xform, null);
    		graphics2D.dispose();
    		
    		bigImg = aux;
    	} catch (IOException e) {
    		System.err.println(imageName);
    		System.exit(0);
    	}
		
		return bigImg;
	}
	
	private BufferedImage GenerateSprite (String imageName, double scale) {
		BufferedImage bigImg = null, aux = null;
		
		try {
    		bigImg = ImageIO.read(new File(imageName));
    		
    		// Resize
    		aux = new BufferedImage(
    				(int) (bigImg.getWidth() * scale), (int) (bigImg.getHeight() * scale), BufferedImage.TYPE_INT_ARGB);
    		Graphics2D graphics2D = aux.createGraphics();
    		AffineTransform xform = AffineTransform.getScaleInstance(scale, scale);
    		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    		graphics2D.drawImage(bigImg, xform, null);
    		graphics2D.dispose();
    		
    		bigImg = aux;
    	} catch (IOException e) {
    		System.err.println(imageName);
    		System.exit(0);
    	}
		
		return bigImg;
	}
	
	public void update () {
		super.update();
		
		move ();
		
		// Animation Change.
		atuAnimation();
		
		BufferedImage prevImage = animation.getSprite();
		animation.update();
		BufferedImage atuImage = animation.getSprite();
		
		if (orientation == Orientation.RIGHT) {
			position[X] = position[X] - (prevImage.getWidth() / 2) + atuImage.getWidth() / 2;
			position[Y] = position[Y] - prevImage.getHeight() + atuImage.getHeight();
		}
		else {
			position[X] = position[X] - (prevImage.getWidth() / 2) + atuImage.getWidth() / 2;
			position[Y] = position[Y] - prevImage.getHeight() + atuImage.getHeight();
		}
	}
	
	private void atuAnimation () {
		if (!animation.Completed()) {
			return;
		}
		
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
		else if (charState == State.DUCKING && animation != ducking) {
			BufferedImage prevImage = animation.getSprite();
			
			animation.stop();
			animation.reset();
			animation = ducking;
			animation.start();
			
			position[X] = position[X] - (prevImage.getWidth() / 2) + animation.getSprite().getWidth() / 2;
			position[Y] = position[Y] - prevImage.getHeight() + animation.getSprite().getHeight();
		}
		else if (charState == State.DEAD && animation != deadAnimation) {
			BufferedImage prevImage = animation.getSprite();
			
			animation.stop();
			animation.reset();
			animation = deadAnimation;
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
		
		if (!animation.Completed()) {
			return;
		}

        if (key == KeyEvent.VK_LEFT && charState != State.DUCKING) {
        	velocity[X] = -2f;
        }

        if (key == KeyEvent.VK_RIGHT && charState != State.DUCKING) {
            velocity[X] = 2f;
        }

        if (key == KeyEvent.VK_UP && 
        	(charState != State.AIRRISING && charState != State.AIRFALLING)) {
            velocity[Y] = -14;
            
            BufferedImage prevImage = animation.getSprite();
			
			animation.stop();
			animation.reset();
			animation = jumping;
			animation.start();
			
			position[X] = position[X] - (prevImage.getWidth() / 2) + animation.getSprite().getWidth() / 2;
			position[Y] = position[Y] - prevImage.getHeight() + animation.getSprite().getHeight();
        }
        
        if (key == KeyEvent.VK_DOWN &&
        	(charState != State.AIRRISING && charState != State.AIRFALLING)) {
        	velocity[X] = 0;
        	charState = State.DUCKING;
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
        
        if (key == KeyEvent.VK_DOWN) {
            charState = State.WALKING;
        }
        
        if (!animation.Completed()) {
			return;
		}
        
        if (key == KeyEvent.VK_SPACE && charState != State.DUCKING) {
        	charState = State.MOVE1;
        	Move1();
        }
        
        if (key == KeyEvent.VK_SPACE && charState == State.DUCKING) {
        	charState = State.MOVE2;
        	Move2();
        }
	}
	
	@Override
	public BufferedImage getImage () {
		
		BufferedImage image = animation.getSprite();
		
		if (orientation == Orientation.LEFT) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(- image.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	        image = op.filter(image, null);
		}
		
		return image;
	}
	
	@Override
	public Rectangle getBounds() {
		BufferedImage image = animation.getSprite();
		
        return new Rectangle((int) position[X], 
        					 (int) position[Y],
        					 image.getWidth(),
        					 image.getHeight());
    }
	
	@Override
	protected void initHPBar (float maxLife) {
		hpBar = new HealthBar(maxLife);
	}
	
	@Override
	protected void initHPBar (float maxLife, float[] hpPos) {
		hpBar = new HealthBar(maxLife, hpPos);
	}

	@Override
	public void Move1() {
		BufferedImage image = animation.getSprite();
		float xScale = .5f, yScale = .7f;
		Rectangle damageArea;
		
		if (orientation == Orientation.LEFT)
			damageArea = new Rectangle((int) (position[0] - (int) (image.getWidth() * xScale / 2)), 
												(int) (position[1] + (int) (image.getHeight() * yScale)),
												(int) (image.getWidth() * xScale),
												(int) (image.getHeight() * yScale));
		else
			damageArea = new Rectangle((int) (position[0] + getImage().getWidth() + (int) (image.getWidth() * xScale / 2)), 
												(int) (position[1] + (int) (image.getHeight() * yScale)),
												(int) (image.getWidth() * xScale),
												(int) (image.getHeight() * yScale));
		
		enviroment.addNonSpriteAttack (new SimpleAttack(power, damageArea, this));
		
		// atualize animation
		animation.stop();
		animation.reset();
		animation = attack1;
		animation.start();
		
		position[X] = position[X] - (image.getWidth() / 2) + animation.getSprite().getWidth() / 2;
		position[Y] = position[Y] - image.getHeight() + animation.getSprite().getHeight();
	}

	@Override
	public void Move2() {
		// TODO Auto-generated method stub
		BufferedImage image = animation.getSprite();
		float xScale = .5f, yScale = .7f;
		Rectangle damageArea;
		
		// atualize animation
		animation.stop();
		animation.reset();
		animation = attack2;
		animation.start();
		
		position[X] = position[X] - (image.getWidth() / 2) + animation.getSprite().getWidth() / 2;
		position[Y] = position[Y] - image.getHeight() + animation.getSprite().getHeight();
		
		image = animation.getSprite();
		
		if (orientation == Orientation.LEFT)
			damageArea = new Rectangle((int) (position[0] - (int) (image.getWidth() * xScale / 2)), 
												(int) (position[1] + (int) (image.getHeight() * yScale)),
												(int) (image.getWidth() * xScale),
												(int) (image.getHeight() * yScale));
		else
			damageArea = new Rectangle((int) (position[0] + getImage().getWidth() + (int) (image.getWidth() * xScale / 2)), 
												(int) (position[1] + (int) (image.getHeight() * yScale)),
												(int) (image.getWidth() * xScale),
												(int) (image.getHeight() * yScale));
		
		enviroment.addNonSpriteAttack (new SimpleAttack(power, damageArea, this));
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