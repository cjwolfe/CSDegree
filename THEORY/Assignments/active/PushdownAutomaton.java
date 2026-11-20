import java.util.*;

/**
 * Implementation of a Non-deterministic Pushdown Automaton (PDA)
 * A PDA is defined by (Q, Σ, Γ, δ, q0, Z0, F) where:
 * - Q: finite set of states
 * - Σ: input alphabet
 * - Γ: stack alphabet
 * - δ: transition function Q × (Σ ∪ {ε}) × Γ → P(Q × Γ*)
 * - q0: initial state
 * - Z0: initial stack symbol
 * - F: set of accepting states
 */
public class PushdownAutomaton {
    
    // PDA components
    private Set<String> states;
    private Set<Character> inputAlphabet;
    private Set<Character> stackAlphabet;
    private Map<TransitionKey, Set<TransitionValue>> transitions;
    private String initialState;
    private char initialStackSymbol;
    private Set<String> acceptingStates;
    
    // Epsilon symbol
    public static final char EPSILON = '\u03B5'; // ε
    
    /**
     * Constructor for PDA
     */
    public PushdownAutomaton(Set<String> states, Set<Character> inputAlphabet, 
                            Set<Character> stackAlphabet, String initialState,
                            char initialStackSymbol, Set<String> acceptingStates) {
        this.states = new HashSet<>(states);
        this.inputAlphabet = new HashSet<>(inputAlphabet);
        this.stackAlphabet = new HashSet<>(stackAlphabet);
        this.transitions = new HashMap<>();
        this.initialState = initialState;
        this.initialStackSymbol = initialStackSymbol;
        this.acceptingStates = new HashSet<>(acceptingStates);
    }
    
    /**
     * Represents a key for the transition function: (state, input, stack_top)
     */
    public static class TransitionKey {
        String state;
        char input;  // can be EPSILON
        char stackTop;
        
        public TransitionKey(String state, char input, char stackTop) {
            this.state = state;
            this.input = input;
            this.stackTop = stackTop;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TransitionKey)) return false;
            TransitionKey that = (TransitionKey) o;
            return input == that.input && stackTop == that.stackTop && 
                   Objects.equals(state, that.state);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(state, input, stackTop);
        }
        
        @Override
        public String toString() {
            return "(" + state + ", " + input + ", " + stackTop + ")";
        }
    }
    
    /**
     * Represents a value for the transition function: (next_state, stack_string)
     */
    public static class TransitionValue {
        String nextState;
        String stackString;  // string to push (can be empty for pop)
        
        public TransitionValue(String nextState, String stackString) {
            this.nextState = nextState;
            this.stackString = stackString;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TransitionValue)) return false;
            TransitionValue that = (TransitionValue) o;
            return Objects.equals(nextState, that.nextState) && 
                   Objects.equals(stackString, that.stackString);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(nextState, stackString);
        }
        
        @Override
        public String toString() {
            return "(" + nextState + ", " + stackString + ")";
        }
    }
    
    /**
     * Represents a configuration of the PDA: (state, remaining_input, stack)
     */
    public static class Configuration {
        String state;
        String remainingInput;
        Stack<Character> stack;
        
        public Configuration(String state, String remainingInput, Stack<Character> stack) {
            this.state = state;
            this.remainingInput = remainingInput;
            this.stack = new Stack<>();
            this.stack.addAll(stack);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Configuration)) return false;
            Configuration that = (Configuration) o;
            return Objects.equals(state, that.state) &&
                   Objects.equals(remainingInput, that.remainingInput) &&
                   Objects.equals(stack, that.stack);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(state, remainingInput, stack);
        }
        
        @Override
        public String toString() {
            return "(" + state + ", \"" + remainingInput + "\", " + stack + ")";
        }
    }
    
    /**
     * Add a transition to the PDA
     * δ(state, input, stackTop) → (nextState, stackString)
     */
    public void addTransition(String state, char input, char stackTop, 
                             String nextState, String stackString) {
        TransitionKey key = new TransitionKey(state, input, stackTop);
        TransitionValue value = new TransitionValue(nextState, stackString);
        
        transitions.computeIfAbsent(key, k -> new HashSet<>()).add(value);
    }
    
    /**
     * Apply a transition to a configuration
     */
    public Configuration applyTransition(Configuration current, TransitionValue tv, 
                                         boolean consumeInput) {
        // Create new stack
        Stack<Character> newStack = new Stack<>();
        newStack.addAll(current.stack);
        newStack.pop(); // Remove the top symbol
        
        // Push new symbols (in reverse order since it's a stack)
        for (int i = tv.stackString.length() - 1; i >= 0; i--) {
            newStack.push(tv.stackString.charAt(i));
        }
        
        // Update remaining input
        String newInput = current.remainingInput;
        if (consumeInput && !newInput.isEmpty()) {
            newInput = newInput.substring(1);
        }
        
        return new Configuration(tv.nextState, newInput, newStack);
    }
    
    /**
     * Print the PDA definition
     */
    public void printDefinition() {
        System.out.println("PDA Definition:");
        System.out.println("States: " + states);
        System.out.println("Input Alphabet: " + inputAlphabet);
        System.out.println("Stack Alphabet: " + stackAlphabet);
        System.out.println("Initial State: " + initialState);
        System.out.println("Initial Stack Symbol: " + initialStackSymbol);
        System.out.println("Accepting States: " + acceptingStates);
        System.out.println("\nTransitions:");
        for (Map.Entry<TransitionKey, Set<TransitionValue>> entry : transitions.entrySet()) {
            System.out.println("  \u03B4" + entry.getKey() + " \u2192 " + entry.getValue());
        }
    }
    
    // Getters for PDA components
    public String getInitialState() {
        return initialState;
    }
    
    public char getInitialStackSymbol() {
        return initialStackSymbol;
    }
    
    public Set<String> getAcceptingStates() {
        return new HashSet<>(acceptingStates);
    }
    
    public Map<TransitionKey, Set<TransitionValue>> getTransitions() {
        return new HashMap<>(transitions);
    }
    
}
