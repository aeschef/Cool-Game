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
		
		
		
		while(!(player1.isWinner()) && !(player2.isWinner()))
		{
			Player currentPlayer = (turnCounter % 2 != 0 ? player1 : player2);
			int playCount = 0;
			int moveCount = 0;
			
				//print board & player's hand 
				gameBoard.printBoard();
				currentPlayer.printHand();
				
				//prints out the funds in currentPlayer's hand
				System.out.println(currentPlayer + "'s funds: " + currentPlayer.getFunds());
				String turn = "";
				//while the playCount less than 1 or move count is less than 2
 				while((playCount < 1 || moveCount < 2) && !(turn.equals("pass")))
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
 							currentPlayer.playCard();
 							playCount++;
 						}
 						else
 						{
 							System.out.println("You have already played one card this turn! Either move a creature or pass");
 						}
 					}
 					else if(turn.equals("move"))
 					{
 						if(moveCount < 2)
 						{
 							//moves creature
 							currentPlayer.moveCreature(player2);
 							moveCount++;
 						}
 						else
 						{
 							System.out.println("You have already moved two creatures this turn! Either play a card or pass");
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
 							System.out.println("You have relinquished your right to a turn..... I hope you do not regret this grave decision");
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
 		 							System.out.println("You have already played one card this turn! Either move a creature or pass");
 		 						}
 							}
 							else if(turn.equals("move"))
 							{
 								if(moveCount < 2)
 		 						{
 		 							//moves creature
 		 							currentPlayer.moveCreature(player2);
 		 							moveCount++;
 		 						}
 		 						else
 		 						{
 		 							System.out.println("You have already moved two creatures this turn! Either play a card or pass");
 		 						}
 							}
 						}
				
 					}
 				}
				
			//pause so player 1 can assess && ruminate upon their play
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {}
			
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
}
