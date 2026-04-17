package student;

import java.util.Scanner;
import java.util.HashMap;

public class Collatz
{
	public static HashMap<Long, Long> map = new HashMap();
	public static int collatzLength(long x)
	{
		// if an existing length has been computed, find it
		if(map.containsKey(x)){
			return map.get(x).intValue();
		}
		// base case
		if (x == 1){
			return 1;
		}

		long next = (x % 2 == 0) ? (x / 2) : (x * 3 + 1);
		int len = 1 + collatzLength(next);

		map.put(x, (long) len);
		
		return len;
	}


	public static long longestInRange(long a, long b)
	{


		long start = Math.min(a,b);
		long end = Math.max(a,b);

		long maxLength = 0;
		long ans = start;

		for(long i = start; i <= end; i++){
			int currLength = collatzLength(i);
			if(currLength > maxLength){
				maxLength = currLength;
				ans = i;
			}
		}
		return ans;
		// return -1;
	}
	
	public static void main(String[] args)
	{
		map = new HashMap<Long, Long>();
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the range: ");
		long a = scan.nextLong();
		long b = scan.nextLong();
		long best = longestInRange(a, b);
		int n = collatzLength(best);
		System.out.printf("The number with the greatest Collatz length in this range: %d\n", best);
		System.out.printf("The Collatz length of %d is %d\n", best, n);

		// testing
		// long lastlargest = 100;
		// for(long i = 100; i > 2; i = lastlargest){
		// 	long a = 1;
		// 	long b = lastlargest;

		// 	long best = longestInRange(a,b);
		// 	int n = collatzLength(best);
		// 	lastlargest = best -1;

		// 	System.out.printf("The number with the greatest Collatz Length in this range: %d\n", best);
		// 	System.out.printf("The Collatz length of %d is %d\n", best, n);

		// }


	}
}
