package com.parnensaton.folder;

import java.util.Scanner;
import java.util.ArrayList;

public class Player {
	//Used for setting the player name
	private static Scanner scan = new Scanner(System.in);
	private static int playerCount = 0;
	
	private int playerNumber;
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
		playerNumber = playerCount;
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
	
	public void playCard()
	{
		//Gather info from player
		System.out.println("Enter the # of the card within your hand. (Located just before the name of the card)");
		int index = scan.nextInt();

		//Locate the desired card in the hand
		Card card = hand.get(index);
		
		//Take the card out of the hand and subtract its cost from the available funds
		hand.remove(index);
		subtractFunds(card.getPrice());
		
		if(card instanceof Creature)
		{
			System.out.println("Enter the Y coordinate at which you'd like to place your creature. (Creatures are placed at the far end of your side of the board!)");
			int row = scan.nextInt();
			//Column index is the farthest index depending on the player
			int col = (playerNumber == 1) ? 0 : (board.getBoard()[0].length - 1);
			
			//Bounds checking
			while(row >= board.getBoard().length || board.getBoard()[row][col] != null)
			{
				System.out.println("Invalid coordinates given.");
				System.out.println("Enter the Y coordinate at which you'd like to place your creature. (Creatures are placed at the far end of your side of the board!)");
				row = scan.nextInt();
			}
			
			//Place the creature at the chosen cell
			board.getBoard()[row][col] = (Creature)card;
			System.out.println("Placed " + card.getName() + " at (" + col + ", " + row + ")");
			
			//Update playedCreatures for belongsTo()
			playedCreatures.add((Creature)card);
		} else if(card instanceof Spell) {
			
			System.out.println("Enter the X and Y coordinate of the creature you'd like to target one at a time.");
			System.out.print("X: ");
			int col = scan.nextInt();
			System.out.print("\nY: ");
			int row = scan.nextInt();
			System.out.println();
			
			//Bounds checking
			while(row >= board.getBoard().length || col >= board.getBoard()[0].length)
			{
				System.out.println("Invalid coordinates given.");
				System.out.println("Enter the X and Y coordinate of the creature you'd like to target one at a time.");
				System.out.print("X: ");
				col = scan.nextInt();
				System.out.print("\nY: ");
				row = scan.nextInt();
				System.out.println();
			}
			
			try {
				//Use board methods to apply spell damage/heal value to the creature at the given cell
				((Spell)card).act(board.getBoard()[row][col]);
				
				if(card.getName().equals("Heal"))
					System.out.println("Healed the creature at (" + col + ", " + row + ") for " + card.getDamage() + " HP.");
				else
					System.out.println("Did " + card.getDamage() + " damage to the creature at (" + col + ", " + row + ")");
			} catch(NullPointerException e) {
				//Given coordinates do not point to a creature on the board
				System.out.println("You used a " + card.getName() + " spell on an empty space! Shame.");
			}
		}
	}
	
	/*
	public void moveCreature()
	{
		//Selecting the desired creature to move
		System.out.println("Enter the X and Y coordinate of the creature you'd like to move one at a time.");
		System.out.print("X: ");
		int col = scan.nextInt();
		System.out.print("\nY: ");
		int row = scan.nextInt();
		System.out.println();
		
		//Bounds checking
		while(row >= board.getBoard().length || col >= board.getBoard()[0].length)
		{
			System.out.println("Invalid coordinates given.");
			System.out.println("Enter the X and Y coordinate of the creature you'd like to target one at a time.");
			System.out.print("X: ");
			col = scan.nextInt();
			System.out.print("\nY: ");
			row = scan.nextInt();
			System.out.println();
		}
		
		Creature select = board.getBoard()[row][col];
		
		//Select the desired location
	}
	*/
	@Override
	public String toString()
	{
		return "Name: " + playerName + "\nFunds: $" + funds + "\nNumber of Cards: " + hand.size();
	}
}
