package student;

public class Heights
{

    public static int permitCost(int[] heights, int[] requests)
    {
        // something something naming things, cache invalidation, and off by one errors
        BST a = new BST();
        // have a zero to pull
        a.insert(0);
        for(int h : heights){
            a.insert(h);
        }
        int sum = 0;
        for(int i = 0; i < requests.length; i++){
            sum += a.findPredecessor(a.getRoot(),requests[i]).data;
            // insert to impact future data
            a.insert(requests[i]);
            // tester
            // System.out.println("Sum: " + sum);
        }

        return sum;
    }
    // public static void main(String[] args){

        /**
        // textbook
        int[] height1 = { 5, 2, 17, 9, 3, 6, 10, 14, 11, 7};
        int[] request1 = {16,15,2};
        int ans1 = 28;
        // empty, should have a 0
        int[] height2 = { };
        int[] request2 = { 10, 20, 5};
        int ans2 = 10;
        // next tallest
        int[] height3 = { 10};
        int[] request3 = {11, 12, 13};
        int ans3 = 33;
        // duplicate heights
        int[] height4 = { 5, 10, 15};
        int[] request4 = {15,15};
        int ans4 = 20;

        System.out.println("Test Case 1- Cost: " + permitCost(height1,request1) + " expected: " + ans1);
        System.out.println("Test Case 2- Cost: " + permitCost(height2,request2) + " expected: " + ans2);
        System.out.println("Test Case 3- Cost: " + permitCost(height3,request3) + " expected: " + ans3);
        System.out.println("Test Case 4- Cost: " + permitCost(height4,request4) + " expected: " + ans4);

        */

    // }
}

class BST{
    private Node root;
    public BST(){
        root = null;
    }
    public void insert(int data){
        root = insertRec(root, data);
    }
    public Node insertRec(Node root, int data){
        if(root == null){
            root = new Node(data);
            return root;
        }
        if(data < root.data){
            root.left = insertRec(root.left,data);
        } else if (data > root.data){
            root.right = insertRec(root.right,data);
        }
        return root;
    }
    public Node pred(int data){
        return findPredecessor(root,data);
    }
    public Node findPredecessor(Node root, int data){
        Node predecessor = null;
        while (root != null){
            if(data > root.data){
                predecessor = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return predecessor;
    }
    public Node max(){
        Node temp = this.root;
        while(temp.right != null){
            temp = temp.right;
        }
        return temp;
    }
    public Node min(){
        Node temp = this.root;
        while(temp.right != null){
            temp = temp.left;
        }
        return temp;
    }
    public Node getRoot(){
        return root;
    }

}

class Node{
    int data;
    Node left = null;
    Node right = null;

    Node(int data){
        this.data = data;
    }

    // add(int data){

    // }
}

