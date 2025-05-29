package utils;

import base.Automaton;
import base.State;
import base.Transition;

import java.util.HashSet;
import java.util.Set;
/**
 * Клас за проверка дали езикът, разпознат от даден автомат е краен.
 * Като краен език е такъв, съдържащ краен брой думи.
 * Проверява се чрез откривване на цикли в графа на автомата, които са достижими и водят до финално състояние.
 */
public class FiniteLanguageChecker {
    /**
     * Проверява дали езикът на автомата е краен.
     *
     * @param automaton автомат за проверка
     * @return true ако езикът е краен, false иначе
     */
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
    /**
     * Проверява дали дадено състояние участва в цикъл, който е достижим и води до финално състояние.
     *
     * @param current текущо състояние
     * @param automaton автомат за анализ
     * @param visited вече посетени състояния
     * @param inPath състояния в текущия път на обход
     * @param finalStates финалните състояния на автомата
     * @return true ако съществува цикъл, водещ до финално състояние, false иначе
     */
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

    /**
     * Проверява дали от дадено състояние е възможно да се достигне до поне едно финално състояние.
     *
     * @param from началното състояние
     * @param automaton автомат, в който се извършва проверката
     * @param finalStates множеството от финални състояния
     * @return true ако съществува път до финално състояние, false иначе
     */
    public static boolean isReachableToFinal(State from, Automaton automaton, Set<State> finalStates) {
        return isReachableToFinal(from, automaton, finalStates, new HashSet<>());
    }
    /**
     * Рекурсивен помощен метод за проверка на достижимост до финално състояние с проследяване на посетените състояния.
     *
     * @param from текущо състояние
     * @param automaton автомат за проверка
     * @param finalStates множеството от финални състояния
     * @param visited вече посетени състояния
     * @return true ако е възможно достигане до финално състояние, false иначе
     */
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