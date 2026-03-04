package student;

import java.util.HashSet;
import java.util.*;
import java.io.*;
public class Testing {
    
    public static String ex = "example";
    public static String ex2 = "butts";
    public static String ex3 = "tits";

    public static String fn = "/data/dictionary.txt";

    public static void main(String[] args){
        StringSet set = new StringSet();
        File f = new File(fn);

        System.out.println("Inserting into set");
        // set.insert(ex);
        // set.insert(ex);
        // set.insert(ex);
        // set.insert(ex2);
        


        System.out.println("Testing find");
        System.out.println("Is " + ex + " in the set? " + set.find(ex));
        System.out.println("Is " + ex2 + " in the set? " + set.find(ex2));
        System.out.println("Is " + ex3 + " in the set? " + set.find(ex3));


        System.out.println("Running Print");
        set.print();

    }
}
