// package DSA.Midterm.student;
package student;

public class Trique
{

	private int size = 0;
	public Node mid;

	public Trique()
	{
		// using a bst so no need for some boilerplate setup
	}
	
	public void push_back(int x)
	{
		mid = insert(mid, this.size, x);
		this.size++;
	}

	public void push_front(int x)
	{
		mid = insert(mid, 0, x);
		this.size++;		
	}

	public void push_mid(int x)
	{
		mid = insert(mid, (this.size+1) / 2, x);
		this.size++;

	}

	public int get(int i)
	{
		if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
		return find(mid, i).data;
		
    }

	private Node insert(Node node, int index, int x){
		if (node == null) return new Node(x);

		int leftSize = getSize(node.left);

		if(index <= leftSize){
			node.left = insert(node.left, index, x);
		} else{
			node.right = insert(node.right, index - leftSize - 1, x);
		}

		updateNode(node);
		return balance(node);
		// node.size = 1 + getSize(node.left) + getSize(node.right);
		// return node;
	}

	private Node find(Node node, int index){
		int leftSize = getSize(node.left);
		if (index < leftSize) return find(node.left, index);
		if (index == leftSize) return node;
		return find(node.right, index - leftSize - 1);
	}

	private Node balance(Node n){
		// if(n == null) return null;

		int bf = getBalanceFactor(n);

		//left
		if(bf > 1){
			if(getBalanceFactor(n.left) < 0){
				n.left = rotateLeft(n.left);
			}
			return rotateRight(n);
		}
		// right
		if(bf < -1){
			if(getBalanceFactor(n.right) > 0){
				n.right = rotateRight(n.right);
			}
			return rotateLeft(n);
		}
		return n;
	}
	
	private Node rotateRight(Node y){
		Node x = y.left;
		Node T2 = x.right;
		
		x.right = y;
		y.left = T2;

		updateNode(y);
		updateNode(x);
		return x;
	}
	private Node rotateLeft(Node x) {
		Node y = x.right;
		Node T2 = y.left;

		y.left = x;
		x.right = T2;

		updateNode(x);
		updateNode(y);
		return y;
	}

	private void updateNode(Node n){
		if(n != null){
			n.height = 1 + Math.max(getHeight(n.left),getHeight(n.right));
			n.size = 1 + getSize(n.left) + getSize(n.right);
		}
	}
	private int getBalanceFactor(Node n){
		return (n == null) ? 0 : getHeight(n.left) - getHeight(n.right);
	}

	private int getHeight(Node n){ return (n == null) ? 0 : n.height; }

	private int getSize(Node n) { return n == null ? 0 : n.size; }	

	private static class Node{
		int data;
		int size;
		int height;
		Node right;
		Node left;
		public Node(int data){
			this.data = data;
			this.size = 1;
			this.height = 1;
			this.left = this.right = null;
		}
	}


	public static void main(String[] args)
	{
		java.util.Scanner scan = new java.util.Scanner(System.in);
		Trique t = new Trique();

		while (scan.hasNext())
		{
			String op = scan.next();
			switch(op)
			{
				case "get":
					System.out.println(t.get(scan.nextInt()));
					break;
				case "push_front":
					t.push_front(scan.nextInt());
					break;
				case "push_back":
					t.push_back(scan.nextInt());
					break;
				case "push_mid":
					t.push_mid(scan.nextInt());
					break;
				default:
					return;
			}
		}
	}
}
