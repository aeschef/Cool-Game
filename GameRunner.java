package com.parnensaton.folder;

public class GameRunner 
{
	/*
	 * This class will run the game and initialize the game board.
	 */
	public static void main(String[] args) 
	{
		//creates the board and prints the initial state
		Board gameBoard = new Board();
		gameBoard.printBoard();
	}

}
