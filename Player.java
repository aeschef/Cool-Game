package com.parnensaton.folder;

import java.util.Scanner;
import java.util.ArrayList;

public class Player {
	private static Scanner scan = new Scanner(System.in);
	private static int playerCount = 0;
	private String playerName;
	private double funds;
	private ArrayList<Card> hand;
	private ArrayList<Creature> playedCreatures;
	
	public Player()
	{
		playerCount++;
		
		System.out.println("Enter the name of Player " + playerCount);
		playerName = scan.nextLine();
		
		funds = 4;
		hand = new ArrayList<Card>();
		fillHand();
	}
	
	public String getName()
	{
		return playerName;
	}
	
	public void setName(String newName)
	{
		this.playerName = newName;
	}
	
	public double getFunds()
	{
		return funds;
	}
	
	public void setFunds(double newFunds)
	{
		this.funds = newFunds;
	}
	
	public ArrayList<Card> getHand()
	{
		return hand;
	}
	
	public void setHand(ArrayList<Card> newHand)
	{
		this.hand = newHand;
	}
	
	public void printHand()
	{
		for(int i = 0; i < hand.size(); i++)
		{
			System.out.println(i + ") " + hand.get(i));
		}
	}
	
	private void fillHand()
	{
		for(int i = 0; i < 6; i++)
		{
			hand.add((int)(Math.random() * 10) + 1 >= 3 ? new Creature() : new Spell());
		}
	}
	
	@Override
	public String toString()
	{
		return "Name: " + playerName + "\nFunds: $" + funds + "\nNumber of Cards: " + hand.size();
	}
}
