package student;

public class BST
{
	private Node root;

	public BST()
	{
		root = null;
	}
	
	public void insert(Flight flight)
	{
		root = insertRec(root, flight);

	}

	public Node insertRec(Node root, Flight flight){
		if(root == null){
			root = new Node(flight);
			return root;
		}

		if(flight.time < root.flight.time){
			root.left = insertRec(root.left, flight);
		} else if (flight.time > root.flight.time){
			root.right = insertRec(root.right, flight);
		
		}
		return root;
	}

	public boolean exists(int key){
		Node result = find(root,key);
		return result != null;
		// return find(root, key).flight.time == key;
	}

	public Node find(Node root, int key){
		if (root == null || root.flight.time == key){
			return root;
		}

		if(key < root.flight.time){
			//
			return find(root.left, key);
		} else{
			//
			return find(root.right, key);
		}
		// return root;


	}

	public Node pred(int time)
	{
		return findPredecessor(root,time);
	}

	private Node findPredecessor(Node root, int key){
		Node predecessor = null;
		while (root != null){
			if(key > root.flight.time){
				// potential predecessor
				predecessor = root;
				// look for larger predecessors
				root = root.right;
			} else {
				root = root.left;
			}
		}
		return predecessor;

	}

	public Node succ(int time)
	{
		// Note: You will not find success through simply searching. You have to do something that has meaning.
		return findSuccess(root, time);
	}

	private Node findSuccess(Node root, int key){
		Node success = null;
		// gee I hope I'm not unsuccessful
		while(root != null){
			if(key < root.flight.time){
				//potential success...or
				success = root;
				// look for smaller bois
				root = root.left;
			} else {
				root = root.right;
			}
		}

		return success;
	}

	public Node min()
	{
		// int minv = this.root.flight.time;
		// int minv = root.flight.time;
		Node temp = this.root;
		// if(root == null){
		// 	return root;
		// }
		while(temp.left != null){
			// minv = root.left.flight.time;
			temp = temp.left;
		}
		return temp;
	}

	public Node max()
	{		// int minv = root.flight.time;
		Node temp = this.root;

		while(temp.right != null){
			// minv = root.left.flight.time;
			temp = temp.right;
		}
		return temp;
	}

	public void delete(int time)
	{
		root = deleteRec(root, time);
	}

	public Node deleteRec(Node root, int time){
		if(root == null){
			return root;
		}
		if (time < root.flight.time){
			root.left = deleteRec(root.left,time);
		} else if (time > root.flight.time){
			root.right = deleteRec(root.right, time);
		} else {
			if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            Node successor = getMin(root.right);
			root.flight = successor.flight;

            root.right = deleteRec(root.right, successor.flight.time);
		}


		return root;
	}

	private Node getMin(Node root){
		while(root.left != null){
			root = root.left;
		}
		return root;
	}

	public void print(Node root) {
		if (root == null) return;
		print(root.left);
		System.out.print(root.flight.time + " ");
		print(root.right);
	}

	public Node getRoot(){
		return root;
	}

}
