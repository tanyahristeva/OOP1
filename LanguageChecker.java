package utils;

import base.Automaton;
import base.State;
import base.Transition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Клас за проверка дали езикът, разпознаван от автомат, е празен.
 * Използва алгоритъм за достижимост на финално състояние от началното.
 */
public class LanguageChecker {
    /**
     * Проверява дали автоматът разпознава поне една дума.
     * Методът търси дали съществува път от началното състояние
     * до поне едно финално състояние чрез преходите в автомата.
     *
     * @param automaton автомат за проверка
     * @return true, ако няма път до финално състояние (т.е. езикът е празен),
     * false, ако съществува поне една дума, която се приема
     */
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
                if (transition.getStartingFrom().equals(current)) {
                    toVisit.add(transition.getGoingTo());
                }
            }
        }

        return true;
    }
}

