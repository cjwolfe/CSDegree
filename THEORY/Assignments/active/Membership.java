import java.util.*;

/**
 * The membership algorithm of session 36 (membership() in its Main.java):
 * explore every computation of the PDA on the word, breadth first.
 *
 * One change: it gives up after exploring LIMIT configurations. Remember the
 * catch from session 36 (java Main -inf): a PDA whose ε-transitions can grow
 * the stack forever has infinitely many configurations, and the original
 * version then loops forever. For instance, the PDA built from S → SS | ...
 * can expand S into SS, then SS into SSS, and so on, without reading anything.
 *
 * Do not change this file.
 */
public class Membership {

    /** How many configurations to explore before giving up. */
    public static final int LIMIT = 200_000;

    /**
     * Does the PDA accept the word (in an accepting state, with the whole word read)?
     * Throws an IllegalStateException if it has to give up.
     */
    public static boolean accepts(PushdownAutomaton pda, String word) {
        // Initialize configuration
        Stack<Character> initialStack = new Stack<>();
        initialStack.push(pda.getInitialStackSymbol());
        PushdownAutomaton.Configuration initial =
            new PushdownAutomaton.Configuration(pda.getInitialState(), word, initialStack);

        // Use BFS to explore all possible computation paths
        Queue<PushdownAutomaton.Configuration> queue = new LinkedList<>();
        queue.add(initial);

        // Keep track of visited configurations to avoid infinite loops
        Set<PushdownAutomaton.Configuration> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            PushdownAutomaton.Configuration current = queue.poll();

            // Check if already visited
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            // The change: give up instead of looping forever
            if (visited.size() > LIMIT) {
                throw new IllegalStateException("Membership gave up on \"" + word + "\" after "
                        + LIMIT + " configurations: can this PDA grow its stack forever without reading?");
            }

            // Check if we're in an accepting configuration
            if (current.remainingInput.isEmpty() && pda.getAcceptingStates().contains(current.state)) {
                return true;
            }

            // Check if stack is empty (can't process further)
            if (current.stack.isEmpty()) {
                continue;
            }

            char stackTop = current.stack.peek();

            // Try epsilon transitions
            PushdownAutomaton.TransitionKey epsilonKey =
                new PushdownAutomaton.TransitionKey(current.state, PushdownAutomaton.EPSILON, stackTop);
            if (pda.getTransitions().containsKey(epsilonKey)) {
                for (PushdownAutomaton.TransitionValue tv : pda.getTransitions().get(epsilonKey)) {
                    PushdownAutomaton.Configuration next = pda.applyTransition(current, tv, false);
                    if (next != null) {
                        queue.add(next);
                    }
                }
            }

            // Try input symbol transitions (if there's input remaining)
            if (!current.remainingInput.isEmpty()) {
                char inputSymbol = current.remainingInput.charAt(0);
                PushdownAutomaton.TransitionKey inputKey =
                    new PushdownAutomaton.TransitionKey(current.state, inputSymbol, stackTop);

                if (pda.getTransitions().containsKey(inputKey)) {
                    for (PushdownAutomaton.TransitionValue tv : pda.getTransitions().get(inputKey)) {
                        PushdownAutomaton.Configuration next = pda.applyTransition(current, tv, true);
                        if (next != null) {
                            queue.add(next);
                        }
                    }
                }
            }
        }

        return false;
    }
}
