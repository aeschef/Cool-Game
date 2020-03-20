package com.parnensaton.folder;


public class Board 
{
	
	//private instance variables
	private Creature[][] boardCreatures;
 	private static String border = "---------------------------------------------";
	
	//constructor
	public Board()
	{		
		// initializes board containing creatures
		boardCreatures = new Creature[4][5];
		
		
		
		//25x75
	}
	
	//Prints out the board in an aesthetically pleasing way :)
	public void printBoard()
	{
		//adds top of board
		String aestheticBoard = border;
		aestheticBoard += "\n";
		
		//outer for loop iterates down each row to access all 4 rows of creature array
		for(int row = 0; row < boardCreatures.length; row++)
		{
			aestheticBoard += returnRow(row);
			aestheticBoard += border + "\n";
		}
		
		System.out.println(aestheticBoard);
	}
	
	
	public String returnRow(int r)
	{
		String out = "";
	
			for(int col1 = 0; col1 < 5; col1++)
			{
				if(boardCreatures[r][col1] != null)
					out += "|" +  boardCreatures[r][col1].getFirstName() + "      |";
				else
				{
					
						out += "|       |";
				}
			}
			out += "\n";
		
			for(int col2 = 0; col2 < 5; col2++)
			{
				if(boardCreatures[r][col2] != null)
					out += " | " + boardCreatures[r][col2].getLastName() + "       |";
				else
				{
					
						out += "|       |";
				}
			}
			out += "\n";
		
			for(int col3 = 0; col3 < 5; col3++)
			{
				if(boardCreatures[r][col3] != null)
					out += "| " + boardCreatures[r][col3].getHealth() + "        " + boardCreatures[r][col3].getDamage() + "|";
				else
				{
					
						out += "|       |";
				}
			}
			out += "\n";
		
		
		return out;
	}
	/*
	//fills the board with appropriate cells and dimensions
	public void fillBoard()
	{
		/*Implementation not developed
	}
	
	//
	public void playCard(int xCor, int yCor)
	{
		/*Implementation not developed
	}
	
	//
	public void addCreature(int xCor, int yCor, Creature toBeAdded)
	{
		///*Implementation not developed
	}
*/
	
	
}
