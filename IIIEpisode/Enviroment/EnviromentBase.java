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
	abstract public boolean checkEnviromentLeftCollisionX (BaseCharacter bc);
	abstract public boolean checkEnviromentRightCollisionX (BaseCharacter bc);
	abstract public float[] EnvAcceleration (BaseCharacter bc);
	abstract public float getFloorHeight ();
	abstract public float getLeftWall ();
	abstract public float getRightWall ();
}
