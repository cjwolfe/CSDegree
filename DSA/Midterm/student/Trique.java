package DSA.Midterm.student;
// package student;

public class Trique
{

	private int size;
	public Node fr;
	public Node mid;
	public Node butt;

	public Trique()
	{
		// set up a queue where the mid and front and back are set up

		fr.next = mid;
		mid.next = butt;
		size = 3;
		// fr -> mid -> butt -> null;
		// size = 3, so mid = 2;


		// private int mid = ;

	}
	
	public void push_back(int x)
	{
		Node newNode = new Node(x);
		newNode.next = butt;

	}

	public void push_front(int x)
	{
		Node newNode = new Node(x);
		Node temp = fr.next;
		fr.next = newNode;
		newNode.next = temp;
	}

	public void push_mid(int x)
	{
		
	}

	public int get(int i)
	{
		if(size<i){return -1;}
		Node current = fr;
		int count = 0;
		while(current != null){
			
			if(count == i){
				return current.data;
			}
			current = current.next;
			count++;


		}
		
		return -2;
	}

	

	private Node[] q;
	private static class Node{
		int data;
		Node next;
		public Node(int data){
			this.data = data;
			this.next = null;
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
