package student;

public class BinaryHeap
{
	private int capacity;
	private int size;
	private int[] heapArray;

	// items for testing purposes
	// private static int[] answers;
	// private int ansSize;
	
	public BinaryHeap()
	{
		this(100);
	}

	public BinaryHeap(int n){
		capacity = n;
		heapArray = new int[capacity];
		size = 0;

		// answers = new int[capacity];
		// ansSize = 0;
	}

	public void insert(int k)
	{
		// insert at the last location, increment size, and sift-up
		// might require a resize if heap is full

		if(size == heapArray.length){
			//enbiggen
			int[] newHeap = new int[heapArray.length *2];

			System.arraycopy(heapArray, 0, newHeap, 0, heapArray.length);
			heapArray = newHeap;
		}

		// int i = size;

		heapArray[size] = k;
		size++;

		sift_up(size-1);

	}

	public int remove_min()
	{
		// swap with last location, decrement size, then sift-down the new root
		if(size == 0){
			throw new RuntimeException("Heap is empty");
		}
		int ans = find_min();
		heapArray[0] = heapArray[size()-1];
		size--;
		if(size > 0){
			sift_down(0);
		}
		return ans;
	}

	public int find_min()
	{
		// answers[ansSize] = heapArray[0];
		// ansSize++;
		return heapArray[0];
	}

	public int size()
	{
		return this.size;
	}

	private void sift_up(int i)
	{
		while(i != 0 && heapArray[i] < heapArray[(i-1)/2]){
			// determine parent and swap
			int parentIndex = (i-1)/2;
			int temp = heapArray[parentIndex];
			heapArray[parentIndex] = heapArray[i];
			heapArray[i] = temp;
			// track new parent
			i = parentIndex;
		}

	
	}

	private void sift_down(int i)
	{
		while (true) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < size && heapArray[left] < heapArray[smallest]) {
            smallest = left;
        }

        if (right < size && heapArray[right] < heapArray[smallest]) {
            smallest = right;
        }

        if (smallest == i) {
            break;
        }

        int temp = heapArray[i];
        heapArray[i] = heapArray[smallest];
        heapArray[smallest] = temp;

        i = smallest;
    	}
	}

	public String print(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size(); i++){
			sb.append(heapArray[i] + ", ");
		}

		// sb.append("\n answers : \n");
		// for(int i = 0; i < ansSize; i++){
		// 	sb.append(answers[i] + ", ");
		// }
		return sb.toString();
	}
	
}
