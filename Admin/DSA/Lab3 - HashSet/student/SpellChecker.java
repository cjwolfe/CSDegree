package student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class SpellChecker
{
	private static final String HOME_STRING = "DSA/Lab3/student/";
	private static final String DICT_FILE = "dictionary.txt";
	private StringSet words;
	
	
	public SpellChecker()
	{
		words = new StringSet();
		File f = new File(DICT_FILE);

		try{
		if(!f.exists()){
			File g = new File(HOME_STRING+DICT_FILE);
			f = g;
		}
		} catch (Exception fuck){
			System.out.println("FUCK");
		}

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


		StringBuilder result = new StringBuilder(input.length());

		

		char[] chars = input.toCharArray();
		for(char c : chars){
			result.append(c);
		}

		for(int i = 0; i < input.length(); i++){
			char originalChar = result.charAt(i);
			for(char c = 'a'; c <= 'z'; c++){
				if(c == originalChar) continue;
				result.setCharAt(i,c);
				String currentTry = result.toString();
				// testing
				// System.out.print(currentTry);
				if(words.find(currentTry)){
					suggestions.add(result.toString());
				}

			}
			result.setCharAt(i,originalChar);

		}


		return suggestions;
	}

	public static void main(String[] args)
	{
		SpellChecker checker = new SpellChecker();

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter word: ");
		String word = scanner.next();
		scanner.close();

		// if(word == "coee"){
		// 	System.out.print(
		// 		"Could not find coee\n"
		// 		+ "Consider the following alternatives...\n"
		// 		+ "code\n"
		// 		+ "coke\n"
		// 		+ "cole\n"
		// 		+ "come\n"
		// 		+ "cone\n"
		// 		+ "cope\n"
		// 		+ "core\n"
		// 		+ "cove\n"
		// 		// + "new String[]{\"code\", \"coke\", \"cole\", \"come\", \"cone\", \"cope\", \"core\", \"cove\", }"
		// 		);

		// } else{



		if (checker.words.find(word)){
			System.out.println(word + " is a valid word.");
		// suggestions testing
			// System.out.println(word + " and suggestions are: ");
			// 			System.out.println("Consider the following alternatives...");
			// for (String candidate : checker.getSuggestions(word))
			// 	System.out.println(candidate);
		}
			else
		{
			System.out.println("Could not find " + word);
			System.out.println("Consider the following alternatives...");
			// StringBuilder sb = new StringBuilder();

			for (String candidate : checker.getSuggestions(word)){
				System.out.println(candidate);
				// sb.append(candidate);
				// sb.append("\n");
			}
	

			// System.out.print("new String[]{");
			// 	boolean first = true;
			// 	for (String candidate : checker.getSuggestions(word)) {
			// 		if (!first) {
			// 			System.out.print(", ");
			// 		}
			// 		System.out.print("\"" + candidate + "\"");
			// 		first = false;
			// 	}
			// 	System.out.println("}");


			System.out.print("new String[]{");
			for (String candidate : checker.getSuggestions(word))
				System.out.print("\"" + candidate + "\", ");
			System.out.println("}");
		}
				// }
	}
}