package IA;

import Enviroment.EnviromentBase;
import IIIEpisode.BaseCharacter;

public class BasicArtificialInteligence {
	EnviromentBase enviroment;
	BaseCharacter bc;
	
	ActionDecision lastDecision;
	
	public enum ActionDecision { WALKRIGHT, WALKLEFT, BLOCK, ATTACK, DUCK, NOTHING, JUMP };
	
	public BasicArtificialInteligence(BaseCharacter bc, EnviromentBase enviroment) {
		this.bc = bc;
		this.enviroment = enviroment;
		
		lastDecision = ActionDecision.NOTHING;
	}
	
	public ActionDecision Decide () {
		float[] otherPos = enviroment.getOtherPlayerPosition(bc);
		
		if (enviroment.DeadPlayerExist()) {
			return ActionDecision.NOTHING;
		}
		
		double rand = Math.random();
		
		if (otherPos[0] > bc.getX()) {
			if (rand < .4)
				return ActionDecision.WALKRIGHT;
			else if (rand < .55)
				return ActionDecision.WALKLEFT;
		}
		else if (rand < .4)
			return ActionDecision.WALKLEFT;
		else if (rand < .55)
			return ActionDecision.WALKRIGHT;
		
		rand = Math.random();
		
		if (Math.abs(bc.getX() - otherPos[0]) < 100 && rand < .6)
			return ActionDecision.ATTACK;
		else if (Math.abs(bc.getX() - otherPos[0]) < 100 && Math.random() < .6)
			return ActionDecision.BLOCK;
		
		rand = Math.random();
		
		if (rand < .1)
			return ActionDecision.JUMP;
		else if (rand > .9)
			return ActionDecision.DUCK;
		
		return ActionDecision.NOTHING;
	}
}
