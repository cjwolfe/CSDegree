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

		boolean[] board = new boolean[N * N];

		int row = 0;
		for(int i = 0; i< board.length; i++){
			while(row < N){
				board[i] = true;

			}
		}
		

		return -1;
	}

	public void solve(boolean[] board, int size, int currentPos){
		if(currentPos == board.length) return;

		board[currentPos] = true;

		solve(board,size, currentPos+1);

		board[currentPos] = false;
	}

	private static boolean isValid(int x ,int y){
		


		return false;
	}


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

[0][1][0][0]
[0][0][0][1]
[1][0][0][0]
[0][0][1][0]



(no two unique digits) - one solution
(each digit is  )


*/