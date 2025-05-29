package utils;

import base.Automaton;
import base.State;
import manager.TransitionManager;

import java.util.HashSet;
import java.util.Set;
/**
 * Клас за разпознаване на дума от недетерминиран автомат с ε-преходи.
 * Използа преходи и начални/финални състояния, за да определи дали автоматът приема дадена дума.
 */
public class RecognizeChecker {
    private TransitionManager transitionManager;
    private State startState;
    private Set<State> finalStates;
    /**
     * Създава нов разпознавател, използвайки мениджър класа за преходи,
     * начално състояние и множество от финални състояния.
     *
     * @param transitionManager мениджър на преходи - съдържа всички преходи на автомата
     * @param startState начално състояние на автомата
     * @param finalStates финални състояния на автомата
     */
    public RecognizeChecker(TransitionManager transitionManager, State startState, Set<State> finalStates) {
        this.transitionManager = transitionManager;
        this.startState = startState;
        this.finalStates = finalStates;
    }
    /**
     * Изчислява всички състояния, достижими чрез ε-преходи.
     *
     * @param states начални състояния
     * @return множество от достижими чрез последователност от ε-преходи състояния
     */
    public Set<State> getEpsilonReachableStates(Set<State> states) {
    Set<State> epsilonReachableStates=new HashSet<>(states);
    boolean changed;

    do{
        changed=false;
        Set<State> newStates=new HashSet<>(epsilonReachableStates);
        for(State state:epsilonReachableStates){
            for(State next:transitionManager.getTargetTransitions(state,'ε')){
                if(!epsilonReachableStates.contains(next)){
                    newStates.add(next);
                    changed=true;
                }
            }
        }
        epsilonReachableStates=newStates;
    }while(changed);

    return  epsilonReachableStates;
    }
    /**
     * Проверява дали автоматът приема/разпознава дадена дума.
     *
     * @param word входна дума, която трябва да бъде проверена
     * @return {@code true} ако думата е разпозната от автомата, {@code false} ако не е
     */
    public boolean recognize(String word) {
        Set<State> startStates = new HashSet<>();
        startStates.add(startState);
        Set<State> currentStates = getEpsilonReachableStates(startStates);

        for (char c : word.toCharArray()) {
            Set<State> nextStates = new HashSet<>();
            for (State state : currentStates) {
                for (State target : transitionManager.getTargetTransitions(state, c)) {
                    Set<State> targetSet = new HashSet<>();
                    targetSet.add(target);
                    nextStates.addAll(getEpsilonReachableStates(targetSet));
                }
            }
            currentStates = nextStates;
        }

        for (State state : currentStates) {
            if (finalStates.contains(state)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Статичен помощен метод за разпознаване на дума от автомат,
     * без нужда от създаване на обект от класа.
     *
     * @param automaton автомат, който ще бъде използван за проверка
     * @param word дума за разпознаване
     * @return {@code true} ако думата е разпозната от автомата, {@code false} ако не е
     */
    public static boolean recognize(Automaton automaton, String word) {
        RecognizeChecker checker = new RecognizeChecker(
                automaton.getTransitionManager(),
                automaton.getStartState(),
                automaton.getFinalStates()
            );
            return checker.recognize(word);
    }

}

