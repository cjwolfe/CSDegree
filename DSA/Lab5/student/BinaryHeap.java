package student;

public class BinaryHeap
{
	private int capacity;
	private int size;
	private int[] heapArray;
	
	public BinaryHeap()
	{
		new BinaryHeap(100);
	}

	public BinaryHeap(int n){
		capacity = n;
		heapArray = new int[capacity];
		size = 0;
	}

	public void insert(int k)
	{

		// TODO: insert into the binary heap
		// insert at the last location, increment size, and sift-up
		// might require a resize if heap is full

		if(size == capacity){
			//enbiggen
			System.arraycopy(heapArray, k, heapArray, k, k);
		}

		int i = size;

		heapArray[i] = k;
		size++;

		if(size > 1){


		// ??
		while(i != 0 && heapArray[i] < heapArray[i - 1]){
			int temp = heapArray[i - 1];
			heapArray[i-1] = heapArray[i];
			heapArray[i] = temp;


			// swap(heapArray,i, parent(i));
		}
				}
	}

	public int remove_min()
	{
		// TODO: return smallest value (stored at root) and remove
		// swap with last location, decrement size, then sift-down the new root
		return -1;
	}

	public int find_min()
	{
		// TODO: return smallest value (stored at root)
		return -1;
	}

	public int size()
	{
		return this.size;
	}

	private void sift_up(int i)
	{
	}

	private void sift_down(int i)
	{
	}

	
}
