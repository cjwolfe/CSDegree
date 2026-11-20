import java.util.*;

/**
 * Part 2: the LL(1) parser (session 30).
 *
 * You write the body of parse. Do not change anything else in this file.
 */
public class LL1Parser {

    /**
     * Run the LL(1) parser of session 30 (slide "The LL(1) parser") on word,
     * with the table given. The grammar is table.getGrammar().
     *
     * The parser runs on word⊣ (⊣ is LL1Table.END), starting with the stack S$
     * (S the start symbol on top, $ is LL1Table.BOTTOM below it). At each step,
     * with the next letter a of word⊣:
     * - a letter on top of the stack: it must be a; pop it and move on to the
     *   next letter (match). Otherwise, reject.
     * - a variable A on top: apply the rule A → x in the cell (A, a) of the
     *   table: pop A and push x, its first symbol on top (expand). If the cell
     *   is empty, reject.
     * - $ on top: accept if a is ⊣, reject otherwise.
     *
     * Return the list of the rules applied by the expand moves, in order (that
     * is the leftmost derivation of word), or null if the parser rejects.
     *
     * A rule A → ε has rhs "ε": expanding it pushes nothing.
     */
    public static List<Rule> parse(LL1Table table, String word) {
        // TODO
        throw new UnsupportedOperationException("TODO: parse");
    }
}
