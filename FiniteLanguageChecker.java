import java.util.HashSet;
import java.util.Set;

public class FiniteLanguageChecker {
    public static boolean isFinite(Automaton automaton) {
        Set<State> visited = new HashSet<>();
        Set<State> inPath = new HashSet<>();
        Set<State> finalStates = automaton.getFinalStates();

        for (State state : automaton.getStates()) {
            if (!visited.contains(state)) {
                if (hasReachableFinalCycle(state, automaton, visited, inPath, finalStates)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hasReachableFinalCycle(State current, Automaton automaton, Set<State> visited, Set<State> inPath, Set<State> finalStates) {
        inPath.add(current);
        visited.add(current);

        for (Transition t : automaton.getTransitionManager().getTransitionFrom(current)) {
            State next = t.getGoingTo();

            if (!visited.contains(next)) {
                if (hasReachableFinalCycle(next, automaton, visited, inPath, finalStates)) {
                    return true;
                }
            } else if (inPath.contains(next) && isReachableToFinal(next, automaton, finalStates)) {
                return true;
            }
        }
        inPath.remove(current);
        return false;
    }


    public static boolean isReachableToFinal(State from, Automaton automaton, Set<State> finalStates) {
        return isReachableToFinal(from, automaton, finalStates, new HashSet<>());
    }

    public static boolean isReachableToFinal(State from, Automaton automaton, Set<State> finalStates, Set<State> visited) {
        if (finalStates.contains(from)) {
            return true;
        }
        visited.add(from);
        for (Transition t : automaton.getTransitionManager().getTransitionFrom(from)) {
            State next = t.getGoingTo();
            if (!visited.contains(next) && isReachableToFinal(next, automaton, finalStates, visited)) {
                return true;
            }
        }
        return false;
    }
}