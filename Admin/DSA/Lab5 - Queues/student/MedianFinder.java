package student;

import java.util.Scanner;
import java.io.*;

public class MedianFinder
{

	public BinaryHeap small;
	public BinaryHeap large;


	public MedianFinder()
	{
		small = new BinaryHeap(10);
		large = new BinaryHeap(10);

	}

	public void insert(int v)
	{
		if (small.size() == 0 || v <= -small.find_min()) {
			small.insert(-v);
		} else {
			large.insert(v);
		}


		if (small.size() > large.size() + 1) {
			large.insert(-small.remove_min());
		} else if (large.size() > small.size() + 1) {
			small.insert(-large.remove_min());
		}
	}

	public int getMedian()
	{
		if(small.size() >= large.size()){
			return -small.find_min();
		} else {
			// large has more elements
			return large.find_min();
		}
		// return -1;
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
		// runFile(args[0]);

		MedianFinder mfdoom = new MedianFinder();
		mfdoom.insert(1);
		mfdoom.insert(-1);
		mfdoom.insert(2);
		mfdoom.insert(3);
		mfdoom.insert(4);
		mfdoom.insert(5);
		System.out.println("Small size: " + mfdoom.small.size() + " Large size: " + mfdoom.large.size());
		// System.out.println(mfdoom.large.remove_min());
		// System.out.println(mfdoom.large.remove_min());
		// System.out.println(mfdoom.large.remove_min());
		// System.out.println(mfdoom.getMedian());
		System.out.println(mfdoom.getMedian());
	}
}
