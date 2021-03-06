package Attacks;

import java.awt.Rectangle;

import Common.Sprite;
import IIIEpisode.BaseCharacter;

public abstract class SpriteAttack extends Sprite {
	// Attributes
	private float damage;
	protected Rectangle damageArea;
	private BaseCharacter owner;
	boolean destroyed;
	
	// Constructor
	SpriteAttack (float damage, Rectangle damageArea, BaseCharacter bc) {
		this.damage = damage;
		destroyed = false;
		
		owner = bc;
		
		setDamageArea(damageArea);
	}

	// Methods
	public float getDamage() {
		return damage;
	}
	
	public boolean collided (BaseCharacter bc) {
		Rectangle bBound = bc.getBounds(), 
				  dBound = getDamageArea();
	
		if (destroyed == true) return false;
		
		return (bBound.intersects(dBound) || bBound.contains(dBound)) && !isOwner(bc);
	}
	
	private void setDamageArea (Rectangle damageArea) {
		this.damageArea = damageArea;
	}
	
	public Rectangle getDamageArea () {
		return damageArea;
	}
	
	private boolean isOwner (BaseCharacter bc) {
		return (owner == bc);
	}

	public void destroy() {
		destroyed = true;
	}
	
	public boolean wasDestroyed () {
		return destroyed;
	}
}
