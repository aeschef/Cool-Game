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
	
	@Override
	public String toString()
	{
		return super.getName() + " Spell\n" + super.getName() + " value: " + super.getDamage() + "\nPrice: " + super.getPrice();
	}
}
