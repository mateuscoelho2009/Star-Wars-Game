package Attacks;

import java.awt.Rectangle;

import Common.Sprite;

public abstract class SpriteAttack extends Sprite {
	// Attributes
	private float damage;
	protected Rectangle damageArea;
		
	SpriteAttack (float damage) {
		this.damage = damage;
	}

	public float getDamage() {
		return damage;
	}
	
	public Rectangle getDamageArea () {
		return damageArea;
	}
}
