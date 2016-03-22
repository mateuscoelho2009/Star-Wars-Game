package IIIEpisode;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import Enviroment.EnviromentBase;

public class Ken extends BaseCharacter {	
	// Attributes
	EnviromentBase enviroment;
	
	// Constructor
	Ken (EnviromentBase enviroment, int x, int y) {
		super(x, y);
		
		initKen(enviroment);
	}
	
	Ken (EnviromentBase enviroment) {
		super();
		
		initKen(enviroment);
	}
	
	// Methods
	private void initKen (EnviromentBase enviroment) {
		int deltax_ = 19;
		int deltay_ = 10;
		width = 70;
		height = 120;
		int rows_ = 2;
		int col_ = 2;
		
		try {
			String strPath = System.getProperty("user.dir") + "\\src\\Images";
			loadImage(strPath + "\\ken-sprite-sheet.png", width, height, rows_, col_, deltax_, deltay_);
		} catch (Exception e) {
			
		}
		
		this.enviroment = enviroment;
		
		charState = State.STOP;
	}
	
	public void move () {
		if (position[Y] < enviroment.getFloorHeight () - getImage().getHeight())
			charState = State.AIR;
		else
			charState = State.STOP;
		
		if (enviroment.checkEnviromentCollisionY(this) && velocity[Y] > 0) {
			position[Y] = enviroment.getFloorHeight () - getImage().getHeight();
			velocity[Y] = 0;
		}
		
		position[X] += velocity[X];
		
		position[Y] += velocity[Y];
		
		acceleration = enviroment.EnvAcceleration(this);
		
		velocity[X] += acceleration[X];
		velocity[Y] += acceleration[Y];
	}
	
	public void keyPressed (KeyEvent ke) {
		int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	velocity[X] = -4;
        }

        if (key == KeyEvent.VK_RIGHT) {
            velocity[X] = 4;
        }

        if (key == KeyEvent.VK_UP && charState != State.AIR) {
            velocity[Y] = -10;
        }

        /*if (key == KeyEvent.VK_DOWN) {
            velocity[Y] = 1;
        }*/
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
