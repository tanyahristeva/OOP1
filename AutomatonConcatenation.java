package utils;

import base.Automaton;
import base.State;
import base.Transition;

import java.util.Map;
/**
 * Клас за конкатенация на два крайни автомата.
 * Създава нов автомат, който разпознава езика, получен чрез конкатенация на
 * езиците на първия и втория автомат.
 */
public class AutomatonConcatenation {
    /**
     * Конкатенира два крайни автомата в един, като създава нов автомат с обединена азбука,
     * копира състояния от първия и втория автомат, копира преходи от двата автомата
     * и добавя ε-преходи от всяко финално състояние на първия автомат
     * към началното състояние на втория
     * копира преходи от двата автомата
     *
     * @param first първият автомат
     * @param second вторият автомат
     * @return нов автомат, представящ конкатенацията на езиците на двата автомата
     *
     */
    public Automaton concatAutomatons(Automaton first, Automaton second) {

        Automaton result=new Automaton(AutomatonOperationHelper.createCombinedAlphabet(first,second));


        Map<State, State> firstMap = AutomatonOperationHelper.copyStates(first,result,"A1-",false);
        Map<State, State> secondMap = AutomatonOperationHelper.copyStates(second,result,"A2-",true);
        result.setStartState(firstMap.get(first.getStartState()));

        for (State finalState : second.getFinalStates()) {
            result.getFinalStates().add(secondMap.get(finalState));
        }
        AutomatonOperationHelper.copyTransitions(first, result, firstMap);
        AutomatonOperationHelper.copyTransitions(second, result, secondMap);

        addEpsilonTransitions(first, second, result, firstMap, secondMap);
        return result;
    }
    /**
     * Добавя ε-преходи между финалните състояния на първия автомат
     * и началното състояние на втория автомат.
     *
     * @param first първи автомат
     * @param second втори автомат
     * @param result резултатен автомат
     * @param firstMap мап на състоянията от първия автомат
     * @param secondMap мап на състоянията от втория автомат
     */
    private void addEpsilonTransitions(Automaton first,Automaton second, Automaton result, Map<State,State> firstMap,Map<State,State>secondMap) {
        State secondStart = secondMap.get(second.getStartState());
        for (State finalState : first.getFinalStates()) {
            State firstFinal = firstMap.get(finalState);
            result.addTransition(new Transition(firstFinal, secondStart, 'ε'));
        }
    }
}
