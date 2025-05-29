package utils;

import base.Automaton;
import base.State;
import base.Transition;

import java.util.Set;
/**
 * Клас за прилагане на операцията звезда на Клини върху краен автомат.
 * Създава нов автомат, който разпознава нула или повече повторения на езика на дадения автомат.
 * Езикът съдържа всички низове, които могат да бъдат получени чрез конкатенация
 * на нула или повече думи от оригиналния език.
 */
public class AutomatonKleeneStar {
    /**
     * Прилага операцията звезда на Клини върху даден автомат, като се създава ново
     * начално състояние, добавя се ε-преход от новото начално състояние
     * към началното на оригиналния автомат, също се добавят и  ε-преходи от всяко финално
     * състояние към началното и новото начално състояние става финално
     *
     * @param automaton автоматът, върху който се прилага операцията
     * @return нов автомат, представящ езика L*
     */
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
