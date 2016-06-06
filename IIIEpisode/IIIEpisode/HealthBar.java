package IIIEpisode;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Common.Animation;
import Common.Sprite;
import IIIEpisode.BaseCharacter.State;

public class HealthBar extends Sprite {
	// attributes
	float maxLife,
		  atuLife;
	
	BufferedImage HPBase;
	BufferedImage SP;
	
	// Constructors
	HealthBar (float maxLife) {
		this.maxLife = atuLife = maxLife;
		
		try {
			String strPath = System.getProperty("user.dir") + "\\Imagens";
			loadImage(strPath + "\\DarthVader\\Board");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	HealthBar (float maxLife, float[] position) {
		this.maxLife = atuLife = maxLife;
		
		this.position = position;
		
		try {
			String strPath = System.getProperty("user.dir") + "\\Imagens";
			loadImage(strPath + "\\DarthVader\\Board");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	// Methods
	@Override
	protected void loadImage (String imageName) {
		BufferedImage bigImg = null;
		float scale = .8f;
		
		BufferedImage[] temp = new BufferedImage[1];
		
		temp[0] = GenerateSprite(imageName + "\\BarraVida.png", scale);
		
		animation = new Animation(temp, 10);
		
		HPBase = GenerateSprite(imageName + "\\BarraFundo.png", scale);
		
		SP = GenerateSprite(imageName + "\\Barraespecial.png", scale);
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
	
	@Override
	public BufferedImage getImage () {
		
		BufferedImage image = animation.getSprite();
		
		if (atuLife < 0)
			atuLife = 0;
		
		try {
			image = image.getSubimage(0, 0, 1 + (int) ((image.getWidth() - 1) * (atuLife / maxLife)), image.getHeight());
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		
		if (position[0] > 0) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(- image.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	        image = op.filter(image, null);
		}
		
		return image;
	}
	
	public void DrawHPBar (Graphics2D g, JFrame frame, float[] pos) {
		if (position[0] > 0) {
			BufferedImage aux;
			
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(- HPBase.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	        aux = op.filter(HPBase, null);
	        
	        g.drawImage(aux, (int) pos[0], (int) pos[1], frame);			
			g.drawImage(getImage(), (int) pos[0] + (int) ((animation.getSprite().getWidth()) * (1 - atuLife / maxLife)), (int) pos[1], frame);
			g.drawImage(SP, (int) (pos[0] + animation.getSprite().getWidth() - SP.getWidth()), (int) pos[1], frame);
		}
		else {
			g.drawImage(HPBase, (int) pos[0], (int) pos[1], frame);
			g.drawImage(getImage(), (int) pos[0], (int) pos[1], frame);
			g.drawImage(SP, (int) pos[0], (int) pos[1], frame);
		}
	}
	
	public void setDamage (float damage) {
		atuLife -= damage;
	}
}
