package student;

import java.util.Scanner;
import java.io.*;

public class MedianFinder
{
	public MedianFinder()
	{
		
	}

	public void insert(int v)
	{
	}

	public int getMedian()
	{
		return -1;
	}

	public static void runFile(String filename)
		throws IOException
	{
		MedianFinder mfinder = new MedianFinder();
		Scanner scan = new Scanner(new File(filename));

		while (scan.hasNext())
		{
			char command = scan.next().charAt(0);
			if (command == 'i')
				mfinder.insert(scan.nextInt());
			else if (command == 'q')
				System.out.println(mfinder.getMedian());
			else break;
		}
		scan.close();
	}

	public static void main(String[] args)
		throws IOException
	{
		runFile(args[0]);
	}
}
