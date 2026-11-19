import java.util.*;

/**
 * Implementation of a Context-Free Grammar (CFG)
 * A CFG is defined by (Σ, V, S, P) where:
 * - V: finite set of non-terminal symbols (variables)
 * - Σ: finite set of terminal symbols
 * - P: finite set of production rules (V → (V ∪ Σ)*)
 * - S: start symbol (S ∈ V)
 */
public class ContextFreeGrammar {
    
    // CFG components
    private Set<Character> nonTerminals;
    private Set<Character> terminals;
    private Map<Character, Set<String>> productions;
    private char startSymbol;
    
    // Special symbol for epsilon (empty string)
    public static final char EPSILON = '\u03B5'; // ε
    
    /**
     * Constructor for CFG
     */
    public ContextFreeGrammar(Set<Character> nonTerminals, Set<Character> terminals,
                             char startSymbol) {
        this.nonTerminals = new HashSet<>(nonTerminals);
        this.terminals = new HashSet<>(terminals);
        this.productions = new HashMap<>();
        this.startSymbol = startSymbol;
        
        // Initialize production map for all non-terminals
        for (char nt : nonTerminals) {
            this.productions.put(nt, new HashSet<>());
        }
    }
    
    /**
     * Add a production rule: nonTerminal → production
     */
    public void addProduction(char nonTerminal, String production) {
        if (!nonTerminals.contains(nonTerminal)) {
            throw new IllegalArgumentException("Invalid non-terminal: " + nonTerminal);
        }
        
        // Validate that production only contains valid symbols
        for (char c : production.toCharArray()) {
            if (c != EPSILON && !nonTerminals.contains(c) && !terminals.contains(c)) {
                throw new IllegalArgumentException("Invalid symbol in production: " + c);
            }
        }
        
        productions.get(nonTerminal).add(production);
    }
    
    /**
     * Get all productions for a non-terminal
     */
    public Set<String> getProductions(char nonTerminal) {
        return new HashSet<>(productions.getOrDefault(nonTerminal, new HashSet<>()));
    }
    
    /**
     * Check if a symbol is a non-terminal
     */
    public boolean isNonTerminal(char symbol) {
        return nonTerminals.contains(symbol);
    }
    
    /**
     * Print the CFG definition
     */
    public void printGrammar() {
        System.out.println("Context-Free Grammar:");
        System.out.println("Non-terminals (V): " + nonTerminals);
        System.out.println("Terminals (\u03A3): " + terminals);
        System.out.println("Start Symbol: " + startSymbol);
        System.out.println("\nProduction Rules:");
        
        for (Map.Entry<Character, Set<String>> entry : productions.entrySet()) {
            char nt = entry.getKey();
            Set<String> prods = entry.getValue();
            
            if (!prods.isEmpty()) {
                System.out.print(nt + " \u2192 ");
                System.out.println(String.join(" | ", prods));
            }
        }
    }
    
    // Getters
    public Set<Character> getNonTerminals() {
        return new HashSet<>(nonTerminals);
    }
    
    public char getStartSymbol() {
        return startSymbol;
    }

    // ===== Added for the extra homework (not in the session 36 version) =====

    /**
     * Get the letters (terminal symbols) of the grammar
     */
    public Set<Character> getTerminals() {
        return new HashSet<>(terminals);
    }

    /**
     * Build a grammar from its rules, written as in class. For example:
     *
     *     ContextFreeGrammar.parse("S → aSb | ε")
     *     ContextFreeGrammar.parse("V → n | [A]", "A → ε | VB", "B → ε | ,VB")
     *
     * - one string per variable (you can write -> instead of →);
     * - the start symbol is the variable of the first string;
     * - the variables are the symbols on the left of an arrow,
     *   and every other symbol (except ε and |) is a letter;
     * - spaces are ignored.
     */
    public static ContextFreeGrammar parse(String... rules) {
        List<Character> variables = new ArrayList<>();
        List<String> rightSides = new ArrayList<>();
        for (String rule : rules) {
            String r = rule.replace(" ", "").replace("->", "\u2192");
            if (r.length() < 2 || r.charAt(1) != '\u2192') {
                throw new IllegalArgumentException("Not a rule: " + rule);
            }
            variables.add(r.charAt(0));
            rightSides.add(r.substring(2));
        }
        Set<Character> letters = new HashSet<>();
        for (String rhs : rightSides) {
            for (char c : rhs.toCharArray()) {
                if (c != '|' && c != EPSILON && !variables.contains(c)) {
                    letters.add(c);
                }
            }
        }
        ContextFreeGrammar cfg = new ContextFreeGrammar(new HashSet<>(variables), letters, variables.get(0));
        for (int i = 0; i < rules.length; i++) {
            for (String production : rightSides.get(i).split("\\|", -1)) {
                cfg.addProduction(variables.get(i), production.isEmpty() ? String.valueOf(EPSILON) : production);
            }
        }
        return cfg;
    }

    /**
     * The rules on one line, start symbol first: "S → aSb | ε   A → a"
     */
    @Override
    public String toString() {
        List<Character> order = new ArrayList<>(nonTerminals);
        Collections.sort(order);
        order.remove((Character) startSymbol);
        order.add(0, startSymbol);
        StringBuilder sb = new StringBuilder();
        for (char nt : order) {
            List<String> prods = new ArrayList<>(productions.get(nt));
            if (prods.isEmpty()) {
                continue;
            }
            Collections.sort(prods);
            if (sb.length() > 0) {
                sb.append("   ");
            }
            sb.append(nt).append(" \u2192 ").append(String.join(" | ", prods));
        }
        return sb.toString();
    }
}
