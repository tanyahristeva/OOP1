package utils;

import base.Automaton;
import base.State;
import base.Transition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * Клас с помощни операции за работа с автомати.
 * Предоставя методи за копиране на състояния и преходи между автомати,
 * създаване на комбинирана азбука и клониране на автомат.
 */
public class AutomatonOperationHelper {

    /**
     * Обединява азбуките на два автомата и създава ново множество символи.
     * Използва се при операциите обединение и конкатенация на автомати.
     *
     * @param a1 първи автомат
     * @param a2 втори автомат
     * @return нова азбука, съдържаща всички символи от двете азбуки
     */
    public static Set<Character> createCombinedAlphabet(Automaton a1, Automaton a2) {
        Set<Character> alphabet = new HashSet<>(a1.getAlphabet());
        alphabet.addAll(a2.getAlphabet());
        return alphabet;
    }
    /**
     * Копира състояния от изходен автомат в целеви автомат.
     * Новите състояния получават нови имена чрез префикс.
     *
     * @param source изходният автомат, от който се копират състоянията
     * @param target целевият автомат, в който се копират състоянията
     * @param prefix префикс, който се добавя към имената на новите състояния
     * @param addToFinal ако е true, копираните финални състояния се добавят и към финалните на целевия автомат
     * @return Map от оригиналните състояния към техните копия
     */
    public static Map<State,State> copyStates(Automaton source, Automaton target, String prefix, boolean addToFinal) {
        Map<State, State> map = new HashMap<>();
        for (State state : source.getStates()) {
            State copy = new State(prefix + state.getName(), state.isFinal());
            target.getStates().add(copy);
            if (addToFinal && state.isFinal()) {
                target.getFinalStates().add(copy);
            }
            map.put(state, copy);
        }
        return map;

    }
    /**
     * Копира преходите от един автомат в друг.
     * Използва се Map от оригиналните към новите състояния, за да се запазят връзките.
     *
     * @param source изходният автомат
     * @param target целевият автомат
     * @param map съответствие между оригиналните състояния и техните копия
     */
    public static void copyTransitions(Automaton source, Automaton target, Map<State,State>map) {
        for(Transition t: source.getTransitions()){
            State from=map.get(t.getStartingFrom());
            State to=map.get(t.getGoingTo());
            target.addTransition(new Transition(from,to, t.getSymbol()));
        }
    }
    /**
     * Създава пълно копие на автомат, включително състояния, преходи и начален/финални състояния.
     *
     * @param source автоматът, който ще бъде копиран
     * @param prefix префикс за имената на новите състояния
     * @return нов автомат, идентичен по структура с оригиналния
     */
    public static Automaton copyAutomaton(Automaton source, String prefix){
        Automaton copy=new Automaton(new HashSet<>(source.getAlphabet()));
        final boolean copyFinalStates=true;
        Map<State,State>map=copyStates(source,copy,prefix,copyFinalStates);
        copy.setStartState(map.get(source.getStartState()));
        copyTransitions(source,copy,map);
        return copy;

        }

}
