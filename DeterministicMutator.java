import java.util.*;

public class DeterministicMutator {
    public static Automaton determinize(Automaton nfa) throws IllegalAccessException {
        Set<Character> alphabet = prepareAlphabet(nfa);
        Set<State>inititalStateSet=new HashSet<>();
        inititalStateSet.add(nfa.getStartState());

        Set<State> startClosure = calculateEpsilonClosure(inititalStateSet, nfa.getTransitionManager());
        State startState = createNewState(startClosure);

        Map<Set<State>, State> stateMap = new HashMap<>();
        stateMap.put(startClosure, startState);

        List<Set<State>> pendingStates = new ArrayList<>();
        pendingStates.add(startClosure);

        Set<State> dfaStates = new HashSet<>();
        dfaStates.add(startState);

        TransitionManager transitions = new TransitionManager();
        processStates(nfa, alphabet, stateMap, pendingStates, dfaStates, transitions);

        Set<State> finalStates = findFinalStates(dfaStates);

        return new Automaton(alphabet, dfaStates, startState, finalStates, transitions);
    }

    private static Set<Character> prepareAlphabet(Automaton nfa) {
        Set<Character> alphabet = new HashSet<>(nfa.getAlphabet());
        alphabet.remove('ε');
        return alphabet;
    }

    private static Set<State> calculateEpsilonClosure(Set<State>states, TransitionManager manager) {
        Set<State> closure = new HashSet<>(states);

        boolean changed;
        do {
            changed = false;
            Set<State> newStates = new HashSet<>();

            for (State s : closure) {
                for (Transition t : manager.getTransitionFrom(s)) {
                    if (t.getSymbol() == 'ε' && !closure.contains(t.getGoingTo())) {
                        newStates.add(t.getGoingTo());
                        changed = true;
                    }
                }
            }
            closure.addAll(newStates);

        } while (changed);
        return closure;
    }

    private static State createNewState(Set<State> states) {
        String name="";
        boolean isFinal = false;
        List<String> stateNames = new ArrayList<>();
        for (State s : states) {
            stateNames.add(s.getName());
            if (s.isFinal()) {
                isFinal = true;
            }
        }
        Collections.sort(stateNames);


        for (int i = 0; i < stateNames.size(); i++) {
            name += stateNames.get(i);
            if (i < stateNames.size() - 1) {
                name += "_";
            }
        }
        return new State(name, isFinal);
    }

    private static void processStates(Automaton nfa, Set<Character> alphabet, Map<Set<State>, State> stateMap, List<Set<State>> pendingStates, Set<State> dfaStates, TransitionManager transitions) throws IllegalAccessException {
        while (!pendingStates.isEmpty()) {
            Set<State> current = pendingStates.remove(0);
            State currentDfaState = stateMap.get(current);

            for (char symbol : alphabet) {
                Set<State> move = new HashSet<>();

                for (State s : current) {
                    move.addAll(nfa.getTransitionManager().getTargetTransitions(s, symbol));
                }
                if (!move.isEmpty()) {
                    Set<State> newClosure = calculateEpsilonClosure(move, nfa.getTransitionManager());
                    State newDfaState = stateMap.get(newClosure);
                    if (newDfaState == null) {
                        newDfaState = createNewState(newClosure);
                        stateMap.put(newClosure, newDfaState);
                        pendingStates.add(newClosure);
                        dfaStates.add(newDfaState);
                    }
                    transitions.addTransition(new Transition(currentDfaState, newDfaState, symbol));
                }
            }
        }
    }

    private static Set<State> findFinalStates(Set<State> dfaStates) {
        Set<State> finals = new HashSet<>();
        for (State s : dfaStates) {
            if (s.isFinal()) {
                finals.add(s);
            }
        }
        return finals;
    }
}


