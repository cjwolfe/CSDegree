import java.util.*;
import java.util.concurrent.*;

/**
 * The public tests of the extra homework.
 *
 *     java Tests        all the tests
 *     java Tests 1      only Part 1 (from a CFG to a PDA)
 *     java Tests 2      only Part 2 (the LL(1) parser)
 *
 * Each test prints ✓ or ✗, and the reason when it fails. I will also run
 * tests you have not seen, on edge cases: think about them yourself.
 *
 * Do not change this file: I grade with the original version.
 */
public class Tests {

    /** Seconds before a test is stopped (an infinite loop?). */
    static final int TIMEOUT = 5;

    /** After this many infinite loops in a part, its remaining tests are skipped. */
    static final int MAX_TIMEOUTS = Integer.getInteger("maxTimeouts", 3);

    static final String EPS = String.valueOf(ContextFreeGrammar.EPSILON);

    static int[] passed = new int[3];
    static int[] total = new int[3];
    static int[] timeouts = new int[3];
    static int part = 1;

    public static void main(String[] args) {
        checkEncoding();
        List<String> parts = Arrays.asList(args);
        if (parts.isEmpty() || parts.contains("1")) {
            part1();
        }
        if (parts.isEmpty() || parts.contains("2")) {
            part2();
        }
        summary("Public tests");
        System.exit(0);
    }

    // ============================== The tests ==============================

    static void part1() {
        start(1, "Part 1: from a CFG to a PDA (session 27)");

        ContextFreeGrammar palindromes = ContextFreeGrammar.parse("S → aSa | bSb | a | b | ε");
        ContextFreeGrammar zeroesOnes = ContextFreeGrammar.parse("S → 0S1 | ε");
        ContextFreeGrammar midterm = ContextFreeGrammar.parse("S → XY", "X → aXb | ε", "Y → bYc | ε");
        ContextFreeGrammar parentheses = ContextFreeGrammar.parse("S → (S) | SS | ε");

        section("toGCNF");
        checkGCNF("palindromes (session 27)", palindromes);
        checkGCNF("0^n 1^n (session 36)", zeroesOnes);
        checkGCNF("a^i b^j c^k with j = i + k (midterm 2)", midterm);
        checkGCNF("balanced parentheses (session 36)", parentheses);

        section("toPDA, on grammars already in GCNF");
        checkPDA("palindromes (session 27)",
                ContextFreeGrammar.parse("S → ASA | BSB | A | B | ε", "A → a", "B → b"));
        checkPDA("0^n 1^n", ContextFreeGrammar.parse("S → ZSU | ε", "Z → 0", "U → 1"));

        section("cfgToPda, then Membership on every word up to some length");
        checkLanguage("palindromes", palindromes);
        checkLanguage("0^n 1^n", zeroesOnes);
        checkLanguage("a^i b^j c^k with j = i + k", midterm);
        // Not for the balanced parentheses: with S → SS, Membership gives up (session 36).
    }

    static void part2() {
        start(2, "Part 2: the LL(1) parser (session 30)");

        section("S → (S)S | ε (session 30, slide \"LL(1) tables\")");
        LL1Table parentheses = new LL1Table(ContextFreeGrammar.parse("S → (S)S | ε"));
        parentheses.put('S', '(', "(S)S");
        parentheses.put('S', ')', "ε");
        parentheses.put('S', LL1Table.END, "ε");
        for (String word : new String[] {"()", "(())()", "", "(()", ")("}) {
            checkParse(parentheses, word);
        }

        section("lists of numbers (session 30)");
        LL1Table lists = new LL1Table(ContextFreeGrammar.parse("V → n | [A]", "A → ε | VB", "B → ε | ,VB"));
        lists.put('V', 'n', "n");
        lists.put('V', '[', "[A]");
        lists.put('A', 'n', "VB");
        lists.put('A', '[', "VB");
        lists.put('A', ']', "ε");
        lists.put('B', ']', "ε");
        lists.put('B', ',', ",VB");
        for (String word : new String[] {"[n,[]]", "n", "[]", "[n,[n,n],[]]", "[n,]", "[n"}) {
            checkParse(lists, word);
        }

        section("expressions (session 30, \"Fix 2\", with R for E', P for T' and * for ×)");
        LL1Table expressions = new LL1Table(ContextFreeGrammar.parse(
                "E → TR", "R → +TR | ε", "T → FP", "P → *FP | ε", "F → (E) | n"));
        expressions.put('E', 'n', "TR");
        expressions.put('E', '(', "TR");
        expressions.put('R', '+', "+TR");
        expressions.put('R', ')', "ε");
        expressions.put('R', LL1Table.END, "ε");
        expressions.put('T', 'n', "FP");
        expressions.put('T', '(', "FP");
        expressions.put('P', '+', "ε");
        expressions.put('P', '*', "*FP");
        expressions.put('P', ')', "ε");
        expressions.put('P', LL1Table.END, "ε");
        expressions.put('F', 'n', "n");
        expressions.put('F', '(', "(E)");
        for (String word : new String[] {"n+n*n", "(n+n)*n", "n+", "n(n)"}) {
            checkParse(expressions, word);
        }
    }

