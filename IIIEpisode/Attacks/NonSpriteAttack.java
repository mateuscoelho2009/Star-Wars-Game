package Attacks;

import java.awt.Rectangle;

import IIIEpisode.BaseCharacter;

public abstract class NonSpriteAttack {
	// Attributes
	private float damage;
	private Rectangle damageArea;
	private BaseCharacter owner;
	private boolean destroyed;
	
	// Constructor
	NonSpriteAttack (float damage, Rectangle damageArea, BaseCharacter bc) {
		this.damage = damage;
		
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
	
	protected void setDamageArea (Rectangle damageArea) {
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