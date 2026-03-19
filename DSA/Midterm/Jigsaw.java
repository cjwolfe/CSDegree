package student;

import java.io.IOException;

public class Jigsaw {
	public static int piecesMissing(String filename)
		throws IOException
	{
		return -1;
	}

	public static void main(String[] args)
		throws IOException
	{
		// pass the filename in as a command-line argument to test
		// for example: java Jigsaw puzzle-01-in.txt
		int count = piecesMissing(args[0]);
		System.out.println(count);
	}
}