    // ============================== Checks ==============================

    /** toGCNF(g): in GCNF, built as in session 27, and generating the same words as g. */
    static void checkGCNF(String name, ContextFreeGrammar g) {
        String test = "toGCNF, " + name;
        ContextFreeGrammar result;
        try {
            result = run(() -> CfgToPda.toGCNF(copy(g)));
        } catch (Throwable e) {
            fail(test, describe(e));
            return;
        }
        if (result == null) {
            fail(test, "toGCNF returned null");
            return;
        }

        List<String> problems = new ArrayList<>();
        Set<Character> letters = g.getTerminals();
        if (!result.getTerminals().equals(letters)) {
            problems.add("the letters should stay " + sorted(letters) + ", not " + sorted(result.getTerminals()));
        }
        if (result.getStartSymbol() != g.getStartSymbol()) {
            problems.add("the start symbol should stay " + g.getStartSymbol() + ", not " + result.getStartSymbol());
        }
        if (!result.getNonTerminals().containsAll(g.getNonTerminals())) {
            problems.add("the variables of G should all stay");
        }
        int variables = g.getNonTerminals().size() + letters.size();
        if (result.getNonTerminals().size() != variables) {
            problems.add("session 27 adds exactly one new variable per letter: expected "
                    + variables + " variables, got " + result.getNonTerminals().size());
        }
        int rules = countRules(g) + letters.size();
        if (countRules(result) != rules) {
            problems.add("expected " + rules + " rules (those of G, plus one rule A_a → a per letter a), got "
                    + countRules(result));
        }
        int shown = 0;
        for (char variable : sorted(result.getNonTerminals())) {
            for (String production : result.getProductions(variable)) {
                if (!isGCNF(result, production) && shown++ < 3) {
                    problems.add("not in GCNF: " + new Rule(variable, production));
                }
            }
        }
        String difference = sameLanguage(g, result);
        if (difference != null) {
            problems.add(difference);
        }

        if (problems.isEmpty()) {
            pass(test);
        } else {
            problems.add("G:         " + g);
            problems.add("toGCNF(G): " + result);
            fail(test, problems.toArray(new String[0]));
        }
    }

    /** toPDA(g), for g in GCNF: exactly the PDA of session 27. */
    static void checkPDA(String name, ContextFreeGrammar g) {
        String test = "toPDA, " + name;
        PushdownAutomaton pda;
        try {
            pda = run(() -> CfgToPda.toPDA(copy(g)));
        } catch (Throwable e) {
            fail(test, describe(e));
            return;
        }
        if (pda == null) {
            fail(test, "toPDA returned null");
            return;
        }

        List<String> problems = new ArrayList<>();
        if (!"init".equals(pda.getInitialState())) {
            problems.add("the initial state should be init, not " + pda.getInitialState());
        }
        if (pda.getInitialStackSymbol() != '$') {
            problems.add("the initial stack symbol should be $, not " + pda.getInitialStackSymbol());
        }
        if (!pda.getAcceptingStates().equals(Set.of("accept"))) {
            problems.add("the accepting states should be [accept], not " + pda.getAcceptingStates());
        }
        Set<String> expected = expectedTransitions(g);
        Set<String> actual = transitions(pda);
        List<String> missing = new ArrayList<>(expected);
        missing.removeAll(actual);
        List<String> extra = new ArrayList<>(actual);
        extra.removeAll(expected);
        Collections.sort(missing);
        Collections.sort(extra);
        for (int i = 0; i < Math.min(4, missing.size()); i++) {
            problems.add("missing transition:    " + missing.get(i));
        }
        for (int i = 0; i < Math.min(4, extra.size()); i++) {
            problems.add("unexpected transition: " + extra.get(i));
        }
        if (missing.size() + extra.size() > 8) {
            problems.add("(and more)");
        }
        for (String t : actual) {
            if (t.contains("\"")) {
                problems.add("hint: you push the symbol ε on the stack. To push nothing, push the empty string \"\".");
                break;
            }
        }

        if (problems.isEmpty()) {
            pass(test);
        } else {
            problems.add("G: " + g);
            fail(test, problems.toArray(new String[0]));
        }
    }

