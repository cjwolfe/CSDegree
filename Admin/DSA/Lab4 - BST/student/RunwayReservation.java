package student;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * you know it's my bullshit when I don't remove a bunch of commented out bullshit
 * @author cwolfe
 */
public class RunwayReservation
{
	private int grace;
	private BST bst;
	private int time;

	public RunwayReservation(int grace)
	{
		this.time = 0;
		this.bst = new BST();
		this.grace = grace;
	}

	// return true if the reservation was made
	// return false if the reservation request was rejected
	public boolean requestReservation(Flight flight)
	{
		Node predecessor = bst.pred(flight.time);
		Node successor = bst.succ(flight.time);

		if(predecessor != null && flight.time - predecessor.flight.time < grace){
			return false;
		}
		if(successor != null && successor.flight.time - flight.time < grace){
			return false;
		}

		bst.insert(flight);
		return true;

	}

	public List<Flight> advanceTime(int t)
	{

		this.time += t;


		List<Flight> takeoff = new ArrayList<>();
		// add all flights that took off in this time to the list
		// int minTime = bst.min().flight.time;

		while(bst.getRoot() != null && bst.min().flight.time <= this.time){
			Flight f = bst.min().flight;
			takeoff.add(f);
			bst.delete(f.time);
		}

		// while(minTime <= getCurrentTime()){
		// minTime = bst.min().flight.time;
		// takeoff.add(bst.min().flight);
		// bst.delete(minTime);



		// }
		return takeoff;
	}

	public int getCurrentTime()
	{
		return this.time;
	}

	private static void runFile(String filename)
		throws IOException
	{
		// File fine = new File(filename);
		// System.out.println("File exists?" + fine.exists());

		Scanner file = new Scanner(new File(filename));
	
		
		int n = file.nextInt(); // The total number of requests.
		int k = file.nextInt(); // Grace time between requests.

		RunwayReservation runway = new RunwayReservation(k);

		// Variables for getting the input.
		char cmd;
		int time;

		while (file.hasNext())
		{
			cmd = file.next().charAt(0);
			time = file.nextInt();

			if (cmd == 'r')
				// Flights contain flight name, flight number, source, and destination
				runway.requestReservation(new Flight(time, file.next(), file.next(), file.next(), file.next()));
			else
			{
				List<Flight> flights = runway.advanceTime(time);
				System.out.printf("Current time = %d units\n", runway.getCurrentTime());
				for (Flight f : flights) System.out.println(f);
			}	
			file.nextLine();
		}
		file.close();
	}





	public static void main(String[] args)
		throws IOException
	{		
		runFile(args[0]);


	}
}
