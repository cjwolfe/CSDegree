package student;


public class IntStackDriver {

    private static int getMass(char c1){

        return switch (c1) {
            case 'H' -> 1;
            case 'C' -> 12;
            case 'O' -> 16;
            default -> 0;
        };


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

    public static void main(String[] args){
        // IntStack s = new IntStack();
        String input = "";
        String ans = "";

        input = "H2O";

        ans = "18";

        for(int i = 0; i < 5; i++){
            switch (i) {
                case 1:
                    input = "CH(CO2H)3";
                    ans = "148";
                    break;
                case 2:
                    input = "((CH)2(OH2H)(C(H))O)3";
                    ans = "222";
                    break;
                case 3:
                    input = "((((CH)(H3)(O2))2)2)2";
                    ans = "384";
                    break;
                case 4:
                    input = "((C8H2)4(C2O2H7)3(C(H3)4)2O)5";
                    ans = "3225";
                    break;
                case 5:
                    input = "CH(CO2H)3";
                    ans = "duplicate";
                    break;
            
                default:
                    break;
            }

            System.out.println("Input is " + input);

            System.out.println("Expected molecular mass is " + ans);

            System.out.println("Actual mass is " + calculate(input));


            
        }


        


    }
}
