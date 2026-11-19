import java.util.*;

/**
 * A place to try your code. Change it as you like: it is not graded.
 *
 *     javac -encoding UTF-8 *.java
 *     java Main
 */
public class Main {

    public static void main(String[] args) {
        if (!"ε→⊣".equals("\u03B5\u2192\u22A3")) {
            System.out.println("Compile with:  javac -encoding UTF-8 *.java");
            return;
        }
        part1();
        part2();
    }

    static void part1() {
        System.out.println("=== Part 1: from a CFG to a PDA");
        ContextFreeGrammar g = ContextFreeGrammar.parse("S → aSa | bSb | a | b | ε");
        System.out.println("G:         " + g);
        try {
            ContextFreeGrammar gcnf = CfgToPda.toGCNF(g);
            System.out.println("toGCNF(G): " + gcnf);
            PushdownAutomaton pda = CfgToPda.toPDA(gcnf);
            pda.printDefinition();
            for (String word : new String[] {"", "aba", "abba", "ab"}) {
                System.out.println("Membership of \"" + word + "\": " + Membership.accepts(pda, word));
            }
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    static void part2() {
        System.out.println();
        System.out.println("=== Part 2: the LL(1) parser");
        // Session 30, slide "Example: lists of numbers"
        ContextFreeGrammar lists = ContextFreeGrammar.parse("V → n | [A]", "A → ε | VB", "B → ε | ,VB");
        LL1Table table = new LL1Table(lists);
        table.put('V', 'n', "n");
        table.put('V', '[', "[A]");
        table.put('A', 'n', "VB");
        table.put('A', '[', "VB");
        table.put('A', ']', "ε");
        table.put('B', ']', "ε");
        table.put('B', ',', ",VB");
        System.out.println("G: " + lists);
        System.out.print(table);
        for (String word : new String[] {"[n,[]]", "[n,]"}) {
            try {
                List<Rule> rules = LL1Parser.parse(table, word);
                System.out.println("\"" + word + "\": "
                        + (rules == null ? "rejected" : Rule.derivation(lists, rules)));
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }
}
