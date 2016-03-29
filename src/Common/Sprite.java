package Common;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Sprite {
	// Constants
	public final int X = 0,
					 Y = 1;
	
	// Attributes
    protected float[] position;
    protected int width;
    protected int height;
    protected boolean vis;
    
    protected int atuSprite;
    
    // You can choose to use the sprite mode
    protected BufferedImage[] sprites;
    // You can use the animation class
    protected Animation animation;

    // Constructors
    public Sprite(int x, int y) {
    	position = new float[2];
    	
        position[X] = x;
        position[Y] = y;
        vis = true;
        
        sprites = null;
        atuSprite = -1;
    }
    
    public Sprite() {
    	position[X] = 0;
        position[Y] = 0;
        vis = true;
        
        sprites = null;
        atuSprite = -1;
    }

    // Methods
    protected void loadImage(String imageName, int width, int height, int rows, int cols, int deltax, int deltay) {
    	BufferedImage bigImg = null;
    	
    	try {
    		bigImg = ImageIO.read(new File(imageName));
    	} catch (IOException e) {
    		System.err.println("Image not found in: " + imageName);
    		System.exit(0);
    	}
    	
    	sprites = new BufferedImage[rows * cols];
    	atuSprite = 0;

    	for (int i = 0; i < rows; i++)
    	{
    	    for (int j = 0; j < cols; j++)
    	    {
    	        sprites[(i * cols) + j] = bigImg.getSubimage(
    	            deltax + j * width,
    	            deltay + i * height,
    	            width,
    	            height
    	        );
    	    }
    	}
    }
    
    protected void loadImage(String imageName) {
    	sprites = new BufferedImage[1];
    	atuSprite = 0;
    	
    	try {
    		sprites[0] = ImageIO.read(new File(imageName));
    	} catch (IOException e) {
    		System.err.println("Image not found in: " + imageName);
    		System.exit(0);
    	}
    }
    
    protected void getImageDimensions() {
        width = sprites[atuSprite].getWidth(null);
        height = sprites[atuSprite].getHeight(null);
    }    

    public BufferedImage getImage() {
        return sprites[atuSprite];
    }

    public int getX() {
        return (int) position[X];
    }

    public int getY() {
        return (int) position[Y];
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) position[X], (int) position[Y], sprites[atuSprite].getWidth(), sprites[atuSprite].getHeight());
    }
}