package student;

import java.io.*;
import java.util.Scanner;

public class Closest
{
	public static double closest(String fn)
		throws IOException
	{
		File f = new File(fn);
		Scanner scan = new Scanner(f);

		// ...

		scan.close();
		return 0.0;
	}

	public static void main(String[] args)
		throws IOException
	{
		System.out.println(closest("../data/points.txt"));
	}
}