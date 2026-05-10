package student;

import student.IntStack;
public class MolecularMass
{ 
    
    public static void main(String[] args)
    {
        java.util.Scanner kb = new java.util.Scanner(System.in);
        // System.out.print("Enter the molecule: ");
        String molString = kb.nextLine();     
        System.out.print(/*"Your Molecular Weight is: " +*/ calculate(molString));
    }

    public static int calculate(String m)
    {
        char[] n = m.toCharArray();
        IntStack massStack = new IntStack();
        IntStack parenStack = new IntStack();

        for (int i = 0; i < n.length; i++) {
            char c = n[i];

            if (c == '(') {
                parenStack.push(massStack.size());
            } else if (Character.isUpperCase(c)) {
                int mass = getMass(c);
                
                // Check for multiplier: H2
                int multiplier = 0;
                while (i + 1 < n.length && Character.isDigit(n[i + 1])) {
                    multiplier = multiplier * 10 + (n[++i] - '0');
                }
                massStack.push((int) mass * (multiplier == 0 ? 1 : multiplier));
                
            } else if (c == ')') {
                int start = parenStack.pop();
                double subTotal = 0;
                while (massStack.size() > start) {
                    subTotal += massStack.pop();
                }

                // Check for multiplier: (OH)2
                int multiplier = 0;
                while (i + 1 < n.length && Character.isDigit(n[i + 1])) {
                    multiplier = multiplier * 10 + (n[++i] - '0');
                }
                massStack.push((int) subTotal * (multiplier == 0 ? 1 : multiplier));
            }
        }

        int total = 0;
        while (!massStack.isEmpty()) {
            total += massStack.pop();
        }
        return total;

    }

    private static int getMass(char c1){
        //send help... but not for me! I think autograder is unable to deal with one measly extra method. Maybe not. IDK.

        return switch (c1) {
            case 'H' -> 1;
            case 'C' -> 12;
            case 'O' -> 16;
            default -> 0;
        };


    }
}
