public class WGC
{
    public static int START = 0b0000;
    public static int GOAL = 0b1111;
    public static boolean[] visited;
    public static int[] pred;
    
    public static int bit(int state, int b)
    {
        return (state >> b) & 0x01;
    }

    public static boolean valid(int state)
    {
        int w = bit(state, 0);
        int g = bit(state, 1);
        int c = bit(state, 2);
        int m = bit(state, 3);

        if (m != w && w == g) return false;
        if (m != g && g == c) return false;
        return true;
    }

    public static boolean solve(int state)
    {
        if (state == GOAL) return true;
        if (state < 0 || state >= 16) return false;
        visited[state] = true;

        int man = bit(state, 3);
        boolean solved = false;
        for (int i = 0; i < 3; i++)
        {
            int obj = bit(state, i);
            if (man == obj)
            {
                int newstate = state ^ 0b1000 ^ (1 << i);
                if (!visited[newstate] && valid(newstate))
                {
                    pred[newstate] = state;
                    solved |= solve(newstate);
                }
            }
        }

        int newstate = state ^ 0b1000;
        if (!visited[newstate] && valid(newstate))
        {
            pred[newstate] = state;
            solved |= solve(newstate);
        }

        return solved;
    }

    public static void print(int state)
    {
        if (state != START)
            print(pred[state]);
        
        String OBJ = "WGCM";
        String lt = "";
        String rt = "";
        for (int i = 0; i <= 3; i++)
        {
            if (bit(state, i) == 0)
            {
                lt += OBJ.charAt(i);
                rt += " ";
            }
            else
            {
                rt += OBJ.charAt(i);
                lt += " ";
            }
        }
        System.out.println(lt + " | " + rt);
    }

    public static void main(String[] args)
    {
        visited = new boolean[16];
        pred = new int[16];
        for (int i = 0; i < 16; i++)
        {
            visited[i] = false;
            pred[i] = -1;
        
        }

        if (!solve(START))
        {
            System.out.println("Impossible.");
        }
        else print(GOAL);
    }
}
