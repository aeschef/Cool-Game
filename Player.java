package com.parnensaton.folder;

import java.util.Scanner;
import java.util.ArrayList;

public class Player {
	//Used for setting the player name
	private static Scanner scan = new Scanner(System.in);
	private static int playerCount = 0;
	
	private String playerName;
	private double funds;
	private int points;
	private ArrayList<Card> hand;
	private ArrayList<Creature> playedCreatures;
	private Board board;
	
	public Player(Board board)
	{
		//Allows both players to change the board without scope issues
		this.board = board;
		
		playerCount++;
		System.out.println("Enter the name of Player " + playerCount);
		playerName = scan.nextLine();
		
		//All players start with 4 coins and no points
		funds = 4;
		points = 0;
		
		//At the start of the game, fill the hand with 6 randomly generated cards
		hand = new ArrayList<Card>();
		fillHand();
		
		//Used for functionality of belongsTo() in Creature.java
		playedCreatures = new ArrayList<Creature>();
	}
	
	public String getName()
	{
		return playerName;
	}
	
	public void setName(String playerName)
	{
		this.playerName = playerName;
	}
	
	public double getFunds()
	{
		return funds;
	}
	
	public void setFunds(double funds)
	{
		this.funds = funds;
	}
	
	public void addFunds(double funds)
	{
		this.funds += funds;
	}
	
	public void subtractFunds(double funds)
	{
		this.funds -= funds;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	public void addPoints(int add)
	{
		//Easier alternative to "player.setPoints(player.getPoints() + x)"
		points += add;
	}
	
	public ArrayList<Card> getHand()
	{
		return hand;
	}
	
	public void setHand(ArrayList<Card> hand)
	{
		this.hand = hand;
	}
	
	public ArrayList<Creature> getPlayedCreatures()
	{
		return playedCreatures;
	}
	
	public void printHand()
	{
		System.out.println("Format: Name [Health / Damage] Price");
		for(int i = 0; i < hand.size(); i++)
		{
			System.out.println(i + ") " + hand.get(i));
		}
	}
	
	public void drawCard()
	{
		hand.add((int)(Math.random() * 10) + 1 >= 3 ? new Creature() : new Spell());
	}
	
	private void fillHand()
	{
		//Fill the hand with about 66% creatures and 33% spells
		for(int i = 0; i < 6; i++)
		{
			drawCard();
		}
	}
	
	public void playCard(int xCor, int yCor, int index)
	{
		Card card = hand.get(index);
		
		//Take the card out of the hand and subtract its cost from the available funds
		hand.remove(index);
		subtractFunds(card.getPrice());
		
		if(card instanceof Creature)
		{
			//Use board methods to place that creature at the given cell
		} else if(card instanceof Spell) {
			//Use board methods to apply spell damage/heal value to the creature at the given cell
		}
	}
	
	@Override
	public String toString()
	{
		return "Name: " + playerName + "\nFunds: $" + funds + "\nNumber of Cards: " + hand.size();
	}
}
