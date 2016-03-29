package Attacks;

import java.awt.Rectangle;

import Common.Sprite;

public abstract class DamageAttack {
	// Attributes
	private float damage;
	protected Rectangle damageArea;
	
	DamageAttack (float damage) {
		this.damage = damage;
	}

	public float getDamage() {
		return damage;
	}
	
	public Rectangle getDamageArea () {
		return damageArea;
	}
}
