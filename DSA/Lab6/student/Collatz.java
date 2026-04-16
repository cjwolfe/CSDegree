package student;

import java.util.Scanner;

public class Collatz
{
	public static int collatzLength(long x)
	{
		return -1;
	}

	public static long longestInRange(long a, long b)
	{
		return -1;
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the range: ");
		long a = scan.nextLong();
		long b = scan.nextLong();
		long best = longestInRange(a, b);
		int n = collatzLength(best);
		System.out.printf("The number with the greatest Collatz length in this range: %d\n", best);
		System.out.printf("The Collatz length of %d is %d\n", best, n);
	}
}
