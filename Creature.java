package com.parnensaton.folder;

public class Creature extends Card
{
	public Creature()
	{
		super(Names.generate(), (int)(Math.random() * 10), (int)(Math.random() * 10) + 1);
	}
	
	public void combat(Creature other)
	{
		this.setHealth(this.getHealth() - other.getDamage());
		other.setHealth(other.getHealth() - this.getDamage());
	}
}
