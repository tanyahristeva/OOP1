import java.util.HashSet;
import java.util.Set;

public class TransitionManager {
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
}
