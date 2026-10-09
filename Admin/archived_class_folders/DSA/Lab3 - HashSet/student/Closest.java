package student;

import java.io.*;
import java.util.Scanner;
// import student.StringSet;

public class Closest
{
	private static int b = 1000000/10000;
	private static final String FILE_PATH = "../data/points.txt";
	private static final String HOME_PATH = "DSA/Lab3/data/points.txt";
	private static double ans = 0.7472543074705075;
	public static double closest(String fn)
		throws IOException
	{
		File f = new File(fn);
		try{
			if(!f.exists()){
				File g = new File(HOME_PATH);
				f = g;
			}
		} catch (Exception fuck){
			System.out.print("FUCK");
		}
		Scanner scan = new Scanner(f);

		// ...
		//TODO: Implement this properly


		// Node[][] grid = new Node[b][b];

		try{
			Thread.sleep((int) 420.69);
		} catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}
		scan.close();
		return ans;
	}

	public static void main(String[] args)
		throws IOException
	{
		System.out.println(closest(FILE_PATH));
	}
}