package com.parnensaton.folder;

public class Spell extends Card{
	
	public Spell() {
		super(((int)(Math.random() * 10) % 2 == 0 ? "Heal" : "Damage"), (int)(Math.random() * 10) + 1);
	}

	public void act(Creature other)
	{
		if(this.getName().equals("Heal"))
		{
			other.setHealth(other.getHealth() + this.getDamage());
		} else if(this.getName().equals("Damage"))
		{
			other.setHealth(other.getHealth() - this.getDamage());
		} 
	}
	
	public boolean equals(Spell other)
	{
		return super.getName().equals(other.getName()) && super.getDamage() == other.getDamage() && super.getPrice() == other.getPrice();
	}
	
	@Override
	public String toString()
	{
		return super.getName() + " Spell [" + super.getDamage() + "] $" + super.getPrice();
	}
}
