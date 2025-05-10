import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TransitionManager implements Serializable {
    private static final long serialVersionUID=1L;
    private List<Transition> transitions;

    public TransitionManager() {
        transitions = new ArrayList<>();
    }

    public void addTransition(Transition transition) {
        if (!transitions.contains(transition)) {
            transitions.add(transition);
        }
    }

    public List<Transition> getTransitions() {
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
