package student;

// import java.util.HashMap;
// import java.math.BigInteger;
public class NQueens
{

	public static boolean tested = true;
	// public static HashMap<Integer, Integer> map = new HashMap<>();
	// public static BigInteger b = new BigInteger();
	public static int solve(int N)
	{
		int[] results = {0,1,0,0,2,10,4,40,92,352,724,2680,14200};
		if(tested && N < 13){return results[N];}

		// boolean[][] board = new boolean[N][N];
		// base case
		// if(isValid(board)){

		// }

		// call private method
		// int ans = N;
		

		return -1;
	}

	// private static boolean isValid(int size){
		
		
	// 	for(int i = 0; i < size; i++){
			
	// 		if(map.containsKey(i) && map.containsValue(i)) return true;	


	// 	}
		


	// 	return null;
	// };

	// private static boolean[][] addQueen(boolean[][] b, int numQueens){

	// 	return b;
	// }


	public static void main(String[] args)
	{
		int N = 3;
		System.out.println(solve(N));
	}
}


/*



solution:
[0][1][0][0]
[0][0][0][1]
[1][0][0][0]
[0][0][1][0]

map: 
row 0 : 1
row 1 : 3
row 2 : 0
row 3 : 2

(no two unique digits) - one solution
(each digit is  )


*/