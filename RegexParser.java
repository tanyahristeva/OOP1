import java.util.Set;

public class RegexParser {
    private final String regex;
    private int pos;
    private char currentChar;

    public RegexParser(String regex) {
        this.regex = regex;
        this.pos = 0;
        this.currentChar = pos < regex.length() ? regex.charAt(pos) : '\0';
    }

    public Automaton parse() {
        return parseExpression();
    }

    private Automaton parseExpression() {
        Automaton term = parseTerm();
        while (currentChar == '|') {
            next();
            Automaton right = parseTerm();
            term = new AutomatonUnion().union(term, right);
        }
        return term;
    }

    private Automaton parseTerm() {
        Automaton factor = parseFactor();
        while (hasMore() && (isSymbol(currentChar) || currentChar == '(')) {
            Automaton nextFactor = parseFactor();
            factor = new AutomatonConcatenation().concatAutomatons(factor, nextFactor);
        }
        return factor;
    }

    private Automaton parseFactor() {
        Automaton base = parseBase();
        while (currentChar == '*') {
            next();
            base = new AutomatonKleeneStar().applyKleeneStar(base);
        }
        return base;
    }

    private Automaton parseBase() {
        if (currentChar == '(') {
            next();
            Automaton inside = parseExpression();
            next();
            return inside;
        } else {
            char symbol = currentChar;
            next();
            return createBasicAutomaton(symbol);
        }
    }

    private void next() {
        pos++;
        currentChar = pos < regex.length() ? regex.charAt(pos) : '\0';
    }

    private boolean hasMore() {
        return pos < regex.length();
    }

    private boolean isSymbol(char c) {
        return Character.isLetterOrDigit(c);
    }

    private Automaton createBasicAutomaton(char symbol) {
        State s0 = new State("q0", false);
        State s1 = new State("q1", true);
        Automaton automaton = new Automaton(Set.of(symbol));
        automaton.setStartState(s0);
        automaton.getStates().add(s0);
        automaton.getStates().add(s1);
        automaton.getFinalStates().add(s1);
        automaton.addTransition(new Transition(s0, s1, symbol));
        return automaton;
    }
}

