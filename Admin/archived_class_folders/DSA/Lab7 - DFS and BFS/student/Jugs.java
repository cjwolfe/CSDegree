package student;

import java.util.Scanner;
import java.util.Stack;

public class Jugs
{
	private static int A, B, C;
	private static boolean[][] visited;
	private static final int MAX_VALUE = 1000 + 1;
	// private static StringBuilder log = new StringBuilder();
	private static Stack log = new Stack<Character>();

	public static String solve(int a, int b, int c)
	{
		A = a; B = b; C = c;
		visited = new boolean[MAX_VALUE][MAX_VALUE];
		log.clear();

		if(dfs(0,0)){
			// at the goal, time to send out
			StringBuilder sb = new StringBuilder();
			// while(!log.isEmpty()){sb.append(log.pop());}
			Stack rev = new Stack<Character>();
			while(!log.isEmpty()){rev.push(log.pop());}
			while(!rev.isEmpty()){sb.append(rev.pop());}
			
			return sb.toString();
		}

		return null;
	}


	private static boolean dfs(int a, int b){
		if(a + b == C) return true; // goooooooooooal
		if(visited[a][b]) return false;

		visited[a][b] = true;

		if(tryMove(A,b,'A')) return true;
		if(tryMove(a,B,'B')) return true;
		if(tryMove(0,b,'C')) return true;
		if(tryMove(a,0,'D')) return true;

		int pourToTwo = Math.min(a, B - b);
		if(tryMove(a - pourToTwo, b + pourToTwo, 'E')) return true;

		int pourToOne = Math.min(b, A - a);
		if(tryMove(a + pourToOne, b - pourToOne, 'F')) return true;

		return false;
	}

private static boolean tryMove(int nextA, int nextB, char move) {
        if (nextA >= 0 && nextA <= A && nextB >= 0 && nextB <= B && !visited[nextA][nextB]) {
            log.push(move); // store 
            if (dfs(nextA, nextB)) {
                return true;
            }
            log.pop(); // backtrack
        }
        return false;
    }



	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter A: ");
		// A = 3;
		A = scan.nextInt();
		System.out.print("Enter B: ");
		// B = 4;
		B = scan.nextInt();
		System.out.print("Enter C: ");
		// C = 5;
		C = scan.nextInt();

		String steps = solve(A, B, C);
		if (steps == null)
			System.out.println("Impossible!");
		else System.out.println("Possible: " + steps);
		// System.out.println("\n" + log.toString());
		scan.close();
	}
}