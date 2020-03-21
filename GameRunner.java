package com.parnensaton.folder;

import java.lang.Thread;
import java.util.Scanner;
public class GameRunner 
{
	/*
	 * This class will run the game and initialize the game board.
	 */
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		//creates the board and Players and prints 
		Board gameBoard = new Board();
		gameBoard.printBoard();
		Player player1 = new Player(gameBoard);
		Player player2 = new Player(gameBoard);
		
		//variables to track whose turn is when
		int turnCounter = 1;
		boolean isPlayer1Turn = true;
		
		while(/*!(player1.isWinner()) && !(player2.isWinner())*/ turnCounter < 10)
		{
			isPlayer1Turn = (turnCounter % 2 != 0) ? true : false;
			
			if(isPlayer1Turn)
			{
				//print board & player's hand 
				gameBoard.printBoard();
				player1.printHand();
				
				System.out.println("Would you like to play a card(creature/spell) or move a creature? (\"play\" , \"move\", \"pass\"");
				String turn = scan.nextLine();
				if(turn.equals("play"))
				{
					player1.playCard();
				}
				else if(turn.equals("move"))
				{
					player1.moveCreature();
				}
				else
				{
					System.out.println("You have relinquished your right to a turn..... I hope you do not regret this grave decision");
				}
			
				
			}
			else
			{
				//print board & player's hand 
				gameBoard.printBoard();
				player2.printHand();
				
				System.out.println("Would you like to play a card(creature/spell) or move a creature? (\"play\" , \"move\", \"pass\")");
				String turn = scan.nextLine();
				if(turn.equals("play"))
				{
					player2.playCard();
				}
				else if(turn.equals("move"))
				{
					player2.moveCreature();
				}
				else
				{
					System.out.println("You have relinquished your right to a turn..... I hope you do not regret this grave decision");
				}
			}
			try {
			Thread.sleep(3000);
			}catch(InterruptedException e) {}
			
			for(int i = 0; i < 10; i++)
					System.out.println();
			turnCounter++;
		}
		scan.close();
	}

}
