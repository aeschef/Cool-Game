package com.parnensaton.folder;


public class Board 
{
	
	//private instance variables
	private Creature[][] boardCreatures;
 	private static String border = "------------------------------------------------------------------------------------------";
	
	//constructor
	public Board()
	{		
		// initializes board containing creatures
		boardCreatures = new Creature[5][6];
		
	}
	
	/**
	 * Prints out the board in an aesthetically pleasing way :)
	 */
	public void printBoard()
	{
		//adds top of board
		String aestheticBoard = "           Col 0          Col 1          Col 2          Col 3          Col 4          Col 5";
		
		aestheticBoard += "\n" + "      " + border;

		aestheticBoard += "\n";
		
		//outer for loop iterates down each row to access all 4 rows of creature array
		for(int row = 0; row < boardCreatures.length; row++)
		{
			aestheticBoard += returnRow(row);
			aestheticBoard += border + "\n";
		}
		
		System.out.println(aestheticBoard);
	}
	
	/**
	 * 
	 * @param r the row being accessed
	 * @return the row as a string with cards in appropriate locations
	 */
	public String returnRow(int r)
	{
		//declare and initialize the String that will be returned
		String out = "      ";
	
			//iterates through each creature in row r
			for(int col1 = 0; col1 < 6; col1++)
			{
				// if Creature exists at given indices adds the first name to out
				if(boardCreatures[r][col1] != null)
				{
					String tempString = boardCreatures[r][col1].getFirstName();
					while(tempString.length() < 13)
					{
						if(tempString.length() % 2 == 0)
							tempString = " " + tempString;
						else
							tempString = tempString + " ";
					}
					tempString = "|" + tempString + "|";
					out += tempString;
				}
				else
				{
					//filler spaces in the event no creature exists at given indices
					out += "|             |";
				}
			}
			
			//new line
			out += "\n Row " + r;
		
			//iterates through each creature in row r
			for(int col2 = 0; col2 < 6; col2++)
			{
				// if Creature exists at given indices adds the Last name to out
				if(boardCreatures[r][col2] != null)
				{
					String tempString = boardCreatures[r][col2].getLastName();
					while(tempString.length() < 13)
					{
						if(tempString.length() % 2 == 0)
							tempString = " " + tempString;
						else
							tempString = tempString + " ";
					}
					tempString = "|" + tempString + "|";
					out += tempString;
				}
				else
				{
					//filler spaces in the event no creature exists at given indices
					out += "|             |";
				}
			}
			
			//new line
			out += "\n      ";
		
			//iterates through each creature in row r
			for(int col3 = 0; col3 < 6; col3++)
			{
				//if Creature exists at given indices adds teh health and damage to out
				if(boardCreatures[r][col3] != null)
				{
					if(boardCreatures[r][col3].getHealth() / 10 > 0 && boardCreatures[r][col3].getDamage() / 10 > 0)
					{
						out += "| " + boardCreatures[r][col3].getHealth() + "        " + boardCreatures[r][col3].getDamage() + "|";
					}
					else if(boardCreatures[r][col3].getHealth() / 10 > 0)
					{
						out += "|" + boardCreatures[r][col3].getHealth() + "          " + boardCreatures[r][col3].getDamage() + "|";
					}
					else if(boardCreatures[r][col3].getDamage() / 10 > 0)
					{
						out += "| " + boardCreatures[r][col3].getHealth() + "          " + boardCreatures[r][col3].getDamage() + "|";
					}
					else
					{
						out += "| " + boardCreatures[r][col3].getHealth() + "         " + boardCreatures[r][col3].getDamage() + " |";
					}
				}
				else
				{
					//filler spaces in the event no creature exists at given indices
					out += "|             |";
				}
			}
			
			//new line
			out += "\n      ";
		
		
		return out;
	}
	
	
	
	/**
	 * 
	 * @return the 2D array containing all cards on the board
	 */
	public Creature[][] getBoard()
	{
		return boardCreatures;
	}
	
	
	/**
	 * 
	 * @param xCor
	 * @param yCor
	 */
	public void playCard(int xCor, int yCor)
	{
		//Implementation not developed
	}
	
	
	
	/**
	 * 
	 * @param xCor
	 * @param yCor
	 * @param toBeAdded
	 */
	public void addCreature(int xCor, int yCor, Creature toBeAdded)
	{
		//Implementation not developed
	}

	
	
}
