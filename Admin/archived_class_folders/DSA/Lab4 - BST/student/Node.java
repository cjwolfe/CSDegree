package student;

public class Node
{
	public Flight flight;
	public Node left;
	public Node right;

	public Node(Flight flight)
	{
		this.flight = flight;
		left = right = null;
	}

	public String toString()
	{
		return flight.toString();
	}
}