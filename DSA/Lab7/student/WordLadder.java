package student;

import java.util.*;
import java.io.*;

public class WordLadder
{
	public static List<String> solve(String dictfile, String start, String end)
	{
		try
		{
			File dict = new File(dictfile);
			Scanner scan = new Scanner(dict);
			while (scan.hasNext())
				; // add scan.next() to the collection of words
			scan.close();
		}
		catch (IOException e)
		{
			return null;
		}

		// TODO: create a word ladder
		return null;
	}

	// call this with 1 command line argument
	// args[0] should be the relative path to "dictionary4.txt"
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the start word: ");
		String start = scan.next();
		System.out.print("Enter the end word: ");
		String end = scan.next();

		List<String> solution = solve(args[0], start, end);
		if (solution == null)
			System.out.println("Impossible!");
		else
		{
			System.out.println("Possible!\n");
			for (String word : solution)
				System.out.println(word);
		}
	}
}