package IA;

import Enviroment.EnviromentBase;
import IIIEpisode.BaseCharacter;

public class BasicArtificialInteligence {
	EnviromentBase enviroment;
	BaseCharacter bc;
	
	public enum ActionDecision { WALKRIGHT, WALKLEFT, BLOCK, ATTACK, DUCK, NOTHING };
	
	public BasicArtificialInteligence(BaseCharacter bc, EnviromentBase enviroment) {
		this.bc = bc;
		this.enviroment = enviroment;
	}
	
	public ActionDecision Decide () {
		return ActionDecision.NOTHING;
	}
}
