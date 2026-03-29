package student;

import java.util.*;

public class RunwayReservation
{
	private int grace;

	public RunwayReservation(int grace)
	{
		this.grace = grace;
	}

	// return true if the reservation was made
	// return false if the reservation request was rejected
	public boolean requestReservation(Flight flight)
	{
		return false;
	}

	public List<Flight> advanceTime(int t)
	{
		List<Flight> takeoff = new ArrayList<>();
		// add all flights that took off in this time to the list
		return takeoff;
	}

	public int getCurrentTime()
	{
		return 0;
	}

	private static void runFile(String filename)
		throws IOException
	{
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
