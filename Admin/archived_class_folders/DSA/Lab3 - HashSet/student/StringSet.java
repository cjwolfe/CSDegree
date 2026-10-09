package student;

public class StringSet {
	private final int PRIME = 31;
	private static class Node
	{
		String data;
		int count = 0;
		Node next; //collisions?
		// this.instance = 1;

		public Node(String data){
			this.data = data;
			this.count = 1;
			this.next = null;
		}
		
	}

	private Node[] table;
	private int size;

	public StringSet()
	{
		size = 0;
		table = new Node[100];
	}

	public void insert(String key)
	{
		if(size >= table.length * .75){
			resize();
		}
		int index = hash(key);
		Node head = table[index];

		// 	// insert the key into the table
		if(head == null){
			table[index] = new Node(key);
			size++;
			return;
		}

		Node current = head;
		while(current != null){
			if(current.data.equals(key)){
				current.count++; //increment
				return;
			}
			if(current.next == null) break;
			current = current.next;
		}
		//key not in chain, new node
		current.next = new Node(key);
		size++;

	}

	public boolean find(String key)
	{
		int index = hash(key);
		Node head = table[index];
		Node current = head;
		while(current != null){
			if(current.data.equals(key)){
				// found it
				return true;
			}
			current = current.next;
		}

		return false;
		// return table[hash(key)].equals(key);
	}

	public void print()
	{
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
		System.out.println(sb.toString());

	}

	public int hash(String key)
	{
		long h = 0;
		for(int i = 0; i < key.length(); i++){
			h = (PRIME * h + key.charAt(i));
		}
		// for(char c : key.toCharArray()){
		// 	ans += (int) c;
		// 	ans *= 31;
		// }
		// ans *=prime;
		h %= table.length;
		int index = (int)(h % table.length);

		return (index < 0) ? (index + table.length) : index;

		// return ans;
	}

	private void resize() {
    Node[] oldTable = table;
    table = new Node[oldTable.length * 2];
    size = 0;

    for (Node head : oldTable) {
        Node current = head;
        while (current != null) {
            // Re-insert into the new, larger table
            insert(current.data);
            current = current.next;
        }
    }
}
}
