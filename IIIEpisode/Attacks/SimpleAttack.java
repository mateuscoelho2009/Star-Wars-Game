package Attacks;

import java.awt.Rectangle;

import IIIEpisode.BaseCharacter;

/*
 * Like Punches and kicks.
 */
public class SimpleAttack extends NonSpriteAttack {
	public SimpleAttack (float damage, Rectangle damageArea, BaseCharacter bc) {
		super (damage, damageArea, bc);
	}
}
