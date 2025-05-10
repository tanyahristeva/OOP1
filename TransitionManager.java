import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TransitionManager implements Serializable {
    private static final long serialVersionUID=1L;
    private Set<Transition> transitions;

    public TransitionManager() {
        transitions = new HashSet<>();
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public Set<State> getTargetTransitions(State state, char symbol){
        Set<State>targets=new HashSet<>();
        for(Transition t:transitions){
            if(t.getStartingFrom().equals(state)&&t.getSymbol()==symbol){
                targets.add(t.getGoingTo());
            }
        }
        return targets;
    }
}
