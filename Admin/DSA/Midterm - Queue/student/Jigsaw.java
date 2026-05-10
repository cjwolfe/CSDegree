// package DSA.Midterm.student;
package student;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Jigsaw {
	//Hashset

	public static Jigsaw set;


	public static int piecesMissing(String filename)
		throws IOException
	{

		File f = new File(filename);

		if(!f.exists()){
			//localization because f me for having a production structure
			f = new File("DSA/Midterm/student/"+filename);
		}
		if(f.exists()){
			long fileSize = f.length();
			set = new Jigsaw(fileSize);


			Scanner s = new Scanner(f);
			int lines = intakeFile(s);


			return lines;
		}

		throw new IOException(filename + " not found");

	}

	/**
	 * 
	 * @param f
	 * @return number of unique lines in the file, not counting line 1
	 */
	public static int intakeFile(Scanner s){
		// String firstLine = s.nextLine();
		int k = s.nextInt();
		int n = s.nextInt();

		System.out.println("k = " + k + " n = " + n);
		
		while(s.hasNext()){
			set.add((int)s.nextInt());			

		}
		int simpleUnique = k - set.size;
		System.out.println("Output: " + set.toString());
		// this feels wrong. 
		// If n is larger than k then it will always return 0.
		// if n is smaller than k then it will give the simple unique.
		// lgtm.
		if(set.size > k){
			if(set.size == n){
				return 0;
			}
			return -69;
		}



		return simpleUnique;
	}

	
	private static class Node{
		int data;
		Node next;
		public Node(int data){
			this.data = data;
			this.next = null;
		}
	}

	private Node[] table;
	private int size;
	public Jigsaw(long length){
		size = 0;
		// could be improved
		int l = (int) length;
		table = new Node[l];
	}
	public Jigsaw(){
		size = 0;
		table = new Node[69];
	}

	public void add (int key){
		// not worrying about resizing because I'm making it to size
		int index = hash(key);
		Node head = table[index];
		// insert
		if(head == null){
			table[index] = new Node(key);
			size++;
			return;
		}

		Node current = head;
		while(current != null){
			if(current.data == key){
				// current.count++; //increment
				return;
			}
			if(current.next == null) break;
			current = current.next;
		}
		//key not in chain, new node
		current.next = new Node(key);
		size++;

	}


	public int hash(int key)
	{
		long h = 0;
		// for(int i = 0; i < key.length(); i++){
			h = (8675309 * h + key);
		// }

		h %= table.length;
		int index = (int)(h % table.length);

		return (index < 0) ? (index + table.length) : index;

		// return ans;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Node head : table){
			if(head != null){
				Node current = head;
				while(current != null){
					sb.append(current.data).append("\t");
					current = current.next;
				}
			}
			
			// sb.append(t.data + "\n");
			
		}
		return sb.toString();
	}

	public static void main(String[] args)
		throws IOException
	{
		// pass the filename in as a command-line argument to test
		// for example: java Jigsaw puzzle-01-in.txt
		int count = piecesMissing(args[0]);
		System.out.println(count);
	}
}


