package com.parnensaton.folder;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

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
		System.out.print("Enter the name of Player " + playerCount + ": ");
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
	
	public void subtractPoints(int sub)
	{
		//Easier alternative to "player.setPoints(player.getPoints() - x)"
		points -= sub;
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
		System.out.println(playerName + "'s hand: ");
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
	
	public void playCard() throws InputMismatchException
	{
		//Gather info from player
		System.out.println("Enter the # of the card within your hand.(Located just before the name of the card)");
		int index = scan.nextInt();
		
		if(index == -1)
			return;
		
		while(index < 0 || index >= hand.size())
		{
			System.out.println("Please enter a valid card #.");
			System.out.println("Enter the # of the card within your hand.(Located just before the name of the card)");
			index = scan.nextInt();
			
			if(index == -1)
				return;
		}
		
		//Locate the desired card in the hand
		Card card = hand.get(index);
		
		//Ensure card is affordable
		while(funds - card.getPrice() < 0)
		{
			System.out.println("Cannot afford card, please choose another");
			System.out.println("Enter the # of the card within your hand.(Located just before the name of the card)");
			index = scan.nextInt();
			
			if(index == -1)
				return;
			
			card = hand.get(index);
		}
		
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
			while(row >= board.getBoard().length || row < 0 || board.getBoard()[row][col] != null)
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
			
			System.out.println("Enter the X and Y coordinate of the creature you'd like to target.");
			System.out.print("X: ");
			int col = scan.nextInt();
			System.out.print("\nY: ");
			int row = scan.nextInt();
			
			//Bounds checking
			while(row >= board.getBoard().length || col >= board.getBoard()[0].length || row < 0 || col < 0)
			{
				System.out.println("Invalid coordinates given.");
				System.out.println("Enter the X and Y coordinates of the creature you'd like to target.");
				System.out.print("X: ");
				col = scan.nextInt();
				System.out.print("\nY: ");
				row = scan.nextInt();
			}
			
			try {
				//Use board methods to apply spell damage/heal value to the creature at the given cell
				((Spell)card).act(board.getBoard()[row][col]);
				
				if(board.getBoard()[row][col].getHealth() <= 0)
					board.getBoard()[row][col] = null;
				
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
	
	
	public void moveCreature(Player otherPlayer) throws InputMismatchException
	{
		//Selecting the desired creature to move
		System.out.println("Enter the X and Y coordinate of the creature you'd like to move.");
		System.out.print("X: ");
		int col = scan.nextInt();
		System.out.print("\nY: ");
		int row = scan.nextInt();
		
		if(col == -1 && row == -1)
			return;
		
		//Bounds checking
		while(row >= board.getBoard().length || col >= board.getBoard()[0].length || row < 0 || col < 0 || board.getBoard()[row][col] == null || (board.getBoard()[row][col] != null && !board.getBoard()[row][col].belongsTo(this)))
		{
			System.out.println("Invalid coordinates given.");
			System.out.println("Enter the X and Y coordinate of the creature you'd like to move.");
			System.out.print("X: ");
			col = scan.nextInt();
			System.out.print("\nY: ");
			row = scan.nextInt();
			
			if(col == -1 && row == -1)
				return;
		}
		
		Creature selected = board.getBoard()[row][col];
		int oldRow = row;
		int oldCol = col;
		
		//Select the desired location
		System.out.println("Enter the X and Y coordinate of location you'd like to move the selected creature to.");
		System.out.print("X: ");
		col = scan.nextInt();
		System.out.print("\nY: ");
		row = scan.nextInt();
		
		//Make sure desired location is adjacent to the current location
		while(row >= board.getBoard().length || col >= board.getBoard()[0].length || row < 0 || col < 0 || Math.abs(oldRow - row) > 1 || Math.abs(oldCol - col) > 1 || (board.getBoard()[row][col] != null && board.getBoard()[row][col].belongsTo(this)))
		{
			System.out.println("Invalid coordinates given.");
			System.out.println("Enter the X and Y coordinate of location you'd like to move the selected creature to.");
			System.out.print("X: ");
			col = scan.nextInt();
			System.out.print("\nY: ");
			row = scan.nextInt();
		}
		
		if(board.getBoard()[row][col] != null)
		{
			//Combat
			Creature other = board.getBoard()[row][col];
			selected.combat(other);
			
			//Cleanup
			if(selected.isDead() && other.isDead())
			{
				board.getBoard()[oldRow][oldCol] = null;
				board.getBoard()[row][col] = null;
				
				System.out.println("Kamikaze! Both players earn $1.5 compensation coins! :)");
				funds += 1.5;
				otherPlayer.addFunds(1.5);
			} else if(selected.isDead())
			{
				board.getBoard()[oldRow][oldCol] = null;
				
				System.out.println(playerName + " lost 500 points!");
				System.out.println(otherPlayer.getName() + " gained 750 points!");
				points -= 500;
				otherPlayer.addPoints(750);
			} else if(other.isDead())
			{
				board.getBoard()[oldRow][oldCol] = null;
				board.getBoard()[row][col] = selected;
				
				System.out.println(playerName + " gained 1500 points!");
				System.out.println(otherPlayer.getName() + " lost 500 points!");
				points += 1500;
				otherPlayer.subtractPoints(500);
			}
		} else {
			//Empty space
			board.getBoard()[oldRow][oldCol] = null;
			board.getBoard()[row][col] = selected;
		}
		
		System.gc();
	}
	
	public boolean isWinner()
	{
		return points == 7500;
	}
	
	@Override
	public String toString()
	{
		return "Name: " + playerName + "\nFunds: $" + funds + "\nNumber of Cards: " + hand.size();
	}
}
