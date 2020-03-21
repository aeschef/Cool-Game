package com.parnensaton.folder;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public abstract class Names
{
	public static String generate()
	{
		String out = "";
		
		/*
		 * Create the first name by concatenating one string from either
		 * One_Syllable_Names.txt or Two_Syllable_Names.txt
		 */
		try {
			Scanner scan;
			int duration;

			int num = (int)(Math.random() * 10);

			//Random chance to choose a one or two syllable first name
			if(num % 2 == 0)
			{
				scan = new Scanner(new File("res\\One_Syllable_Names.txt"));
				duration = (int)(Math.random() * 1100) + 1;
			} else {
				scan = new Scanner(new File("res\\Two_Syllable_Names.txt"));
				duration = (int)(Math.random() * 4588) + 1;
			}

			out += findName(duration, scan) + " ";
			scan.close();

		} catch(FileNotFoundException e) {
			//Initiate computer-is-crapping-itself-protocol
			System.out.println("FileNotFoundException thrown while generating first name.");
		} catch(NoSuchElementException e) {
			System.out.println("NoSuchElementException thrown while generating last name.");
		}


		/*
		 * Create the last name by concatenating one string each
		 * from Khordaldrum_Last_A.txt and Khordaldrum_Last_B.txt
		 */

		try {
			//Prepare to read from source files for the first name
			Scanner scanOne = new Scanner(new File("res\\Khordaldrum_Last_A.txt"));
			Scanner scanTwo = new Scanner(new File("res\\Khordaldrum_Last_B.txt"));

			//Generate "indices" of random word-parts in source files based on the number of lines in each file
			int durationOne = (int)(Math.random() * 168) + 1;
			int durationTwo = (int)(Math.random() * 61) + 1;
			
			//Find the first word-part and add it to the final name
			out += findName(durationOne, scanOne);
			scanOne.close();

			//Find the second word-part and add it to the final name
			out += findName(durationTwo, scanTwo);
			scanTwo.close();
			
		} catch(FileNotFoundException e) {
			//Initiate computer-is-crapping-itself-protocol
			System.out.println("FileNotFoundException thrown while generating last name.");
		} catch(NoSuchElementException e) {
			System.out.println("NoSuchElementException thrown while generating last name.");
		}
	
		return out;
	}
	
	public static String generateBossName()
	{
		String out = "";
		
		try {
			Scanner scan;
			int duration;
			
			int num = (int)(Math.random() * 10) + 1;

			//Random chance to choose a Star Wars or Warhammer title
			if(num % 2 == 0)
			{
				scan = new Scanner(new File("res\\Galactic Empire Titles.txt"));
				duration = (int)(Math.random() * 8) + 1;
			} else {
				scan = new Scanner(new File("res\\Space Marine Titles.txt"));
				duration = (int)(Math.random() * 5) + 1;
			}
			
			out += findName(duration, scan) + " ";
			
			//Redirect scanner to read from new set of names
			//Could use generate() w/ .substring(), but wouldn't be as efficient
			scan = new Scanner(new File("res\\Male & Female.txt"));
			duration = (int)(Math.random() * 627) + 1;
			
			out += findName(duration, scan);
			
			scan.close();
		} catch(FileNotFoundException e) {
			//Initiate computer-is-crapping-itself-protocol
			System.out.println("FileNotFoundException thrown while generating boss name.");
		} catch(NoSuchElementException e) {
			System.out.println("NoSuchElementException thrown while generating boss name.");
		}
		
		return out;
	}
	
	//Helper method to avoid a bunch of the same while loop over and over again
	public static String findName(int duration, Scanner scan) throws NoSuchElementException
	{
		while(duration > 0 && scan.hasNextLine())
		{
			scan.nextLine();
			duration--;
		}
		
		return scan.nextLine();
	}
}
