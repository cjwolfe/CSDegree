import java.util.*;

/**
 * Part 1: from a CFG to a PDA (session 27).
 *
 * You write the bodies of toGCNF and toPDA. Everything else in this file is
 * provided: do not change it.
 */
public class CfgToPda {

    /** The three states of the PDA of session 27. */
    public static final String INIT = "init";
    public static final String SIMULATE = "simulate";
    public static final String ACCEPT = "accept";

    /** The initial stack symbol $. */
    public static final char BOTTOM = '$';

    /**
     * Transform g into an equivalent grammar in Generalized Chomsky Normal
     * Form, as in session 27 (slide "Modifying CFGs (cont'd)"). The new grammar has:
     * - the same letters and the same start symbol as g;
     * - the variables of g, plus one new variable A_a per letter a
     *   (get its name from freshVariable);
     * - every rule of g, in which each occurrence of a letter a is replaced by A_a
     *   (a rule A → ε stays A → ε);
     * - plus the rules A_a → a, one per letter a.
     *
     * Do not change g: build a new ContextFreeGrammar and return it.
     */
    public static ContextFreeGrammar toGCNF(ContextFreeGrammar g) {
        // TODO
        throw new UnsupportedOperationException("TODO: toGCNF");
    }

    /**
     * Build the PDA of session 27 (slide "Transforming a CFG into a PDA") for
     * a grammar g in Generalized Chomsky Normal Form:
     * - states INIT, SIMULATE and ACCEPT; INIT is initial, ACCEPT is accepting;
     * - input alphabet: the letters of g; stack alphabet: the variables of g and BOTTOM;
     * - initial stack symbol BOTTOM;
     * - INIT → SIMULATE on ε, $, S$ (S is the start symbol of g);
     * - SIMULATE → ACCEPT on ε, $, $;
     * - a loop on SIMULATE on ε, A, W for each rule A → W where W is made of variables;
     * - a loop on SIMULATE on a, A, ε for each rule A → a where a is a letter.
     *
     * In PushdownAutomaton, the input ε is PushdownAutomaton.EPSILON, and
     * "push nothing" is the empty string "" (not the string "ε").
     */
    public static PushdownAutomaton toPDA(ContextFreeGrammar g) {
        // TODO
        throw new UnsupportedOperationException("TODO: toPDA");
    }

    // ===================== Provided: do not change =====================

    /** The whole construction: a CFG in, an equivalent PDA out. */
    public static PushdownAutomaton cfgToPda(ContextFreeGrammar g) {
        return toPDA(toGCNF(g));
    }

    /**
     * A symbol that is not in used, to name a new variable. It also adds that
     * symbol to used, so that the next call returns a different one.
     * Put every symbol already taken in used (variables and letters) before
     * the first call. Capital letters come first, then Greek capitals.
     */
    public static char freshVariable(Set<Character> used) {
        String candidates = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "\u0391\u0392\u0393\u0394\u0398\u039B\u039E\u03A0\u03A6\u03A8\u03A9";
        for (char c : candidates.toCharArray()) {
            if (!used.contains(c)) {
                used.add(c);
                return c;
            }
        }
        throw new IllegalStateException("No fresh variable left");
    }
}
