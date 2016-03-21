package Enviroment;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import IIIEpisode.BaseCharacter;

public abstract class EnviromentBase {
	// Attributes
	Image scenary;
	
	// Constructors
	EnviromentBase () {
		initEnviroment();
	}
	
	// Methods
	abstract protected void initEnviroment ();
	abstract public boolean checkEnviromentCollisionY (BaseCharacter bc);
	abstract public boolean checkEnviromentCollisionX (BaseCharacter bc);
		// abstract void floorCollision ();
}
