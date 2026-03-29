package student;

public class Flight
{
	public int time; // Scheduled time of departure
	public String name; // Name of the airline
	public String number; // Flight number
	public String source; // Flight source
	public String destination; // Flight destination

	/**
		Constructor: creates an Flight object with appropriate information.
	**/
	public Flight(int time, String name, String number, String source, String destination)
	{
		this.time = time;
		this.name = name;
		this.number = number;
		this.source = source;
		this.destination = destination;
	}

	/**
		toString method to print the string representation of a flight.
	**/
	public String toString()
	{
		return name + " " + number + " " + source + " " + destination + " @ " + time;
	}
}
