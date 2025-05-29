package utils;

import base.Automaton;
import base.State;
import base.Transition;
import manager.TransitionManager;

import java.util.*;
/**
 * Клас, реализиращ трансформация на недетерминиран автомат в детерминиран
 * чрез стандартния алгоритъм на детерминизация (subset construction).
 */
public class DeterministicMutator {
    /**
     * Детерминира недетерминиран автомат.
     *
     * @param nfa недетерминиран автомат (с ε-преходи)
     * @return детерминиран автомат, еквивалентен на входния
     * @throws IllegalAccessException aко възникне грешка при създаване на състояния
     */
    public static Automaton determinize(Automaton nfa){
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
    /**
     * Подготвя азбуката на автомата, като премахва ε-преходите.
     *
     * @param nfa недетерминиран автомат
     * @return азбука без символа ε
     */
    private static Set<Character> prepareAlphabet(Automaton nfa) {
        Set<Character> alphabet = new HashSet<>(nfa.getAlphabet());
        alphabet.remove('ε');
        return alphabet;
    }
    /**
     * Изчислява ε-затваряне (множество от всички състояния, които могат бъдат достигнати чрез ε-преходи.
     *
     * @param states начално множество състояния
     * @param manager мениджър на преходите
     * @return ε-затваряне  на състоянията
     */
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
    /**
     * Създава ново състояние на базата на множество от оригинални състояния.
     * Името на новото състояние е комбинация от имената на включените състояния.
     *
     * @param states множество от състояния
     * @return ново състояние за детерминирания автомат
     */
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
    /**
     * Обработва всички нови състояния, като прилага трансформации върху всички символи от азбуката.
     *
     * @param nfa входният недетерминиран автомат
     * @param alphabet азбуката без ε
     * @param stateMap карта от множества към детерминирани състояния
     * @param pendingStates лист от още непроцесирани състояния
     * @param dfaStates създадените детерминирани състояния
     * @param transitions преходите на детерминирания автомат
     */
    private static void processStates(Automaton nfa, Set<Character> alphabet, Map<Set<State>, State> stateMap, List<Set<State>> pendingStates, Set<State> dfaStates, TransitionManager transitions) {
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
    /**
     * Открива финалните състояния на DFA автомата на база оригиналните финални състояния на недетерминирания автомат.
     *
     * @param dfaStates всички състояния на DFA автомата
     * @return множество от финални състояния
     */
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


