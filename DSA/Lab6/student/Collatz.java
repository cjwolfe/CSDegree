package student;

import java.util.Scanner;
import java.util.HashMap;

public class Collatz
{
	public static HashMap<Long, Long> map;
	private static long length;
	public static int collatzLength(long x)
	{
		// if an existing length has been computed, find it


		if(map.containsKey(x)){
			return map.get(x).intValue();
		}

		// this is a new item, compute the collatz length
		// long length = 0;
		long key = 0;
		// while(key != 1){
		
		key = (long) collatz(x);
		// }
		if(key == 1){
			// it puts the key in the basket
			map.put(x, length);
		}



		// cleanup tracker
		length = 0;
		return map.get(x).intValue();
	}

	public static int collatz(long n){
		if(n == 1){
			length++;
			return (int) n;
		}
		if(n % 2 == 0){
			length++;
			return collatz(n/2);
		} else {
			long ret = 3 * (n) + 1;
			length++;
			return collatz(ret);
		}
	}

	public static long longestInRange(long a, long b)
	{
		// compute range first
		// would be more efficient to check for longest values now 
		// but let's get it working first
		if(a < b){
			for(long i = a; i < b; i++){
				// long temp = collatzLength(i);
				map.put(i,(long)collatzLength(i));
			}
		} else if(a > b){
			for(long i = b; i < a; i++){
				map.put(i,(long)collatzLength(i));
			}
		} else {
			return (long) collatzLength(a);
		}

		long max = map.getOrDefault(a, null);
		int i = (int) a;
		for(; i < b; i++){
			max = Math.max(map.getOrDefault(i,null),map.getOrDefault(a,null));

		}
		return max;
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
	}
}
