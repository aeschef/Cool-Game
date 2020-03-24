package com.parnensaton.folder;

import java.util.ArrayList;

public class Creature extends Card
{
	public Creature() {
		super(Names.generate(), (int)(Math.random() * 10) + 1, (int)(Math.random() * 10) + 1);
	}
	
	public void combat(Creature other)
	{
		this.setHealth(this.getHealth() - other.getDamage());
		other.setHealth(other.getHealth() - this.getDamage());
	}
	
	public boolean belongsTo(Player p)
	{
		ArrayList<Creature> list = p.getPlayedCreatures();
		
		for(int i = 0; i < list.size(); i++)
		{
			if(this.equals(list.get(i)))
				return true;
		}
		
		return false;
	}
	
	public boolean equals(Creature other)
	{
		return super.getName().equals(other.getName()) && super.getHealth() == other.getHealth() && super.getDamage() == other.getDamage();
	}
	
	@Override
	public String toString()
	{
		return super.getName() + " [" + super.getHealth() + " / " + super.getDamage() + "] $" + super.getPrice();

	}
}
