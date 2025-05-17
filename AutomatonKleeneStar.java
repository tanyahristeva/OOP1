import java.util.Set;
import java.util.HashSet;

public class AutomatonKleeneStar {
    public Automaton applyKleeneStar(Automaton automaton)  {
        Automaton result = new Automaton(automaton.getAlphabet());

        Set<State> oldStates = automaton.getStates();
        for (State state : oldStates) {
            result.getStates().add(state);
        }

        State newStart = new State("Start*", false);
        result.setStartState(newStart);
        result.getStates().add(newStart);

        result.addTransition(new Transition(newStart, automaton.getStartState(), 'ε'));

        for (Transition t : automaton.getTransitions()) {
            result.addTransition(t);
        }

        for (State finalState : automaton.getFinalStates()) {
            result.addTransition(new Transition(finalState, automaton.getStartState(), 'ε'));
        }

        result.getFinalStates().add(newStart);

        return result;
    }
}
