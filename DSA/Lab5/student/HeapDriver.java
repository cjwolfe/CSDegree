package student;

import java.io.File;
import java.util.Scanner;

public class HeapDriver {

    public static void main(String args[]){

    BinaryHeap bh = new BinaryHeap();

    StringBuilder sb = new StringBuilder();
        try{
            File f = new File("test-04-input.txt");
            if(!f.exists()){
                throw new Exception(f.getAbsolutePath() + "file not found");
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()){

                sb.append(s.next());

                if(sb.charAt(0) == 'i'){
                    bh.insert(s.nextInt());
                } else if (sb.charAt(0) == 'q'){
                    bh.remove_min();

                    // System.out.println(bh.remove_min());
                }
                // should print out everything
                // System.out.print(sb.toString());

                // System.out.print("\n\tasdf\n");

                sb.delete(0, sb.length());

            }

            System.out.println(bh.print());


        } catch (Exception e){
            System.out.println("Failed. Exiting " + e);
            return;
        }


    }



    
    
    
}
