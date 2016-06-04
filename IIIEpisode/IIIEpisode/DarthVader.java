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
					  jumping;
	
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
		
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\andando\\star wars fight-13.png"));
    		
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
    		System.err.println("Image not found in: " + imageName + "\\andando\\star wars fight-13.png");
    		System.exit(0);
    	}
    	
    	BufferedImage[] standVector = new BufferedImage[3];
    	
    	standVector[0] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado02.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado02.png");
    		System.exit(0);
    	}
    	
    	standVector[1] = bigImg; 
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado12.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado12.png");
    		System.exit(0);
    	}
    	
    	standVector[2] = bigImg;
    	
    	standing = new Animation(standVector, 20);
    	
    	BufferedImage[] walkingLeftVector = new BufferedImage[4];
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\andando\\star wars fight-13.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\andando\\star wars fight-13.png");
    		System.exit(0);
    	}
    	
    	walkingLeftVector[0] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\andando\\star wars fight-14.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\andando\\star wars fight-14.png");
    		System.exit(0);
    	}
    	
    	walkingLeftVector[1] = bigImg;
    	walkingLeftVector[3] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\andando\\star wars fight-15.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\andando\\star wars fight-15.png");
    		System.exit(0);
    	}
    	
    	walkingLeftVector[2] = bigImg;
    	
    	walkingLeft = new Animation(walkingLeftVector, 14);
    	
    	BufferedImage[] blockingVector = new BufferedImage[1];
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\forca e defesa\\star wars fight-21.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\força e defesa\\star wars fight-21.png");
    		System.exit(0);
    	}
    	
    	blockingVector[0] = bigImg;
    	
    	BufferedImage[] attack1Vector = new BufferedImage[5];
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\golpe\\star wars fight-16.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\golpe\\star wars fight-16.png");
    		System.exit(0);
    	}
    	
    	attack1Vector[0] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\golpe\\star wars fight-17.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\golpe\\star wars fight-17.png");
    		System.exit(0);
    	}
    	
    	attack1Vector[1] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\golpe\\star wars fight-18.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\golpe\\star wars fight-18.png");
    		System.exit(0);
    	}
    	
    	attack1Vector[2] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\golpe\\star wars fight-19.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\golpe\\star wars fight-19.png");
    		System.exit(0);
    	}
    	
    	attack1Vector[3] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\golpe\\star wars fight-20.png"));
    		
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
    		System.err.println("Image not found in: " + imageName + "\\golpe\\star wars fight-20.png");
    		System.exit(0);
    	}
    	
    	attack1Vector[4] = bigImg;
    	
    	attack1 = new Animation(attack1Vector, 10, true);
    	
    	//
    	BufferedImage[] jumpingVector = new BufferedImage[12];
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado01.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado01.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[0] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado02.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado02.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[1] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado03.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado03.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[2] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado04.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado04.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[3] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado05.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado05.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[4] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado06.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado06.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[5] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado07.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado07.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[6] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado08.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado08.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[7] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado09.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado09.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[8] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado10.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado10.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[9] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado11.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado11.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[10] = bigImg;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName + "\\pulo direcionado\\star wars fight-pulo direcionado12.png"));

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
    		System.err.println("Image not found in: " + imageName + "\\pulo direcionado\\star wars fight-pulo direcionado12.png");
    		System.exit(0);
    	}
    	
    	jumpingVector[11] = bigImg;
    	
    	jumping = new Animation(jumpingVector, 5, true);
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

        if (key == KeyEvent.VK_LEFT) {
        	velocity[X] = -2f;
        }

        if (key == KeyEvent.VK_RIGHT) {
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
        
        if (!animation.Completed()) {
			return;
		}
        
        if (key == KeyEvent.VK_SPACE) {
        	charState = State.MOVE1;
        	Move1();
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
			damageArea = new Rectangle((int) (position[0] - (int) (image.getWidth() * xScale)), 
												(int) (position[1] + (int) (image.getHeight() * yScale)),
												(int) (image.getWidth() * xScale),
												(int) (image.getHeight() * yScale));
		else
			damageArea = new Rectangle((int) (position[0] + getImage().getWidth() + (int) (image.getWidth() * xScale)), 
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