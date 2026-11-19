import java.util.*;

/**
 * A rule A → x of a grammar: the variable A and its right-hand side x.
 * As in ContextFreeGrammar, the right-hand side of A → ε is the string "ε".
 *
 * new Rule('S', "aSb") builds the rule S → aSb, and rule.variable() and
 * rule.rhs() read its two fields. Two rules with the same fields are equal.
 *
 * Do not change this file.
 */
public final class Rule {
    private final char variable;
    private final String rhs;

    public Rule(char variable, String rhs) {
        this.variable = variable;
        this.rhs = rhs;
    }

    public char variable() {
        return variable;
    }

    public String rhs() {
        return rhs;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rule)) {
            return false;
        }
        Rule other = (Rule) o;
        return variable == other.variable && Objects.equals(rhs, other.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable, rhs);
    }

    @Override
    public String toString() {
        return variable + " \u2192 " + rhs;
    }

    /**
     * The derivation obtained by applying the rules one after the other,
     * each to the leftmost variable, starting from the start symbol of g.
     * For instance, for S → (S)S | ε and the rules S → (S)S, S → ε, S → ε:
     *
     *     S ⇒ (S)S ⇒ ()S ⇒ ()
     *
     * If a rule cannot be applied (its variable is not the leftmost one),
     * the derivation stops there with a message.
     */
    public static String derivation(ContextFreeGrammar g, List<Rule> rules) {
        StringBuilder form = new StringBuilder(String.valueOf(g.getStartSymbol()));
        StringBuilder out = new StringBuilder(form);
        for (Rule rule : rules) {
            int leftmost = -1;
            for (int i = 0; i < form.length(); i++) {
                if (g.isNonTerminal(form.charAt(i))) {
                    leftmost = i;
                    break;
                }
            }
            if (leftmost < 0 || form.charAt(leftmost) != rule.variable()) {
                return out + "   (cannot apply " + rule + " here)";
            }
            String rhs = rule.rhs().equals(String.valueOf(ContextFreeGrammar.EPSILON)) ? "" : rule.rhs();
            form.replace(leftmost, leftmost + 1, rhs);
            out.append(" \u21D2 ").append(form.length() == 0 ? String.valueOf(ContextFreeGrammar.EPSILON) : form);
        }
        return out.toString();
    }
}
