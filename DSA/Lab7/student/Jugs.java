package student;

import java.util.Scanner;

public class Jugs
{
	private static int A, B, C;
	private static boolean[][] visited;
	private static final int MAX_VALUE = 1000 + 1;

	public static String solve(int a, int b, int c)
	{
		A = a; B = b; C = c;
		visited = new boolean[MAX_VALUE][MAX_VALUE];
		// TODO: call a private method here to
		// generate a solution string and return it
		return null;
	}

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter A: ");
		A = scan.nextInt();
		System.out.print("Enter B: ");
		B = scan.nextInt();
		System.out.print("Enter C: ");
		C = scan.nextInt();

		String steps = solve(A, B, C);
		if (steps == null)
			System.out.println("Impossible!");
		else System.out.println("Possible: " + steps);
	}
}