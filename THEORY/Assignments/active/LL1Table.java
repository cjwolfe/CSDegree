import java.util.*;

/**
 * The LL(1) table of a grammar (session 30): one row per variable, one column
 * per letter plus a column for the end marker ⊣. Each cell holds at most one rule.
 *
 * You fill the table yourself, cell by cell. For S → (S)S | ε:
 *
 *     LL1Table table = new LL1Table(ContextFreeGrammar.parse("S → (S)S | ε"));
 *     table.put('S', '(', "(S)S");
 *     table.put('S', ')', "ε");
 *     table.put('S', LL1Table.END, "ε");
 *
 * Do not change this file.
 */
public class LL1Table {

    /** The end marker ⊣, written after the word. */
    public static final char END = '\u22A3';

    /** The bottom of the stack: the parser starts with S$ on the stack. */
    public static final char BOTTOM = '$';

    private final ContextFreeGrammar grammar;
    private final Map<Character, Map<Character, Rule>> cells = new HashMap<>();

    public LL1Table(ContextFreeGrammar grammar) {
        this.grammar = grammar;
    }

    /** The grammar of this table. */
    public ContextFreeGrammar getGrammar() {
        return grammar;
    }

    /**
     * Put the rule variable → rhs in the cell (variable, next).
     * Throws an exception if variable → rhs is not a rule of the grammar,
     * or if the cell already holds another rule (then the grammar is not LL(1)).
     */
    public void put(char variable, char next, String rhs) {
        if (!grammar.isNonTerminal(variable) || !grammar.getProductions(variable).contains(rhs)) {
            throw new IllegalArgumentException(new Rule(variable, rhs) + " is not a rule of the grammar");
        }
        Rule old = get(variable, next);
        if (old != null && !old.rhs().equals(rhs)) {
            throw new IllegalArgumentException("Cell (" + variable + ", " + next + ") already holds "
                    + old + ": with " + new Rule(variable, rhs) + " too, the grammar is not LL(1)");
        }
        cells.computeIfAbsent(variable, v -> new HashMap<>()).put(next, new Rule(variable, rhs));
    }

    /**
     * The rule in the cell (variable, next), or null if the cell is empty.
     * next is a letter or END.
     */
    public Rule get(char variable, char next) {
        Map<Character, Rule> row = cells.get(variable);
        return row == null ? null : row.get(next);
    }

    /** The table, one line per non-empty cell. */
    @Override
    public String toString() {
        List<Character> rows = new ArrayList<>(cells.keySet());
        Collections.sort(rows);
        StringBuilder sb = new StringBuilder();
        for (char variable : rows) {
            List<Character> columns = new ArrayList<>(cells.get(variable).keySet());
            Collections.sort(columns);
            for (char next : columns) {
                sb.append("(").append(variable).append(", ").append(next).append("): ")
                  .append(cells.get(variable).get(next)).append("\n");
            }
        }
        return sb.toString();
    }
}
