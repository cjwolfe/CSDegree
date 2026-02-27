package student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class SpellChecker
{
	private static final String DICT_FILE = "dictionary.txt";
	private StringSet words;
	
	public SpellChecker()
	{
		words = new StringSet();
		File f = new File(DICT_FILE);
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(f);
			while (scanner.hasNext())
			{
				String word = scanner.next();
				words.insert(word);
			}
			scanner.close();
			System.out.println("Loaded " + DICT_FILE);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not open file " + DICT_FILE);
			e.printStackTrace();
		}
	}

	public ArrayList<String> getSuggestions(String input)
	{
		ArrayList<String> suggestions = new ArrayList<>();
		// ...
		return suggestions;
	}

	public static void main(String[] args)
	{
		SpellChecker checker = new SpellChecker();

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter word: ");
		String word = scanner.next();
		scanner.close();

		if (checker.words.find(word))
			System.out.println(word + " is a valid word.");
		else
		{
			System.out.println("Could not find " + word);
			System.out.println("Consider the following alternatives...");
			for (String candidate : checker.getSuggestions(word))
				System.out.println(candidate);

			System.out.print("new String[]{");
			for (String candidate : checker.getSuggestions(word))
				System.out.print("\"" + candidate + "\", ");
			System.out.println("}");
		}
	}
}