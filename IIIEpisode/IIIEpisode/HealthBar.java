package IIIEpisode;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Common.Animation;
import Common.Sprite;
import IIIEpisode.BaseCharacter.State;

public class HealthBar extends Sprite {
	// attributes
	float maxLife,
		  atuLife;
	
	// Constructors
	HealthBar (float maxLife) {
		this.maxLife = atuLife = maxLife;
		
		try {
			String strPath = System.getProperty("user.dir") + "\\src\\Images";
			loadImage(strPath + "\\bargreen.png");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	HealthBar (float maxLife, float[] position) {
		this.maxLife = atuLife = maxLife;
		
		this.position = position;
		
		try {
			String strPath = System.getProperty("user.dir") + "\\src\\Images";
			loadImage(strPath + "\\bargreen.png");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	// Methods
	@Override
	protected void loadImage (String imageName) {
		BufferedImage bigImg = null;
		
		try {
    		bigImg = ImageIO.read(new File(imageName));
    	} catch (IOException e) {
    		System.err.println("Image not found in: " + imageName);
    		System.exit(0);
    	}
		
		BufferedImage[] temp = new BufferedImage[1];
		
		temp[0] = bigImg;
		
		animation = new Animation(temp, 10);
	}
	
	@Override
	public BufferedImage getImage () {
		
		BufferedImage image = animation.getSprite();
		
		if (atuLife < 0)
			atuLife = 0;
		
		image = image.getSubimage(0, 0, 10 + (int) ((image.getWidth() - 10) * (atuLife / maxLife)), image.getHeight());
		
		if (position[0] > 0) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(- image.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	        image = op.filter(image, null);
		}
		
		return image;
	}
	
	public void setDamage (float damage) {
		atuLife -= damage;
	}
}
