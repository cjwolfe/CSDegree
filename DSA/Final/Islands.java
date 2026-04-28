package student;

import java.util.Scanner;

public class Islands
{
    public static int countIslands(String filename)
    {
        Scanner scan;
        try {
            scan = new Scanner(new java.io.File(filename));
        } catch (java.io.IOException e) {
            return -1;
        }

        scan.close();

        return -1;
    }
}