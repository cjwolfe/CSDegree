package DSA.Midterm.student;


public class MidtermDriver {
    public static String puzzle01 = "puzzle-01-in.txt";
    public static String puzzle02 = "puzzle-02-in.txt";
    public static String puzzle04 = "puzzle-03-in.txt";
    public static String trique01 = "trique-01-in.txt";
    public static String trique02 = "trique-02-out.txt";
    public static void main(String[] args){

        int ans = Integer.MIN_VALUE;
        // try{
        // ans = Jigsaw.piecesMissing(puzzle01);
        // } catch (Exception e){
        //     System.out.println("Unable to get ans"
        //     );
        // }
        // // int ans = j.piecesMissing(puzzle01);
        // System.out.println(puzzle01 + " , result: " + ans);
        // System.out.println("expected result: " + 0);

        // try{
        // ans = Jigsaw.piecesMissing(puzzle02);
        // } catch (Exception e){
        //     System.out.println("Unable to get ans"
        //     );
        // }
        // // int ans = j.piecesMissing(puzzle01);
        // System.out.println(puzzle02 + " , result: " + ans);
        // System.out.println("expected result: " + 1);

        // tricky input had issues
        try{
        ans = Jigsaw.piecesMissing(puzzle04);
        } catch (Exception e){
            System.out.println("Unable to get ans"
            );
        }
        // int ans = j.piecesMissing(puzzle01);
        System.out.println(puzzle04 + " , result: " + ans);
        System.out.println("expected result: " + 0);

    }   
}
