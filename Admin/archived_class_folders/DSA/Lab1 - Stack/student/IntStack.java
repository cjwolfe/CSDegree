package student;

public class IntStack
{
	// declare your private fields here
	private int[] stack;
	private int top;
	private final int MAX_NUM_VALUES = 420;

	/**
	 * Create an empty stack.
	 */
	public IntStack()
	{
		this.stack = new int[MAX_NUM_VALUES];
		this.top = 0;
	}

	/** 
	 * Pushes an item onto the top of this stack.
	 * @param x
	 */
	public void push(int x)
	{
		stack[top] = x;
		top++;
		
	}
	
	/** 
	 * Removes the object at the top of this stack and returns that object as the value of this function.
	 * @return int The value removed from the stack. If empty, returns -1
	 */
	public int pop()
	{
		if(top == 0){
			return -1;
		} else {
			top--;
			int ans = stack[top];
			// stack[top] = Integer.MIN_VALUE;
			return ans;
		}
		// return -2;
	}


	public int peek()
	{
		if(top == 0){
			return -1;
		}
		return stack[top - 1];
	}

	public boolean isEmpty(){
		return top == 0;
	}

	public int size(){
		return top;
	}
}