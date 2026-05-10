package student;

import java.util.Scanner;

public class Islands
{

    public static int countIslands(String filename)
    {
        boolean[][] map;
        boolean[][] visited;
        int size = 0;
        int count = 0;
        int islands = 0;
        Scanner scan;
        try {  
            scan = new Scanner(new java.io.File(filename));
            int temp = scan.nextInt();

            if(temp > 0){
                size = temp;
            } else {
                return -1;
            }

            //ingest file
            map = new boolean[size][size];

            while(scan.hasNext()){
                String tok = scan.next();
                char[] asdf = tok.toCharArray();
                if(asdf.length == map.length){
                    for(int i = 0; i < size; i++){
                        if(asdf[i] == '*'){
                            map[count][i] = true;
                        } else {
                            map[count][i] = false;
                        }
                        
                    }
                }
                count++;
                // System.out.println(tok);
            }

            // print map for funzies
            // printMap(map);

            int rows = map.length;
            int cols = map[0].length;
            visited = new boolean[rows][cols];   
            
            // loop thru map and visited
            for(int r = 0; r < rows; r++){
                for(int c = 0; c < cols; c++){
                    if(map[r][c] && !visited[r][c]){
                        // flood fill when map true & !visited, increment
                        islands++;
                        floodFill(map,visited,r,c);
                    }

                }
            }

        } catch (java.io.IOException e) {
            return -1;
        }

        

        scan.close();

        return islands;
    }
    
    public static void printMap(boolean[][] map){

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j]){
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();

        }
    }

    public static void floodFill(boolean[][] map, boolean[][] visited, int r, int c){
        int rows = map.length;
        int cols = map[0].length;
        //base case
        if(r < 0 || r >= rows || c < 0 || c >= cols) return;
        if(!map[r][c]) return;
        if(visited[r][c]) return;

        //mark visited
        visited[r][c] = true;
        // fill
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                floodFill(map, visited, r + dr, c + dc);
            }
        }
    }

    


    // public static void main(String[] args){
        // String input = "map-sample.txt";
        // String input2 = "map-mytester.txt";

        // int ans = countIslands(input);

        // System.out.println("Map: " + input);
        // System.out.println("Expected: 3 Result: " + ans);

        // ans = countIslands(input2);
        // System.out.println("Map:  " + input2);
        // System.out.println("Expected: 5 Result: " + ans);


    // }
}