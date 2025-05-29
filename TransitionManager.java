package manager;

import base.State;
import base.Transition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Мениджър за управление на преходите в автоматите.
 * Поддържа сериализация за запазване на състоянието.
 */
public class TransitionManager implements Serializable {
    private static final long serialVersionUID=1L;
    private List<Transition> transitions;
    /**
     * Създава нов празен мениджър за преходи.
     */
    public TransitionManager() {
        transitions = new ArrayList<>();
    }

    /**
     * Добавя нов преход.
     *
     * @param transition преходът за добавяне
     */
    public void addTransition(Transition transition) {
        if (!transitions.contains(transition)) {
            transitions.add(transition);
        }
    }
    /**
     * Връща всички преходи.
     *
     * @return списък с преходите
     */
    public List<Transition> getTransitions() {
        return transitions;
    }
    /**
     * Връща целевите състояния за дадено начално състояние и символ.
     *
     * @param state начално състояние
     * @param symbol символ за преход
     * @return множество от целеви състояния
     */
    public Set<State> getTargetTransitions(State state, char symbol){
        Set<State>targets=new HashSet<>();
        for(Transition t:transitions){
            if(t.getStartingFrom().equals(state)&&t.getSymbol()==symbol){
                targets.add(t.getGoingTo());
            }
        }
        return targets;
    }
    /**
     * Връща всички преходи от дадено състояние.
     *
     * @param state начално състояние
     * @return списък с преходите
     */
    public List<Transition> getTransitionFrom(State state){
        List<Transition>result=new ArrayList<>();
        for(Transition transition:transitions){
            if(transition.getStartingFrom().equals(state)){
                result.add(transition);
            }
        }
        return result;
    }
}
