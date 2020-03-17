package com.parnensaton.folder;

public class Card {
	private String name;
	private int damage;
	private int health;
	private double price;
	
	public Card()
	{
		this.name = "Default Card";
		this.damage = 0;
		this.health = 0;
		this.price = 0.0;
	}
	
	public Card(String name, int damage)
	{
		//Spell super constructor
		this.name = name;
		this.damage = damage;
		this.health = 0;
		this.price = (Math.random() * damage + 1) - (damage - 1);
	}
	
	public Card(String name, int damage, int health)
	{
		//Creature super constructor
		this.name = name;
		this.damage = damage;
		this.health = health;
		this.price = (damage + health) / 2;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public boolean isDead()
	{
		return health <= 0;
	}
}
