import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LanguageChecker {

    public static boolean isEmptyLanguage(Automaton automaton) {
        Set<State> reachable = new HashSet<>();
        List<State> toVisit = new ArrayList<>();
        toVisit.add(automaton.getStartState());

        while (!toVisit.isEmpty()) {
            State current = toVisit.remove(toVisit.size() - 1);

            if (!reachable.add(current)) continue;

            if (automaton.getFinalStates().contains(current)) {
                return false;
            }

            for (Transition transition : automaton.getTransitions()) {
                if (transition.getStartingFrom().equals(current) && transition.getSymbol().equals("ε")) {
                    toVisit.add(transition.getGoingTo());
                }
            }
        }

        return true;
    }
}

