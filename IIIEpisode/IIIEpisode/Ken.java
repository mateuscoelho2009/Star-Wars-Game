package IIIEpisode;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import Enviroment.EnviromentBase;

public class Ken extends BaseCharacter {
	EnviromentBase enviroment;
	
	// Constructor
	Ken (EnviromentBase enviroment) {
		super();
		
		initKen(enviroment);
	}
	
	// Methods
	private void initKen (EnviromentBase enviroment) {
		try {
			ImageIcon ii = new ImageIcon("ken-sprite-sheet.png");
			sprite = ii.getImage();
		} catch (Exception e) {
			System.out.println("Monkey");
		}
		
		this.enviroment = enviroment;
		
		charState = State.STOP;
		
		position[X] = 40;
		position[Y] = 60;
	}
	
	public void move () {
		
		
		position[X] += velocity[X];
		
		if (charState != State.AIR || enviroment.checkEnviromentCollisionY(this))
			position[Y] += velocity[Y];
	}
	
	public void keyPressed (KeyEvent ke) {
		int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	velocity[X] = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            velocity[X] = 1;
        }

        if (key == KeyEvent.VK_UP) {
            velocity[Y] = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            velocity[Y] = 1;
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

        if (key == KeyEvent.VK_UP) {
            velocity[Y] = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            velocity[Y] = 0;
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
