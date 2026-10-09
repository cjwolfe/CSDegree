package student;

import java.util.*;
import java.io.*;

public class WordLadder
{
	private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private static HashSet dictionary = new HashSet();
	public static List<String> solve(String dictfile, String start, String end)
	{
		try
		{
			File dict = new File(dictfile);
			Scanner scan = new Scanner(dict);
			while (scan.hasNext())
				dictionary.add(scan.next());
			scan.close();

		}
		catch (IOException e)
		{
			return null;
		}

		if(!dictionary.contains(start) || !dictionary.contains(end)){
			return null;
		}

		// System.out.println("The difference between " + start + " and " + end + " is " + difference(start,end));

		Queue<String> queue = new LinkedList<>();
		Map<String, String> parentMap = new HashMap<>();

		queue.add(start);
		parentMap.put(start,null);

		while(!queue.isEmpty()){
			String current = queue.poll();
			
			if(current.equals(end)){
				LinkedList<String> path = new LinkedList<>();
				String step = end;
				
				while (step != null) {
					path.addFirst(step);
					step = parentMap.get(step);
				}
				
				return path;
			}
			bfs(current,queue,parentMap);

		}
		return null;
	}

	public static void bfs(String current, Queue<String> queue, Map<String, String> parentMap) {
		char[] chars = current.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			char originalChar = chars[i];

			for (char c : alphabet) {
				if (c == originalChar) continue;

				chars[i] = c;
				String neighbor = new String(chars);

				if (dictionary.contains(neighbor) && !parentMap.containsKey(neighbor)) {
					parentMap.put(neighbor, current);
					queue.add(neighbor);
				}
			}
			chars[i] = originalChar;
		}
	}

	// public static int difference(String m, String n){
	// 	int count = 0;
	// 	// char [] M = m.toCharArray();
	// 	// char [] N = n.toCharArray();

	// 	for(int i = 0; i < m.length(); i++){
	// 		if(m.charAt(i) != n.charAt(i)){
	// 			count++;
	// 		}
	
	// 	}
	// 	return count;
	// }

	// call this with 1 command line argument
	// args[0] should be the relative path to "dictionary4.txt"
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the start word: ");
		String start = scan.next();
		System.out.print("Enter the end word: ");
		String end = scan.next();

		String testing = "dictionary4.txt";

		// replace testing with args[0] for submit
		List<String> solution = solve(args[0], start, end);
		if (solution == null)
			System.out.println("Impossible!");
		else
		{
			System.out.println("Possible!\n");
			for (String word : solution)
				System.out.println(word);
		}
		scan.close();
	}
}