    /** The PDA cfgToPda(g) accepts exactly the words of g, up to some length. */
    static void checkLanguage(String name, ContextFreeGrammar g) {
        int length = maxLength(g.getTerminals().size());
        String test = "cfgToPda + Membership, " + name + " (words up to length " + length + ")";
        String problem;
        try {
            problem = run(() -> {
                PushdownAutomaton pda = CfgToPda.cfgToPda(copy(g));
                if (pda == null) {
                    return "cfgToPda returned null";
                }
                for (String word : words(g.getTerminals(), length)) {
                    boolean inG = generates(g, word);
                    if (Membership.accepts(pda, word) != inG) {
                        return show(word) + (inG ? " is in L(G), but the PDA rejects it"
                                                 : " is not in L(G), but the PDA accepts it");
                    }
                }
                return null;
            });
        } catch (Throwable e) {
            fail(test, describe(e));
            return;
        }
        if (problem == null) {
            pass(test);
        } else {
            fail(test, problem, "G: " + g);
        }
    }

    /** parse(table, word): accepts exactly the words of the grammar, with their leftmost derivation. */
    static void checkParse(LL1Table table, String word) {
        checkParse(table, word, generates(table.getGrammar(), word));
    }

    static void checkParse(LL1Table table, String word, boolean inLanguage) {
        ContextFreeGrammar g = table.getGrammar();
        String test = "parse " + show(word);
        List<Rule> rules;
        try {
            rules = run(() -> LL1Parser.parse(table, word));
        } catch (Throwable e) {
            fail(test, describe(e));
            return;
        }
        if (rules == null) {
            if (inLanguage) {
                fail(test, "rejected, but " + show(word) + " is in the language");
            } else {
                pass(test + ": rejected");
            }
        } else if (!inLanguage) {
            fail(test, "accepted, but " + show(word) + " is not in the language", "rules returned: " + rules);
        } else {
            String problem = replay(g, rules, word);
            if (problem == null) {
                pass(test + ": accepted, " + rules.size() + (rules.size() == 1 ? " rule" : " rules"));
            } else {
                fail(test, problem, "rules returned: " + rules, "derivation:     " + Rule.derivation(g, rules));
            }
        }
    }

    // ============================== Helpers ==============================

    /** The tests are written with ε, → and ⊣: check that javac read them right. */
    static void checkEncoding() {
        if (!"ε→⊣".equals("\u03B5\u2192\u22A3")) {
            System.out.println("These files were compiled with the wrong character encoding.");
            System.out.println("Compile them with:  javac -encoding UTF-8 *.java");
            System.exit(1);
        }
    }

    static void start(int p, String title) {
        part = p;
        System.out.println();
        System.out.println("=== " + title);
    }

    static void section(String title) {
        System.out.println("-- " + title);
    }

    static void pass(String test) {
        passed[part]++;
        total[part]++;
        System.out.println("  ✓ " + test);
    }

    static void fail(String test, String... reasons) {
        total[part]++;
        System.out.println("  ✗ " + test);
        for (String reason : reasons) {
            System.out.println("        " + reason);
        }
    }

    static void summary(String title) {
        System.out.println();
        System.out.println("=== " + title);
        if (total[1] > 0) {
            System.out.println("Part 1 (CFG → PDA):    " + passed[1] + "/" + total[1] + " tests passed");
        }
        if (total[2] > 0) {
            System.out.println("Part 2 (LL(1) parser): " + passed[2] + "/" + total[2] + " tests passed");
        }
    }

