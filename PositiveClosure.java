package utils;

import base.Automaton;
import base.State;
import base.Transition;
/**
 * Клас за положителна обвивка на автомат.
 * Положителната обвивка генерира нов автомат, който разпознава един или повече пъти езика
 * на оригиналния автомат.
 */
public class PositiveClosure {
    /**
     * Създава нов автомат, който представлява положителната обвивка на подадения автомат.
     * За целта се копира автоматът и се добавят ε-преходи от всяко финално състояние
     * обратно към началното състояние. Също така, началното състояние става и финално,
     * за да се позволи приемане на дума при завършване на последната итерация.
     *
     * @param automaton входният автомат, който трябва да бъде обвит
     * @return нов автомат, разпознаващ поне едно повторение на езика на оригиналния автомат
     */
    public static Automaton createPositiveClosure(Automaton automaton)  {
        Automaton result=AutomatonOperationHelper.copyAutomaton(automaton, "Позитивна обвивка-");
        for(State finalState:result.getFinalStates()){
            result.addTransition(new Transition(finalState,result.getStartState(),'ε'));
        }
        result.getStartState().setFinal(true);
        return result;
    }
}
