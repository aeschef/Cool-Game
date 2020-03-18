package com.parnensaton.folder;

public class Creature extends Card
{
	public Creature() {
		super(Names.generate(), (int)(Math.random() * 10), (int)(Math.random() * 10) + 1);
	}
	
	public void combat(Creature other)
	{
		this.setHealth(this.getHealth() - other.getDamage());
		other.setHealth(other.getHealth() - this.getDamage());
	}
	
	public boolean belongsTo(Player p)
	{
		//Implemenation not shown
		return false;
	}
	
	@Override
	public String toString()
	{
		return super.getName() + " [" + super.getHealth() + " / " + super.getDamage() + "] $" + super.getPrice();

	}
}
