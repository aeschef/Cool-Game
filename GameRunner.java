package com.parnensaton.folder;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameRunner 
{
	/*
	 * This class will run the game and initialize the game board.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
	
		printTitle();
		//Absorbs "Press Enter to continue: " and makes garbage
		String string = scan.nextLine();
		string = null;
		
		//creates the board and Players and prints 
		Board gameBoard = new Board();
		Player player1 = new Player(gameBoard);
		Player player2 = new Player(gameBoard);
		
		//variables to track whose turn is when
		int turnCounter = 1;
		
		
		
		while(!(player1.isWinner()) && !(player2.isWinner()))
		{
			Player currentPlayer = (turnCounter % 2 != 0 ? player1 : player2);
			Player otherPlayer = (turnCounter % 2 != 0 ? player2 : player1);
			int playCount = 0;
			int moveCount = 0;
			//Prevents board and hand from being printed when player chooses to pass
			boolean isPassing = false;
			
			//print board & player's hand 
			gameBoard.printBoard();
			System.out.println("~~~~~~~~~ < " + currentPlayer.getName() + "'s turn > ~~~~~~~~~");
			System.out.println("Points: " + currentPlayer.getPoints() + "\nFunds: $" + currentPlayer.getFunds() + "\n");
			currentPlayer.printHand();
			
			String turn = "";
			
				//while the playCount less than 1 or move count is less than 2
 				while((playCount < 1 || moveCount < 2) && !(turn.equals("y")))
 				{
 					//ask player what they want to do
 					System.out.println("Would you like to play a card(creature/spell) or move a creature? (\"play\" , \"move\", \"pass\")");
				
 					//take in users response
 					turn = scan.nextLine();
 					if(turn.equals("play"))
 					{
 						//plays card
 						if(playCount < 1)
 						{
	 						try {
 								currentPlayer.playCard();
	 							playCount++;
	 							
	 						} catch(InputMismatchException e) {
	 							System.out.println("Invalid input! Maybe play by the rules next time...");
	 						}
 						}
 						else
 						{
 							System.out.println("<<<You have already played one card this turn! Either move a creature or pass.>>>");
 						}
 					}
 					else if(turn.equals("move"))
 					{
 						if(moveCount < 2)
 						{
 							//moves creature
 							try {
 								currentPlayer.moveCreature(otherPlayer);
 								moveCount++;
 							} catch(InputMismatchException e) {
 								System.out.println("Invalid input! You should know better than that.");
 							}
 						}
 						else
 						{
 							System.out.println("<<<You have already moved two creatures this turn! Either play a card or pass.>>>");
 						}
 					}
 					else
 					{
 						//checks to make sure player intends to pass their turn
 						System.out.println("You are relinquishing your right to a turn..... are you sure??? (y / n)");
					
 						//takes in player's response
 						turn = scan.nextLine();
					
 						//player chooses to pass
 						if(turn.equals("y"))
 						{
 							System.out.println("You have relinquished your right to a turn..... I hope you do not regret this grave decision.");
 							isPassing = true;
 						}
 						else//player doesnt pass
 						{
 							//second chance to choose correct option
 							System.out.println("Would you like to play a card(creature/spell) or move a creature? (\"play\" , \"move\", \"pass\")");
 							turn = scan.nextLine();
 							if(turn.equals("play"))
 							{
 		 						if(playCount < 1)
 		 						{
 		 							//plays card
 		 							currentPlayer.playCard();
 		 							playCount++;
 		 						}
 		 						else
 		 						{
 		 							System.out.println("<<<You have already played one card this turn! Either move a creature or pass.>>>");
 		 						}
 							}
 							else if(turn.equals("move"))
 							{
 								if(moveCount < 2)
 		 						{
 		 							//moves creature
 		 							currentPlayer.moveCreature(otherPlayer);
 		 							moveCount++;
 		 						}
 		 						else
 		 						{
 		 							System.out.println("<<<You have already moved two creatures this turn! Either play a card or pass.>>>");
 		 						}
 							}
 						}
				
 					}
 					
 					if(!isPassing)
 					{
	 					gameBoard.printBoard();
	 					System.out.println("Points: " + currentPlayer.getPoints() + "\nFunds: $" + currentPlayer.getFunds() + "\n");
	 					currentPlayer.printHand();
 					}
 				}
 				
				
			//pause so player 1 can assess && ruminate upon their play
			try {
				System.out.println("Turn over. Loading " + otherPlayer.getName() + "'s data...");
				Thread.sleep(3000);
			}catch(InterruptedException e) {}
			
			//Deduct health from all creatures with health > 10
			deductPastTen(gameBoard.getBoard());
			
			//Print lines to make other board dissappear
			for(int i = 0; i < 10; i++)
					System.out.println();
			
			//give the player 3.5 at the end of every turn without going over max capacity
			if(currentPlayer.getFunds() < 6.5)
				currentPlayer.addFunds(3.5);
			else
				currentPlayer.setFunds(10);
			
			//player draws card 
			currentPlayer.drawCard();
			
			//increment
			turnCounter++;
			
			
		}
		scan.close();
		
	}
	
	public static void printTitle()
	{
		try {
			//Read from text file containing title screen
			Scanner printer = new Scanner(new File("res\\title.txt"));
			
			while(printer.hasNextLine())
				System.out.println(printer.nextLine());
			
			
			String txt = "/!\\ Warning: This game contains neither snakes nor deities of any kind... for now.";
			
			//Print the above string all fancy-like
			for(int i = 0; i < txt.length(); i++)
			{
				if(i >= txt.indexOf('.') && i <= txt.lastIndexOf('.'))
					Thread.sleep(300);
				else
					Thread.sleep(30);
				
				System.out.print(txt.charAt(i));
			}
			
			System.out.print("\nPress Enter to continue: ");
			
			printer.close();
		} catch (FileNotFoundException | InterruptedException e) {
			System.out.println("Resource files could not be accessed.\nPress Enter to continue: ");
		}
	}
	
	public static void deductPastTen(Creature[][] arr)
	{
		for(Creature[] row : arr)
		{
			for(Creature creature : row)
			{
				try {
					if(creature.getHealth() > 10)
						creature.setHealth(creature.getHealth() - 1);
				} catch(NullPointerException e) {}
			}
		}
	}
}