package student;

public class Trique
{
	public Trique()
	{
	}
	
	public void push_back(int x)
	{
	}

	public void push_front(int x)
	{
	}

	public void push_mid(int x)
	{
	}

	public int get(int i)
	{
		return -1;
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