    static class Timeout extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    static class Skipped extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    /** Run a task, but give up after TIMEOUT seconds. */
    static <T> T run(Callable<T> task) throws Throwable {
        if (timeouts[part] >= MAX_TIMEOUTS) {
            throw new Skipped();
        }
        FutureTask<T> future = new FutureTask<>(task);
        Thread thread = new Thread(future);
        thread.setDaemon(true);
        thread.start();
        try {
            return future.get(TIMEOUT, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            timeouts[part]++;
            throw new Timeout();
        } catch (ExecutionException e) {
            throw e.getCause();
        }
    }

    /** What went wrong, and where in your code. */
    static String describe(Throwable e) {
        if (e instanceof Timeout) {
            return "stopped after " + TIMEOUT + " seconds: an infinite loop?";
        }
        if (e instanceof Skipped) {
            return "not run: " + MAX_TIMEOUTS + " tests of this part already looped forever. Fix that first.";
        }
        if (e instanceof UnsupportedOperationException && String.valueOf(e.getMessage()).startsWith("TODO")) {
            return "not done yet (" + e.getMessage() + ")";
        }
        for (StackTraceElement frame : e.getStackTrace()) {
            if ("CfgToPda.java".equals(frame.getFileName()) || "LL1Parser.java".equals(frame.getFileName())) {
                return e + "\n        at " + frame.getFileName() + ", line " + frame.getLineNumber();
            }
        }
        return e.toString();
    }

    static String show(String word) {
        return word.isEmpty() ? "ε" : "\"" + word + "\"";
    }

    static List<Character> sorted(Set<Character> symbols) {
        List<Character> list = new ArrayList<>(symbols);
        Collections.sort(list);
        return list;
    }

    static ContextFreeGrammar copy(ContextFreeGrammar g) {
        ContextFreeGrammar c = new ContextFreeGrammar(g.getNonTerminals(), g.getTerminals(), g.getStartSymbol());
        for (char variable : g.getNonTerminals()) {
            for (String production : g.getProductions(variable)) {
                c.addProduction(variable, production);
            }
        }
        return c;
    }

    static int countRules(ContextFreeGrammar g) {
        int count = 0;
        for (char variable : g.getNonTerminals()) {
            count += g.getProductions(variable).size();
        }
        return count;
    }

    /** Is A → production of the form A → a (a a letter or ε) or A → W (W only variables)? */
    static boolean isGCNF(ContextFreeGrammar g, String production) {
        if (production.equals(EPS)) {
            return true;
        }
        if (production.length() == 1 && g.getTerminals().contains(production.charAt(0))) {
            return true;
        }
        for (char symbol : production.toCharArray()) {
            if (!g.isNonTerminal(symbol)) {
                return false;
            }
        }
        return true;
    }

    /** null if g and h generate the same words up to some length, else a word on which they differ. */
    static String sameLanguage(ContextFreeGrammar g, ContextFreeGrammar h) {
        for (String word : words(g.getTerminals(), maxLength(g.getTerminals().size()))) {
            boolean inG = generates(g, word);
            if (generates(h, word) != inG) {
                return show(word) + (inG ? " is in L(G) but not in L(toGCNF(G))"
                                         : " is in L(toGCNF(G)) but not in L(G)");
            }
        }
        return null;
    }

    /** How long the words of a language check can be, for an alphabet of that size. */
    static int maxLength(int letters) {
        switch (letters) {
            case 0: case 1: return 10;
            case 2: return 8;
            case 3: return 6;
            case 4: return 5;
            default: return 4;
        }
    }

    /** All the words over the letters, up to the given length, shortest first. */
    static List<String> words(Set<Character> letters, int maxLength) {
        List<String> all = new ArrayList<>();
        List<String> current = new ArrayList<>(List.of(""));
        for (int n = 0; n <= maxLength; n++) {
            all.addAll(current);
            List<String> longer = new ArrayList<>();
            for (String word : current) {
                for (char letter : sorted(letters)) {
                    longer.add(word + letter);
                }
            }
            current = longer;
        }
        return all;
    }

    /**
     * Does g generate the word? (A general recognizer for any CFG, which has
     * nothing to do with the homework: it marks, for each variable A, the
     * pieces of the word that A can derive, until nothing changes.)
     */
    static boolean generates(ContextFreeGrammar g, String word) {
        int n = word.length();
        Map<Character, boolean[][]> derives = new HashMap<>();
        for (char variable : g.getNonTerminals()) {
            derives.put(variable, new boolean[n + 1][n + 1]);
        }
        boolean changed = true;
        while (changed) {
            changed = false;
            for (char variable : g.getNonTerminals()) {
                boolean[][] mine = derives.get(variable);
                for (String production : g.getProductions(variable)) {
                    for (int i = 0; i <= n; i++) {
                        boolean[] reached = new boolean[n + 1];
                        reached[i] = true;
                        for (char symbol : production.toCharArray()) {
                            if (symbol == ContextFreeGrammar.EPSILON) {
                                continue;
                            }
                            boolean[] next = new boolean[n + 1];
                            for (int p = i; p <= n; p++) {
                                if (!reached[p]) {
                                    continue;
                                }
                                if (g.isNonTerminal(symbol)) {
                                    for (int q = p; q <= n; q++) {
                                        if (derives.get(symbol)[p][q]) {
                                            next[q] = true;
                                        }
                                    }
                                } else if (p < n && word.charAt(p) == symbol) {
                                    next[p + 1] = true;
                                }
                            }
                            reached = next;
                        }
                        for (int j = i; j <= n; j++) {
                            if (reached[j] && !mine[i][j]) {
                                mine[i][j] = true;
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
        return derives.get(g.getStartSymbol())[0][n];
    }

    /** The transitions of the PDA of session 27 for g, written as by transitions(). */
    static Set<String> expectedTransitions(ContextFreeGrammar g) {
        char e = ContextFreeGrammar.EPSILON;
        Set<String> expected = new HashSet<>();
        expected.add(transition("init", e, '$', "" + g.getStartSymbol() + '$', "simulate"));
        expected.add(transition("simulate", e, '$', "$", "accept"));
        for (char variable : g.getNonTerminals()) {
            for (String production : g.getProductions(variable)) {
                if (production.equals(EPS)) {
                    expected.add(transition("simulate", e, variable, "", "simulate"));
                } else if (production.length() == 1 && g.getTerminals().contains(production.charAt(0))) {
                    expected.add(transition("simulate", production.charAt(0), variable, "", "simulate"));
                } else {
                    expected.add(transition("simulate", e, variable, production, "simulate"));
                }
            }
        }
        return expected;
    }

    /** The transitions of a PDA, as "p → q on a, A, W". */
    static Set<String> transitions(PushdownAutomaton pda) {
        Set<String> all = new HashSet<>();
        for (Map.Entry<PushdownAutomaton.TransitionKey, Set<PushdownAutomaton.TransitionValue>> entry
                : pda.getTransitions().entrySet()) {
            PushdownAutomaton.TransitionKey key = entry.getKey();
            for (PushdownAutomaton.TransitionValue value : entry.getValue()) {
                all.add(transition(key.state, key.input, key.stackTop, value.stackString, value.nextState));
            }
        }
        return all;
    }

    static String transition(String from, char input, char top, String push, String to) {
        String pushed = push == null ? "null"
                : push.isEmpty() ? EPS
                : push.contains(EPS) ? "\"" + push + "\""
                : push;
        return from + " → " + to + " on " + input + ", " + top + ", " + pushed;
    }

    /** null if the rules form a leftmost derivation of the word in g, else what is wrong. */
    static String replay(ContextFreeGrammar g, List<Rule> rules, String word) {
        StringBuilder form = new StringBuilder(String.valueOf(g.getStartSymbol()));
        for (int k = 0; k < rules.size(); k++) {
            Rule rule = rules.get(k);
            String number = "rule number " + (k + 1);
            if (rule == null) {
                return number + " is null";
            }
            int leftmost = -1;
            for (int i = 0; i < form.length(); i++) {
                if (g.isNonTerminal(form.charAt(i))) {
                    leftmost = i;
                    break;
                }
            }
            if (leftmost < 0) {
                return number + ", " + rule + ", comes after the derivation has reached " + show(form.toString());
            }
            if (form.charAt(leftmost) != rule.variable()) {
                return number + ", " + rule + ", cannot be applied to " + form
                        + ": its leftmost variable is " + form.charAt(leftmost);
            }
            if (!g.getProductions(rule.variable()).contains(rule.rhs())) {
                return number + ", " + rule + ", is not a rule of the grammar";
            }
            form.replace(leftmost, leftmost + 1, rule.rhs().equals(EPS) ? "" : rule.rhs());
        }
        if (!form.toString().equals(word)) {
            return "the rules derive " + show(form.toString()) + ", not " + show(word);
        }
        return null;
    }
}
