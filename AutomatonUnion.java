package utils;

import base.Automaton;
import base.State;
import base.Transition;

import java.util.Map;
/**
 * Клас за прилагане на операцията обединение върху два крайни автомата.
 * Създава нов автомат, който разпознава езика, представляващ обединението
 * на езиците на двата подадени автомата.
 *
 * Обединението се реализира чрез добавяне на ново начално състояние с ε-преходи
 * към началните състояния на двата оригинални автомата.
 */
public class AutomatonUnion {
    /**
     * Извършва операцията обединение между два крайни автомата, като се
     * създава нов автомат с обединена азбука от двата автомата,
     * състоянията на двата автомата се копират с различни префикси,
     * създава се ново начално състояние и ε-преходи се добавят от новото начално състояние
     * към началните състояния на копираните автомати,
     * и финално всички преходи от оригиналните автомати се копират
     *
     * @param first първият автомат
     * @param second вторият автомат
     * @return нов автомат, представящ обединението на езиците на двата автомата

     */
    public Automaton union(Automaton first, Automaton second) {
    Automaton result=new Automaton(AutomatonOperationHelper.createCombinedAlphabet(first,second));

    Map<State,State>firstMap=AutomatonOperationHelper.copyStates(first,result,"A1-",true);
    Map<State,State>secondMap=AutomatonOperationHelper.copyStates(second,result,"A2-",true);

    State newStart=new State("Union Start",false);
    result.getStates().add(newStart);
    result.setStartState(newStart);

    AutomatonOperationHelper.copyTransitions(first,result,firstMap);
    AutomatonOperationHelper.copyTransitions(second,result,secondMap);
    result.addTransition(new Transition(newStart,firstMap.get(first.getStartState()),'ε'));
    result.addTransition(new Transition(newStart,secondMap.get(second.getStartState()),'ε'));
    return result;
    }
}
